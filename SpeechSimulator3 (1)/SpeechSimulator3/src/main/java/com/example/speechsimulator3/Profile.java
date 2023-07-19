package com.example.speechsimulator3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class Profile {
    @FXML
    private Button mainMenuB;

    @FXML
    private Button notesB;

    @FXML
    private Button settingsB;

    @FXML
    private Button b4;

    @FXML
    private Label label;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private ProgressIndicator pb;

    @FXML
    private ProgressIndicator pb1;

    @FXML
    private ProgressIndicator pb2;

    @FXML
    private ProgressIndicator pb3;

    @FXML
    private ProgressIndicator pb4;

    private Stage stage;

    public void setStage(Stage stage) throws IOException {
        this.stage = stage;
    }


    /** Переход в главное меню
     *
     * @param event
     * @throws IOException
     */
    public void handleToMain(ActionEvent event)  throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        updateLabels();
    }

    /** Переход к аудиозаписям
     *
     * @param event
     * @throws IOException
     */
    public void handleToNotes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("notes.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        updateLabels();
    }

    /** Переход в настройки аккаунта
     *
     * @param event
     * @throws IOException
     */
    public void handleToSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("account_management.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        updateLabels();
    }

    /** Переход в неведомое нечто
     *
     * @param event
     */
    public void handleButton4(ActionEvent event) {
        // Обработчик нажатия на кнопку b4
        System.out.println("Button 4 clicked");
    }


    /** Обновление индикаторов прогресса
     *
     */
    private void updateLabels() {
        double pbValue = pb.getProgress() * 100;
        label.setText(String.format("%.0f%%", pbValue));

        double pb1Value = pb1.getProgress() * 100;
        label1.setText(String.format("%.0f%%", pb1Value));

        double pb2Value = pb2.getProgress() * 100;
        label2.setText(String.format("%.0f%%", pb2Value));

        double pb3Value = pb3.getProgress() * 100;
        label3.setText(String.format("%.0f%%", pb3Value));

        double pb4Value = pb4.getProgress() * 100;
        label4.setText(String.format("%.0f%%", pb4Value));
    }

}



