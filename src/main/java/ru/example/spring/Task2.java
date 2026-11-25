package ru.example.spring;

import java.util.HashMap;
import java.util.Map;

public class Task2 {
    public <K> Map<K, Integer> count(K[] arr) {
        Map<K, Integer> map = new HashMap<>();

        for (K k : arr) {
            if (map.containsKey(k)) {
                int val = map.get(k);
                val++;
                map.put(k, val);
            } else map.put(k, 1);
        }
        return map;
    }
}
