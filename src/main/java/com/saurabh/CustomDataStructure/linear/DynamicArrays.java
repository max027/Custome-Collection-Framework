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
    /**
     * Default capacity if not provided
     */
    private static final int DEFAULT_CAPACITY=10;

    /**
     * Array which hold data
     */
    private Object[] ElementData;

    /**
     * Number of element currently in array
     */
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

    /**
     * Returns number of element present in array
     * @return number of element in array
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return if array is empty
     * @return true if empty else false
     */
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * Increase the capacity of array to ensure that array can hold atleast
     * the number of element specified.
     * @param minCapacity desired minimum capacity
     */
    private void ensureCapacity(int minCapacity){
        if (minCapacity>ElementData.length){
            grow(minCapacity);
        }
    }

    /**
     * Increase the capacity if needed
     * @param minCapacity desired minimum capacity
     * @throws OutOfMemoryError
     */
    private void grow(int minCapacity){
        int OldCapacity=ElementData.length;
        int NewCapacity=OldCapacity+(OldCapacity*2);
        if (NewCapacity<minCapacity){
           NewCapacity=minCapacity;
        }

        ElementData= Arrays.copyOf(ElementData,NewCapacity);
    }

    /**
     * Append specified element at end of array
     * @param element element to be inserted
     * @return true if inserted else false
     */
    @Override
    public boolean add(E element) {
        ensureCapacity(size+1);
        ElementData[size++]=element;
        return true;
    }

    /**
     * Remove element from end of array
     * @return removed element
     * @throws IndexOutOfBoundsException
     */
    public  E remove(){
        if (size==0){
            throw new IndexOutOfBoundsException("Cannot remove from empty array");
        }
        E lastElement=(E)ElementData[size-1];
        ElementData[--size]=null;

        return lastElement;
    }

    /**
     * Remove element at specified index
     * @param index index at which element to be removed
     * @return removed element
     * @throws IndexOutOfBoundsException
     */
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

    /**
     * Returns element at specified index
     * @param index index at which element is to be retrieved
     * @return element at specified index
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index){
        rangeCheck(index);
        return (E)ElementData[index];
    }

    /**
     * Replace element at specified index with specified element
     * @param index index at which element is to be replace
     * @param element element to be replace with
     * @return previous element
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue=(E)ElementData[index];
        ElementData[index]=element;
        return oldValue;
    }

    /**
     * Inserts specified element at specified index
     * @param index index at which element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException
     */

    @Override
    public void add(int index, E element) {
        rangeCheckAdd(index);
        ensureCapacity(size+1);
        System.arraycopy(ElementData,index,ElementData,index+1,size-index);
        ElementData[index]=element;
        size++;
    }


    /**
     * Returns index of specified element
     * @param element
     * @return index of specified element or -1
     */
    @Override
    public int indexOf(E element) {
        for (int i=0;i<ElementData.length;i++){
            if (ElementData[i]==element){
                return i;
            }
        }
       return -1;
    }

    /**
     * Check if specified element is present in array
     * @param element
     * @return true if present else flase
     */
    public boolean contains(E element){
        return indexOf(element)>=0;
    }

    /**
     * Removes specified element from array
     * @param o element to be removed
     * @return true if removed else false
     */
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

    /**
     * Skips bound checking does not return element
     * @param index
     */
    private void fastRemove(int index){
        int numMoved=size-index-1;
        if (numMoved>0){
            System.arraycopy(ElementData,index+1,ElementData,index,numMoved);
        }
        ElementData[--size]=null;
    }


    /**
     * Clear array for garbage collection
     */
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
