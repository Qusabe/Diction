package com.example.speechsimulator3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class AccountManagement {
    @FXML
    private Button profileB; //кнопка возвращения в профиль

    @FXML
    private Button changePassB; //кнопка смены пароля

    @FXML
    private Button quitB; //кнопка выхода из акка

    @FXML
    private Button delAccB; //кнопка удаления акка

    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        profileB.setOnAction(this::handleToProfile);
        changePassB.setOnAction(this::handleChangePass);
        quitB.setOnAction(this::handleQuit);
        delAccB.setOnAction(this::handleDeleteAcc);
        // Оставляем обработчик на кнопку db без логики

        // Дополнительные инициализации, если необходимо
    }

    //переход в профиль
    public void handleToProfile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
            Parent root = loader.load();

            Profile profileController = loader.getController();
            profileController.setStage(stage);

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //переход на смену пароля
    private void handleChangePass(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("password_rebut_part1.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();


            Scene scene = new Scene(root);
            stage.setScene(scene);;
            MWcontroller.setPreviousWindow("account_management.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //переход на меню входа (вышли из аккаунта)
    private void handleQuit(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();


            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //переходим на удаление акка
    private void handleDeleteAcc(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("deleting_an_account.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();


            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}