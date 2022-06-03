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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private AnchorPane MenuPane;

    @FXML
    private Text SecoundsTimer;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<Integer> hoursInput;

    @FXML
    private Text hoursTimer;

    @FXML
    private ComboBox<Integer> minutesInput;

    @FXML
    private Text minutesTimer;

    @FXML
    private ComboBox<Integer> secoundInput;

    @FXML
    private AnchorPane timerPane;

    @FXML
    void restartTimer(ActionEvent event) {
        scrollDOWN();
    }

    @FXML
    void start(ActionEvent event){
        System.out.println("ZOSTAŁEM WCIŚNIĘTY");
        scrollUP();
    }

    void scrollUP(){
        TranslateTransition trl = new TranslateTransition();
        trl.setDuration(Duration.millis(100));
        trl.setToX(0);
        trl.setToY(-200);
        trl.setNode(MenuPane);
        TranslateTransition tr2 = new TranslateTransition();
        tr2.setDuration(Duration.millis(100));
        tr2.setFromX(0);
        tr2.setFromY(200);
        tr2.setToX(0);
        tr2.setToY(0);
        trl.setNode(timerPane);
        ParallelTransition pt = new ParallelTransition(trl ,tr2);
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
        trl.setNode(MenuPane);
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

        secoundInput.setItems(minutesAndSecondsList);
        secoundInput.setValue(0);
    }
}
