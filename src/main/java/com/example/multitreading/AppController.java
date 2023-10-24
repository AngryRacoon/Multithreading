package com.example.multitreading;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;
import java.util.Queue;

public class AppController {

    @FXML
    private Circle producerCircle;

    @FXML
    private Circle consumerCircle;
    @FXML
    private HBox queueBox;

    @FXML
    private Pane queuePane;
    private Producer producer;
    private Producer producer2;
    private Consumer consumer;
    private Thread producerThread;
    private Thread consumerThread;
    Queue<Integer> counter = new LinkedList<>();

    public void initialize() {
        producerCircle.setFill(Color.RED);
        consumerCircle.setFill(Color.RED);
    }

    @FXML
    private void onStartButtonClick() {

        producer = new Producer(counter, producerCircle, queuePane);
        producer2 = new Producer(counter, producerCircle, queuePane);
        consumer = new Consumer(counter, consumerCircle, queuePane);

        producerThread = new Thread(producer);
        Thread producerThread2 = new Thread(producer);
        consumerThread = new Thread(consumer);



        producerThread.start();
        //producerThread2.start();
        consumerThread.start();

    }

    @FXML
    private void onStopButtonClick() {
        producerThread.interrupt();
        consumerThread.interrupt();
        counter.clear();
        queuePane.getChildren().clear();
    }

}
