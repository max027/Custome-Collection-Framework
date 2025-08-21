package com.saurabh.CustomDataStructure.Utils;

public class TreeNode <E>{
    public TreeNode<E> left;
    public TreeNode<E> right;
    public E element;
    public TreeNode(E element,TreeNode<E>left,TreeNode<E>right){
       this.element=element;
       this.left=left;
       this.right=right;
    }

}
