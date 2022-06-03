module com.example.simpletimer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.simpletimer to javafx.fxml;
    exports com.example.simpletimer;
}