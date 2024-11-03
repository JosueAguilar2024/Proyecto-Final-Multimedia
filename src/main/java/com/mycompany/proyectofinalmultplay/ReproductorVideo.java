/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalmultplay;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.JFrame;
import java.io.File;

public class ReproductorVideo {
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private JFrame videoFrame;

    public ReproductorVideo(JFXPanel jfxPanel) {
        // Inicializa el JFrame pero no lo muestra todavía
        videoFrame = new JFrame("Reproductor de Video");
        videoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Crear el MediaView
        mediaView = new MediaView();
        
        // Crear el Pane y añadir el MediaView
        Pane root = new Pane(mediaView);
        Scene scene = new Scene(root, 600, 600);
        
        // Configura la escena en el JFXPanel usando Platform.runLater
        javafx.application.Platform.runLater(() -> {
            jfxPanel.setScene(scene);
            videoFrame.add(jfxPanel);
        });
    }

    public void reproducirVideo(String rutaArchivo) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        Media media = new Media(new File(rutaArchivo).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        // Agregar un Listener para ajustar el tamaño de la ventana cuando el video esté listo
        mediaPlayer.setOnReady(() -> {
            double videoWidth = mediaPlayer.getMedia().getWidth();
            double videoHeight = mediaPlayer.getMedia().getHeight();
            videoFrame.setSize((int) videoWidth, (int) videoHeight); // Ajusta el tamaño de la ventana
            videoFrame.setLocationRelativeTo(null); // Centrar la ventana
        });

        // Mostrar la ventana y comenzar a reproducir
        videoFrame.setVisible(true); // Muestra la ventana aquí, justo antes de empezar a reproducir
        mediaPlayer.play();
    }
}

