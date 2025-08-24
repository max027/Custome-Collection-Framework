package com.saurabh.CustomDataStructure;
import com.saurabh.CustomDataStructure.linear.DynamicArrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class Dynamic_ArrayTest {
    private DynamicArrays<Integer> arr;
    private DynamicArrays<String> arr2;

    @BeforeEach
    void initialize(){
        arr=new DynamicArrays<>();
        arr2=new DynamicArrays<>();
    }

    @AfterEach
    void drop(){
        arr=null;
        arr2=null;
    }

    @DisplayName("test default constructor")
    @Test
    void testDefault(){
        assertEquals(0,arr.size());
        assertTrue(arr.isEmpty());
        assertEquals(10,arr.capacity());
    }

    @DisplayName("test constructor with specified capacity")
    @Test
    void testConstructorWithValidCapacity() {
        DynamicArrays<String> customArray = new DynamicArrays<>(5);
        assertEquals(0, customArray.size());
        assertEquals(5, customArray.capacity());
    }

    @DisplayName("test constructor with zero capacity")
    @Test
    void testConstructorWithZeroCapacity() {
        DynamicArrays<String> zeroArray = new DynamicArrays<>(0);
        assertEquals(0, zeroArray.size());
        assertEquals(0, zeroArray.capacity());
    }

    @DisplayName("test constructor with negative capacity")
    @Test
    void testConstructorWithNegativeCapacity() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DynamicArrays<String>(-1)
        );
        assertEquals("cannot create arrays with capacity -1", exception.getMessage());
    }

    @DisplayName("test insertion and removel of element")
    @Test
    void testAddRemoveElements(){
        arr.add(1);
        arr.add(2);
        assertEquals(2, arr.size());
        arr.add(3);
        assertEquals(3, arr.size());
        arr.remove();
        assertEquals(2, arr.size());
        arr.remove(0);
        assertEquals(1, arr.size());

        arr2.add("Hello");
        arr2.add("World");
        assertEquals(2, arr2.size());
        arr2.remove(0);
        assertEquals(1, arr2.size());
    }

    @DisplayName("test null case")
    @Test
    void testNull(){
        assertTrue(arr.add(null));
        assertEquals(1,arr.size());
        assertNull(arr.get(0));
    }

    @DisplayName("test retrieval at specified index")
    @Test
    void testIndex(){
        arr2.add("Hello");
        assertEquals("Hello",arr2.get(0));
    }

    @DisplayName("test valid index")
    @Test
    void testInvalidIndex(){
        arr2.add("World");
        assertThrows(IndexOutOfBoundsException.class,()->arr2.get(1));
        assertThrows(IndexOutOfBoundsException.class,()->arr2.get(100));
    }

    @DisplayName("test array growth")
    @Test
    void testArrayGrowth(){
        DynamicArrays<Integer> arr=new DynamicArrays<>(2);
        assertEquals(2,arr.capacity());
        arr.add(1);
        arr.add(2);
        arr.add(3);
        assertTrue(arr.capacity()>2);
    }

    @DisplayName("test insertion at middle of array")
    @Test
    void testInsertMiddle(){
        DynamicArrays<String>arr=new DynamicArrays<>();
        arr.add("First");
        arr.add("Third");
        arr.add(1,"Second");
        assertEquals(3,arr.size());
        assertEquals("Second",arr.get(1));
    }

    @DisplayName("test Deletion")
    @Test
    void testRemove(){
        DynamicArrays<String>arr=new DynamicArrays<>();
        arr.add("First");
        arr.add("Second");
        arr.add("Third");

        assertEquals(3,arr.size());
        arr.remove();
        assertEquals(2,arr.size());
        assertEquals("Second",arr.get(arr.size()-1));

        arr.remove();
        assertEquals(1,arr.size());
        assertEquals("First",arr.get(arr.size()-1));
    }

    @DisplayName("test Deletion of element at specified index")
    @Test
    void testRemoveByIndex(){
        DynamicArrays<String>arr=new DynamicArrays<>();
        arr.add("First");
        arr.add("Second");
        arr.add("Third");
        assertEquals(3,arr.size());
        arr.remove(1);
        assertEquals(2,arr.size());
        assertEquals("Third",arr.get(1));
    }

    @DisplayName("test replace of specified element")
    @Test
    void testSet(){
        DynamicArrays<String>arr=new DynamicArrays<>();
        arr.add("First");
        arr.add("Second");
        arr.add("Third");
        assertEquals(3,arr.size());


        arr.set(0,"Hello");
        assertEquals("Hello",arr.get(0));


        arr.set(2,"World");
        assertEquals("World",arr.get(2));
    }
}
