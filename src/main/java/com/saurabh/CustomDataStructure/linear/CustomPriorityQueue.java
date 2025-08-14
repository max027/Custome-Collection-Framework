package com.saurabh.CustomDataStructure.linear;

import com.saurabh.CustomDataStructure.interfaces.queue;

import java.util.Comparator;

public class CustomPriorityQueue<E> implements queue<E> {

    //Number of elements in priority queue
    private int size;

    //Initial capacity
    private static final int DEFAULT_CAPACITY=11;

 //   private final Comparator <? super E> comparator;

    //Array where elements in the queue are stored
    private Object[] ElementData;


    @Override
    public boolean offer(E element) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}
