package controllers;

import core.Controller;
import views.RegistrarInvitadoView;
import javax.swing.JOptionPane;

public class RegistrarInvitadoController extends Controller
{
    //-----------------------------------------------------------------------
    //    Attributes
    //-----------------------------------------------------------------------
    private RegistrarInvitadoView registrarInvitadoView;


    //-----------------------------------------------------------------------
    //    Methods
    //-----------------------------------------------------------------------
    @Override
    public void run()
    {
        registrarInvitadoView = new RegistrarInvitadoView(this);
    }

    public void guardarInvitado(String nombre, String celular, String genero,
                                int dia, String mes, int anio, String direccion)
    {
        // Validaciones básicas
        if (nombre.isEmpty() || celular.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
            return;
        }

        JOptionPane.showMessageDialog(null,
                "Invitado registrado exitosamente:\n" +
                        "Nombre: "    + nombre   + "\n" +
                        "Celular: "   + celular  + "\n" +
                        "Género: "    + genero   + "\n" +
                        "Nacimiento: "+ dia + " " + mes + " " + anio + "\n" +
                        "Dirección: " + direccion);
    }


    //-----------------------------------------------------------------------
    //    Getters
    //-----------------------------------------------------------------------
    public RegistrarInvitadoView getView()
    {
        return registrarInvitadoView;
    }
}