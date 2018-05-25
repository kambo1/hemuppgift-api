package com.cygni.hemuppgiftdemo.service;



import com.cygni.hemuppgiftdemo.model.Artist;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class CacheService {

    private static Map<String, Artist> store = new HashMap<String, Artist>();


    @Cacheable(value = "artist", key = "#mbid")
    public Artist get(String mbid) {
        Artist artist = JsonParser.parse(mbid);

        return artist;
    }

    @CacheEvict(value = "artist", key = "#mbid")
    public void evict(String mbid) {
    }
}