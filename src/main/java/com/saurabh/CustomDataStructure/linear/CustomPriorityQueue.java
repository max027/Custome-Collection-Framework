package com.saurabh.CustomDataStructure.linear;

import com.saurabh.CustomDataStructure.interfaces.queue;
import jdk.internal.util.ArraysSupport;

import java.lang.annotation.ElementType;
import java.util.Arrays;
import java.util.Comparator;

public class CustomPriorityQueue<E> implements queue<E> {

    private int size;

    private static final int DEFAULT_CAPACITY=11;

    // If null uses natural ordering.
     private final Comparator <? super E> comparator;

    //Array where elements in the queue are stored.represented by binary tree
    private Object[] ElementData;

    //create priority queue with default capacity and order element with natural ordering
    public CustomPriorityQueue(){
        this(DEFAULT_CAPACITY);
    }

    //create priority queue with given capacity and order element with natural ordering
    public CustomPriorityQueue(int capacity){
        this(capacity,null);
    }

    //create priority queue with default capacity and order element with specified comparator.
    public CustomPriorityQueue(Comparator<? super E>comp){
        this(DEFAULT_CAPACITY,comp);
    }

    //create priority queue with given capacity and order element with specified comparator.
    public CustomPriorityQueue(int capacity,Comparator<? super E>comp){
        if (capacity<1){
            throw  new IllegalArgumentException();
        }
        this.comparator=comp;
        this.ElementData=new Object[capacity];
    }

   //ensure that Object[0] exist
    private Object[] ensureNonEmpty(Object[] obj){
       return (obj.length>0) ? obj:new Object[1];
    }


    private void grow(int needed_capacity){
        int oldCap=ElementData.length;
        int minCap=oldCap+needed_capacity;
        int preferedCap=(oldCap<64)?oldCap+2:oldCap>>1;

        int newCap= ArraysSupport.newLength(oldCap,minCap,preferedCap);
        ElementData= Arrays.copyOf(ElementData,newCap);
    }

    @Override
    public boolean offer(E element) {
        if (element==null){
            throw new NullPointerException();
        }
        int i=size;
        if (size>=ElementData.length){
            grow(i+1);
        }
        shiftUp(i,element);
        size=i+1;
        return true;
    }

    /**
     * insert element at position i maintaining heap by moving element up the tree
     * until it is greater than or equal to its parent.
     * @param i position to insert
     * @param element element which is to insert
     */
    private void shiftUp(int i,E element){
        if (comparator!=null){
            shiftUpComparator(i,element,ElementData,comparator);
        }else {
            shiftUpCompare(i,element,ElementData);
        }

    }

    private static <E> void shiftUpCompare(int i,E element,Object[] ar){
        Comparable<? super E>key=(Comparable <? super E>) element;
        while (i>0){
            int parent=(i-1)>>>1;   //find parent
            Object e= ar[parent];  //get parent
            if (key.compareTo((E)e)>=0){
               break;  //if current element>=parent , it satisfies heap condition.
            }
            ar[i]=e;   //move parent down
            i=parent;  //move up to parent
        }
        ar[i]=key;
    }

    private static <E> void shiftUpComparator(int i,E element,Object[] ar,Comparator<? super E> comp){
       while(i>0){
           int parent=(i-1)>>>1;
           Object e=ar[parent];
           if (comp.compare(element,(E)e)>=0){
               break;
           }
           ar[i]=e;
           i=parent;
       }
       ar[i]=element;

    }


    /**
     * insert element at position i while maintaining heap by
     * moving element down the tree until element is less than or equal to its parent
     * @param i position to insert
     * @param element queue
     */
    private void shiftDown(int i,E element){
        if (comparator!=null){
           shiftDownComparator(i,element,ElementData,comparator,size);
        }else {
            shiftDownCompare(i,element,ElementData,size);
        }
    }

    private static <E> void shiftDownCompare(int i,E element,Object[] ar,int size){
        Comparable<? super E>key=(Comparable<? super E>) element;
        int half=size>>>1; //last index that has children
        while (i<half){
            int child=(i<<1)+1;    //Left child index: 2*k + 1
            Object e=ar[child];   //Assume left child is smaller
            int right=child+1;   //right child index: 2*k + 2

            //right child exists AND is smaller than left child
            if (right<size && ((Comparable<?super E>)e).compareTo((E)ar[right])>0){
                //use right
                child=right;
               e=ar[child];
            }

            if (key.compareTo((E)e)<=0){
                break;
            }

            ar[i]=e; // Move smaller child up to current position

            i=child; // Move down to child's position
        }
        ar[i]=key;
    }

    private static <E> void shiftDownComparator(int i,E element,Object[] ar,Comparator<? super E>comp,int size){
       int half=size>>>1;
       while (i<half){
          int child=(i<<1)+1;
          Object e=ar[child];
          int right=child+1;
          if (right<size && comp.compare((E)e,(E)ar[right])>0){
              child=right;
              e=ar[child];
          }
          if (comp.compare(element,(E)e)<=0){
             break;
          }
          ar[i]=e;
          i=child;
       }
       ar[i]=element;
    }

    private void heapify(){
       final Object[] ar=ElementData;
       final Comparator<? super E>comp=comparator;
       int n=size,i=(n>>>1)-1;
       if (comp==null){
           for (;i>=0;i--){
               shiftDownCompare(i,(E) ar[i],ar,n);
           }
       }else {
           shiftDownComparator(i,(E) ar[i],ar,comp,n);
       }

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
        return (E)ElementData[0];
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
