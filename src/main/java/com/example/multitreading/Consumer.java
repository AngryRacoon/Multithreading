package com.example.multitreading;

import java.util.Queue;

class Consumer implements Runnable {
    private Queue<Integer> counter;

    public Consumer(Queue<Integer> counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1500);// Задержка для имитации производства
                synchronized (counter) {
                    while (counter.isEmpty()){
                        counter.wait();
                    }
                    int item = counter.poll();
                    System.out.println("покущал" + item);
                    counter.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}