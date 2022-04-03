package SourceCode;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

public class VerificadorAcceso {

    private ArrayList<Usuarios> users;
    boolean noVacio = false;
    boolean numColumnas = false;
    String origen;
    String ArchivoContraseñas;
    final String claveEncriptacion = "secreto!";

    public VerificadorAcceso(String origen, String archivoContraseñas, ArrayList<Usuarios> users) {
        super();
        if (users == null) {
            this.users = new ArrayList<Usuarios>();
        } else {
            this.users = users;
        }
    }

    public void mostrarUsuarios() {
        // Se imprime el listado de alumnos
        for (Usuarios u : users) {
            System.out.println(u.toString());
        }
    }

    public boolean iniciarLogin(ArrayList<Usuarios> users) throws InvalidKeyException, UnsupportedEncodingException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        boolean usuarioCorrecto = false;
        String usuarioIngresar = JOptionPane.showInputDialog("Ingresa tu nombre de usuario");
        String contraseñaIngresar = JOptionPane.showInputDialog("Ingresa tu contraseña");

        usuarioCorrecto = verificarPerfil(usuarioIngresar, contraseñaIngresar);

        // return accessGranted;
        return usuarioCorrecto;

    }

    public boolean verificarPerfil(String usuario, String contraseña)
            throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        Usuarios u = new Usuarios();
        boolean perfilVerificado = false;
        int numIntentos = 0;
        boolean bloqueo = false;
        // u.actualizarBloqueo();

        do {
            boolean perfilExistente = verificarNombrePerfil(usuario);
            if (perfilExistente) {
                if (numIntentos != 0) {
                    contraseña = JOptionPane
                            .showInputDialog(
                                    "Contraseña incorrecto, por favor vuelve a intentar. \nIngresa tu contraseña");
                }
                boolean contraseñaValidada = verificarContraseñaPerfil(usuario, contraseña);
                if (contraseñaValidada) {
                    perfilVerificado = true;
                    break;
                } else {
                    // System.out.println("Intento fallido");
                    numIntentos = actualizarNumIntentos(numIntentos);
                    if (actualizarNumIntentos(numIntentos) > 3) {
                        bloquearPerfil();
                        bloqueo = true;
                    }
                }
            } else {
                break;
            }
        } while (bloqueo == false);

        return perfilVerificado;
    }

    public boolean verificarNombrePerfil(String usuario) {
        boolean perfilExistente = false;
        for (Usuarios u : users) {
            String obtenerUsuario = u.getNombreUsuario();
            boolean userEqual = obtenerUsuario.equals(usuario);
            if (userEqual) {
                perfilExistente = true;
            }
        }
        return perfilExistente;
    }

    public boolean verificarContraseñaPerfil(String usuario, String contraseña)
            throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        EncriptadorAES encriptador = new EncriptadorAES();
        boolean contraseñaValidada = false;

        for (Usuarios u : users) {
            String obtenerUsuario = u.getNombreUsuario();
            boolean userEqual = obtenerUsuario.equals(usuario);
            if (userEqual) {
                String obtenerContraseña = encriptador.desencriptar(u.getContraseña(), this.claveEncriptacion);
                boolean passEqual = obtenerContraseña.equals(contraseña);
                if (passEqual) {
                    contraseñaValidada = true;
                }
            }
        }
        return contraseñaValidada;
    }

    public int actualizarNumIntentos(int numIntentos) {
        numIntentos++;
        return numIntentos;
    }

    public void bloquearPerfil() {
        Usuarios u = new Usuarios();
        u.setBloqueo(true);
        JOptionPane.showMessageDialog(null,
                "Se ha superado el limite de intentos. \n Intenta de nuevo en 10 minutos", "USUARIO BLOQUEADO",
                JOptionPane.WARNING_MESSAGE);
    }
}
