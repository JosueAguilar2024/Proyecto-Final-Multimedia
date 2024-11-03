/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalmultplay;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.io.File;
import java.io.IOException;
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
    private File carpetaSeleccionada; // Almacena la carpeta seleccionada
   
    
    public GestionarArchivos(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
      
    }

    // obtenerla para pder usarla
    public File getCarpetaSeleccionada() {
        return carpetaSeleccionada;
    }

    // Método para seleccionar la carpeta que el usuario desea explorar
    public void seleccionarCarpeta() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // carpetas

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            carpetaSeleccionada = fileChooser.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ninguna carpeta.");
        }
    }

    
    public void filtrarArchivosPorTipo(String tipo) {
        if (carpetaSeleccionada == null) {
            return;
        }
          modeloTabla.setRowCount(0);
       
        filtrarArchivosEnCarpeta(carpetaSeleccionada, tipo);
    }

    // Método recursivo para listar y filtrar archivos en la carpeta y sus subcarpetas
    public void filtrarArchivosEnCarpeta(File carpeta, String tipo) {
        File[] archivos = carpeta.listFiles(); // Lista de archivos y carpetas en el directorio actual
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    // Si es un directorio, llamada recursiva para explorar su contenido
                    filtrarArchivosEnCarpeta(archivo, tipo);
                } else if (archivo.isFile()) {
                    // Si es un archivo, verifica su tipo (extensión) para decidir si se agrega a la tabla
                    String extension = getFileExtension(archivo).toLowerCase();
                    boolean agregar = false;

                    switch (tipo.toLowerCase()) {//con minuscula
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
                            agregar = true; // Agrega todos los archivos independientemente de la extensión
                            break;
                    }

                    if (agregar) {
                        // Datos para agregar el archivo a la tabla
                        String[] datos = new String[7];
                        datos[0] = archivo.getName(); // NOMBRE
                        datos[1] = archivo.getName(); // ARCHIVO
                        datos[2] = obtenerAutor(archivo);// AUTOR 
                        datos[3] = obtenerAlbum(archivo);  // ALBUM
                        datos[4] = obtenerGenero(archivo);
                      
                       long tamañoEnBytes = archivo.length();
                    String tamañoFormato;
         
                    if (tamañoEnBytes >= 1024 * 1024 * 1024) { 
                        double tamañoEnGB = tamañoEnBytes / (1024.0 * 1024.0 * 1024.0);
                        tamañoFormato = String.format("%.2f GB", tamañoEnGB); // Formato en GB
                    } else {
                        double tamañoEnMB = tamañoEnBytes / (1024.0 * 1024.0);
                        tamañoFormato = String.format("%.2f MB", tamañoEnMB); // Formato en MB
                    }
                        datos[5] = tamañoFormato; 
                        datos[6] = archivo.getAbsolutePath(); // RUTA

                        modeloTabla.addRow(datos); // Agrega el archivo a la tabla
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
            return nombreArchivo.substring(lastIndex + 1); // Retorna la extensión del archivo
        }
        return ""; // Retorna una cadena vacía si no tiene extensión
    }
    public String obtenerAutor(File archivo) {
          try {
        if (archivo.getName().endsWith(".mp3")) {
            Mp3File mp3File = new Mp3File(archivo);
            if (mp3File.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                return id3v2Tag.getArtist() != null ? id3v2Tag.getArtist() : "Autor desconocido";
            } else if (mp3File.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3File.getId3v1Tag();
                return id3v1Tag.getArtist() != null ? id3v1Tag.getArtist() : "Autor desconocido";
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "Autor desconocido";
}
    
    public String obtenerAlbum(File archivo) {
       try {
        if (archivo.getName().endsWith(".mp3")) {
            Mp3File mp3File = new Mp3File(archivo);
            if (mp3File.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                return id3v2Tag.getAlbum() != null ? id3v2Tag.getAlbum() : "Álbum desconocido";
            } else if (mp3File.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3File.getId3v1Tag();
                return id3v1Tag.getAlbum() != null ? id3v1Tag.getAlbum() : "Álbum desconocido";
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "Álbum desconocido";
    }
    public String obtenerGenero(File archivo) {
    try {
        if (archivo.getName().endsWith(".mp3")) {
            Mp3File mp3File = new Mp3File(archivo);
            if (mp3File.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                return id3v2Tag.getGenreDescription() != null ? id3v2Tag.getGenreDescription() : "Género desconocido";
            } else if (mp3File.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3File.getId3v1Tag();
                return id3v1Tag.getGenre() != -1 ? id3v1Tag.getGenreDescription() : "Género desconocido";
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "Género desconocido";
}

}