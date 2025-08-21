package com.saurabh.CustomDataStructure.Tree;

import com.saurabh.CustomDataStructure.Utils.TreeNode;
import com.saurabh.CustomDataStructure.interfaces.tree;

import java.util.Objects;

public class BinarySearchTree<E extends  Comparable<? super E>> implements tree<E>  {

    private TreeNode<E>root;
    public BinarySearchTree(){
        this.root=null;
    }

    @Override
    public void insert(E element) {
        if (element==null){
            throw  new IllegalArgumentException("Cannot insert null");
        }

        root=insertRecursive(root,element);
    }


    private TreeNode<E> insertRecursive(TreeNode<E>node,E element){
       if (node==null){
           return new TreeNode<E>(element,null,null);
       }
       int compare=element.compareTo(node.element);
       if (compare<0){
           node.left=insertRecursive(node.left,element);
       }else if (compare>0){
           node.right=insertRecursive(node.right,element);
       }

       return node;
    }



    @Override
    public boolean remove(E element) {
        if (element==null){
            return false;
        }
        root=removeRecursive(root,element);
        return true;
    }

    private TreeNode<E>removeRecursive(TreeNode<E>node,E element){
        if (node==null){
            return null;
        }
        int compare=element.compareTo(node.element);
        if (compare<0){
           node.left=removeRecursive(node.left,element);
        } else if (compare>0) {
            node.right=removeRecursive(node.right,element);
        }else{
            //no children
            if (node.left==null && node.right==null){
                return null;
            }

            //one children
            if (node.left==null){
                return node.right;
            }
            if (node.right==null){
                return node.left;
            }

            //in-order successor
            TreeNode<E> successor=findmin(node.right);
            node.element= successor.element;
            node.right=removeRecursive(node.right,successor.element);
        }
        return node;
    }

    private TreeNode<E>findmin(TreeNode<E>node){
        while (node.left!=null){
            node=node.left;
        }
        return node;
    }

    private TreeNode<E>findmax(TreeNode<E>node){
        while (node.right!=null){
            node=node.right;
        }
        return node;
    }

    @Override
    public boolean contains(E element) {
        if (element==null){
            return false;
        }
        return searchRecursive(root,element);
    }

    private boolean searchRecursive(TreeNode<E>node,E element){
       if (node==null){
           return false;
       }
       if (node.element==element){
          return true;
       }
        int compare=element.compareTo(node.element);
       if (compare<0){
           return searchRecursive(node.left,element);
       }else {
           return searchRecursive(node.right,element);
       }
    }

    @Override
    public E findMin() {
        if (root==null){
            return null;
        }
        return findmin(root).element;
    }

    @Override
    public E findMax() {
        if (root==null){
            return null;
        }
        return findmax(root).element;
    }

    @Override
    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(TreeNode<E>node){
        if (node==null){
            return -1;
        }
        return 1+Math.max(heightRecursive(node.left),heightRecursive(node.right));
    }

    @Override
    public void inorderTraversal() {
        inorderRecursive(root);
    }
    private void inorderRecursive(TreeNode<E>node){
        if (node==null){
            return;
        }
        inorderRecursive(node.left);
        System.out.println(node.element);
        inorderRecursive(node.right);
    }

    @Override
    public void preorderTraversal() {
        preorderRecursive(root);
    }

    private void preorderRecursive(TreeNode<E>node){
        if (node==null){
            return;
        }
        System.out.println(node.element);
        preorderRecursive(node.left);
        preorderRecursive(node.right);
    }

    @Override
    public void postorderTraversal() {
        postorderRecursive(root);
    }

    private void postorderRecursive(TreeNode<E>node){
        if (node==null){
            return;
        }
        postorderRecursive(node.left);
        postorderRecursive(node.right);
        System.out.println(node.element);
    }
    public boolean isEmpty(){
        return root==null;
    }

    public int size(){
        return sizeRecursive(root);
    }

    private int sizeRecursive(TreeNode<E>node){
        if (node==null){
            return 0;
        }
        return 1+sizeRecursive(node.left)+sizeRecursive(node.right);
    }
}
