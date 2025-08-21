package com.saurabh.CustomDataStructure;

import com.saurabh.CustomDataStructure.Tree.BinarySearchTree;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer>intBST;
    private BinarySearchTree<String>stringBST;

    @BeforeEach
    void initialize(){
        intBST=new BinarySearchTree<>();
        stringBST=new BinarySearchTree<>();
    }

    @AfterEach
    void drop(){
        intBST=null;
        stringBST=null;
    }


    @Nested
    class TestInsertion{
        @Test
        @DisplayName("Insert single elements")
        void testSingleElement() {
            intBST.insert(50);
            assertFalse(intBST.isEmpty());
            assertEquals(1, intBST.size());
            assertTrue(intBST.contains(50));
        }

        @Test
        @DisplayName("Insert multiple elements")
        void testMultipleElements() {
            intBST.insert(50);
            intBST.insert(30);
            intBST.insert(70);
            intBST.insert(20);
            intBST.insert(40);

            assertEquals(5, intBST.size());
            assertTrue(intBST.contains(50));
            assertTrue(intBST.contains(30));
            assertTrue(intBST.contains(70));
        }


        @Test
        @DisplayName("Insert duplicate elements")
        void testDuplicateElements() {
            intBST.insert(50);
            intBST.insert(50);
            intBST.insert(50);

            assertEquals(1, intBST.size());
            assertTrue(intBST.contains(50));
        }

        @Test
        @DisplayName("Insert null value throws exception")
        void testNullValue() {
            assertThrows(IllegalArgumentException.class, () -> {
                intBST.insert(null);
            });
        }

        @Test
        @DisplayName("Insert string elements")
        void testInsertStringElements() {
            stringBST.insert("dog");
            stringBST.insert("cat");
            stringBST.insert("elephant");

            assertEquals(3, stringBST.size());
            assertTrue(stringBST.contains("dog"));
            assertTrue(stringBST.contains("cat"));
            assertTrue(stringBST.contains("elephant"));
        }
    }

    @Nested
    @DisplayName("Search Tests")
    class TestSearch{
        @BeforeEach
        void setUpTree() {
            intBST.insert(50);
            intBST.insert(30);
            intBST.insert(70);
            intBST.insert(20);
            intBST.insert(40);
            intBST.insert(60);
            intBST.insert(80);
        }

        @Test
        @DisplayName("Search existing elements")
        void testSearchExistingElements() {
            assertTrue(intBST.contains(50));
            assertTrue(intBST.contains(30));
            assertTrue(intBST.contains(70));
            assertTrue(intBST.contains(20));
            assertTrue(intBST.contains(80));
        }

        @Test
        @DisplayName("Search non-existing elements")
        void testSearchNonExistingElements() {
            assertFalse(intBST.contains(25));
            assertFalse(intBST.contains(90));
            assertFalse(intBST.contains(15));
            assertFalse(intBST.contains(100));
        }

        @Test
        @DisplayName("Search in empty tree")
        void testSearchEmptyTree() {
            BinarySearchTree<Integer> emptyBST = new BinarySearchTree<>();
            assertFalse(emptyBST.contains(50));
        }

        @Test
        @DisplayName("Search null value returns false")
        void testSearchNullValue() {
            assertFalse(intBST.contains(null));
        }
    }
    @Nested
    @DisplayName("Deletion Tests")
    class TestDeletion{

        @BeforeEach
        void setUpTree() {
            intBST.insert(50);
            intBST.insert(30);
            intBST.insert(70);
            intBST.insert(20);
            intBST.insert(40);
            intBST.insert(60);
            intBST.insert(80);
        }

        @Test
        @DisplayName("Delete leaf node")
        void testDeleteLeafNode() {
            intBST.remove(20);
            assertFalse(intBST.contains(20));
            assertEquals(6, intBST.size());
        }

        @Test
        @DisplayName("Delete node with one child")
        void testDeleteNodeWithOneChild() {
            intBST.insert(25);
            intBST.remove(20);

            assertFalse(intBST.contains(20));
            assertTrue(intBST.contains(25));
            assertEquals(7, intBST.size());
        }

        @Test
        @DisplayName("Delete node with two children")
        void testDeleteNodeWithTwoChildren() {
            intBST.remove(30);

            assertFalse(intBST.contains(30));
            assertTrue(intBST.contains(20));
            assertTrue(intBST.contains(40));
            assertEquals(6, intBST.size());
        }

        @Test
        @DisplayName("Delete root node")
        void testDeleteRootNode() {
            intBST.remove(50);

            assertFalse(intBST.contains(50));
            assertTrue(intBST.remove(30));
            assertTrue(intBST.remove(70));
            assertEquals(4, intBST.size());
        }
    }


    @Nested
    @DisplayName("Min/Max Tests")
    class TestMinMax{
        @Test
        @DisplayName("Find min and max in populated tree")
        void testFindMinMaxPopulatedTree() {
            intBST.insert(50);
            intBST.insert(30);
            intBST.insert(70);
            intBST.insert(20);
            intBST.insert(80);

            assertEquals(Integer.valueOf(20), intBST.findMin());
            assertEquals(Integer.valueOf(80), intBST.findMax());
        }

        @Test
        @DisplayName("Find min and max in empty tree")
        void testFindMinMaxEmptyTree() {
            assertNull(intBST.findMin());
            assertNull(intBST.findMax());
        }

        @Test
        @DisplayName("Find min and max with strings")
        void testFindMinMaxStrings() {
            stringBST.insert("dog");
            stringBST.insert("cat");
            stringBST.insert("elephant");
            stringBST.insert("ant");

            assertEquals("ant", stringBST.findMin());
            assertEquals("elephant", stringBST.findMax());
        }
    }

    @Nested
    @DisplayName("Size and Height Tests")
    class TestHeight{
        @Test
        @DisplayName("Size of empty tree")
        void testEmptyTreeSize() {
            assertEquals(0, intBST.size());
        }

        @Test
        @DisplayName("Size after insertions")
        void testSizeAfterInsertions() {
            assertEquals(0, intBST.size());

            intBST.insert(50);
            assertEquals(1, intBST.size());

            intBST.insert(30);
            intBST.insert(70);
            assertEquals(3, intBST.size());

            intBST.insert(30);
            assertEquals(3, intBST.size());
        }

        @Test
        @DisplayName("Height of empty tree")
        void testEmptyTreeHeight() {
            assertEquals(-1, intBST.height());
        }

        @Test
        @DisplayName("Height of single node tree")
        void testSingleNodeHeight() {
            intBST.insert(50);
            assertEquals(0, intBST.height());
        }
    }

}
