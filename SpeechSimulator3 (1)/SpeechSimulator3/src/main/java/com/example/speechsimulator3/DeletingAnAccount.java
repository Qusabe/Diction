package com.example.speechsimulator3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;

public class DeletingAnAccount extends MWcontroller  {

    @FXML
    private Button dab;

    @FXML
    private Label dabc;

    @FXML
    private ProgressIndicator pb_dab;

    private int clickCounter = 0;


    /** Удаление аккаунта после 5 кликов (на данный момент 6, надо доработать логику)
     *
     */
    @FXML
    private void initialize() {
        dab.setOnAction(event -> {

            clickCounter++;
            if (clickCounter <= 5) {
                dabc.setText(String.valueOf(clickCounter));
                pb_dab.setProgress(clickCounter * 0.2);
            }
            if (clickCounter > 5) {
                if (exportMail != "") {
                    DatabaseHandler dbHandler = new DatabaseHandler();
                    Mail dbMail = new Mail();
                    if (dbMail.mailCheck(exportMail) == true) {
                        //условие выполняеется если пользователь с указанной почтой  зарегестрирован

                        dbHandler.deleteUser(exportMail);


                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /** Возвращение обратно к настройке аккаунта
     *
     * @param event
     * @throws IOException
     */
    public void toSettings(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("account_management.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


// Более красивый и по сути логичный код, но который почему-то не удаляет аккаунт

//public class DeletingAnAccount extends MWcontroller {
//
//    @FXML
//    private Button dab;
//
//    @FXML
//    private Label dabc;
//
//    @FXML
//    private ProgressIndicator pb_dab;
//
//    private int clickCounter = 0;
//
//
//    // удаление аккаунта после 5 кликов (на данный момент 6, надо доработать логику)
//    @FXML
//    private void initialize() {
//        dab.setOnAction(event -> {
//            clickCounter++;
//            if (clickCounter <= 5) {
//                dabc.setText(String.valueOf(clickCounter));
//                pb_dab.setProgress(clickCounter * 0.2);
//            }
//        });
//    }
//
//    public void delete(ActionEvent event) throws IOException {
//        if (dabc.getText() == "5") {
//
//            if (exportMail != "") {
//                DatabaseHandler dbHandler = new DatabaseHandler();
//                Mail dbMail = new Mail();
//                if (dbMail.mailCheck(exportMail) == true) {
//                    //условие выполняеется если пользователь с указанной почтой  зарегестрирован
//
//                    dbHandler.deleteUser(exportMail);
//
//
//                    try {
//                        Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
//                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                        Scene scene = new Scene(root);
//                        stage.setScene(scene);
//                        stage.show();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//
//
//}