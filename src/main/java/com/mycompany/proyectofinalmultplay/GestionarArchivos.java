/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalmultplay;
import java.io.File;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
/**
 *
 * @author josue
 */
public class GestionarArchivos {

    private DefaultTableModel modeloTabla;

    public GestionarArchivos(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    // Método para listar archivos en un directorio seleccionado por el usuario
    public void listarArchivos() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File carpeta = fileChooser.getSelectedFile();
            listarArchivosEnCarpeta(carpeta);
        }
    }

    // Método para listar archivos en la carpeta seleccionada
    public void listarArchivosEnCarpeta(File carpeta) {
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de listar

        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    String[] datos = new String[7];
                    datos[0] = archivo.getName(); // NOMBRE
                    datos[1] = archivo.getName(); // ARCHIVO (puedes cambiarlo si es necesario)
                    datos[2] = ""; // AUTOR (debes implementarlo)
                    datos[3] = ""; // ALBUM (debes implementarlo)
                    datos[4] = ""; // GENERO (debes implementarlo)
                    datos[5] = String.valueOf(archivo.length()); // TAMAÑO
                    datos[6] = archivo.getAbsolutePath(); // RUTA
                    modeloTabla.addRow(datos);
                }
             listarArchivosEnCarpeta(archivo);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron archivos en la carpeta seleccionada.");
        }
    }
    // Método para filtrar archivos por tipo (musica, videos, fotos)
    public void filtrarArchivos(String tipo) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File carpeta = fileChooser.getSelectedFile();
            filtrarArchivosEnCarpeta(carpeta, tipo);
        }
    }

    // Método para filtrar archivos en la carpeta seleccionada
    public void filtrarArchivosEnCarpeta(File carpeta, String tipo) {
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de listar

        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    String extension = getFileExtension(archivo).toLowerCase();
                    boolean agregar = false;

                    switch (tipo.toLowerCase()) {
                        case "musica":
                            if (extension.equals("mp3") || extension.equals("wav") || extension.equals("flac")) {
                                agregar = true;
                            }
                            break;
                        case "videos":
                            if (extension.equals("mp4") || extension.equals("avi") || extension.equals("mkv")) {
                                agregar = true;
                            }
                            break;
                        case "fotos":
                            if (extension.equals("jpg") || extension.equals("png") || extension.equals("gif")) {
                                agregar = true;
                            }
                            break;
                        case "todos":
                            agregar = true; // Agregar todos los archivos
                            break;
                    }

                    if (agregar) {
                        String[] datos = new String[7];
                        datos[0] = archivo.getName(); // NOMBRE
                        datos[1] = archivo.getName(); // ARCHIVO (puedes cambiarlo si es necesario)
                        datos[2] = ""; // AUTOR (debes implementarlo)
                        datos[3] = ""; // ALBUM (debes implementarlo)
                        datos[4] = ""; // GENERO (debes implementarlo)
                        datos[5] = String.valueOf(archivo.length()); // TAMAÑO
                        datos[6] = archivo.getAbsolutePath(); // RUTA
                        modeloTabla.addRow(datos);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron archivos en la carpeta seleccionada.");
        }
    }

    // Método para obtener la extensión de un archivo
    public String getFileExtension(File file) {
        String nombreArchivo = file.getName();
        int lastIndex = nombreArchivo.lastIndexOf('.');
        if (lastIndex > 0 && lastIndex < nombreArchivo.length() - 1) {
            return nombreArchivo.substring(lastIndex + 1);
        }
        return "";
    }
}