package com.saurabh.CustomDataStructure.linear;

import com.saurabh.CustomDataStructure.interfaces.deque;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class CustomeDeque<E> implements deque<E> {
    //Array which holds data in deque. Circular buffer
    private Object[] elements;

    static final int DEFAULT_CAPACITY=16;

    //Index of first element in deque
    private int head;

    //Index of last element in deque
    private int tail;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    //Handel dynamic resizing of circular buffer
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

    public CustomeDeque(){
        elements=new Object[DEFAULT_CAPACITY+1];
    }
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


    //Safely move backward
    private int dec(int i,int m){
        if (--i<0){
            i=m-1;
        }
        return i;
    }

    //Safely move ahead
    private int inc(int i,int m){
        if (++i>=m){
            i=0;
        }
        return i;
    }

    static final <E> E elemantAt(Object[] es,int index){
       return (E) es[index];
    }

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

    @Override
    public E peekFirst() {
        E element=elemantAt(elements,head);
        if (element==null){
            throw new NoSuchElementException();
        }
        return element;
    }

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

    // Add given element at the back
    @Override
    public boolean offer(E element) {
        offerLast(element);
        return true;
    }

    //Remove Element from front
    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    public boolean isEmpty(){
        return head==tail;
    }

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
