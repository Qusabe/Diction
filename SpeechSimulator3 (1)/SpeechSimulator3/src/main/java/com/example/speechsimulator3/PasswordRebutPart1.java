package com.example.speechsimulator3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;


public class PasswordRebutPart1 {
    private Scene scene;
    private Parent root;
    @FXML
    public Label codSav;
    @FXML
    private static TextField codeField;
    private static Stage stage;
    public static String expCode;
    public static String expMail;
    @FXML
    private TextField mailField;
    @FXML
    private Button pushCodeB;
    @FXML
    private Label incorrectMail;


    /** Если почта для восстановления пароля введена, то вызывает processChangePassword
     *
     * @param event
     * @throws IOException
     */
    public void rebut(ActionEvent event) throws  IOException{

        String mail = mailField.getText();
        expMail = mail;

        if (mail!="") {
            try {
                processChangePassword(mail, event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /** Возвращает сгенерированный код
     *
     * @return expCode
     */
    public static String getCode() {
        return expCode;
    }

    /** Возвращает введённую почту
     *
     * @return expMail
     */
    public static String getMail() {
        return expMail;
    }


    /**Если введеная почта есть в БД, то генерируется случайный код и отправляется на почту, после чего
     * перходим к окну password_rebut_part2
     *
     * @param mail
     * @param event
     * @throws MessagingException
     * @throws IOException
     */
    private void processChangePassword(String mail, ActionEvent event)
            throws MessagingException, IOException {
        Mail dbMail = new Mail();
        if (dbMail.mailCheck(mail) == true) { //обращаемся к функции mailCheck из Mail
            //условие выполняеется если пользователь с указанной почтой зарегестрирован

            //Генерируем код и скидываем его на указанную почту
            String code = dbMail.generateRandomCode();
            expCode = code;
            dbMail.sendingLetter(mail, code);
            System.out.println("Проверьте почту");

            try {
                Parent root = FXMLLoader.load(getClass().getResource("password_rebut_part2.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Пользователь с такой почтой не зарегестрирован");
            incorrectMail.setVisible(true);
        }
    }

    public void toBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(MWcontroller.getPreviousWindow()));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
