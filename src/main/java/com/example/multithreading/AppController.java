package com.example.multithreading;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.Queue;

public class AppController {

    @FXML
    private ImageView stive;

    @FXML
    private ImageView villager;
    @FXML
    private HBox queueBox;

    @FXML
    private Pane queuePane;
    private Producer producer;
    private Consumer consumer;
    private Thread producerThread;
    private Thread consumerThread;
    Queue<Integer> counter = new LinkedList<>();
    public AppController(){

    }

    public void initialize() {
        stive.setImage(new Image("C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\stive_sleep.png"));
        villager.setImage(new Image("C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\villager_sleep.png"));
    }



    @FXML
    private void onStartButtonClick() {

        producer = new Producer(counter, stive, queuePane);
        consumer = new Consumer(counter, villager, queuePane);

        producerThread = new Thread(producer);
        consumerThread = new Thread(consumer);

        producerThread.setDaemon(true);
        consumerThread.setDaemon(true);

        producerThread.start();
        consumerThread.start();

    }

    @FXML
    public void onStopButtonClick() {
        producerThread.interrupt();
        consumerThread.interrupt();
        counter.clear();
        queuePane.getChildren().clear();
        stive.setImage(new Image("C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\stive_sleep.png"));
        villager.setImage(new Image("C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\villager_sleep.png"));
    }


}
