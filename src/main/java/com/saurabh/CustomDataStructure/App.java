package com.saurabh.CustomDataStructure;


import com.saurabh.CustomDataStructure.Tree.BinarySearchTree;
import com.saurabh.CustomDataStructure.linear.CustomLinkedList;

import java.util.Objects;

public class App
{
    public static void main( String[] args )
    {
        BinarySearchTree<Integer>intBST=new BinarySearchTree<>();
        intBST.insert(50);
        intBST.insert(30);
        intBST.insert(70);
        intBST.insert(20);
        intBST.insert(40);
        intBST.insert(60);
        intBST.insert(80);
//        intBST.inorderTraversal();


        intBST.remove(50);
        System.out.println(intBST.contains(50));

    }
}
