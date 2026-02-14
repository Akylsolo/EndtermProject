package com.example.endterm.utils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class CacheManager {
    private static CacheManager instance;
    private final Map<String, Object> cache = new ConcurrentHashMap<>();
    private CacheManager(){}
    public static synchronized CacheManager getInstance(){
        if(instance == null){
            instance = new CacheManager();
        }
        return instance;
    }
    public void put(String key, Object value){
        cache.put(key, value);
    }
    public Object get(String key){
        return cache.get(key);
    }
    public void remove(String key){
        cache.remove(key);
    }
    public void clear(){
        cache.clear();
    }
}
