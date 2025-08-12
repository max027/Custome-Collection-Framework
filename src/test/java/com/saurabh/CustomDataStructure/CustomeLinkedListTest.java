package com.saurabh.CustomDataStructure;

import com.saurabh.CustomDataStructure.linear.CustomLinkedList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomeLinkedListTest {
    CustomLinkedList<Integer> list;
    CustomLinkedList<String> string_list;

    @BeforeEach
    void initialize(){
        list=new CustomLinkedList<>();
        string_list=new CustomLinkedList<>();
    }

    @AfterEach
    void drop(){
        list=null;
        string_list=null;
    }

    @Test
    void testConstructor(){
        CustomLinkedList<Integer>arr=new CustomLinkedList<>();
        assertEquals(0,arr.size());
    }


    @Test
    void testAddFirstLast(){
        string_list.add("First");
        string_list.add("Second");

        assertEquals(2,string_list.size());

        string_list.addFirst("Third");
        assertEquals(3,string_list.size());
        assertEquals("Third",string_list.getFirst());


        string_list.addLast("Fourth");
        assertEquals(4,string_list.size());
        assertEquals("Fourth",string_list.getLast());

        assertThrows(IndexOutOfBoundsException.class,()->string_list.add(12,"Seventh"));

    }
    @Test
    void testAddIndex(){
        string_list.add("First");
        string_list.add("Second");
        string_list.add("Third");

        assertEquals(3,string_list.size());
        string_list.add(1,"Fourth");

        assertEquals(4,string_list.size());
        assertEquals("Fourth",string_list.get(1));

        assertThrows(IndexOutOfBoundsException.class,()->string_list.add(12,"Seventh"));
    }

    @Test
    void testInvalidIndex(){
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class,()->list.get(12));
    }

    @Test
    void testRemoveFirstLast(){
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        assertEquals(5,list.size());

        list.removeFirst();
        assertEquals(4,list.size());
        assertEquals(2,(int)list.getFirst());

        list.removeLast();
        assertEquals(3,list.size());
        assertEquals(4,(int)list.getLast());

        CustomLinkedList<Integer>arr=new CustomLinkedList<>();
        assertThrows(NoSuchElementException.class, arr::removeFirst);
        assertThrows(NoSuchElementException.class, arr::removeLast);
    }

    @Test
    void testRemoveIndex(){
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        assertEquals(5,list.size());
        list.remove(1);
        assertEquals(4,list.size());
        assertEquals(3,(int)list.get(1));

        assertThrows(IndexOutOfBoundsException.class,()->list.remove(11));
    }

    @Test
    void testGetFirstLast(){
        string_list.add("First");
        string_list.add("Second");
        string_list.add("Last");
        assertEquals(3,string_list.size());

        assertEquals("First",string_list.getFirst());

        assertEquals("Last",string_list.getLast());

        CustomLinkedList<Integer>arr=new CustomLinkedList<>();
        assertThrows(NoSuchElementException.class,arr::getFirst);
        assertThrows(NoSuchElementException.class,arr::getLast);
    }

    @Test
    void testGetIndex() {
        string_list.add("First");
        string_list.add("Second");
        string_list.add("Last");
        assertEquals(3,string_list.size());

        assertEquals("Second",string_list.get(1));
        assertThrows(IndexOutOfBoundsException.class,()->string_list.get(5));
    }

    @Test
    void testSet(){
        string_list.add("First");
        string_list.add("Second");
        string_list.add("Last");

        assertEquals(3,string_list.size());
        string_list.set(2,"Third");

        assertEquals("Third",string_list.get(2));
        assertThrows(IndexOutOfBoundsException.class,()->string_list.get(5));
    }

    @Test
    void testIndexOf(){
        string_list.add("First");
        string_list.add("Second");
        assertEquals(2,string_list.size());

        assertEquals(1,string_list.indexOf("Second"));

        assertEquals(-1,string_list.indexOf("Hello"));
    }

}
