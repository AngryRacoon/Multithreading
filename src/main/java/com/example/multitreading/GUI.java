package com.example.multitreading;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;

public class GUI extends Application {
    private Circle leftCircle;
    private Circle rightCircle;
    private Text queueText;
    private Text producerText;
    private Text consumerText;
    private int queueSize = 0;

    @Override
    public void start(Stage primaryStage) {
        HBox root = new HBox(20); // Горизонтальный контейнер для размещения элементов
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);

        leftCircle = createCircle(Color.RED);
        rightCircle = createCircle(Color.GREEN);
        queueText = new Text("Queue Size: " + queueSize);
        queueText.setStyle("-fx-font-size: 24px;");
        producerText = new Text("Производитель");
        consumerText = new Text("Потребитель");

        root.getChildren().addAll(leftCircle, producerText, queueText, consumerText, rightCircle);

        // Создаем анимацию для изменения цвета кругов
        Timeline colorChangeAnimation = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> toggleCircleColors())
        );
        colorChangeAnimation.setCycleCount(Timeline.INDEFINITE);
        colorChangeAnimation.play();

        primaryStage.setTitle("Queue Visualization");
        primaryStage.show();
    }

    private Circle createCircle(Color color) {
        Circle circle = new Circle(50, color);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        return circle;
    }

    private void toggleCircleColors() {
        if (leftCircle.getFill() == Color.RED) {
            leftCircle.setFill(Color.GREEN);
            rightCircle.setFill(Color.RED);
        } else {
            leftCircle.setFill(Color.RED);
            rightCircle.setFill(Color.GREEN);
        }

        // Моделируем изменение размера очереди и обновляем текст
        queueSize++;
        queueText.setText("Queue Size: " + queueSize);

        // Добавляем квадраты для элементов в очереди
        Rectangle square = createSquare();
        ((HBox)leftCircle.getParent()).getChildren().add(1, square);
    }

    private Rectangle createSquare() {
        Rectangle square = new Rectangle(30, 30, Color.BLUE);
        return square;
    }


}
