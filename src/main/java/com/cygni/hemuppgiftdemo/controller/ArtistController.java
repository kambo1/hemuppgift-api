package com.cygni.hemuppgiftdemo.controller;

import com.cygni.hemuppgiftdemo.exception.ArtistNotFoundException;
import com.cygni.hemuppgiftdemo.model.Artist;
import com.cygni.hemuppgiftdemo.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ArtistController {

    @Autowired
    CacheService service;

    @GetMapping("/artist/{mbid}")
    public Artist getAtist(@PathVariable("mbid") String mbid){

        Artist artist = service.get(mbid);
        if (artist.getName() == null){
            throw new ArtistNotFoundException("MBID: "+ mbid);
        }
        return artist;
    }
}
