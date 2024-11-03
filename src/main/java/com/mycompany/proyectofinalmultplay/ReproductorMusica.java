/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalmultplay;

import java.awt.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.application.Platform;
import javax.swing.JOptionPane;
/**
 *
 * @author josue
 */


public class ReproductorMusica {
    private MediaPlayer mediaPlayer;
   
public void reproducirMusica(String rutaArchivo) {
    try {
        File archivoMusica = new File(rutaArchivo);
        Media media = new Media(archivoMusica.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, "Archivo no encontrado o formato no soportado.");
    } catch (Exception e) {
        System.out.println("Error al reproducir la música: " + e.getMessage());
        e.printStackTrace();
    }
}

    // Método para detener la música si ya está sonando
    public void detenerMusica() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}