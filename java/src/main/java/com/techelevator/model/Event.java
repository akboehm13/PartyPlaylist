package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Event {

    private int eventId;
    private String name;
    private int djId;
    private int hostId;
    private LocalDate date;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event() { }

    public Event(int eventId, String name, int djId, int hostId, LocalDate date, String description, LocalTime startTime, LocalTime endTime) {
        this.eventId = eventId;
        this.name = name;
        this.djId = djId;
        this.hostId = hostId;
        this.date = date;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
