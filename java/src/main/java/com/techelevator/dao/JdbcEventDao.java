package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Event;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, name, dj_id, host_id, date, description, start_time, end_time, location FROM \"event\"";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Event event = mapRowToEvent(results);
                events.add(event);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return events;
    }

    @Override
    public Event getEventByName(String name) {
        Event event = null;
        String sql = "SELECT event_id, name, dj_id, host_id, date, description, start_time, end_time, location FROM \"event\" " + "WHERE name = ?";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, name);
            if (result.next()) {
                event = mapRowToEvent(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return event;
    }

    @Override
    public List<Event> getAllEventsByHostID(int hostId) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, name, dj_id, host_id, date, description, start_time, end_time, location FROM \"event\" " + "WHERE host_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, hostId);
            while (results.next()) {
                Event event = mapRowToEvent(results);
                events.add(event);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return events;
    }

    @Override
    public Event getEventById(int eventid) {
        Event event = null;
        String sql = "SELECT event_id, name, dj_id, host_id, date, description, start_time, end_time, location FROM \"event\" " + "WHERE event_id = ?";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, eventid);
            if (result.next()) {
                event = mapRowToEvent(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return event;
    }

    @Override
    public List<Event> getAllEventsByDJID(int DJid) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, name, dj_id, host_id, date, description, start_time, end_time, location FROM \"event\" " + "WHERE dj_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, DJid);
            while (results.next()) {
                Event event = mapRowToEvent(results);
                events.add(event);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return events;
    }

    @Override
    public void createEvent(Event event) {
        String sql = "INSERT INTO \"event\" (name, dj_id, host_id, date, description, start_time, end_time, location) VALUES(?,?,?,?,?,?,?,?);";

        try {
            jdbcTemplate.update(sql,event.getName(),event.getDjId(),event.getHostId(),event.getDate(),
                    event.getDescription(), event.getStartTime(), event.getEndTime(), event.getLocation());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public Event updateEventById(Event event) {
        Event newEvent = null;

        String sql = "UPDATE \"event\" SET name=?, dj_id=?, host_id=?, date=?, description=?, start_time=?, end_time=?, location=?"+
                " WHERE event_id=?;";

        try {
            jdbcTemplate.update(sql,event.getName(),event.getDjId(),event.getHostId(),event.getDate(),
                    event.getDescription(), event.getStartTime(), event.getEndTime(), event.getLocation(), event.getEventId());
            newEvent = getEventById(event.getEventId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newEvent;
    }

    @Override
    public int deleteEventById(int eventId) {
        int numberOfRows = 0;

        String sql = "DELETE FROM event WHERE event_id = ?;";

        try {
            numberOfRows = jdbcTemplate.update(sql, eventId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private Event mapRowToEvent(SqlRowSet rowSet) {
        Event event = new Event();

        event.setEventId(rowSet.getInt("event_id"));
        event.setName(rowSet.getString("name"));
        event.setDjId(rowSet.getInt("dj_id"));
        event.setDescription(rowSet.getString("description"));
        event.setHostId(rowSet.getInt("host_id"));
        event.setDate(rowSet.getString("date"));
        event.setStartTime(rowSet.getTime("start_time").toLocalTime());
        event.setEndTime(rowSet.getTime("end_time").toLocalTime());
        event.setLocation(rowSet.getString("location"));

        return event;
    }
}