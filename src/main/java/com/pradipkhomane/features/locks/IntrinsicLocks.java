package com.pradipkhomane.features.locks;

import java.util.concurrent.Executors;

public class IntrinsicLocks {
    private boolean state;

    // Method that demonstrates synchronized keyword usage to synchronize access to a method
    public synchronized void synchronizedMethod() {
        // Toggle the state variable
        state = !state;

        // Print the current state
        System.out.println("Current state is " + state);
    }

    // Method that demonstrates synchronized block usage to synchronize access to a block of code
    public void synchronizedBlock() {
        // Print the owner of the lock
        System.out.println("Lock is owned by " + Thread.currentThread().getName());

        // Synchronize access to the block of code using 'this' object
        synchronized (this) {
            // Toggle the state variable
            state = !state;
            // Print the owner of the lock after state changes
            System.out.println("Who owns my lock after state changes: " + Thread.currentThread().getName());
            // Print the current state
            System.out.println("Current State is: " + state);
            // Print a separator
            System.out.println("====");
        }
    }

    // Method that demonstrates reentrant locking (a thread holding a lock can acquire the same lock again)
    public synchronized void reentrancy() {
        // Print a message before acquiring the lock
        System.out.println("Before acquiring lock");
        // Acquire the lock again (reentrant lock)
        synchronized (this) {
            // Print the owner of the lock
            System.out.println("I am owner " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a thread pool using Executors
        var executor = Executors.newCachedThreadPool();
        // Create an instance of IntrinsicLocks class
        var lockObject = new IntrinsicLocks();

        // Execute synchronizedMethod() by multiple threads concurrently
        for (int i = 0; i < 10; i++) {
            executor.execute(lockObject::synchronizedMethod);
        }

        // Pause the main thread for a short duration
        Thread.sleep(100);

        // Execute synchronizedBlock() by multiple threads concurrently
        for (int i = 0; i < 10; i++) {
            executor.execute(lockObject::synchronizedBlock);
        }

        // Pause the main thread for a short duration
        Thread.sleep(100);

        // Execute reentrancy() by multiple threads concurrently
        for (int i = 0; i < 10; i++) {
            executor.execute(lockObject::reentrancy);
        }
    }
}
