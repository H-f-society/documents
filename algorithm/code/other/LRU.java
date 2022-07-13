package other;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: root
 * @Date: 2022/6/24 9:12
 * @Description: 最近最少使用
 */
public class LRU {

    private int cap;
    private LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
    public LRU(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if(map.keySet().contains(key)) {
            int value = map.get(key);
            map.remove(key);
            map.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.keySet().contains(key)) {
            map.remove(key);
        }else if(map.size() == cap) {
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            iterator.next();
            iterator.remove();
        }
        map.put(key, value);
    }
}
