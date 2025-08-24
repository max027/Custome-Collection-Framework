package com.saurabh.CustomDataStructure;

import com.saurabh.CustomDataStructure.linear.CustomeDeque;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @DisplayName("test default constructor")
    @Test
    void testDefaultConstructor(){
        CustomeDeque<String>deque=new CustomeDeque<>();
        assertEquals(true,deque.isEmpty());
        assertEquals(0,deque.size());
    }

    @DisplayName("test parametrized constructor")
    @Test
    void testParameParametrizedConstructor(){
        CustomeDeque<Integer> deque1=new CustomeDeque<>(10);
        assertEquals(true,deque1.isEmpty());
        assertEquals(0,deque1.size());
    }

    @DisplayName("test size")
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

    @DisplayName("test peek")
    @Nested
    class TestPeek {
        @BeforeEach
        void initialize() {
            deque.offer(12);
            deque.offer(22);
            deque.offer(34);
            deque.offer(35);

        }

        @DisplayName("test peek First element")
        @Test
        void testPeekFirst(){
            assertEquals(12,(int)deque.peekFirst());
            deque.pollFirst();
            assertEquals(22,(int)deque.peekFirst());

            CustomeDeque<Integer>ar=new CustomeDeque<>();
            assertThrows(NoSuchElementException.class,()->ar.peekFirst());
        }

        @DisplayName("test peek last element")
        @Test
        void testPeekLast(){
            assertEquals(35,(int)deque.peekLast());
            deque.pollLast();
            assertEquals(34,(int)deque.peekLast());
            CustomeDeque<Integer>ar=new CustomeDeque<>();
            assertThrows(NoSuchElementException.class,()->ar.peekLast());
        }

        @Test
        void testPeek(){
            assertEquals(12,(int)deque.peekFirst());
            deque.pollFirst();
            assertEquals(22,(int)deque.peekFirst());

            CustomeDeque<Integer>ar=new CustomeDeque<>();
            assertThrows(NoSuchElementException.class,()->ar.peek());
        }
    }


    @DisplayName("test insertion of element")
    @Nested
    class TestInsertion{

        @DisplayName("test insert element in front of queue")
       @Test
       void testOfferFirst(){
           assertTrue(string_deque.offerFirst("A"));
           assertEquals(1,string_deque.size());
           assertEquals("A",string_deque.peekFirst());

           string_deque.offerFirst("B");
           assertEquals(2,string_deque.size());
           assertEquals("B",string_deque.peekFirst());

           string_deque.offerFirst("C");
           assertEquals(3,string_deque.size());
           assertEquals("C",string_deque.peekFirst());

           assertThrows(NullPointerException.class,()->string_deque.offerLast(null));

       }

       @DisplayName("test insert element at end of queue")
       @Test
        void testOfferLast(){
           assertTrue(string_deque.offerLast("C"));
           assertEquals(1,string_deque.size());
           assertEquals("C",string_deque.peekLast());

           string_deque.offerLast("B");
           assertEquals(2,string_deque.size());
           assertEquals("B",string_deque.peekLast());

           assertThrows(NullPointerException.class,()->string_deque.offerLast(null));
       }

        @DisplayName("test insert element at end of queue")
        @Test
        void testOffer(){
           assertTrue(deque.offer(5));
           assertEquals(1,deque.size());
           assertEquals(5,(int)deque.peekLast());

           deque.offerLast(4);
           assertEquals(2,deque.size());
           assertEquals(4,(int)deque.peekLast());

           assertThrows(NullPointerException.class,()->deque.offer(null));
       }
    }

    @DisplayName("test deletion of element")
    @Nested
    class TestDeletion{
       @BeforeEach
       void initialize(){
           deque.offer(12);
           deque.offer(22);
           deque.offer(34);
           deque.offer(35);


           string_deque.offer("First");
           string_deque.offer("Second");
           string_deque.offer("Third");
           string_deque.offer("Fourth");
           string_deque.offer("Fifth");
           string_deque.offer("Sixth");
       }

       @DisplayName("test deletion of element at end of queue")
       @Test
        void testPollLast(){
           assertEquals(4,deque.size());
           assertEquals(12,(int)deque.peekFirst());
           assertEquals(35,(int)deque.peekLast());

           assertEquals(35,(int)deque.pollLast());
           assertEquals(3,deque.size());


           assertEquals(34,(int)deque.pollLast());
           assertEquals(2,deque.size());
           assertFalse(deque.contains(34));



       }

       @DisplayName("test deletion of element in-front of queue")
       @Test
        void testPollFirst(){
           assertEquals(6,string_deque.size());
           assertEquals("First",string_deque.peekFirst());
           assertEquals("Sixth",string_deque.peekLast());

           assertEquals("First",string_deque.pollFirst());
           assertEquals("Second",string_deque.peekFirst());
           assertEquals(5,string_deque.size());

           assertEquals("Second",string_deque.pollFirst());
           assertEquals("Third",string_deque.peekFirst());
           assertEquals(4,string_deque.size());
       }

       @DisplayName("test deletion of element in-front of queue")
       @Test
        void testPoll(){
           assertEquals(6,string_deque.size());
           assertEquals("First",string_deque.poll());
           assertFalse(string_deque.contains("First"));
           assertEquals(5,string_deque.size());


           assertEquals("Second",string_deque.peekFirst());
           assertEquals("Second",string_deque.poll());
           assertFalse(string_deque.contains("Second"));
           assertEquals(4,string_deque.size());
       }

       @DisplayName("test deletion from empty queue ")
       @Test
        void testPollEmpty(){
           CustomeDeque<Integer>ar=new CustomeDeque<>();
           assertNull(ar.poll());
           assertThrows(NoSuchElementException.class,()->ar.peek());

           assertNull(ar.pollLast());
           assertNull(ar.pollFirst());
       }
    }

    @DisplayName("test edge cases")
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

}

