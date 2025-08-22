package com.saurabh.CustomDataStructure.Maps;

public class CustomeHashMap <K,V>{

    static class Map <K,V>{
        final K key;
        V value;
        Map<K,V> next;

        public Map(K key, V value,Map<K,V> next) {
            this.key=key;
            this.value=value;
            this.next=next;
        }
    }
    private static final int DEFAULT_CAPACITY=16;

    private static final float DEFAULT_LOAD_FACTOR=0.65f;

    private int capacity;
    private float loadFactor;
    private int size;

    /**
     * Bucket array
     */
    private Map<K,V> table[];

    public CustomeHashMap(){
        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
    }
    public CustomeHashMap(int capacity,float loadFactor){
       this.capacity=capacity;
       this.loadFactor=loadFactor;
       this.table =(Map<K,V>[]) new Map[capacity];
    }

    private int hash(K key){
        return key.hashCode()%capacity;
    }

    public void put(K key, V value){
        int index=hash(key);
        Map<K,V>node= table[index];
        while (node!=null){
            if (node.key.equals(key)){
                node.value=value;
                return;
            }
            node=node.next;
        }
        Map<K,V>newNode=new Map<>(key,value,table[index]);
        table[index]=newNode;
        size++;
        if (size>capacity * loadFactor){
            resize();
        }
    }
    private void resize(){
        int newCap=capacity*2;
        Map<K,V>newTable[]=(Map<K, V>[]) new Map[capacity];
        for (int i=0;i<capacity;i++){
            Map<K,V>node= table[i];
            while (node!=null){
                Map<K,V>next=node.next;
                int index=hash(node.key);
                node.next=newTable[index];
                newTable[index]=node;
                node=next;
            }
        }
        table =newTable;
        capacity=newCap;
    }

    public V get(K key){
        int index=hash(key);
        Map<K,V>node=table[index];
        while (node!=null){
            if (node.key.equals(key)){
                return node.value;
            }
            node=node.next;
        }
        return null;
    }

    public void remove(K key){
        if (key==null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index=hash(key);
        Map<K,V>node=table[index];
        Map<K,V>prev=null;
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

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }


}
