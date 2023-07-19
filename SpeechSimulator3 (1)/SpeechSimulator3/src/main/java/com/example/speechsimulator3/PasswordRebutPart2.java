package com.example.speechsimulator3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;


public class PasswordRebutPart2 extends PasswordRebutPart1 {
    @FXML
    private  TextField codeField;
    private String cod;
    private String Mail;
    @FXML
    private TextField newPasswordField;

    @FXML
    private Button backB;
    @FXML
    private Button rebutB;
    @FXML
    private Label incorrectCode;

    /**Проверяет, заполнены ли поля ввода кода и нового пароля. Если да,
     * то вызывает processChangePassword2
     *
     * @param event
     * @throws IOException
     */
    public void rebut2 (ActionEvent event) throws IOException {

        String myCode = codeField.getText(); //
        String myPassword = newPasswordField.getText();
        cod = PasswordRebutPart1.getCode();
        Mail = PasswordRebutPart1.getMail();
        
        if (!myCode.isEmpty() && !myPassword.isEmpty()) {
            try {
                processChangePassword2(cod, myCode, myPassword, Mail, event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**Если введённый код и сгенерированный совпадают, то
     * вызывает DatabaseHandler.changePassword и переходит на
     * меню авторизации
     *
     * @param code
     * @param myCode
     * @param myPassword
     * @param mail
     * @param event
     */
    private void processChangePassword2(String code, String myCode, String myPassword, String mail, ActionEvent event)  {
        DatabaseHandler dbHandler = new DatabaseHandler();

        System.out.println(code);
        System.out.println(myCode);
        System.out.println(myPassword);
        System.out.println(mail);
        if (Integer.parseInt(code) == Integer.parseInt(myCode)) {
            //Условие выполняется, если сгенерированный код и код, написанный пользователем, совпадает
            //В таком случае пользователь вводит новый пароль и функция changePassword меняет его
//            System.out.println("Введите новый пароль");

            dbHandler.changePassword(mail, myPassword);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
         } else {
            System.out.println("Неверный код");
            incorrectCode.setVisible(true);
        }
    }

    /** Возвращение к предыдущему меню генерации кода
     *
     * @param event
     * @throws IOException
     */
    public void retToPart1(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("password_rebut_part1.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





