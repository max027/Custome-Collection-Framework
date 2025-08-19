package com.saurabh.CustomDataStructure.interfaces;

public interface tree<E> {
   void insert(E element);
   boolean remove(E element);
   boolean contains(E element);
   E findMin();
   E findMax();
   int height();
   void inorderTraversal();
   void preorderTraversal();
   void postorderTraversal();
}
