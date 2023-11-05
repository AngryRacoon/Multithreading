package com.example.multithreading;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Producer implements Runnable {
    private Queue<Integer> counter;
    private ImageView stive_status;
    private final Image stive_sleep = new Image("C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\stive_sleep.png");
    private final Image stive = new Image("C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\stive.png");

    private List<String> imagePaths = Arrays.asList("C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\Diamond.png", "C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\Emerald.png",
            "C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\ender-perl.png", "C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\Dragon-breathe.png",
            "C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\Golden-apple.png","C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\Redstone-dust.png",
            "C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\axolotl.png", "C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\Golden_bar.png","C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\golden_carrot.png",
            "C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\lapis.png");
    private Pane queuePane;
    private int imageIndex = 0;

    public Producer(Queue<Integer> counter, ImageView stive_status, Pane queuePane) {
        this.counter = counter;
        this.stive_status = stive_status;
        this.queuePane = queuePane;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1000);// Задержка для имитации производства
                synchronized (counter) {
                    while (counter.size() >= 5){
                        Platform.runLater(() -> stive_status.setImage(stive_sleep));
                        counter.wait();
                    }
                    int item = (int) (Math.random() * imagePaths.size());
                    counter.offer(item);
                    Platform.runLater(() -> {
                        Image image = new Image(imagePaths.get(item));
                        ImageView imageView = new ImageView(image);
                        imageView.setFitHeight(70.0);
                        imageView.setFitWidth(70.0);
                        imageView.setX(queuePane.getChildren().size()*70);
                        queuePane.getChildren().add(imageView);
                        stive_status.setImage(stive);
                    });
                    System.out.println("приготовил" + item);
                    counter.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}