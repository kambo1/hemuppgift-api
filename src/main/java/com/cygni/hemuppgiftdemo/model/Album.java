package com.cygni.hemuppgiftdemo.model;

public class Album {

    private String name;
    private String releaseDate;
    private String albumMbid;
    private String coverUrl;

    public Album(String name, String releaseDate, String albumMbid) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.albumMbid = albumMbid;
        this.coverUrl = coverUrl;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getAlbumMbid() {
        return albumMbid;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", albumMbid='" + albumMbid + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                '}';
    }
}
