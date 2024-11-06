package com.uniquindio.tallervehiculos;

import com.uniquindio.tallervehiculos.View.ReservaViewController;
import com.uniquindio.tallervehiculos.View.UsuarioViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Creacion de los controladores
        UsuarioViewController usuarioViewController = new UsuarioViewController();
        ReservaViewController reservaViewController = new ReservaViewController();
        usuarioViewController.setReservaViewController(reservaViewController);


        //Carga de la vista general
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("General.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}