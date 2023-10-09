package com.example.multitreading;

import java.util.LinkedList;
import java.util.Queue;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Queue<Integer> counter = new LinkedList<>();

        Thread producerThread = new Thread(new Producer(counter));
        Thread consumerThread = new Thread(new Consumer(counter));

        producerThread.start();
        consumerThread.start();
    }
}