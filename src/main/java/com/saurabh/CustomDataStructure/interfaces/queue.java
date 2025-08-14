package com.saurabh.CustomDataStructure.interfaces;

public interface queue<E> {


    // Add specific element in queue
    boolean offer(E element);

    // Removes head of queue.
    E poll();

    //returns head of queue return null if not present.
    E peek();
}
