package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class GamePagecontroller {
    @FXML
    Text number;
    @FXML
    Text turnname;

    @FXML
    ImageView Player1;

    @FXML
    ImageView Player2;

    int turn = 1;

    HashMap<Pair<Double,Double>,Pair<Double,Double>> snakeLadderCoordinateChanges;


    @FXML
    public void rolldice(MouseEvent event) throws IOException
    {
        SnakeLadderCordinate();
        Random random = new Random();
        int randomnumber = random.nextInt(6) + 1;
        // randomnumber =1;
        number.setText("" + randomnumber);
        if(turn ==1) {
            double movex = Player1.getTranslateX();
            double movey = Player1.getTranslateY();

            Pair<Double,Double> MovmentCordination = movement(movex,movey,randomnumber);
            Player1.setTranslateX(MovmentCordination.getKey());
            Player1.setTranslateY(MovmentCordination.getValue());
            if(snakeLadderCoordinateChanges.containsKey(MovmentCordination)){
                Player1.setTranslateX(snakeLadderCoordinateChanges.get(MovmentCordination).getKey());
                Player1.setTranslateY(snakeLadderCoordinateChanges.get(MovmentCordination).getValue());
            }
            checkWin(Player1.getTranslateX(),Player1.getTranslateY());

        }
        else{
            double movex = Player2.getTranslateX();
            double movey = Player2.getTranslateY();

            Pair<Double,Double> MovmentCordination = movement(movex,movey,randomnumber);
            Player2.setTranslateX(MovmentCordination.getKey());
            Player2.setTranslateY(MovmentCordination.getValue());
            if(snakeLadderCoordinateChanges.containsKey(MovmentCordination)){
                Player2.setTranslateX(snakeLadderCoordinateChanges.get(MovmentCordination).getKey());
                Player2.setTranslateY(snakeLadderCoordinateChanges.get(MovmentCordination).getValue());
            }

            checkWin(Player2.getTranslateX(),Player2.getTranslateY());

        }
        if (randomnumber!=6) {
            if (turn == 1) {
                turnname.setText("Player 2 Turn");
                turn = 2;
            } else {
                turnname.setText("Player 1 Turn");
                turn = 1;
            }
        }


    }
     Pair<Double,Double> movement(double movex, double movey, int randomnumber){
        double x = movex;
        double y  = movey;
         if(movey%100==0) {
             movex += randomnumber * 50;
             if(movex>500){
                 movex= 500*2 - movex +50;   // for moving upward direction
                 movey-=50;
             }
         }
         else {
             movex -= randomnumber * 50;
             if(movex<50){
                 if (movey== -450){
                     return new Pair<>(x,y);
                 }
                 movex =-1*(movex-50);
                 movey-=50;
             }
         }
         return new Pair<>(movex,movey);
     }
     void SnakeLadderCordinate(){
         snakeLadderCoordinateChanges = new HashMap<>();
         snakeLadderCoordinateChanges.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));
         snakeLadderCoordinateChanges.put(new Pair <> (50.0, 0.0),new Pair <> (150.0, -150.0));
         snakeLadderCoordinateChanges.put(new Pair <> (200.0, 0.0),new Pair <> (350.0, -50.0));
         snakeLadderCoordinateChanges.put(new Pair <> (200.0, -50.0),new Pair <> (350.0, 0.0));
         snakeLadderCoordinateChanges.put(new Pair <> (450.0, 0.0),new Pair <> (500.0, -150.0));
         snakeLadderCoordinateChanges.put(new Pair <> (50.0, -100.0),new Pair <> (100.0, -200.0));
         snakeLadderCoordinateChanges.put(new Pair <> (400.0, -100.0),new Pair <> (200.0, -400.0));
         snakeLadderCoordinateChanges.put(new Pair <> (100.0, -300.0),new Pair <> (100.0, -50.0));
         snakeLadderCoordinateChanges.put(new Pair <> (50.0, 0.0),new Pair <> (150.0, -150.0));
         snakeLadderCoordinateChanges.put(new Pair <> (500.0, -250.0),new Pair <> (350.0, -300.0));
         snakeLadderCoordinateChanges.put(new Pair <> (500.0, -350.0),new Pair <> (500.0, -450.0));
         snakeLadderCoordinateChanges.put(new Pair <> (50.0, -350.0),new Pair <> (50.0, -450.0));
         snakeLadderCoordinateChanges.put(new Pair <> (200.0, -300.0),new Pair <> (50.0, -250.0));
         snakeLadderCoordinateChanges.put(new Pair <> (350.0, -250.0),new Pair <> (350.0, -150.0));
         snakeLadderCoordinateChanges.put(new Pair <> (150.0, -450.0),new Pair <> (100.0, -350.0));
         snakeLadderCoordinateChanges.put(new Pair <> (300.0, -450.0),new Pair <> (300.0, -350.0));
         snakeLadderCoordinateChanges.put(new Pair <> (400.0, -450.0),new Pair <> (400.0, -350.0));
         snakeLadderCoordinateChanges.put(new Pair <> (350.0, -400.0),new Pair <> (200.0, -100.0));


     }
     void checkWin(Double X , Double Y) throws IOException
     {
        if (X == 50.0 && Y == -450.00){
            Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
            resultAlert.setHeaderText("Result");
            if(turn==1){

                resultAlert.setContentText("Player 1 is Winner");

                resultAlert.showAndWait();

            }
            else{
                resultAlert.setContentText("Player 2 is Winner");

                resultAlert.showAndWait();
            }
            StartGame start = new StartGame();
            HelloApplication.root.getChildren().setAll(start.root);
        }

     }
}
