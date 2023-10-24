package com.example.multithreading;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Queue;

public class Consumer implements Runnable {
    private Queue<Integer> counter;
    private final Image villager_sleep =
            new Image("C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\villager_sleep.png");
    private final Image villager =
            new Image("C:\\Users\\admin\\IdeaProjects\\Multithreading1.0\\src\\main\\resources\\image\\villager.png");
    private ImageView villager_status;
    private Pane queuePane;

    public Consumer(Queue<Integer> counter, ImageView villager_status, Pane queuePane) {
        this.counter = counter;
        this.villager_status = villager_status;
        this.queuePane =queuePane;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1500);// Задержка для имитации производства
                synchronized (counter) {
                    while (counter.isEmpty()){
                        Platform.runLater(() -> villager_status.setImage(villager_sleep));
                        counter.wait();

                    }
                    int item = counter.poll();
                    Platform.runLater(() -> {
                        if (!queuePane.getChildren().isEmpty()) {
                            queuePane.getChildren().remove(0);
                            for(Node imageView: queuePane.getChildren()){
                                imageView.setLayoutX(imageView.getLayoutX() - 70);
                            }

                        }
                        villager_status.setImage(villager);
                    });
                    System.out.println("покушал" + item);

                    counter.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}