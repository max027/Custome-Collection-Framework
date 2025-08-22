package com.saurabh.CustomDataStructure.linear;

import com.saurabh.CustomDataStructure.interfaces.deque;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Deque
 * Time Complexities
 * offerFirst / offerLast - O(1)
 * pollFirst / pollLast - O(1)
 * peekFist / peekLast - O(1)
 */
public class CustomeDeque<E> implements deque<E> {
    /**
     * Array which holds data in deque
     */
    private Object[] elements;

    static final int DEFAULT_CAPACITY=16;

    /**
     * Index of first element
     */
    private int head;

    /**
     * Index of last element
     */
    private int tail;

    /**
     * Safe max size for array
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    /**
     * Handel dynamic resizing of buffer
     * @param givenCap
     */
    private void grow(int givenCap){
        final int oldCap=elements.length;

        //for small double else grow by 50%
        int preferedCap=(oldCap<64)?(oldCap+2):(oldCap>>1);

        int newCap=oldCap+preferedCap;

        //check for overflow conditions
        if (preferedCap<givenCap||newCap-MAX_ARRAY_SIZE>0){
            newCap=newCapacity(givenCap,preferedCap);
        }

        final Object[] es=elements=Arrays.copyOf(elements,newCap);

        //handel circular buffer wrap around
        if (tail<head || (tail==head && es[head]!=null)){
            int space=newCap-oldCap;
            System.arraycopy(es,head,es,head+space,oldCap-head);
            for (int i=head,to=(head+=space);i<to;i++){
                es[i]=null;
            }
        }
    }

    /**
     * Handel edge case if array size is large
     * @param givenCap
     * @param preferedCap
     * @return capacity
     */
    private int newCapacity(int givenCap,int preferedCap){
        final int oldCap= elements.length,minCap;
        minCap = oldCap + givenCap;
        if (minCap-MAX_ARRAY_SIZE>0){
            //check for overflow
            if (minCap<0){
                throw new IllegalStateException("Deque to big");
            }
            return Integer.MAX_VALUE;
        }
        if (givenCap>preferedCap){
            return minCap;
        }
        return (oldCap+preferedCap-MAX_ARRAY_SIZE)<0?oldCap+preferedCap:MAX_ARRAY_SIZE;
    }

    /**
     * construct empty array deque if initial capacity not provided
     */
    public CustomeDeque(){
        elements=new Object[DEFAULT_CAPACITY+1];
    }

    /**
     * construct empty array deque with specified initial capacity
     */
    public CustomeDeque(int cap){
        int needed_cap;
        if (cap<1){
           needed_cap=1;
        }else {
            if (cap==Integer.MAX_VALUE){
                needed_cap=Integer.MAX_VALUE;
            }else {
                needed_cap=cap+1;
            }
        }
        elements=new Object[needed_cap];
    }



    /**
     * Circular decrement
     * @param i
     * @param m modulus
     * @return
     */
    private int dec(int i,int m){
        if (--i<0){
            i=m-1;
        }
        return i;
    }


    /**
     * Circular Increment
     * @param i
     * @param m modulus
     * @return
     */
    private int inc(int i,int m){
        if (++i>=m){
            i=0;
        }
        return i;
    }

    /**
     * Return element at specified index
     * @param es
     * @param index
     * @return element
     */
    static final <E> E elemantAt(Object[] es,int index){
       return (E) es[index];
    }


    /**
     * Insert specified index in front of queue
     * @param element element to be inserted
     * @return true in inserted else false
     */
    @Override
    public boolean offerFirst(E element) {
        if (element==null){
           throw new NullPointerException();
        }
        final Object[] es=elements;

        head=dec(head,es.length);

        es[head]=element;
        if (head==tail){
            grow(1);
        }

        return true;
    }


    /**
     * Insert specified index at end of queue
     * @param element element to be inserted
     * @return true in inserted else false
     */
    @Override
    public boolean offerLast(E element) {
        if (element==null){
            throw new NullPointerException();
        }
        final Object[] es=elements;
        es[tail]=element;
        tail=inc(tail,es.length);

        if (head==tail){
            grow(1);
        }
        return true;
    }

    /**
     * Remove element infront of queue
     * @return removed element
     */
    @Override
    public E pollFirst() {
        final Object[] es=elements;
        final int index=head;
        E element=elemantAt(es,index);

        if (element!=null){
            es[index]=null;
            head=inc(head,elements.length);
        }

        return element;
    }

    /**
     * Remove element at end of queue
     * @return Removed element
     */
    @Override
    public E pollLast() {
        final Object[] es=elements;
        final int index=dec(tail,es.length);
        E element=elemantAt(es,index);
        if (element!=null){
            tail=index;
            es[index]=null;
        }
        return element;
    }

    /**
     * Return element in front of queue
     * @return
     */
    @Override
    public E peekFirst() {
        E element=elemantAt(elements,head);
        if (element==null){
            throw new NoSuchElementException();
        }
        return element;
    }

    /**
     * Return element at end of queue
     * @return
     */
    @Override
    public E peekLast() {
        final Object[] es=elements;
        E element=elemantAt(es,dec(tail,es.length));
        if (element==null){
            throw new NoSuchElementException();
        }
        return element;
    }

    // queue methods

    /**
     * Insert element at end of queue
     * @param element element to be inserted
     * @return true if inserted else false
     */
    @Override
    public boolean offer(E element) {
        offerLast(element);
        return true;
    }


    /**
     * Remove element in front of queue
     * @return Removed element
     */
    @Override
    public E poll() {
        return pollFirst();
    }

    /**
     * Return First element
     * @return
     */
    @Override
    public E peek() {
        return peekFirst();
    }

    public boolean isEmpty(){
        return head==tail;
    }

    /**
     * Check if specified element is present in queue
     * @param o element to be searched
     * @return true if present else false
     */
    @Override
    public boolean contains(Object o) {
        if (o!=null){
            final Object[]es=elements;
            int i=head;
            int j=tail;
            int boundry=(i<=j)?j:es.length;
            for (;i<boundry;i++){
               if (o.equals(es[i])){
                   return true;
               }
            }
            if (i>j){
                for (i=0;i<j;i++){
                    if (o.equals(es[i])){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @return return number of element in queue
     */
    public int size(){
        int i=tail-head;
        if (i<0){
            i+=elements.length;
        }
        return i;
    }

    public String toString(){
        if (elements.length==0){
            return "[]";
        }
        StringBuilder st=new StringBuilder();
        st.append('[');
        for (int i=0;i<elements.length;i++){
            if (elements[i]==null){
                continue;
            }
            st.append(elements[i]);
            if (i!=elements.length-1){
               st.append(" ,");
            }
        }
        st.append("]");
        return st.toString();
    }
}
