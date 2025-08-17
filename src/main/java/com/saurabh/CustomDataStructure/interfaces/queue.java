package com.saurabh.CustomDataStructure.interfaces;

public interface queue<E> {


    // Add specific element in queue
    boolean offer(E element);

    //Return number of elements in queue.
    int size();

    // Removes head of queue.
    E poll();

    //returns head of queue return null if not present.
    E peek();

    //Returns true if queue is empty.
    public boolean isEmpty();
}
