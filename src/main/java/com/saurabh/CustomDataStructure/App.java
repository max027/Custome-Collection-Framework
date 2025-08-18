package com.saurabh.CustomDataStructure;

import com.saurabh.CustomDataStructure.Tree.CustomPriorityQueue;
import com.saurabh.CustomDataStructure.linear.CustomeDeque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;

public class App
{
    public static void main( String[] args )
    {
        ArrayDeque<Integer>arr=new ArrayDeque<>();
        PriorityQueue<Integer>q=new PriorityQueue<>();
        q.addAll(Arrays.asList(1,2,3,4,5));
        CustomPriorityQueue<Integer>q2=new CustomPriorityQueue<>();

        q2.offer(1);
        q2.offer(2);
        q2.offer(3);
        q2.offer(4);


        System.out.println(q2.remove(3));
        CustomeDeque<Integer>ar=new CustomeDeque<>();
        System.out.println(ar.poll());

    }
}
