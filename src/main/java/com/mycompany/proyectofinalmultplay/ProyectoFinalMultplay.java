/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinalmultplay;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 *
 * @author josue
 */
public class ProyectoFinalMultplay extends Application {

    @Override
    public void start(Stage primaryStage) {
        Interfaz inter = new Interfaz(); // Suponiendo que `Interfaz` es una clase que extiende Stage o Scene.
        inter.show(); // Muestra la ventana (debería llamarse sobre una instancia de Stage o Scene).
    }

    public static void main(String[] args) {
        launch(args); // Inicia la aplicación JavaFX.
    }
}