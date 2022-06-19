package com.example.simpletimer;
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
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

//Kolory
//C5E063 - tekst
//4AAD52 - guziki
//507255 - tło
//aktualna minuta filmu 16:51

public class HelloController implements Initializable {

    @FXML
    private ComboBox<Integer> hoursInput;

    @FXML
    private Text hoursTImer;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private ComboBox<Integer> minutesInput;

    @FXML
    private Text minutesTImer;

    @FXML
    private ComboBox<Integer> secondsInput;

    @FXML
    private Text secondsTImer;

    @FXML
    private AnchorPane timerPane;

    Map<Integer , String> numberMap;

    Integer currseconds;
    Thread thrd;

     Integer hmsToSeconds(Integer h, Integer m , Integer s){
        Integer htoSeconds = h * 3600;
        Integer mtoSeconds = m * 60;
        Integer total = htoSeconds + mtoSeconds + s;
        return total;
     }

     LinkedList<Integer> secondsToHms(Integer currseconds){
         Integer hours = currseconds / 3600;
         currseconds = currseconds % 3600;
         Integer minutes = currseconds / 60;
         currseconds = currseconds % 60;
         Integer seconds = currseconds;
         LinkedList<Integer> answer = new LinkedList<>();
         answer.add(hours);
         answer.add(minutes);
         answer.add(seconds);
        return answer;
     }

    @FXML
    void reset(ActionEvent event) {
        scrollDOWN();
        thrd.stop();
    }

    void startCountDown(){
        thrd = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        setOutput();
                        Thread.sleep(1000);
                        if(currseconds == 0 ){
                            //System.out.println("Finished");
                            scrollDOWN();
                            thrd.stop();
                        }
                        currseconds -=1;
                    }
                } catch (Exception e){
                    //TODO handle exception
                }
            }
        });
        thrd.start();
    }

    void setOutput(){
         LinkedList<Integer> currHms = secondsToHms(currseconds);
         hoursTImer.setText(numberMap.get(currHms.get(0)));
         minutesTImer.setText(numberMap.get(currHms.get(1)));
         secondsTImer.setText(numberMap.get(currHms.get(2)));
         System.out.println(currHms.get(0) + ":" + currHms.get(1) + ":" + currHms.get(2));
    }


    @FXML
    void start(ActionEvent event){
        currseconds = hmsToSeconds(hoursInput.getValue(), minutesInput.getValue(), secondsInput.getValue());
        hoursInput.setValue(0);
        minutesInput.setValue(0);
        secondsInput.setValue(0);
        scrollUP();
    }

    void scrollUP(){
        TranslateTransition tr1 = new TranslateTransition(Duration.millis(100) , menuPane);
        tr1.setToX(0);
        tr1.setToY(-200);
        tr1.play();

        TranslateTransition tr2 = new TranslateTransition(Duration.millis(100) , timerPane);
        tr2.setFromX(0);
        tr2.setFromY(200);
        tr2.setToX(0);
        tr2.setToY(0);
        tr1.setOnFinished(e -> {
            try{
                //System.out.println("Started countdown");
                startCountDown();
            } catch (Exception e2){
            //todo catch exceptions
            }
        });
        tr2.play();

    }

    void scrollDOWN(){

        TranslateTransition tr1 = new TranslateTransition(Duration.millis(100) , timerPane);
        TranslateTransition tr2 = new TranslateTransition(Duration.millis(100) , menuPane);
        tr1.setByX(0);
        tr1.setByY(200);
        tr1.play();
        tr1.setNode(timerPane);

        tr2.setFromX(0);
        tr2.setFromY(200);
        tr2.setToX(0);
        tr2.setToY(0);
        tr2.setNode(menuPane);
        tr2.play();

//        tr2.setOnFinished(e -> {
//            try{
//                thrd.stop();
//            } catch (Exception e2){
//                //TODO cath an exception
//            }
//        });`

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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

        numberMap = new TreeMap<Integer , String>();

        for (Integer i = 0; i <=60 ; i++) {
            if(i<=9){
                numberMap.put(i , "0" + String.valueOf(i));
            } else {
                numberMap.put(i , String.valueOf(i));
            }
        }

    }
}
