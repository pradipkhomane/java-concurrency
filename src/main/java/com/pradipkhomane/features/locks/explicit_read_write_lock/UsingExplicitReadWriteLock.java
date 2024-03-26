package com.pradipkhomane.features.locks.explicit_read_write_lock;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class UsingExplicitReadWriteLock {
    // Initialize a ReentrantReadWriteLock
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // Initialize a content variable
    private String content = "Default content...";

    // Method to show content
    public String showContent() {
        // Acquire a read lock
        ReadLock readLock = readWriteLock.readLock();
        readLock.lock(); // Lock the read lock
        try {
            System.out.println("Reading a state while holding read lock"); // Output message
            return content; // Return the content
        } finally {
            readLock.unlock(); // Unlock the read lock
        }
    }

    // Method to write content
    public void writeContent(String newContentToAppend) {
        // Acquire a write lock
        WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock(); // Lock the write lock
        try {
            System.out.println("Writing a content : " + newContentToAppend); // Output message with new content
            content = new StringBuilder().append(content).append(newContentToAppend).toString(); // Append new content to existing content
        } finally {
            writeLock.unlock(); // Unlock the write lock
        }
    }

    // Main method
    public static void main(String[] args) {
        var executor = Executors.newCachedThreadPool(); // Create a thread pool
        var usingExplicitReadWriteLock = new UsingExplicitReadWriteLock(); // Instantiate UsingExplicitReadWriteLock object
        // Submit 100 tasks to the executor
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(new Random().nextInt(10) * 100); // Sleep for a random duration
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Handle interrupted exception
                }
                System.out.println(usingExplicitReadWriteLock.showContent()); // Display content
            });
        }

        // Execute 5 write tasks
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> usingExplicitReadWriteLock.writeContent(UUID.randomUUID().toString()));
        }

        executor.shutdown(); // Shut down the executor
    }
}
