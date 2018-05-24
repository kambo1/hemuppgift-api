package com.cygni.hemuppgiftdemo.service;


import com.cygni.hemuppgiftdemo.model.Album;
import com.cygni.hemuppgiftdemo.model.Artist;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    private static final String URL_SOURCE_START = "http://musicbrainz.org/ws/2/artist/";
    private static final String URL_SOURCE_END = "?&fmt=json&inc=url-rels+release-groups";
    private static final String URL_START_WIKI = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=";

    private static String url;


    public JsonParser() {
    }

    public static Artist parse(String MBID) {
        url = URL_SOURCE_START + MBID + URL_SOURCE_END;
        Artist artist = new Artist();
        ArrayList<Album> albums = new ArrayList<>();

        try {
            JSONObject musicBrainz = JsonReader.readJsonFromUrl(url);
            artist.setName(musicBrainz.getString("name"));
            artist.setCountry(musicBrainz.getString("country"));
            artist.setType(musicBrainz.getString("type"));


            JSONArray albumsJson = musicBrainz.getJSONArray("release-groups");
            albums = getAlbums(albumsJson);
            artist.setAlbums(albums);

            JSONArray relationsList = musicBrainz.getJSONArray("relations");
            artist.setDescription(getWikiDescription(relationsList));

            return artist;

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("URL NOT FOUND");
        }
        return artist;
    }

    /**
     * Method for parsing out the wikipedia description from the Json
     * @param relationsList
     * @return Wikipedia Description
     * @throws JSONException
     * @throws IOException
     */
    private static String getWikiDescription(JSONArray relationsList) throws JSONException, IOException{
        String wikiArtist;
        String wikiDescription = "";
        for (int i = 0; i < relationsList.length(); i++) {
            JSONObject obj = relationsList.getJSONObject(i);
            if (obj.getString("type").equals("wikipedia")) {
                String wikiUrl = obj.getJSONObject("url").getString("resource");
                wikiArtist = urlRegex(wikiUrl);
                JSONObject wikiObj = JsonReader.readJsonFromUrl(URL_START_WIKI + wikiArtist);
                JSONObject pages = wikiObj.getJSONObject("query").getJSONObject("pages");

                JSONArray pageElements = pages.names();

                for (int j = 0; j < pageElements.length(); j++) {
                    wikiDescription = pages.getJSONObject(pageElements.get(j).toString()).getString("extract");

                }
            }
        }
        return wikiDescription;
    }

    /**
     * method for parsing out all album objects with covers
     * @param albumsJson
     * @return Arraylist of albums
     * @throws JSONException
     */
    private static ArrayList<Album> getAlbums(JSONArray albumsJson) throws JSONException {
        ArrayList<Album> albums = new ArrayList<>();
        for (int i = 0; i < albumsJson.length(); i++) {
            JSONObject obj = albumsJson.getJSONObject(i);

            Album album = new Album(obj.getString("title"),
                    obj.getString("first-release-date"),
                    obj.getString("id"));

            String id = obj.getString("id");
            String coverUrl = "http://coverartarchive.org/release-group/" + id + "/front";
            //album.setCoverUrl(coverUrl);

            if (httpRequestCheck(coverUrl) == true) {
                album.setCoverUrl(coverUrl);
            } else {
                album.setCoverUrl("URL not found");
            }

            albums.add(album);
        }
        return albums;
    }

    /**
     * Regular expression method to filter the wikipedia url for the wiki api
     * @param url
     * @return
     */
    private static String urlRegex(String url) {
        // String to be scanned to find the pattern.

        String pattern = "([^\\/]*$)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(url);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            return m.group(0);
            //System.out.println("Found value: " + m.group(1) );
        } else {
            System.out.println("NO MATCH");
            return "";
        }
    }

    /**
     * Checks the http request for the right statuscode
     * @param url
     * @return
     */
    private static boolean httpRequestCheck(String url) {
        try {
            URL test = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) test.openConnection();
            huc.setInstanceFollowRedirects(false);
            huc.setRequestMethod("GET");


            huc.connect();

            //System.out.println("RESPONSE CODE : "+huc.getResponseCode());
            if (huc.getResponseCode() != HttpURLConnection.HTTP_NOT_FOUND && huc.getResponseCode() != HttpURLConnection.HTTP_BAD_REQUEST) {
                return true;

            } else {
                return false;
            }
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
