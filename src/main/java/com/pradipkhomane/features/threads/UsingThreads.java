package com.pradipkhomane.features.threads;

public class UsingThreads {

    public static void main(String[] args) throws InterruptedException {

        // Creating and starting a new thread without specifying a task
        var thread = new Thread();
        thread.start();

        // Creating and starting a new thread with a task using lambda expression
        var threadWithTask = new Thread(() -> System.out.println("Thread started: " + Thread.currentThread().getName()));
        threadWithTask.start();

        // Creating a Runnable task using lambda expression
        Runnable runnable = () -> {
            // Running a loop until the thread is interrupted
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Current thread is not interrupted: " + Thread.currentThread().getName());
            }
        };

        // Creating and starting a new thread with the Runnable task
        var threadWithInterrupt = new Thread(runnable);
        threadWithInterrupt.start();

        // Sleeping the main thread for 10 milliseconds
        Thread.sleep(10);

        // Interrupting the threadWithInterrupt thread
        threadWithInterrupt.interrupt();
        System.out.println("This is main thread: " + Thread.currentThread().getName());
    }
}
