package com.saurabh.CustomDataStructure.interfaces;

public interface list<E> {

    // return number of element in the list
    int size();

    // true is list contains element. False if no element
    boolean isEmpty();

    // append given element to end of list.
    boolean add(E element);

    // remove element at specified position. If not present list is unchanged.
    E remove(int  index);

    // remove last element in the list.
    E remove();

    // return element at specified element
    E get(int index);

    // replace element at specified element return the element previously at position
    E set(int index,E element);

    // insert element at specified position and shift element at that position and all after to right
    void add(int index,E element);


    // return the first occurence of specified element.-1 if list does not contain that element.
    int indexOf(E element);

    // check if Element present in list
    boolean contains(E element);

    // remove first occurence of Element.
    boolean remove(Object o);

    // Remove all refence for Garbage collection.
    void clear();


}
