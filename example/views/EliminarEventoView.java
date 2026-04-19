package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import controllers.EliminarEventoController;
import core.Model;
import core.View;

@SuppressWarnings("serial")
public class EliminarEventoView extends JPanel implements View
{
    //-----------------------------------------------------------------------
    //    Attributes
    //-----------------------------------------------------------------------
    private EliminarEventoController controller;
    private JTable table;

    //-----------------------------------------------------------------------
    //    Constructor
    //-----------------------------------------------------------------------
    public EliminarEventoView(EliminarEventoController controller, JTable table)
    {
        this.controller = controller;
        this.table = table;
        make_frame();
    }

    //-----------------------------------------------------------------------
    //    Methods
    //-----------------------------------------------------------------------
    @Override
    public void update(Model model, Object data)
    {
        if (data != null) {
            JOptionPane.showMessageDialog(this, (String) data);
        }
    }

    private void make_frame()
    {
        setLayout(new BorderLayout());

        // Tabla con scroll
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel botones inferiores
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Botón "Seleccionar Todos" a la derecha
        JButton btn_selectAll = new JButton("Seleccionar Todos");
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(btn_selectAll);
        bottomPanel.add(rightPanel, BorderLayout.NORTH);

        // Botones Cancel y Remover al centro
        JButton btn_cancel = new JButton("Cancel");
        JButton btn_remove = new JButton("Remover");
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(btn_cancel);
        centerPanel.add(btn_remove);
        bottomPanel.add(centerPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // Listeners
        btn_selectAll.addActionListener(e -> controller.selectAll());
        btn_remove.addActionListener(e -> controller.removeSelected());
        btn_cancel.addActionListener(e -> controller.deselectAll());
    }
}