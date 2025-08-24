package com.saurabh.CustomDataStructure;

import com.saurabh.CustomDataStructure.Maps.CustomeHashMap;
import org.junit.jupiter.api.AfterEach;
import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CustomeHashMapTest {
    CustomeHashMap<Integer,String>map1;
    CustomeHashMap<String,Integer>map2;
    @BeforeEach
    void initialize(){
        map1=new CustomeHashMap<>();
        map2=new CustomeHashMap<>();
    }
    @AfterEach
    void drop(){
        map1=null;
        map2=null;
    }

    @DisplayName("test constructor with default capacity")
    @Test
    void testConstructorWithDefaultCap(){
        CustomeHashMap<Integer,Integer>test=new CustomeHashMap<>();
        assertEquals(16,test.capacity());
        assertEquals(0,test.size());
    }

    @DisplayName("test constructor with specified capacity")
    @Test
    void testConstructorWithSpecifiedCap(){
        CustomeHashMap<Integer,Integer>test=new CustomeHashMap<>(10);
        assertEquals(10,test.capacity());
        assertEquals(0,test.size());
    }

    @DisplayName("test constructor with capacity zero or less than zero")
    @Test
    void testEdge(){
        CustomeHashMap<Integer,Integer>test;
        assertThrows(IllegalArgumentException.class,()->new CustomeHashMap<>(0));
        assertThrows(IllegalArgumentException.class,()->new CustomeHashMap<>(0.0f));

        assertThrows(IllegalArgumentException.class,()->new CustomeHashMap<>(-1));
        assertThrows(IllegalArgumentException.class,()->new CustomeHashMap<>(-1.0f));
    }
    @Nested
    class TestInsertion{
        @DisplayName("test insertion of single element")
        @Test
        void testSingleItem(){
            map1.put(1,"Hello");
            assertEquals(1,map1.size());
            assertEquals(16,map1.capacity());
            assertEquals("Hello",map1.get(1));
        }
        @DisplayName("test insertion of multiple element")
        @Test
        void testMultipleItem(){
            map1.put(1,"Hello");
            map1.put(2,"Second");
            map1.put(3,"Third");
            map1.put(4,"Fourth");
            assertEquals(4,map1.size());
            assertEquals(16,map1.capacity());

            assertEquals("Third",map1.get(3));
            assertEquals("Fourth",map1.get(4));
        }

        @DisplayName("test insertion of null")
        @Test
        void testNull(){
            assertThrows(IllegalArgumentException.class,()->map1.put(null,null));
        }
    }
}
