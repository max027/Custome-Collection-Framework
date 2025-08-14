package com.saurabh.CustomDataStructure.interfaces;

public interface deque<E> extends queue<E>{
    //Insert specific element infront of queue
    boolean offerFirst(E element);

    //Insert specific element at back of  queue
    boolean offerLast(E element);

    //Remove element infront of queue
    E pollFirst();

    //Remove element at back of queue
    E pollLast();

    //Return but not remove element infront of queue
    E peekFirst();

    //Return but not remove element at back of queue
    E peekLast();

}
