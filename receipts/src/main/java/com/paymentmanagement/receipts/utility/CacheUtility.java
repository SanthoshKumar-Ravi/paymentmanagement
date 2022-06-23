package com.paymentmanagement.receipts.utility;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class CacheUtility<T> {

    private Cache<String, T> cache;
    static final Logger log = LoggerFactory.getLogger(CacheUtility.class);

    //Constructor to build Cache Store
    public CacheUtility(Integer cacheSize, Integer cacheDuration) {
        cache = CacheBuilder.newBuilder().maximumSize(cacheSize).expireAfterWrite(cacheDuration, TimeUnit.MINUTES).build();
    }

    //Method to fetch previously stored record using record key
    public T get(String key) {
        return cache.getIfPresent(key);
    }

    //Method to put a new record in Cache Store with record key
    public void add(String key, T value) {
        if(key != null && value != null) {
            cache.put(key, value);
            log.info("Record stored in Cache with Key = {}" ,key);
        }
    }
}

