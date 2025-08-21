package com.saurabh.CustomDataStructure.linear;

import com.saurabh.CustomDataStructure.Utils.Node;
import com.saurabh.CustomDataStructure.interfaces.list;

import java.util.NoSuchElementException;

/**
 * Linked List
 * Time Complexities:
 * Add First and Last: O(1)
 * Remove First and Last: O(1)
 * Add and Remove at index: O(n)
 * Search: O(n)
 */
public class CustomLinkedList<E> implements list<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size=0;

    public CustomLinkedList(){

    }

    // add new element at head.
    private void linkFirst(E element){
        final Node<E> f=head;
        final Node<E> newNode=new Node<E>(element,null,f);
        head=newNode;
        if (f==null){
            tail=newNode;
        }else{
            f.prev=newNode;
        }
        size++;
    }
    //add new element at tail
    private void linkLast(E element){
        final Node<E> l=tail;
        final Node<E> newNode=new Node<>(element,l,null);
        tail=newNode;
        if (l==null){
            head=newNode;
        }else {
            l.next=newNode;
        }
        size++;
    }

    // Link before a particular element
    void linkBefore(E element,Node<E> node){
        Node<E>prev=node.prev;
        Node<E> newNode=new Node<>(element,prev,node);
        node.prev=newNode;
        if (prev==null){
            head=newNode;
        }else {
            prev.next=newNode;
        }
        size++;
    }

    // remove first element
    private E unlinkFirst(Node<E> f){
        final E items=f.Data;
        final Node<E> next=f.next;
        head=next;
        f.Data=null;
        f.next=null;
        if (next==null){
            tail=null;
        }else {
            next.prev=null;
        }
        size--;
        return items;
    }


    //remove last element
    private E unlinkLast(Node<E> l){
        final E items=l.Data;
        final Node<E> perv=l.prev;
        tail=perv;
        l.Data=null;
        l.prev=null;
        if (perv==null){
           head=null;
        }else{
            perv.next=null;
        }
        size--;
        return items;
    }

    //Remove a particular node in the list
    E unlink(Node<E> node){
        final E items=node.Data;
        final Node<E> next=node.next;
        final Node<E> prev=node.prev;
        if (prev==null){
            head=next;
        }else {
            prev.next=next;
            node.prev=null;
        }
        if (next==null){
            tail=prev;
        }else{
            next.prev=prev;
            node.next=null;
        }

        node.Data=null;
        size--;

        return items;
    }

    /**
     *
     * @return number of elements in list
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }


    /**
     * Insert specified element at beginning of list
     * @param element
     */
    public void addFirst(E element){
        linkFirst(element);
    }

    /**
     * Insert specified element at end of list
     * @param element
     */
    public void addLast(E element){
        linkLast(element);
    }


    /**
     * Append specific element at end of list
     * @param element
     * @return true
     */
    @Override
    public boolean add(E element) {
        linkLast(element);
        return true;
    }

    /**
     * Removes and returns first element in list
     * @return first element
     * @throws NoSuchElementException
     */
    public E removeFirst(){
        final Node<E> f=head;
        if (f==null){
            throw new NoSuchElementException("Cannot Remove from empty list");
        }
        return unlinkFirst(f);
    }

    /**
     * Removes and returns last element in list
     * @return last element
     * @throws NoSuchElementException
     */
    public E removeLast(){
        final Node<E>l=tail;
        if (l==null){
            throw new NoSuchElementException("Cannot Remove from empty list");
        }
        return unlinkLast(l);
    }

    /**
     * Returns first element in list
     * @return first element
     * @throws NoSuchElementException
     */
    public E getFirst(){
       final Node<E> f=head;
       if (f==null){
           throw new NoSuchElementException("Cannot get from empty list");
       }
       return f.Data;
    }
    /**
     * Returns last element in list
     * @return last element
     * @throws NoSuchElementException
     */
    public E getLast(){
        final Node<E> l=tail;
        if (l==null){
            throw new NoSuchElementException("Cannot get from empty list");
        }
        return l.Data;
    }

    /**
     * Check if given index is valid
     * @param index
     * @throws IndexOutOfBoundsException
     */
    private void checkValidIndex(int index){
        if (index<0 || index>size){
            throw new IndexOutOfBoundsException("Index:"+index+" size:"+size);
        }
    }


    /**
     * Returns (Non-null) element at specified position
     * @param index
     * @return element at specified position
     */
    Node<E> node(int index){
        if (index<(size>>1)){
            Node<E>x=head;
            for (int i=0;i<index;i++){
                x=x.next;
            }
            return x;
        }else {
            Node<E>x=tail;
            for (int i=size-1 ;i>index;i--){
                x=x.prev;
            }
            return x;
        }
    }

    /**
     * Returns element at specified position int the list
     * @param index
     * @return element at specified position
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) {
        checkValidIndex(index);
        return node(index).Data;
    }


    /**
     * Replaces element at specified index with specified element
     * @param index  index of element to replace
     * @param element  element to replace with
     * @return previous element
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E set(int index, E element) {
        checkValidIndex(index);
        Node<E>node=node(index);
        E OldVal=node.Data;
        node.Data=element;
        return OldVal;
    }

    /**
     * Insert specified element in the specified index
     * @param index index at which  element to be inserted
     * @param element element that is to be inserted
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(int index, E element) {
        checkValidIndex(index);
        if (index==size){
            linkLast(element);
        }else {
            linkBefore(element,node(index));
        }


    }
    /**
     * Removes element from specified index
     * @param index index at which element is to be removed
     * @return removed element
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E remove(int index) {
        checkValidIndex(index);
        return unlink(node(index));
    }


    /**
     * Returns the index of first occurence of given element in list
     * @param o
     * @return element at specified index or -1
     */
    @Override
    public int indexOf(Object o) {
        int index=0;
        if (o==null){
            for (Node<E>x=head;x!=null;x=x.next){
                if (x.Data==null){
                    return index;
                }
                index++;
            }
        }else{
            for (Node<E>x=head;x!=null;x=x.next){
                if (o.equals(x.Data)){
                    return index;
                }
                index++;
            }
        }

        return -1;
    }

    /**
     * Check if specified element present in list
     * @param o
     * @return true if present else false
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o)>=0;
    }

    /**
     * Removes specified element from list
     * @param o element to be removed
     * @return true if removed else false
     */

    @Override
    public boolean remove(Object o) {
        if (o==null){
            for (Node<E>x=head;x!=null;x=x.next){
                if (x.Data==null){
                    unlink(x);
                    return true;
                }
            }
        }else{
            for(Node<E>x=head;x!=null;x=x.next){
                if (o.equals(x.Data)){
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Removes First element in list
     * @return removed element
     */
    @Override
    public E remove() {
        return removeFirst();
    }

    /**
     * Clears entire list for garbage collection
     */
    @Override
    public void clear() {
        Node<E>x=head;
        while (x!=null){
            Node<E>next=x.next;
            x.Data=null;
            x.next=null;
            x.prev=null;
            x=next;
        }
        head=tail=null;
        size=0;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        Node<E> x = head;
        if (x != null) {
            sb.append(x.Data);
            x = x.next;
            while (x != null) {
                sb.append(" -> ").append(x.Data);
                x = x.next;
            }
        }

        return sb.toString();

    }
}
