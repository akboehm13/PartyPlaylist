package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class Event {

    private int eventId;
    @NotEmpty
    private String name;
    @JsonProperty("dj_id")
    private int djId;
    @JsonProperty("host_id")
    private int hostId;
    @NotEmpty
    private String date;
    @NotEmpty
    private String description;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;
    @NotEmpty
    private String location;

    public Event() { }

    public Event(int eventId, String name, int djId, int hostId, String date, String description, String startTime, String endTime, String location) {
        this.eventId = eventId;
        this.name = name;
        this.djId = djId;
        this.hostId = hostId;
        this.date = date;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public int getDjId() {
        return djId;
    }

    public int getHostId() {
        return hostId;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDjId(int djId) {
        this.djId = djId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
