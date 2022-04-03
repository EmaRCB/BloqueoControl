package SourceCode;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

public class Sistema {
    private String Origen;
    private String ArchivoContraseñas;
    private ArrayList<Usuarios> users;
    boolean noVacio = false;
    boolean numColumnas = false;

    /**
     * @param args
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        String Origen = "C:\\Users\\BlueW\\OneDrive - Universidad Autonoma de Yucatan\\Desktop\\Uni\\DiseñoSoftware\\Tarea2\\Archivos\\";
        String ArchivoContraseñas = "usuarios.csv";
        ArrayList<Usuarios> users = new ArrayList<Usuarios>();
        Sistema s = new Sistema(Origen, ArchivoContraseñas, users);

        boolean ArchivoVerificado = s.administrarArchivos();
        if (ArchivoVerificado) {
            s.guardarInformacion();
            s.mostrarInterfaz();
        }

    }

    public Sistema(String Origen, String archivoContraseñas, ArrayList<Usuarios> users) {
        this.Origen = Origen;
        this.ArchivoContraseñas = archivoContraseñas;
        this.users = users;

    }

    /**
     * @return boolean
     */
    public boolean administrarArchivos() {
        VerificacionArchivo v = new VerificacionArchivo(Origen, ArchivoContraseñas, users);

        boolean existeArchivo = v.verificarExistenciaArchivo(Origen, ArchivoContraseñas);

        boolean estructuraCorrecta = false;
        if (existeArchivo) {
            estructuraCorrecta = v.validarEstructuraArchivo(Origen, ArchivoContraseñas, 2, ',');
        }

        boolean esArchivoCorrecto = false;
        if (existeArchivo && estructuraCorrecta) {
            esArchivoCorrecto = true;
        }
        return esArchivoCorrecto;

    }

    public void guardarInformacion() {
        VerificacionArchivo v = new VerificacionArchivo(Origen, ArchivoContraseñas, users);
        // System.out.println(v.recuperarDatos(Origen, ArchivoContraseñas, ','));

    }

    public void mostrarInterfaz() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        // System.out.println("HOLA");
        VerificadorAcceso a = new VerificadorAcceso(Origen, ArchivoContraseñas, users);
        JOptionPane.showMessageDialog(null, "El archivo fue encontrado correctamente");

        boolean usuarioCorrecto = a.iniciarLogin(users);

        if (usuarioCorrecto) {
            JOptionPane.showMessageDialog(null, "Usuario y contraseña correctos, puede continuar!");
            mostrarMenu();
        } else {
            alertaUsuarioNoExiste();
        }
    }

    public void alertaArchivoUsuarioNoExiste() {
        JOptionPane.showMessageDialog(null,
                "El archivo de estudiantes no es correcto, por lo tanto no se podran capturar calificaciones", "ERROR",
                JOptionPane.WARNING_MESSAGE);
    }

    public void alertaUsuarioNoExiste() {
        JOptionPane.showMessageDialog(null,
                "El usuario o la contraseña son incorrectos", "ERROR",
                JOptionPane.WARNING_MESSAGE);
    }

    public void mostrarMenu() {
        JOptionPane.showMessageDialog(null, "menu");
    }
}