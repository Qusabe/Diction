package com.example.speechsimulator3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainMenu extends Activity implements Initializable  {

    @FXML
    private ScrollPane scrollPane;
    private static final double SCROLL_AMOUNT = 0.005;
    @FXML
    private VBox contentBox;
    @FXML
    private Button artikB;
    private String flagForDB;

    private static String activityName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // <-----------------------------------Инициализация скроллера------------------------------------->

        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() < 0) {
                scrollPane.setVvalue(scrollPane.getVvalue() + SCROLL_AMOUNT);
            } else {
                scrollPane.setVvalue(scrollPane.getVvalue() - SCROLL_AMOUNT);
            }
            event.consume();
        });
    }

    /** Переход в профиль аккаунта
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void toProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    public void toActivity(ActionEvent event) {
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("activity.fxml"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }Button sourceButton = (Button) event.getSource(); // Получаем источник события (кнопку)
////                activityName = sourceButton.getText(); // Сохраняем название кнопки
////                System.out.println("Название кнопки: " + activityName);
    public static String getActivityName() {
        return activityName;
    }

    // <---------------------Топорное решение перхода в активити--------------------------->



//    public void handle(ActionEvent event) throws IOException {
//        Button sourceButton = (Button) event.getSource();
//        String buttonName = sourceButton.getId();
//        System.out.println("Text button" + buttonName);
//        String name = buttonName.substring(0, buttonName.length() - 1);
//        String labelName = name + "Label";
//        System.out.println("name " + labelName);
//        Scene scene = new Scene(scrollPane);
//        Label foundLabel = (Label) scene.lookup("#" + labelName);
//        if (foundLabel != null) {
//            System.out.println("Текст из лейбла: " + foundLabel.getText());
//            activityName = foundLabel.getText();
//            gotoActivity(event);
//        }
//    }

    public void activityArtik(ActionEvent event) {
        activityName = "Артикуляционная зарядка";
        flagForDB = "1";
        gotoActivity(event);
    }

    public void activityVzriv(ActionEvent event) {
        activityName = "Произношение взрывных звуков";
        flagForDB = "2-";
        gotoActivity(event);
    }

    public void activityBreath(ActionEvent event) {
        activityName = "Правильное дыхание";
        flagForDB = "3-";
        gotoActivity(event);
    }
    public void activityTT(ActionEvent event) {
        activityName = "Произношение скороговорок";
        flagForDB = "4-";
        gotoActivity(event);
    }
    public void activityHardWords(ActionEvent event) {
        activityName = "Повторение сложных слов";
        flagForDB = "5-";
        gotoActivity(event);
    }
    public void activitySpeed(ActionEvent event) {
        activityName = "Произношение фраз в разном темпе";
        flagForDB = "6-";
        gotoActivity(event);
    }
    public void activityFear(ActionEvent event) {
        activityName = "Страх перед выступлением";
        flagForDB = "7-";
        gotoActivity(event);
    }

    public void gotoActivity(ActionEvent event) {

        // Другие действия, связанные с нажатием кнопки и сохранением ее имени
        Parent root = null;  // придумать, как подгружать содержимое отдельного файла в меню активити
        try {
            root = FXMLLoader.load(Activity.class.getResource("activity.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            progressUserExercises(MWcontroller.getCurrentMail(), flagForDB);//помечаем активити как пройденное
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /*
    *
    * Функции БД ____________________________________________________________________________
    *
     */

    private static void progressUserExercises(String mail, String progress) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String progressExercises = dbHandler.getProgressExercises(mail);
        if (progressExercises.indexOf(progress)==-1){
            System.out.println("Такой подстроки нет");
            dbHandler.progressUserExercises(mail, progress);
        }
        else
            System.out.println("Такая подстрока есть");
    }

    private static void progressUserSimulators(String mail, String progress) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String progressSimulators = dbHandler.getProgressExercises(mail);
        if (progressSimulators.indexOf(progress)==-1){
            System.out.println("Такой подстроки нет");
            dbHandler.progressUserExercises(mail, progress);
        }
        else
            System.out.println("Такая подстрока есть");
    }

}

