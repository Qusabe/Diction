package com.example.speechsimulator3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainWindow extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("main_window.fxml"));
            Parent root = fxmlLoader.load();
//            ModuleLayer.Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(root);

//            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//                @Override
//                public void handle(KeyEvent keyEvent) {
//                    case (keyEvent.getCode()) {
//                        case KeyCode.ENTER:
//                            controller.goToMain();
//                    }
//                }
//            });
            stage.setScene(scene);
            stage.show();
            MWcontroller.setPreviousWindow("main_window.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void MainWindow(String[] args) { launch(args); }

}