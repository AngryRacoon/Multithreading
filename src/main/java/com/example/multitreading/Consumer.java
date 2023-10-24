package com.example.multitreading;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Queue;

public class Consumer implements Runnable {
    private Queue<Integer> counter;

    private Circle circle;
    private Pane queuePane;

    public Consumer(Queue<Integer> counter, Circle circle, Pane queuePane) {
        this.counter = counter;
        this.circle = circle;
        this.queuePane =queuePane;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1500);// Задержка для имитации производства
                synchronized (counter) {
                    while (counter.isEmpty()){
                        Platform.runLater(() -> circle.setFill(Color.RED));
                        counter.wait();

                    }
                    int item = counter.poll();
                    Platform.runLater(() -> {
                        if (!queuePane.getChildren().isEmpty()) {
                            queuePane.getChildren().remove(0);
                        }
                    });
                    System.out.println("покушал" + item);

                    Platform.runLater(() -> circle.setFill(Color.GREEN));
                    counter.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}