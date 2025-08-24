package com.saurabh.CustomDataStructure.Maps;


import com.saurabh.CustomDataStructure.Utils.Node;

import java.util.Map;
import java.util.Objects;

public class CustomeHashMap <K,V>{
    /**
     * Utility class for hashmap
     * @param <K> key
     * @param <V> value
     */
   static class MapNode<K,V> {
       final K key;
       V value;
       MapNode<K, V> next;

       public MapNode(K key, V value, MapNode<K, V> next) {
           this.key = key;
           this.value = value;
           this.next = next;
       }
   }

    /**
     * Default capacity if not provided
     */
    private static final int DEFAULT_CAPACITY=16;

    private static final float DEFAULT_LOAD_FACTOR=0.65f;

    private int capacity;

    private float loadFactor;

    /**
     * number of elements in map
     */
    private int size;

    /**
     * Bucket array
     */
    private MapNode<K,V> table[];

    public CustomeHashMap(){
        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
    }

    public CustomeHashMap(int capacity,float loadFactor){
        if (capacity<=0 || loadFactor<=0.0){
           throw new IllegalArgumentException("capacity and loadfactor cannot be zero or less than zero");
        }
            this.capacity = capacity;
            this.loadFactor = loadFactor;
            this.table = (MapNode<K, V>[]) new MapNode[capacity];
    }

    public CustomeHashMap(int capacity){
        this(capacity,DEFAULT_LOAD_FACTOR);
    }

    public CustomeHashMap(float loadFactor){
        this(DEFAULT_CAPACITY,loadFactor);
    }

    /**
     * compute hash for specified key and convert to valid index
     * @param key
     * @return  valid index
     */
    private int hash(K key){
        return key.hashCode()%capacity;
    }

    /**
     * Insert specified element with key and value.Collusion is prevented using
     * channing method
     * @param key
     * @param value
     */
    public void put(K key, V value){
        if (key==null){
            throw  new IllegalArgumentException("Key cannot be null");
        }
        if (size>capacity * loadFactor){
            resize();
        }
        int index=hash(key);
        MapNode<K,V>node= table[index];
        while (node!=null){
            if (node.key.equals(key)){
                node.value=value;
                return;
            }
            node=node.next;
        }
        MapNode<K,V>newNode=new MapNode<>(key,value,table[index]);
        table[index]=newNode;
        size++;
    }

    /**
     * Resize if map is full
     */
    private void resize(){
        int newCap=capacity*2;
        MapNode<K,V>newTable[]=(MapNode<K, V>[]) new MapNode[capacity];
        for (int i=0;i<capacity;i++){
            MapNode<K,V>node= table[i];
            while (node!=null){
                MapNode<K,V>next=node.next;
                int index=hash(node.key);
                node.next=newTable[index];
                newTable[index]=node;
                node=next;
            }
        }
        table =newTable;
        capacity=newCap;
    }

    /**
     * Retrieve element with specified key
     * @param key
     * @return value corresponding to specified key
     */
    public V get(K key){
        if (key==null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index=hash(key);
        MapNode<K,V>node=table[index];
        while (node!=null){
            if (node.key.equals(key)){
                return node.value;
            }
            node=node.next;
        }
        return null;
    }

    /**
     * Removes particular element with specified key
     * @param key
     */
    public void remove(K key){
        if (key==null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index=hash(key);
        MapNode<K,V>node=table[index];
        MapNode<K,V>prev=null;
        while (node!=null){
            if (node.key.equals(key)){
               if (prev==null){
                   table[index]=node.next;
               }else {
                   prev.next=node.next;
               }
               size--;
               return;
            }
            prev=node;
            node=node.next;
        }
    }

    /**
     *
     * @return number of element in map
     */
    public int size(){
        return size;
    }

    /**
     *
     * @return true if map is empty
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * @return how much element does map stores.Increases if full
     */
    public int capacity(){
        return capacity;
    }

    public boolean containsKey(K key){
        if (key==null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index=hash(key);
        MapNode<K,V>current=table[index];
        while (current!=null){
            if (Objects.equals(current.key,key)){
                return true;
            }
            current=current.next;
        }
        return false;
    }

    public boolean containsValue(V value){
        if (value==null){
            throw new IllegalArgumentException("Value cannot be null");
        }
        for (MapNode<K,V> node:table){
            while (node!=null){
                if (Objects.equals(node.value,value)){
                    return true;
                }
                node=node.next;
            }
        }
        return false;
    }

}
