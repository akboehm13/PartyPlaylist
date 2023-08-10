package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Song;

import java.util.List;

public interface EventDao {

    List<Event> getAllEvents();

    List<Event> getEventByName(Event event);

    List<Event> getAllEventsByHostID(int id);

    Event getEventById(int id);

    List<Event> getAllEventsByDJID(int id);

    void createEvent(Event event);

    Event updateEvent(Event event);

    int deleteEventById(int id);
}
