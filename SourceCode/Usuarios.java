package SourceCode;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class Usuarios {
    private String nombreUsuario;
    private String contraseña;
    private ArrayList<Usuarios> users;
    private Boolean bloqueo = false;

    public Usuarios(String nombreUsuario, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public Usuarios(String nombreUsuario, String contraseña, ArrayList<Usuarios> users) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.users = users;
    }

    public Usuarios() {

    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void actualizarBloqueo() {
        if (getBloqueo()) {
            JOptionPane.showMessageDialog(null, "El usuario se encuentra bloqueado");
        } else {
            JOptionPane.showMessageDialog(null, "jo");
        }

    }

    public Boolean getBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(Boolean bloqueo) {
        this.bloqueo = true;
    }

    @Override
    public String toString() {
        return "Usuarios [contraseña=" + contraseña + ", nombreUsuario=" + nombreUsuario + "]";
    }

}
