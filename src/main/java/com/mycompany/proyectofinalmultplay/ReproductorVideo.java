/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalmultplay;
import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReproductorVideo {

    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
  private JFXPanel videoPanel; // Guardar el JFXPanel en una variable

    // Constructor que recibe el JFXPanel
    public ReproductorVideo(JFXPanel videoPanel) {
        this.videoPanel = videoPanel; // Asignar videoPanel a la variable de la clase
    }

    // Método para reproducir video
    public void reproducirVideo(String rutaArchivo) {
        // Cargar el video en el hilo de JavaFX
        Platform.runLater(() -> {
            try {
                File archivoVideo = new File(rutaArchivo);
                if (!archivoVideo.exists()) {
                    JOptionPane.showMessageDialog(null, "El archivo no existe.");
                    return;
                }

                // Detener cualquier video anterior antes de reproducir uno nuevo
                detenerVideo();

                Media media = new Media(archivoVideo.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaView = new MediaView(mediaPlayer);

                // Configurar el tamaño del video para ajustarse al panel
        
                // Añadir la vista del video al JFXPanel
                javafx.scene.Scene escenaVideo = new javafx.scene.Scene(new javafx.scene.layout.StackPane(mediaView));
                videoPanel.setScene(escenaVideo);

                // Iniciar la reproducción del video
                mediaPlayer.play();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al reproducir el video: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    // Método para detener el video
    public void detenerVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
