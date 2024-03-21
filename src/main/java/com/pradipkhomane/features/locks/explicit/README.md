# Using Explicit Reentrant Locks in Java

This repository contains Java code demonstrating the usage of explicit locking with ReentrantLock.

## Code Overview

The Java code consists of a class named `UsingExplicitReentrantLocks`, which demonstrates two methods:

1. `lockMethod()`: Acquires and releases the lock using `lock()` and `unlock()` methods of ReentrantLock. Inside the
   locked section, it modifies a boolean state.

2. `lockMethodWithTiming()`: Demonstrates timed locking with `tryLock()` method. It attempts to acquire the lock within
   a specified time and, if successful, simulates a blocking computation before releasing the lock.

The code also includes a `main` method to execute these methods concurrently using a thread pool executor.

## Contents

- `UsingExplicitReentrantLocks.java`: Contains the Java code demonstrating the usage of ReentrantLock.
- `README.md`: This file explaining the code and its usage.

## How to Run

To run the code:

1. Ensure you have JDK installed on your system.
2. Compile `UsingExplicitReentrantLocks.java`.
3. Run the compiled `.class` file.

Example:

```bash
javac UsingExplicitReentrantLocks.java
java UsingExplicitReentrantLocks
