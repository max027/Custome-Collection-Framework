package com.saurabh.CustomDataStructure;

import com.saurabh.CustomDataStructure.linear.CustomLinkedList;
import com.saurabh.CustomDataStructure.linear.CustomeDeque;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.NoSuchElementException;
public class CustomDequeTest {
    CustomeDeque<Integer> deque;
    CustomeDeque<String> string_deque;

    @BeforeEach
    void initialize(){
        deque=new CustomeDeque<>();
        string_deque=new CustomeDeque<>();
    }

    @AfterEach
    void drop(){
        deque=null;
        string_deque=null;
    }

    @Test
    void testDefaultConstructor(){
        CustomeDeque<String>deque=new CustomeDeque<>();
        assertEquals(true,deque.isEmpty());
        assertEquals(0,deque.size());
    }

    @Test
    void testParameParametrizedConstructor(){
        CustomeDeque<Integer> deque1=new CustomeDeque<>(10);
        assertEquals(true,deque1.isEmpty());
        assertEquals(0,deque1.size());
    }

    @Test
    void testSize(){
        deque.offerLast(1);
        deque.offerFirst(2);
        deque.offer(3);

        assertEquals(3,deque.size());

        deque.poll();
        assertEquals(2,deque.size());


        deque.offer(4);
        deque.offer(5);
        deque.offer(6);
        assertEquals(5,deque.size());

        deque.pollLast();
        deque.pollFirst();
        assertEquals(3,deque.size());
    }

    @Test
    void testPeek(){
        string_deque.offer("First");
        string_deque.offer("Second");
        string_deque.offer("Third");

        assertEquals(3,string_deque.size());
        assertEquals("First",string_deque.peekFirst());
        assertEquals("Third",string_deque.peekLast());

        string_deque.offerFirst("Fourth");
        assertEquals(4,string_deque.size());
        assertEquals("Fourth",string_deque.peekFirst());


        string_deque.offerLast("Five");
        assertEquals(5,string_deque.size());
        assertEquals("Five",string_deque.peekLast());

        string_deque.pollFirst();
        assertEquals(4,string_deque.size());
        assertEquals("First",string_deque.peekFirst());
    }


    @Test
    void testOfferFirstandLast(){
        string_deque.offerFirst("A");
        assertEquals(1,string_deque.size());
        assertEquals("A",string_deque.peekFirst());

        string_deque.offerLast("B");

        assertEquals(2,string_deque.size());
        assertEquals("B",string_deque.peekLast());

        string_deque.offerFirst("C");
        assertEquals(3,string_deque.size());
        assertEquals("C",string_deque.peekFirst());


        string_deque.offerLast("D");

        assertEquals(4,string_deque.size());
        assertEquals("D",string_deque.peekLast());
    }

    @Test
    void testPollFirstandLast(){
        deque.offer(12);
        deque.offer(22);
        deque.offer(34);
        deque.offer(35);

        assertEquals(4,deque.size());
        assertEquals(12,(int)deque.peekFirst());
        assertEquals(35,(int)deque.peekLast());

        deque.pollFirst();
        deque.pollLast();

        assertEquals(2,deque.size());
        assertEquals(22,(int)deque.peekFirst());
        assertEquals(34,(int)deque.peekLast());

        deque.pollFirst();
        deque.pollLast();

        assertEquals(0,deque.size());

    }
    @Test
    void testOfferandPoll(){
        string_deque.offer("First");
        string_deque.offer("Second");
        string_deque.offer("Third");
        string_deque.offer("Fourth");
        string_deque.offer("Fifth");
        string_deque.offer("Sixth");

        assertEquals(6,string_deque.size());
        assertEquals("First",string_deque.peekFirst());
        assertEquals("Sixth",string_deque.peekLast());

        string_deque.poll();
        assertEquals(5,string_deque.size());
        assertEquals("Second",string_deque.peekFirst());

        string_deque.offer("First");
        assertEquals(6,string_deque.size());
        assertEquals("First",string_deque.peekLast());

        string_deque.poll();
        string_deque.poll();
        assertEquals(4,string_deque.size());
        assertEquals("Fourth",string_deque.peekFirst());
    }

    @Test
    void testEmptyDeque(){
        CustomeDeque<Integer>deque1=new CustomeDeque<>();
        assertEquals(0,deque1.size());
        assertEquals(true,deque1.isEmpty());

        assertEquals(null,deque1.poll());
        assertEquals(null,deque1.pollFirst());
        assertEquals(null,deque1.pollLast());

        assertThrows(NoSuchElementException.class, deque1::peek);
        assertThrows(NoSuchElementException.class, deque1::peekFirst);
        assertThrows(NoSuchElementException.class, deque1::peekLast);
    }
    @Test
    void testNullHandling(){
        assertThrows(NullPointerException.class, ()->deque.offer(null));
        assertThrows(NullPointerException.class, ()->deque.offerFirst(null));
        assertThrows(NullPointerException.class, ()->deque.offerLast(null));
    }


}

