package views;

import java.awt.Font;
import javax.swing.*;
import controllers.RegistrarInvitadoController;
import core.Model;
import core.View;

@SuppressWarnings("serial")
public class RegistrarInvitadoView extends JPanel implements View
{
    //-----------------------------------------------------------------------
    //    Attributes
    //-----------------------------------------------------------------------
    private RegistrarInvitadoController controller;
    private JTextField tf_nombre;
    private JTextField tf_celular;
    private JTextField tf_direccion;
    private JRadioButton rbtn_masculino;
    private JRadioButton rbtn_femenino;
    private JComboBox<Integer> cmb_dia;
    private JComboBox<String> cmb_mes;
    private JComboBox<Integer> cmb_anio;
    private JCheckBox cbx_terminos;


    //-----------------------------------------------------------------------
    //    Constructor
    //-----------------------------------------------------------------------
    public RegistrarInvitadoView(RegistrarInvitadoController controller)
    {
        this.controller = controller;
        setLayout(null);
        make_field_nombre();
        make_field_celular();
        make_field_genero();
        make_field_fechaNacimiento();
        make_field_direccion();
        make_field_terminos();
        make_btn_guardar();
        make_btn_limpiar();
    }


    //-----------------------------------------------------------------------
    //    Methods
    //-----------------------------------------------------------------------
    @Override
    public void update(Model model, Object data)
    {
        if (data != null)
            JOptionPane.showMessageDialog(this, (String) data);
    }

    private void make_field_nombre()
    {
        JLabel lbl = new JLabel("Ingrese Nombre:");
        lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl.setBounds(29, 30, 140, 14);
        add(lbl);

        tf_nombre = new JTextField();
        tf_nombre.setBounds(200, 27, 196, 20);
        tf_nombre.setColumns(10);
        add(tf_nombre);
    }

    private void make_field_celular()
    {
        JLabel lbl = new JLabel("Ingrese número celular:");
        lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl.setBounds(29, 65, 160, 14);
        add(lbl);

        tf_celular = new JTextField();
        tf_celular.setBounds(200, 62, 196, 20);
        tf_celular.setColumns(10);
        add(tf_celular);
    }

    private void make_field_genero()
    {
        JLabel lbl = new JLabel("Género:");
        lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl.setBounds(29, 100, 60, 14);
        add(lbl);

        ButtonGroup grupo = new ButtonGroup();

        rbtn_masculino = new JRadioButton("Masculino");
        rbtn_masculino.setSelected(true);
        rbtn_masculino.setBounds(200, 96, 90, 23);
        grupo.add(rbtn_masculino);
        add(rbtn_masculino);

        rbtn_femenino = new JRadioButton("Femenino");
        rbtn_femenino.setBounds(295, 96, 90, 23);
        grupo.add(rbtn_femenino);
        add(rbtn_femenino);
    }

    private void make_field_fechaNacimiento()
    {
        JLabel lbl = new JLabel("Fecha de Nacimiento:");
        lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl.setBounds(29, 135, 155, 14);
        add(lbl);

        // Día
        Integer[] dias = new Integer[31];
        for (int i = 0; i < 31; i++) dias[i] = i + 1;
        cmb_dia = new JComboBox<>(dias);
        cmb_dia.setBounds(200, 132, 55, 22);
        add(cmb_dia);

        // Mes
        String[] meses = {"Jan","Feb","Mar","Apr","May","Jun",
                "Jul","Aug","Sep","Oct","Nov","Dec"};
        cmb_mes = new JComboBox<>(meses);
        cmb_mes.setBounds(260, 132, 65, 22);
        add(cmb_mes);

        // Año
        Integer[] anios = new Integer[100];
        for (int i = 0; i < 100; i++) anios[i] = 2025 - i;
        cmb_anio = new JComboBox<>(anios);
        cmb_anio.setBounds(330, 132, 70, 22);
        add(cmb_anio);
    }

    private void make_field_direccion()
    {
        JLabel lbl = new JLabel("Dirección:");
        lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl.setBounds(29, 170, 80, 14);
        add(lbl);

        tf_direccion = new JTextField();
        tf_direccion.setBounds(200, 167, 196, 20);
        tf_direccion.setColumns(10);
        add(tf_direccion);
    }

    private void make_field_terminos()
    {
        cbx_terminos = new JCheckBox("Acepta Términos y Condiciones");
        cbx_terminos.setBounds(29, 205, 250, 23);
        add(cbx_terminos);
    }

    private void make_btn_guardar()
    {
        JButton btn = new JButton("Guardar");
        btn.setBounds(200, 240, 89, 23);
        add(btn);

        btn.addActionListener(e -> {
            if (!cbx_terminos.isSelected()) {
                JOptionPane.showMessageDialog(this,
                        "Debe aceptar los términos y condiciones.");
                return;
            }
            String nombre   = tf_nombre.getText();
            String celular  = tf_celular.getText();
            String genero   = rbtn_masculino.isSelected() ? "Masculino" : "Femenino";
            int dia         = (int) cmb_dia.getSelectedItem();
            String mes      = (String) cmb_mes.getSelectedItem();
            int anio        = (int) cmb_anio.getSelectedItem();
            String direccion = tf_direccion.getText();

            controller.guardarInvitado(nombre, celular, genero, dia, mes, anio, direccion);
            limpiarCampos();
        });
    }

    private void make_btn_limpiar()
    {
        JButton btn = new JButton("Limpiar");
        btn.setBounds(300, 240, 89, 23);
        add(btn);

        btn.addActionListener(e -> limpiarCampos());
    }

    private void limpiarCampos()
    {
        tf_nombre.setText("");
        tf_celular.setText("");
        tf_direccion.setText("");
        rbtn_masculino.setSelected(true);
        cmb_dia.setSelectedIndex(0);
        cmb_mes.setSelectedIndex(0);
        cmb_anio.setSelectedIndex(0);
        cbx_terminos.setSelected(false);
    }
}