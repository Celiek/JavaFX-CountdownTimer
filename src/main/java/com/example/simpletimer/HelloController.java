package com.example.simpletimer;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

//Kolory
//C5E063
//4AAD52
//507255

public class HelloController implements Initializable {


    @FXML
    private ComboBox<Integer> hoursInput;

    @FXML
    private Text hoursTimer;

    @FXML
    private GridPane menuPane;

    @FXML
    private ComboBox<Integer> minutesInput;

    @FXML
    private Text minutesTImer;

    @FXML
    private Text minutesTimer;

    @FXML
    private Button resetButton;

    @FXML
    private ComboBox<Integer> secondsInput;

    @FXML
    private Button startButton;

    @FXML
    private GridPane timerPane;

    @FXML
    void reset(ActionEvent event) {
        scrollDOWN();
    }


    @FXML
    void start(ActionEvent event){
        System.out.println("ZOSTAŁEM WCIŚNIĘTY");
        scrollUP();
    }

    void scrollUP(){
        TranslateTransition tr1 = new TranslateTransition();
        tr1.setDuration(Duration.millis(100));
        tr1.setToX(0);
        tr1.setToY(-200);
        tr1.setNode(menuPane);
        TranslateTransition tr2 = new TranslateTransition();
        tr2.setDuration(Duration.millis(100));
        tr2.setFromX(0);
        tr2.setFromY(200);
        tr2.setToX(0);
        tr2.setToY(0);
        tr1.setNode(timerPane);
        ParallelTransition pt = new ParallelTransition(tr1 ,tr2);
        pt.play();

    }

    void scrollDOWN(){
        TranslateTransition trl = new TranslateTransition();
        trl.setDuration(Duration.millis(100));
        trl.setToX(0);
        trl.setToY(200);
        trl.setNode(timerPane);
        TranslateTransition tr2 = new TranslateTransition();
        tr2.setDuration(Duration.millis(100));
        tr2.setFromX(0);
        tr2.setFromY(-200);
        tr2.setToX(0);
        tr2.setToY(0);
        trl.setNode(menuPane);
        ParallelTransition pt = new ParallelTransition(trl ,tr2);
        pt.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> hoursList = FXCollections.observableArrayList();
        ObservableList<Integer> minutesAndSecondsList = FXCollections.observableArrayList();

        for (int i = 0; i <= 60; i++) {
            if(0 <= i && i <= 24){
                hoursList.add(i);
            }
            minutesAndSecondsList.add(i);
        }
        hoursInput.setItems(hoursList);
        hoursInput.setValue(0);

        minutesInput.setItems(minutesAndSecondsList);
        minutesInput.setValue(0);

        secondsInput.setItems(minutesAndSecondsList);
        secondsInput.setValue(0);
    }
}
