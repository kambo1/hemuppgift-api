package com.cygni.hemuppgiftdemo.model;

import java.util.ArrayList;

public class Artist {

    private String name;
    private String country;
    private String type;

    private String description;

    private ArrayList<Album> albums;

    public Artist(String name, String country, String type, String description, ArrayList<Album> albums) {
        this.name = name;
        this.country = country;
        this.type = type;
        this.description = description;
        this.albums = albums;
    }

    public Artist(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", albums=" + albums +
                '}';
    }
}
