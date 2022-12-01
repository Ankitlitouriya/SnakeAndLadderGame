package com.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Group root;
    @Override
    public void start(Stage stage) throws IOException {
        root = new Group();
        stage.setTitle("Snake And Ladder");
        StartGame start = new StartGame();
        root.getChildren().add(start.root);
        Scene ok = new Scene(root,800,500);
        // stage.setScene(new Scene(root,800,500));
        ok.setFill(Color.web("RED"));
        stage.setScene(ok);



       // stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}