package com.saurabh.CustomDataStructure.Tree;

import com.saurabh.CustomDataStructure.Utils.TreeNode;
import com.saurabh.CustomDataStructure.interfaces.tree;

public class BinarySearchTree<E> implements tree<E> {


    private TreeNode<E>root;
    private TreeNode<E>left;
    private TreeNode<E>right;

    public BinarySearchTree(){

    }

    @Override
    public void insert(E element) {
        if (element==null){
            throw  new IllegalArgumentException("Cannot insert null");
        }
        if (root==null){
            root=new TreeNode<>(element,left,right);
        }else {


        }

    }

    @Override
    public boolean remove(E element) {
        return false;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public E findMin() {
        return null;
    }

    @Override
    public E findMax() {
        return null;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public void inorderTraversal() {

    }

    @Override
    public void preorderTraversal() {

    }

    @Override
    public void postorderTraversal() {

    }
}
