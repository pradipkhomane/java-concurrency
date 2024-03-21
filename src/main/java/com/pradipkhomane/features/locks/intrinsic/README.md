# Intrinsic Locks in Java

This repository contains a Java program demonstrating the use of intrinsic locks for synchronization in multi-threaded
programming.

## Overview

The Java code provided showcases the use of intrinsic locks, also known as synchronized blocks and methods, to manage
concurrent access to shared resources in a multi-threaded environment. Intrinsic locks play a crucial role in preventing
race conditions and ensuring thread safety by allowing only one thread to execute a synchronized block of code or method
at a time.

## Code Explanation

The `IntrinsicLocks` class defines three methods:

1. `synchronizedMethod()`: This method is synchronized using the `synchronized` keyword, ensuring that only one thread
   can execute it at a time. It toggles the state variable and prints the current state.

2. `synchronizedBlock()`: This method demonstrates the use of synchronized blocks to synchronize access to a specific
   block of code within the method. It prints the owner of the lock, toggles the state variable, and prints the current
   state.

3. `reentrancy()`: This method illustrates reentrant locking, where a thread that already holds a lock can acquire the
   same lock again. It prints a message before acquiring the lock and then acquires the lock again to demonstrate
   reentrancy.

The `main` method creates a thread pool using `Executors.newCachedThreadPool()` and executes multiple threads
concurrently to demonstrate the behavior of synchronized methods, synchronized blocks, and reentrant locking.

## Usage

To run the program, simply compile and execute the `IntrinsicLocks.java` file. Ensure you have Java installed on your
system.

```bash
javac IntrinsicLocks.java
java IntrinsicLocks
