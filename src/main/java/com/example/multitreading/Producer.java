package com.example.multitreading;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class Producer implements Runnable {
    private Queue<Integer> counter;
    private Circle circle;

    private List<String> imagePaths = Arrays.asList("C:\\Users\\admin\\IdeaProjects\\Multitreading1.0\\src\\main\\resources\\image\\Diamond.png", "C:\\Users\\admin\\IdeaProjects\\Multitreading1.0\\src\\main\\resources\\image\\Emerald.png",
            "C:\\Users\\admin\\IdeaProjects\\Multitreading1.0\\src\\main\\resources\\image\\ender-perl.png", "C:\\Users\\admin\\IdeaProjects\\Multitreading1.0\\src\\main\\resources\\image\\Dragon-breathe.png",
            "C:\\Users\\admin\\IdeaProjects\\Multitreading1.0\\src\\main\\resources\\image\\Golden-apple.png","C:\\Users\\admin\\IdeaProjects\\Multitreading1.0\\src\\main\\resources\\image\\Redstone-dust.png");
    private Pane queuePane;
    private int imageIndex = 0;

    public Producer(Queue<Integer> counter, Circle circle, Pane queuePane) {
        this.counter = counter;
        this.circle = circle;
        this.queuePane = queuePane;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1000);// Задержка для имитации производства
                synchronized (counter) {
                    while (counter.size() >= 5){
                        Platform.runLater(() -> circle.setFill(Color.RED));
                        counter.wait();
                    }
                    int item = (int) (Math.random() * imagePaths.size());
                    counter.offer(item);
                    Platform.runLater(() -> {
                        Image image = new Image(imagePaths.get(item));
                        ImageView imageView = new ImageView(image);
                        queuePane.getChildren().add(imageView);
                    });
                    System.out.println("приготовил" + item);
                    Platform.runLater(() -> circle.setFill(Color.GREEN));
                    counter.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}