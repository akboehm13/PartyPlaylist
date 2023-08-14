package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalTime;

public class JdbcEventDaoTest extends BaseDaoTests {

    protected final static Event event1 = new Event(1,"Event Test One",1,2,"1/1/2024","New Year's Day", LocalTime.MIN,LocalTime.MIDNIGHT,
            "810 Matson Place Unit 1205, Cincinnati, OH 45204");

    private JdbcEventDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcEventDao(jdbcTemplate);
    }

    @Test
    public void getEventById_ReturnsNullWhenNonexistent() {
        sut.getEventById(0);
    }

}
