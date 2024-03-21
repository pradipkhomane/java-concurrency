package com.pradipkhomane.features.locks.explicit;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class UsingExplicitReentrantLocks {

    // Creating a ReentrantLock instance for explicit locking
    private final ReentrantLock reentrantLock = new ReentrantLock();
    // Boolean state to be modified in the locked section
    private boolean state;

    // Method to demonstrate locking and unlocking with ReentrantLock
    public void lockMethod() {
        // Acquiring the lock
        reentrantLock.lock();
        try {
            // Critical section: Modifying the state in a locked section
            System.out.println("Changing state now");
            state = !state;
            System.out.println("Changed :" + state);
        } finally {
            // Releasing the lock in a finally block to ensure it's always released
            reentrantLock.unlock();
        }
    }

    // Method to demonstrate timed locking and unlocking with ReentrantLock
    public void lockMethodWithTiming() throws InterruptedException {
        // Attempting to acquire the lock within a specified time, returns true if successful
        if (!reentrantLock.tryLock(1L, TimeUnit.SECONDS)) {
            // Handling the case where the lock couldn't be acquired within the specified time
            System.out.println("Failed to acquire lock - it's already held");
        } else {
            try {
                // Simulating a blocking computation that might cause tryLock to fail
                System.out.println("Simulating blocking computation - forcing tryLock to fail!");
                Thread.sleep(3000);
            } finally {
                // Releasing the lock in a finally block to ensure it's always released
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        // Creating a thread pool to execute tasks concurrently
        var executor = Executors.newCachedThreadPool();
        // Creating an instance of UsingExplicitReentrantLocks
        var usingExplicitReentrantLocks = new UsingExplicitReentrantLocks();

        // Executing lockMethod() concurrently 10 times
        for (int i = 0; i < 10; i++) {
            executor.execute(usingExplicitReentrantLocks::lockMethod);
        }

        // Executing lockMethodWithTiming() concurrently 40 times
        for (int i = 0; i < 40; i++) {
            executor.execute(() -> {
                try {
                    usingExplicitReentrantLocks.lockMethodWithTiming();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Shutting down the executor service after all tasks are executed
        executor.shutdown();
    }
}
