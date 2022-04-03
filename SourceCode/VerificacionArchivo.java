package SourceCode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VerificacionArchivo extends Sistema {
    public VerificacionArchivo(String origen, String archivoContraseñas, ArrayList<Usuarios> users) {
        super(origen, archivoContraseñas, users);
        if (users == null) {
            this.users = new ArrayList<Usuarios>();
        } else {
            this.users = users;
        }
        // TODO Auto-generated constructor stub
    }

    private ArrayList<Usuarios> users;
    boolean noVacio = false;
    boolean numColumnas = false;
    String ArchivoContraseñas;

    public boolean verificarExistenciaArchivo(String ruta, String nombre) {
        // AdministradorUsuarios a = new AdministradorUsuarios(Origen,
        // ArchivoContraseñas, users);
        String ArchivoContraseñas = "shs";
        ArchivoContraseñas = ruta + nombre;
        // System.out.println("ruta = " + ArchivoContraseñas);
        FileReader fr;
        boolean archivoExistente = false;
        try {
            fr = new FileReader(ArchivoContraseñas);
            // BufferedReader br = new BufferedReader(fr);
            archivoExistente = true;
            // br.close();
            // System.out.println("Existe un archivo!");
        } catch (FileNotFoundException e) {
            archivoExistente = false;
            JOptionPane.showMessageDialog(null,
                    "El archivo de estudiantes no fue encontrado", "ERROR",
                    JOptionPane.WARNING_MESSAGE);
        }
        return archivoExistente;
    }

    public boolean validarEstructuraArchivo(String ruta, String nombre, int Elementos, char separador) {
        String ArchivoContraseñas;
        ArchivoContraseñas = ruta + nombre;
        // System.out.println("ruta = " + ArchivoContraseñas);
        FileReader fr;
        boolean archivoExistente = false;
        try {
            fr = new FileReader(ArchivoContraseñas);
            String linea = "";
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                noVacio = true;
                String[] datosLinea = linea.split("" + separador); // lee una nueva celda cuando lee una coma

                if (datosLinea.length != Elementos) {
                    numColumnas = false;
                    JOptionPane.showMessageDialog(null, "El archivo no tiene el numero de columnas esperadas");
                    break;
                } else {
                    numColumnas = true;
                }

                String nombreUsuario = datosLinea[0].trim();
                String contraseña = datosLinea[1].trim();
                Usuarios u = new Usuarios(nombreUsuario, contraseña);
                this.users.add(u);

                archivoExistente = true;

            }

            if (!noVacio && (linea = br.readLine()) == null) {
                JOptionPane.showMessageDialog(null, "El archivo seleccionado esta vacio");
            }

            // System.out.println("Estructura correcta");
            br.close();
        } catch (FileNotFoundException e) {
            archivoExistente = false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "El archivo no tiene las columnas esperadas, por lo tanto no se podran capturar calificaciones",
                    "ERROR",
                    JOptionPane.WARNING_MESSAGE);
        }
        return archivoExistente;
    }

    public ArrayList<Usuarios> recuperarDatos(String ruta, String nombre, char separador) {
        String ArchivoContraseñas;
        ArchivoContraseñas = ruta + nombre;
        // System.out.println("ruta = " + ArchivoContraseñas);
        FileReader fr;
        try {
            fr = new FileReader(ArchivoContraseñas);
            String linea = "";
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                noVacio = true;
                String[] datosLinea = linea.split("" + separador); // lee una nueva celda cuando lee una coma

                if (datosLinea.length != 2) {
                    numColumnas = false;
                    JOptionPane.showMessageDialog(null, "El archivo no tiene el numero de columnas esperadas");
                    break;
                } else {
                    numColumnas = true;
                }

                String nombreUsuario = datosLinea[0].trim();
                String contraseña = datosLinea[1].trim();
                Usuarios u = new Usuarios(nombreUsuario, contraseña);
                this.users.add(u);

                // mostrarUsuarios();

            }

            if (!noVacio && (linea = br.readLine()) == null) {
                JOptionPane.showMessageDialog(null, "El archivo seleccionado esta vacio");
            }
            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo seleccionado no existe");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "El archivo no tiene las columnas esperadas, por lo tanto no se podran capturar calificaciones",
                    "ERROR",
                    JOptionPane.WARNING_MESSAGE);
        }
        return users;
    }

}
