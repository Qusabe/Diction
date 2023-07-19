module com.example.speechsimulator3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires java.mail;
    requires java.desktop;



    opens com.example.speechsimulator3 to javafx.fxml;
    exports com.example.speechsimulator3;
}