package com.example.speechsimulator3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Register_part2 {
    @FXML
    private TextField codeField;

    /** Если введенный код совпадает со сгенерированным, то аккаунт добавляется  в БД и перекидывает на меню входа
     *
     * @param event
     * @throws IOException
     */
    public void Registr(ActionEvent event) throws IOException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String code = Register.getCod();
        String login = Register.getLogn();
        String mail = Register.getMal();
        String password = Register.getPasswrd();
        System.out.println(code);
        String myCode = codeField.getText().toString();
        System.out.println(myCode);
        System.out.println(login);
        System.out.println(mail);
        System.out.println(password);
        if (Integer.parseInt(code) == Integer.parseInt(myCode)) {
            //Условие выполняется, если сгенерированный код и код, написанный пользователем, совпадает
            //В таком случае пользователь считается зарегестрированным (после обращения к фукнции singUpUser)
            dbHandler.signUpUser(login, mail, password);
            try {
                dbHandler.creatingUserProgress(dbHandler.getIdUsers(mail));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Incorrect code");
    }

    public void toBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("password_rebut_part1.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
