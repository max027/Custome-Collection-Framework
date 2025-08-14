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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }


    public void addFirst(E element){
        linkFirst(element);
    }

    public void addLast(E element){
        linkLast(element);
    }

    // Append specific element at end of list
    @Override
    public boolean add(E element) {
        linkLast(element);
        return true;
    }

    public E removeFirst(){
        final Node<E> f=head;
        if (f==null){
            throw new NoSuchElementException("Cannot Remove from empty list");
        }
        return unlinkFirst(f);
    }

    public E removeLast(){
        final Node<E>l=tail;
        if (l==null){
            throw new NoSuchElementException("Cannot Remove from empty list");
        }
        return unlinkLast(l);
    }

    //return first element from list
    public E getFirst(){
       final Node<E> f=head;
       if (f==null){
           throw new NoSuchElementException("Cannot get from empty list");
       }
       return f.Data;
    }

    // return last element from list
    public E getLast(){
        final Node<E> l=tail;
        if (l==null){
            throw new NoSuchElementException("Cannot get from empty list");
        }
        return l.Data;
    }

    // check given index
    private void checkValidIndex(int index){
        if (index<0 || index>size){
            throw new IndexOutOfBoundsException("Index:"+index+" size:"+size);
        }
    }

    //returns element at specified position
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

    @Override
    public E get(int index) {
        checkValidIndex(index);
        return node(index).Data;
    }

    @Override
    public E set(int index, E element) {
        checkValidIndex(index);
        Node<E>node=node(index);
        E OldVal=node.Data;
        node.Data=element;
        return OldVal;
    }

    @Override
    public void add(int index, E element) {
        checkValidIndex(index);
        if (index==size){
            linkLast(element);
        }else {
            linkBefore(element,node(index));
        }


    }
    // Remove fist element from list
    @Override
    public E remove(int index) {
        checkValidIndex(index);
        return unlink(node(index));
    }

    // Return the index of first occurence in given list else return -1
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

    @Override
    public boolean contains(Object o) {
        return indexOf(o)>=0;
    }

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


    //remove and return first element in list.
    @Override
    public E remove() {
        return removeFirst();
    }

    //Remove all element from the list
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
