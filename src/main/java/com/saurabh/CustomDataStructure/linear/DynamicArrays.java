package com.saurabh.CustomDataStructure.linear;

import com.saurabh.CustomDataStructure.interfaces.list;

import java.util.Arrays;

/**
 * Dynamic Arrays
 * Time Complexities:
 * Random Access - O(1)
 * Insert At end - O(1)
 * Insert At index - O(n)
 * Remove At end - O(n)
 * Remove - O(n)
 * Search - O(n)
 * Grow - O(n)
 */
public class DynamicArrays<E> implements list<E> {
    // Default capacity of array in not provided
    private static final int DEFAULT_CAPACITY=10;

    // Main array which will hold data
    private Object[] ElementData;

    // Number of element currently in array
    private int size;

    public DynamicArrays(int initialCapacity){
        if (initialCapacity>0){
            ElementData=new Object[initialCapacity];
        }else if (initialCapacity==0){
            ElementData=new Object[0];
        }else {
            throw new IllegalArgumentException("cannot create arrays with capacity "+initialCapacity);
        }
        this.size=0;

    }

    public DynamicArrays(){
       this(DEFAULT_CAPACITY);
    }

    public int capacity() {
       return ElementData.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * Increses the capacity of arrays if necessary
     * @param minCapacity
     */
    private void ensureCapacity(int minCapacity){
        if (minCapacity>ElementData.length){
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity){
        int OldCapacity=ElementData.length;
        int NewCapacity=OldCapacity+(OldCapacity*2);
        if (NewCapacity<minCapacity){
           NewCapacity=minCapacity;
        }

        ElementData= Arrays.copyOf(ElementData,NewCapacity);
    }

    @Override
    public boolean add(E element) {
        ensureCapacity(size+1);
        ElementData[size++]=element;
        return true;
    }

    public  E remove(){
        if (size==0){
            throw new IndexOutOfBoundsException("Cannot remove from empty array");
        }
        E lastElement=(E)ElementData[size-1];
        ElementData[--size]=null;

        return lastElement;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldValue= (E) ElementData[index];
        int numMoved=size-index-1;
        if (numMoved>0){
            System.arraycopy(ElementData,index+1,ElementData,index,numMoved);
        }
        ElementData[--size]= 0;
        return oldValue;
    }

    @Override
    public E get(int index){
        rangeCheck(index);
        return (E)ElementData[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue=(E)ElementData[index];
        ElementData[index]=element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckAdd(index);
        ensureCapacity(size+1);
        System.arraycopy(ElementData,index,ElementData,index+1,size-index);
        ElementData[index]=element;
        size++;
    }


    @Override
    public int indexOf(E element) {
        for (int i=0;i<ElementData.length;i++){
            if (ElementData[i]==element){
                return i;
            }
        }
       return -1;
    }

    public boolean contains(E element){
        return indexOf(element)>=0;
    }

    @Override
    public boolean remove(Object o) {
        if (o==null){
            for (int i=0;i<ElementData.length;i++){
                if (ElementData[i]==null){
                    fastRemove(i);
                   return true;
                }
            }
        }else {
            for (int i=0;i<ElementData.length;i++){
                if (o.equals(ElementData[i])){
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    // Skips bound checking does not return element.
    private void fastRemove(int index){
        int numMoved=size-index-1;
        if (numMoved>0){
            System.arraycopy(ElementData,index+1,ElementData,index,numMoved);
        }
        ElementData[--size]=null;
    }


    public void clear(){
        for (int i=0;i<ElementData.length;i++){
           ElementData[i]=null;
        }
        size=0;
    }

    // check if index within bounds of array.
    private void rangeCheckAdd(int index){
        if (index>size || index <0){
            throw  new IndexOutOfBoundsException("Index:"+index+" Size:"+size);
        }
    }

    // check if index within bounds of array.
    private void rangeCheck(int index){
        if (index>=size || index <0){
            throw  new IndexOutOfBoundsException("Index:"+index+" Size:"+size);
        }
    }

    public  String toString(){
        if (size==0){
            return "[]";
        }
        StringBuilder i=new StringBuilder();
        i.append("[");
        for (int idx=0;idx<ElementData.length;idx++){
            i.append(ElementData[idx]);
            if (idx!=size-1){
                i.append(",");
            }
        }
        i.append("]");
        return i.toString();
    }
}
