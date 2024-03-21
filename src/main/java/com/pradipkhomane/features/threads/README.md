Description:

The UsingThreads class contains the main method, which is the entry point of the program.
Two threads are created and started:
thread: A thread without a specified task.
threadWithTask: A thread with a specified task, which is printing a message when the thread starts.
A Runnable task named runnable is created using a lambda expression. This task contains a while loop that runs until the
thread is interrupted, printing a message indicating that the thread is not interrupted.
Another thread named threadWithInterrupt is created with the runnable task, and it's started.
The main thread sleeps for 10 milliseconds using Thread.sleep(10).
The threadWithInterrupt thread is interrupted using threadWithInterrupt.interrupt() method call. This interrupts the
thread and exits the loop inside the runnable task.