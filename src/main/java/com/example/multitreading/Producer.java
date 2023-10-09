package com.example.multitreading;

import java.util.Queue;

class Producer implements Runnable {
    private Queue<Integer> counter;

    public Producer(Queue<Integer> counter) {
        this.counter = counter;

    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1000);// Задержка для имитации производства
                synchronized (counter) {
                    while (counter.size() >= 5){
                        counter.wait();
                    }
                    int item = (int) (Math.random() * 100);
                    counter.offer(item);
                    System.out.println("приготовил" + item);
                    counter.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}