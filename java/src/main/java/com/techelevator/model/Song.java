package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Song {
    @JsonProperty("song_id")
    private int id;
    private String title;
    private String artist;
    private String genre;
    private String duration;

    public Song() { }

    public Song(int id, String title, String artist, String genre, String duration){
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String toString(){
       return "Song{" + "id=" + id +
               ", title= " + title +
               "artist= " + artist +
               "genre= " + genre +
               "duration= " + duration +
               "}";
    }
}
