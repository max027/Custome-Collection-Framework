package com.saurabh.CustomDataStructure.Utils;

public class Node <T> {
   public T Data;
   public Node<T> next;
   public Node<T> prev;
   public Node(T element,Node<T> prev,Node<T> next){
       this.Data=element;
       this.next=next;
       this.prev=prev;
   }
}

