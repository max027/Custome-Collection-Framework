package com.saurabh.CustomDataStructure.Benchmark;


import com.saurabh.CustomDataStructure.Maps.CustomeHashMap;
import com.saurabh.CustomDataStructure.Tree.BinarySearchTree;
import com.saurabh.CustomDataStructure.linear.CustomLinkedList;
import com.saurabh.CustomDataStructure.linear.DynamicArrays;

import javax.script.AbstractScriptEngine;
import java.util.*;
import java.util.function.Supplier;

public class Benchmark {
    private static final int SMALL_SIZE = 1_000;
    private static final int MEDIUM_SIZE = 10_000;
    private static final int LARGE_SIZE = 100_000;
    private static final int ITERATIONS = 5;


    // Colors for console output
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        Benchmark benchmark=new Benchmark();
        benchmark.arrayBenchmark();
        benchmark.linkedListBenchmark();
        benchmark.TreeBenchmark();
        benchmark.MapBenchmark();

    }

    private void arrayBenchmark(){

        System.out.println("Dynamic Arrays");
        benchmarkArrayAdd("Add operation (Sequential)",MEDIUM_SIZE);
        benchmarkArrayAdd("Add operation (Sequential)",LARGE_SIZE);

        benchmarkArrayRandomAccess("Random Access",MEDIUM_SIZE);

        benchmarkArrayInsert("Insert Element",SMALL_SIZE);

        benchmarkArrayRemove("Remove Element",SMALL_SIZE);
        System.out.println();
    }
    private void benchmarkArrayAdd(String testName, int size){
        System.out.println( testName + " (" + (size) + " elements)" );

        double customeTime=measureTime(()->{
            DynamicArrays<Integer>arr=new DynamicArrays<>();
            for (int i=0;i<size;i++){
                arr.add(i);
            }
            return arr;
        });

        double javaTime=measureTime(()->{
            ArrayList<Integer>arr=new ArrayList<>();
            for (int i=0;i<size;i++){
                arr.add(i);
            }
            return arr;
        });

        printComparasion("Custome Dynamic Array",customeTime,"Array list",javaTime);

    }

    private void benchmarkArrayRandomAccess(String testName, int size){
        System.out.println( testName + " (" + (size) + " elements)" );
        DynamicArrays<Integer> arr1=new DynamicArrays<>();
        ArrayList<Integer>arr2=new ArrayList<>();
        Random random=new Random(42);
        for (int i=0;i<size;i++){
            arr1.add(i);
            arr2.add(i);
        }

        double customeTime=measureTime(()->{
             int sum=0;
            for (int i=0;i<arr1.size();i++){
                int index=random.nextInt(size);
                sum+=arr1.get(index);
            }
            return sum;
        });

        random.setSeed(42);

        double javaTime=measureTime(()->{
            int sum=0;
            for (int i=0;i<arr2.size();i++){
                int index=random.nextInt(size);
                sum+=arr2.get(index);
            }
            return sum;
        });

        printComparasion("Custome Dynamic Array",customeTime,"Array list",javaTime);

    }

    private void benchmarkArrayInsert(String testName,int size){
        System.out.println( testName + " (" + (size) + " elements)" );
        ArrayList<Integer>arr2=new ArrayList<>();

        double customeTime=measureTime(()->{
            DynamicArrays<Integer> arr1=new DynamicArrays<>();
            for (int i=0;i<size;i++){
                arr1.add(0,i);
            }
            return arr1;
        });

        double javaTime=measureTime(()->{
            ArrayList<Integer>arr1=new ArrayList<>();
            for (int i=0;i<size;i++){
                arr1.add(0,i);
            }
            return arr1;
        });
        printComparasion("Custome Dynamic Array",customeTime,"Array list",javaTime);
    }


    private void benchmarkArrayRemove(String testName,int size) {
        System.out.println( testName + " (" + (size) + " elements)" );

        double customeTime=measureTime(()->{
          DynamicArrays<Integer>arr1=new DynamicArrays<>();
          for (int i=0;i<size;i++){
              arr1.add(i);
          }
          while (!arr1.isEmpty()){
              arr1.remove(arr1.size()-1);
          }
          return arr1;
        });
        double javaTime=measureTime(()->{
            ArrayList<Integer>arr1=new ArrayList<>();
            for (int i=0;i<size;i++){
                arr1.add(i);
            }
            while (!arr1.isEmpty()){
                arr1.remove(arr1.size()-1);
            }
            return arr1;
        });
        printComparasion("Custome Dynamic Array",customeTime,"Array list",javaTime);
    }

    private void linkedListBenchmark(){
        System.out.println("Linked List");
        benchmarkLikedListAdd("Add Operation",MEDIUM_SIZE);
        benchmarkLikedListSearch("Search Operation",MEDIUM_SIZE);
        benchmarkLikedListDelete("Delete Operation",MEDIUM_SIZE);
        System.out.println();
    }
    private void benchmarkLikedListAdd(String testName,int size){
        System.out.println( testName + " (" + (size) + " elements)" );

        double customTime=measureTime(()->{
            CustomLinkedList<Integer>list=new CustomLinkedList<>();
            for (int i=0;i<size;i++){
                list.add(i);
            }
            return list;
        });

        double javaTime=measureTime(()->{
            LinkedList<Integer>list=new LinkedList<>();
            for (int i=0;i<size;i++){
                list.add(i);
            }
            return list;
        });

        printComparasion("Custome Linked List",customTime,"Array list",javaTime);
    }

    private void benchmarkLikedListSearch(String testName,int size){
        System.out.println( testName + " (" + (size) + " elements)" );
        CustomLinkedList<Integer>list1=new CustomLinkedList<>();
        LinkedList<Integer>list2=new LinkedList<>();

        for (int i=0;i<size;i++){
            list1.add(i);
            list2.add(i);
        }

        double customTime=measureTime(()->{
            int found =0;
            for (int i = 0; i < size; i++) {
                if (list1.contains(i)){
                    found++;
                }
            }
            return found;
        });

        double javaTime=measureTime(()->{
            int found =0;
            for (int i = 0; i < size; i++) {
                if (list2.contains(i)){
                    found++;
                }
            }
            return found;
        });

        printComparasion("Custome Linked List",customTime,"Array list",javaTime);
    }
    private void benchmarkLikedListDelete(String testName,int size){
        System.out.println( testName + " (" + (size) + " elements)" );
        double customTime=measureTime(()->{
            CustomLinkedList<Integer>list=new CustomLinkedList<>();
            for (int i = 0; i < size; i++) {
               list.add(i);
            }
            while (!list.isEmpty()){
                list.remove(0);
            }
            return list;
        });
        double javaTime=measureTime(()->{
            LinkedList<Integer>list=new LinkedList<>();
            for (int i = 0; i < size; i++) {
                list.add(i);
            }
            while (!list.isEmpty()){
                list.remove(0);
            }
            return list;
        });

        printComparasion("Custome Linked List",customTime,"Array list",javaTime);
    }

    private void TreeBenchmark(){
        System.out.println("Binary Search Tree");
        benchmarkTreeInsertion("Tree Insertion",MEDIUM_SIZE);
        benchmarkTreeSearch("Tree Search Operation",MEDIUM_SIZE);
        System.out.println();
    }

    private void benchmarkTreeInsertion(String testName,int size){
        System.out.println( testName + " (" + (size) + " elements)" );
        Integer[] randomDate=generateRandomArray(size);
        double customTime=measureTime(()->{
            BinarySearchTree<Integer>bst=new BinarySearchTree<>();
            for (int i=0;i<size;i++){
                bst.insert(randomDate[i]);
            }
            return bst;
        });
        double javaTime=measureTime(()->{
            TreeMap<Integer,Integer>bst=new TreeMap<>();
            for (int i=0;i<size;i++){
                bst.put(randomDate[i],randomDate[i]);
            }
            return bst;
        });
        printComparasion("Custome Linked List",customTime,"Array list",javaTime);
    }

    private void benchmarkTreeSearch(String testName,int size) {
        System.out.println(testName + " (" + (size) + " elements)");

        Integer[] randomData=generateRandomArray(size);
        BinarySearchTree<Integer>bst=new BinarySearchTree<>();
        TreeMap<Integer,Integer>bst2=new TreeMap<>();
        for (int i:randomData){
            bst2.put(i,i);
            bst.insert(i);
        }
        double customTime=measureTime(()->{
            int found=0;
            for (int i=0;i<size;i++){
                if (bst.contains(i)){
                    found++;
                }
            }
            return found;
        });
        double javaTime=measureTime(()->{
            int found=0;
            for (int i=0;i<size;i++){
                if (bst2.containsKey(i)){
                    found++;
                }
            }
            return found;
        });
        printComparasion("Custome Linked List",customTime,"Array list",javaTime);
    }

    private void MapBenchmark(){
        System.out.println("Hash Map");
        benchmarkHashMapPut("HashMap put Operation",MEDIUM_SIZE);
        benchmarkHashMapGet("HashMap Get Operation",MEDIUM_SIZE);
        benchmarkHashMapRemove("HashMap remove Operation",MEDIUM_SIZE);
        System.out.println();

    }

    private void benchmarkHashMapPut(String testName,int size){
        System.out.println(testName + " (" + (size) + " elements)");
        double customTime=measureTime(()->{
            CustomeHashMap<Integer,Integer>map=new CustomeHashMap<>(size);
            for (int i=0;i<size;i++){
                map.put(i,i*2);
            }
            return map;
        });

        double javaTime=measureTime(()->{
            HashMap<Integer,Integer>map=new HashMap<>();
            for (int i=0;i<size;i++){
                map.put(i,i*2);
            }
            return map;
        });
        printComparasion("Custome Linked List",customTime,"Array list",javaTime);
    }
    private void benchmarkHashMapGet(String testName,int size){
        System.out.println(testName + " (" + (size) + " elements)");
        CustomeHashMap<Integer,Integer>map1=new CustomeHashMap<>(size);
        HashMap<Integer,Integer>map2=new HashMap<>(size);
        for (int i=0;i<size;i++){
            map1.put(i,i*2);
            map2.put(i,i*2);
        }
        double customTime=measureTime(()->{
            int sum=0;
            for (int i=0;i<size;i++){
                int value=map1.get(i);
                sum+=value;
            }
            return sum;
        });

        double javaTime=measureTime(()->{
            int sum=0;
            for (int i=0;i<size;i++){
                int value=map2.get(i);
                sum+=value;
            }
            return sum;
        });
        printComparasion("Custome Linked List",customTime,"Array list",javaTime);
    }
    private void benchmarkHashMapRemove(String testName,int size){
        System.out.println(testName + " (" + (size) + " elements)");
        CustomeHashMap<Integer,Integer>map1=new CustomeHashMap<>(size);
        HashMap<Integer,Integer>map2=new HashMap<>(size);
        for (int i=0;i<size;i++){
            map1.put(i,i*2);
            map2.put(i,i*2);
        }
        double customTime=measureTime(()->{
            for (int i=0;i<size;i++){
                map1.remove(i);
            }
            return null;
        });
        double javaTime=measureTime(()->{
            for (int i=0;i<size;i++){
                map2.remove(i);
            }
            return null;
        });
        printComparasion("Custome Linked List",customTime,"Array list",javaTime);
    }

    /**
     * Measures execution time of a supplier function
     * Returns average time in milliseconds (with decimal precision)
     */
    private <T> double measureTime(Supplier<T> operation) {
        // Warm up JVM more thoroughly
        for (int i = 0; i < 10; i++) {
            operation.get();
        }

        // Force JIT compilation
        System.gc();
        try { Thread.sleep(100); } catch (InterruptedException e) {}

        long totalTimeNanos = 0;
        int validRuns = 0;

        // Run multiple iterations for better accuracy
        for (int i = 0; i < ITERATIONS * 2; i++) { // Double the iterations
            long startTime = System.nanoTime();
            operation.get();
            long endTime = System.nanoTime();

            long duration = endTime - startTime;
            if (duration > 0) { // Only count positive durations
                totalTimeNanos += duration;
                validRuns++;
            }
        }

        if (validRuns == 0) {
            // If operations are too fast, run them in batch
            return measureTimeBatch(operation);
        }

        // Convert to milliseconds with decimal precision
        double averageTimeNanos = (double) totalTimeNanos / validRuns;
        return Math.max(0.001, averageTimeNanos / 1_000_000.0); // Minimum 0.001ms
    }

    /**
     * Fallback method for extremely fast operations - runs them in batches
     */
    private <T> double measureTimeBatch(Supplier<T> operation) {
        final int BATCH_SIZE = 1000;

        long startTime = System.nanoTime();
        for (int i = 0; i < BATCH_SIZE; i++) {
            operation.get();
        }
        long endTime = System.nanoTime();

        double totalTimeMs = (endTime - startTime) / 1_000_000.0;
        return Math.max(0.001, totalTimeMs / BATCH_SIZE); // Average time per operation
    }


    private static void printComparasion(String customType,double customTime, String javaType,double javaTime){
       double ratio = (double) (customTime/javaTime);
        String  ratioText;

        if (ratio <= 1.0) {
            ratioText = GREEN + "✓ Custom is faster!" + RESET;
        } else if (ratio <= 1.5) {
            ratioText = YELLOW + "~ Competitive performance" + RESET;
        } else if (ratio <= 2.0) {
            ratioText = YELLOW + "⚠ Slightly slower" + RESET;
        } else {
            ratioText = RED + "⚠ Needs optimization" + RESET;
        }

        System.out.printf("  %s%-20s: %7.3f ms%s%n", GREEN,customType, customTime,RESET);
        System.out.printf("  %s%-20s: %7.3f ms%s%n",YELLOW, javaType, javaTime,RESET);
        System.out.printf("  Ratio: %.2fx - %s%n", ratio,ratioText);
        System.out.println();
    }
    /**
     * Generates array with random integers
     */
    private Integer[] generateRandomArray(int size) {
        Random random = new Random(42); // Fixed seed for reproducibility
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 2);
        }
        return array;
    }
}
