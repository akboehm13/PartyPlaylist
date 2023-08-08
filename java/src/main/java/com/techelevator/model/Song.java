package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Song {
    @JsonProperty("song_id")
    private int id;
    private String title;
    private String artist;
    private String genre;
    private String durationStr;
    private BigDecimal duration = new BigDecimal(durationStr);

    public Song() { }

    public Song(int id, String title, String artist, String genre, String durationStr){
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.durationStr = durationStr;
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

    public String getDurationStr() {
        return durationStr;
    }

    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
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
