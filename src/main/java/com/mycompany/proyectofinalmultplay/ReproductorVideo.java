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

    // Constructor modificado para aceptar un JFXPanel como parámetro
    public ReproductorVideo(JFXPanel jfxPanel) {
        videoFrame = new JFrame("Reproductor de Video");
        videoFrame.setSize(600, 400);
        videoFrame.setLocation(250,150); 
        videoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        videoFrame.add(jfxPanel); // Agrega el panel JavaFX al marco

        // Crear el MediaView y configurarlo en una escena de JavaFX
        mediaView = new MediaView();
        Pane root = new Pane(mediaView);
        Scene scene = new Scene(root, 600, 600);

        // Configura la escena en el JFXPanel usando Platform.runLater para el hilo de JavaFX
        javafx.application.Platform.runLater(() -> jfxPanel.setScene(scene));
    }

    public void reproducirVideo(String rutaArchivo) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        Media media = new Media(new File(rutaArchivo).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        // Mostrar la ventana y comenzar a reproducir
        videoFrame.setVisible(true);
        mediaPlayer.play();
    }
}
