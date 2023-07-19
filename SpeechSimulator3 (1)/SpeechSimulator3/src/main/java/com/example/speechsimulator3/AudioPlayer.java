package com.example.speechsimulator3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class AudioPlayer {

    @FXML
    private Label audioLabel;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ProgressBar audioProgressBar;
    @FXML
    private Button playB;


    private Media media;
    private MediaPlayer mediaPlayer;


    private File directory;
    private File[] files;
    private boolean isPlaying = false;

    private ArrayList<File> audio;

    private int audioNumber;

    private Timer timer;
    private TimerTask task;
    private boolean running;

    // инициализация всего (разобрать подробнее)
    public void initialize(URL url, ResourceBundle resourceBundle) {

        audio = new ArrayList<File>(); // создание списка аудио

        directory = new File("Audio Records//" + MWcontroller.getCurrentMail()); // получаем директорию, откуда будем считывать файлы

        files = directory.listFiles(); //получаем список файлов в директории

        if(files != null) { // если список файлов не пустой
            for(File file : files) {
                audio.add(file); //то добавляем файлы в список аудио
            }
        }

        media = new Media(audio.get(audioNumber).toURI().toString());  //спросить у Михаила
        mediaPlayer = new MediaPlayer(media);

        audioLabel.setText(audio.get(audioNumber).getName()); //выводим на экран название играющей аудиозаписи

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {  // настройка громкости (спросить подробнее)
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);

            }
        });

        audioProgressBar.setStyle("-fx-accent: #00FF00;");  //настройка стиля прогресс бара

    }

    public void togglePlay(ActionEvent event) {
        if (isPlaying) {
            pauseAudio();
        }
        else {
            playAudio();
        }
    }
    // плей
    private void playAudio() {
        isPlaying = false;
        playB.setText("Pause");
        beginTimer();
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        mediaPlayer.play();

    }
    // пауза
    private void pauseAudio() {
        isPlaying = true;
        playB.setText("Play");
        cancelTimer();
        mediaPlayer.pause();

    }
    //переключает на предыдущее аудио
    public void previousAudio() {

        if(audioNumber > 0) {

            audioNumber--;

            mediaPlayer.stop();

            if(running) {

                cancelTimer();
            }

            media = new Media(audio.get(audioNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            audioLabel.setText(audio.get(audioNumber).getName());

            playAudio();
        }
        else {

            audioNumber = audio.size() - 1;

            mediaPlayer.stop();

            if(running) {

                cancelTimer();
            }

            media = new Media(audio.get(audioNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            audioLabel.setText(audio.get(audioNumber).getName());

            playAudio();
        }

    }
    //переключает на следующее аудио
    public void nextAudio() {

        if(audioNumber < audio.size() - 1) {

            audioNumber++;

            mediaPlayer.stop();

            if(running) {

                cancelTimer();
            }

            media = new Media(audio.get(audioNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            audioLabel.setText(audio.get(audioNumber).getName());

            playAudio();
        }
        else {

            audioNumber = 0;

            mediaPlayer.stop();

            media = new Media(audio.get(audioNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            audioLabel.setText(audio.get(audioNumber).getName());

            playAudio();
        }
    }
    //начинает работу таймера для работы прогрессбара
    private void beginTimer() {

        timer = new Timer();

        task = new TimerTask() {

            public void run() {

                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                audioProgressBar.setProgress(current/end);

                if(current/end == 1) {

                    cancelTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    //останавливает работу таймера для работы прогрессбара
    private void cancelTimer() {

        running = false;
        timer.cancel();
    }
}
