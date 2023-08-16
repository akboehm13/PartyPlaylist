package com.techelevator.model;

public class Playlist {
    //create an instance variable for each column in the table
    //make constructor, getter, and setter
    //create the dao and all possible methods
    //for the API we don't need authentication
    //when searching for playlist songs you'll need to get the event id (parameter) to find playlist id to get all the songs.
        private int playlistId;
        private int eventId;
        private String name;

        public Playlist(int playlistId, int eventId, String name) {
            this.playlistId = playlistId;
            this.eventId = eventId;
            this.name = name;
        }

        public Playlist() {

        }

    // Getters and Setters
        public int getPlaylistId() {
            return playlistId;
        }

        public void setPlaylistId(int playlistId) {
            this.playlistId = playlistId;
        }

        public int getEventId() {
            return eventId;
        }

        public void setEventId(int eventId) {
            this.eventId = eventId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //these are methods called in the JDBC but maybe getId needs to be event ID or playlist ID
        public int getId() {
            return 0;
        }

        public void setId(int playlistId) {
        }
}
