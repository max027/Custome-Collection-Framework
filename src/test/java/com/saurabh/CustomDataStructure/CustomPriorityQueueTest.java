package com.saurabh.CustomDataStructure;

import com.saurabh.CustomDataStructure.Tree.CustomPriorityQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomPriorityQueueTest {
    CustomPriorityQueue<Integer> minheap;
    CustomPriorityQueue<Integer> maxheap;

    @BeforeEach
    void initialize() {
        minheap = new CustomPriorityQueue<>();
        maxheap = new CustomPriorityQueue<>(Collections.reverseOrder());
    }

    @Test
    void testShiftUpMinheap() {
        minheap.offer(20);
        assertEquals(20, (int) minheap.peek());
        assertEquals(1, minheap.size());

        minheap.offer(10);
        assertEquals(10, (int) minheap.peek());
        assertEquals(2, minheap.size());


        minheap.offer(5);
        assertEquals(5, (int) minheap.peek());
        assertEquals(3, minheap.size());


        minheap.offer(7);
        assertEquals(5, (int) minheap.peek());
        assertEquals(4, minheap.size());

        List<Integer> list = new ArrayList<Integer>();
        while (!minheap.isEmpty()) {
            list.add(minheap.poll());
        }

        assertEquals(Arrays.asList(5, 7, 10, 20), list);

    }

    @Test
    void testShiftUpMaxheap() {
        maxheap.offer(5);
        assertEquals(5, (int) maxheap.peek());
        assertEquals(1, maxheap.size());


        maxheap.offer(10);
        assertEquals(10, (int) maxheap.peek());
        assertEquals(2, maxheap.size());


        maxheap.offer(30);
        assertEquals(30, (int) maxheap.peek());
        assertEquals(3, maxheap.size());


        maxheap.offer(20);
        assertEquals(30, (int) maxheap.peek());
        assertEquals(4, maxheap.size());

        List<Integer> list = new ArrayList<>();
        while (!maxheap.isEmpty()) {
            list.add(maxheap.poll());
        }

        assertEquals(Arrays.asList(30, 20, 10, 5), list);
    }

    @Test
    void testShiftUpDuplicates() {
       minheap.offer(10);
        minheap.offer(5);
        minheap.offer(10);
        minheap.offer(5);
        minheap.offer(3);
        minheap.offer(2);
        minheap.offer(3);
        minheap.offer(2);

        List<Integer> list = new ArrayList<>();
        while (!minheap.isEmpty()) {
            list.add(minheap.poll());
        }
        assertEquals(Arrays.asList(2,2,3,3,5,5,10,10),list);
    }

    @Nested
    class TestShiftDown{
        @BeforeEach
        void initialize(){
            minheap.offer(20);
            minheap.offer(10);
            minheap.offer(3);
            minheap.offer(8);
            minheap.offer(5);
        }

        @Test
        void testShiftDownMinheap(){
            assertEquals(3,(int)minheap.poll());
            assertEquals(5, (int)minheap.peek());

            assertEquals(5,(int)minheap.poll());
            assertEquals(8, (int)minheap.peek());

            assertEquals(8,(int)minheap.poll());
            assertEquals(10, (int)minheap.peek());
        }

        @Test
        void testShiftDownEmpty(){
            List<Integer>list=new ArrayList<>();
            int n=minheap.size();
            while (!minheap.isEmpty()){
                list.add(minheap.poll());
            }

            assertEquals(n,list.size());
            assertTrue(minheap.isEmpty());
            assertNull(minheap.poll());
        }

    }

    @Nested
    class TestRemoveAt{
       @BeforeEach
        void initialize(){
           minheap.offer(10);
           minheap.offer(20);
           minheap.offer(3);
           minheap.offer(60);
           minheap.offer(8);
           minheap.offer(15);
       }
       @Test
        void RemoveSpecificElement(){
           int n=minheap.size();
           assertTrue(minheap.remove(15));
           assertEquals(n-1, minheap.size());
           assertFalse(minheap.contains(15));

           List<Integer> list = new ArrayList<>();
           while (!minheap.isEmpty()) {
               list.add(minheap.poll());
           }
           assertFalse(list.contains(15));

           for (int i=1;i<list.size();i++){
               assertTrue(list.get(i-1)<list.get(i));
           }

       }
       @Test
        void testRemoveRoot(){
           int root= minheap.peek();
           assertTrue(minheap.remove(root));
           assertNotEquals(root,minheap.peek());
           assertFalse(minheap.contains(root));
       }

       @Test
        void testRemoveLeaf(){
           minheap.offer(80);
           assertTrue(minheap.contains(80));
           assertTrue(minheap.remove(80));
           assertFalse(minheap.contains(80));

       }
    }

    @Nested
    class TestEdge{
       @Test
       void testSingleElement(){
           minheap.offer(12);
           assertTrue(minheap.contains(12));
           assertTrue(minheap.remove(12));
           assertTrue(minheap.isEmpty());
       }

       @Test
        void testEmptyheap(){
           assertTrue(minheap.isEmpty());
           assertEquals(0,minheap.size());
           assertNull(minheap.poll());
           assertNull(minheap.peek());
       }

       @Test
        void testNullPointer(){
           assertThrows(NullPointerException.class,()->minheap.offer(null));
       }

    }
}
