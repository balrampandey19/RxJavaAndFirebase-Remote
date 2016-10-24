# Retrofit 2.0 with RxJava Smaple

### What is RxJava?

RxJava is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.


It extends the observer pattern to support sequences of data/events and adds operators that allow you to compose sequences together declaratively while abstracting away concerns about things like low-level threading, synchronization, thread-safety and concurrent data structures.

- Zero Dependencies

- < 1MB Jar

- Java 6+ & Android 2.3+

- Java 8 lambda support

- Polyglot (Scala, Groovy, Clojure and Kotlin)

- Non-opinionated about source of concurrency (threads, pools, event loops, fibers, actors, etc)

- Async or synchronous execution

- Virtual time and schedulers for parameterized concurrency

### Why you should use RxJava in Android with Retrofit?


RxJava is exceptionally good when data is sent as a stream. The Retrofit Observable pushes all elements on the stream at the same time. This isn't particularly useful in itself compared to Callback. But when there are multiple elements pushed on the stream and different times, and you need to do timing-related stuff, RxJava makes the code a lot more maintainable.
