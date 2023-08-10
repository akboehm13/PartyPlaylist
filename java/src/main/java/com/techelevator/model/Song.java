package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Song {
    @JsonProperty("song_id")
    private int id;
    private String title;
    private String artist;
    private String genre;
    private String duration;

    private String audioURL;

    private String imageURL;

    public Song() { }

    public Song(int id, String title, String artist, String genre, String duration, String audioURL, String imageURL) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.audioURL = audioURL;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
