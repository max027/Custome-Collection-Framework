package com.saurabh.CustomDataStructure;

import com.saurabh.CustomDataStructure.Maps.CustomeHashMap;

public class App
{
    public static void main( String[] args )
    {
        CustomeHashMap<Integer,Integer>map=new CustomeHashMap<>(1,1.0f);
        map.put(1,1);
        map.put(3,2);
        System.out.println(map.containsValue(2));

    }
}
