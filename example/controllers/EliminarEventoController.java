package controllers;

import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import core.Controller;
import models.Frequency;
import models.SchedulerIO;
import views.EliminarEventoView;

public class EliminarEventoController extends Controller
{
    //-----------------------------------------------------------------------
    //    Attributes
    //-----------------------------------------------------------------------
    private EliminarEventoView eliminarEventoView;
    private JTable table;

    //-----------------------------------------------------------------------
    //    Methods
    //-----------------------------------------------------------------------
    @Override
    public void run()
    {
        table = new JTable(getDataColumns(), getNameColumns());

        // Columna Boolean como checkbox
        table.getColumnModel().getColumn(5)
                .setCellRenderer(table.getDefaultRenderer(Boolean.class));
        table.getColumnModel().getColumn(5)
                .setCellEditor(table.getDefaultEditor(Boolean.class));

        eliminarEventoView = new EliminarEventoView(this, table);
    }

    /**
     * Selects all rows (sets Boolean column to true).
     */
    public void selectAll()
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(true, i, 5);
        }
    }

    /**
     * Deselects all rows (sets Boolean column to false).
     */
    public void deselectAll()
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(false, i, 5);
        }
    }

    /**
     * Removes selected rows from table and from events.txt.
     */
    public void removeSelected()
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Recopilar índices a eliminar (de atrás hacia adelante)
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            Boolean selected = (Boolean) model.getValueAt(i, 5);
            if (selected != null && selected) {
                model.removeRow(i);
            }
        }

        // Reescribir events.txt con los eventos restantes
        saveRemainingEvents(model);
    }

    /**
     * Rewrites events.txt with the remaining rows in the table.
     */
    private void saveRemainingEvents(DefaultTableModel model)
    {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(new File(".", "events.txt"), false));

            for (int i = 0; i < model.getRowCount(); i++) {
                String date      = model.getValueAt(i, 0).toString();
                String desc      = model.getValueAt(i, 1).toString();
                String freq      = model.getValueAt(i, 2).toString();
                String email     = model.getValueAt(i, 3).toString();
                String alarm     = model.getValueAt(i, 4).toString();

                writer.write(date + ";" + desc + ";" + freq + ";" + email
                        + ";" + (alarm.equals("ON") ? "1" : "0"));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    //    Getters
    //-----------------------------------------------------------------------
    public EliminarEventoView getView() { return eliminarEventoView; }

    public Vector<String> getNameColumns()
    {
        Vector<String> cols = new Vector<>();
        cols.add("Date");
        cols.add("Description");
        cols.add("Frequency");
        cols.add("E-mail");
        cols.add("Alarm");
        cols.add("Boolean");
        return cols;
    }

    public Vector<Vector<Object>> getDataColumns()
    {
        Vector<Vector<Object>> data = null;
        try {
            SchedulerIO schedulerIO = new SchedulerIO();
            data = schedulerIO.getEvents();

            // Agregar columna Boolean (false por defecto)
            for (Vector<Object> row : data) {
                row.add(false);
            }
        } catch (Exception ex) {
            data = new Vector<>();
        }
        return data;
    }

    public void addNewRow(Object[] values)
    {
        ((DefaultTableModel) table.getModel()).addRow(values);
    }
}