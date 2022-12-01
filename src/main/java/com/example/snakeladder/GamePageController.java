package com.example.snakeladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;


import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class GamePageController {
    @FXML
    Text Number;
    @FXML
    Text turnName;

    @FXML
    ImageView player1;

    @FXML
    ImageView player2;

    int turn=1;

    HashMap<Pair<Double,Double>,Pair<Double,Double>> snakeLadderCoordinateChanges;
    @FXML
    public void rollDice(MouseEvent event) throws IOException {
        getSnakeLadderCoordinateChanges();
        Random random = new Random();
        int randumNumber = random.nextInt(6) + 1;
        Number.setText("" + randumNumber);
        if(turn==1) {
            double moveX = player1.getTranslateX();
            System.out.println(moveX);
            double moveY = player1.getTranslateY();

            Pair<Double,Double> movementCoordinates= movement(moveX,moveY,randumNumber);
            player1.setTranslateX(movementCoordinates.getKey());
            player1.setTranslateY(movementCoordinates.getValue());
            if(snakeLadderCoordinateChanges.containsKey(movementCoordinates)){
                player1.setTranslateX(snakeLadderCoordinateChanges.get(movementCoordinates).getKey());
                player1.setTranslateY(snakeLadderCoordinateChanges.get(movementCoordinates).getValue());
            }
            checkWin(player1.getTranslateX(),player1.getTranslateY());
        }else{
            double moveX = player2.getTranslateX();
            System.out.println(moveX);
            double moveY = player2.getTranslateY();

            Pair<Double,Double> movementCoordinates= movement(moveX,moveY,randumNumber);
            player2.setTranslateX(movementCoordinates.getKey());
            player2.setTranslateY(movementCoordinates.getValue());
            if(snakeLadderCoordinateChanges.containsKey(movementCoordinates)){
                player2.setTranslateX(snakeLadderCoordinateChanges.get(movementCoordinates).getKey());
                player2.setTranslateY(snakeLadderCoordinateChanges.get(movementCoordinates).getValue());
            }
            checkWin(player2.getTranslateX(),player2.getTranslateY());
        }
        if (randumNumber != 6) {
            if (turn == 1) {
                turnName.setText("Player 2 Turn");
                turn = 2;
            } else {
                turnName.setText("Player 1 Turn");
                turn = 1;
            }
        }
    }
     Pair<Double,Double> movement(double moveX,double moveY,int randumNumber){
        double X=moveX;
        double Y=moveY;
        if(moveY%100==0){
            moveX+=randumNumber*50;
            //moveX=600;
            if(moveX>500){
                moveX=500*2-moveX+50;
                moveY -=50;
            }
        }
        else
        {

            moveX-=randumNumber*50;
            if(moveX<50){
                if(moveY == -450){
                    return new Pair<>(X,Y);
                }
                moveX=-1*(moveX-50);
                moveY -=50;
            }
        }
        return new Pair<>(moveX,moveY);
    }
    void getSnakeLadderCoordinateChanges(){
        snakeLadderCoordinateChanges=new HashMap<>();
        snakeLadderCoordinateChanges.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));
        snakeLadderCoordinateChanges.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));

        snakeLadderCoordinateChanges.put(new Pair<>(200.0,0.0),new Pair<>(350.0,-50.0));

        snakeLadderCoordinateChanges.put(new Pair<>(200.0,-50.0),new Pair<>(550.0,0.0));

        snakeLadderCoordinateChanges.put(new Pair<>(450.0,0.0),new Pair<>(500.0,-150.0));

        snakeLadderCoordinateChanges.put(new Pair<>(50.0,-100.0),new Pair<>(100.0,-200.0));

        snakeLadderCoordinateChanges.put(new Pair<>(400.0,-100.0),new Pair<>(200.0,-400.0));

        snakeLadderCoordinateChanges.put(new Pair<>(100.0,-300.0),new Pair<>(100.0,-50.0));
        snakeLadderCoordinateChanges.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));
        snakeLadderCoordinateChanges.put(new Pair<>(500.0,-250.0),new Pair<>(350.0,-300.0));
        snakeLadderCoordinateChanges.put(new Pair<>(500.0,-350.0),new Pair<>(500.0,-450.0));
        snakeLadderCoordinateChanges.put(new Pair<>(50.0,-350.0),new Pair<>(50.0,-450.0));

        snakeLadderCoordinateChanges.put(new Pair<>(200.0,-300.0),new Pair<>(50.0,-250.0));
        snakeLadderCoordinateChanges.put(new Pair<>(350.0,-250.0),new Pair<>(350.0,-150.0));
        snakeLadderCoordinateChanges.put(new Pair<>(150.0,-450.0),new Pair<>(100.0,-350.0));
        snakeLadderCoordinateChanges.put(new Pair<>(300.0,-450.0),new Pair<>(300.0,-350.0));
        snakeLadderCoordinateChanges.put(new Pair<>(400.0,-450.0),new Pair<>(400.0,-350.0));
        snakeLadderCoordinateChanges.put(new Pair<>(350.0,-400.0),new Pair<>(200.0,-100.0));

    }
    void checkWin(Double X,Double Y) throws IOException{
        if(X == 50.0 && Y == -450.0){
            Alert resultAlert=new Alert(Alert.AlertType.INFORMATION);
            resultAlert.setHeaderText("RESULT");
            if(turn==1){
                resultAlert.setHeaderText("Player 1 is Winner!!");
                resultAlert.showAndWait();
                StartGame start=new StartGame();
                HelloApplication.root.getChildren().setAll(start.root);
            }else{
                resultAlert.setHeaderText("Player 2 is Winner!!");
                resultAlert.showAndWait();
            }
            StartGame start=new StartGame();
            HelloApplication.root.getChildren().setAll(start.root);
        }
    }
}
