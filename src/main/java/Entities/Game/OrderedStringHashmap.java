package Entities.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderedStringHashmap<V> extends HashMap<String,V> {

    ArrayList<String> keyList = new ArrayList<>();

    @Override
    public V put(String key, V value){
        keyList.add(key);
        return super.put(key,value);
    }

    public String[] getKeys(){
        String[] array = new String[keyList.size()];
        for (int i = 0; i<array.length; i++){
            array[i] = keyList.get(i);
        }
        return array;
    }

}
