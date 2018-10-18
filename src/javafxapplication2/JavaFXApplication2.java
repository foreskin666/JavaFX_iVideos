/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;

/**
 *
 * @author Jorge HP
 */
public class JavaFXApplication2 extends Application {

    MediaPlayer player;
    MediaView mediaView;
    Pane root;

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        File folder = new File("src/pelis");
        File[] listOfFiles = folder.listFiles();
        int posX = 10;
        int posY = 300;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                //System.out.println("File " + listOfFiles[i].getName());
                Button btn = new Button();
                btn.setText(listOfFiles[i].getName());
                btn.setLayoutX(posX);
                btn.setLayoutY(posY);
                posY += 30;
                if (i%15 == 14) {posX += 300; posY = 300;}
                btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    reproduce(btn.getText());
                }

            });
            root.getChildren().add(btn);
            } 
        }

        
        Scene scene = new Scene(root, 1024, 768);

        primaryStage.setTitle("Ahora vídeos!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void reproduce(String n) {
        if (player != null) {
            player.stop(); //player.seek(Duration.ZERO);
            player.dispose();
        }
        player = new MediaPlayer(new Media(getClass().getResource("/pelis/" + n).toString()));
        mediaView = new MediaView(player);
        mediaView.setLayoutX(10);
        mediaView.setLayoutY(10);
        root.getChildren().add(mediaView);

        player.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
