package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Song;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcSongDao implements SongDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSongDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Song> getSongs() {
        return null;
    }

    @Override
    public Song getSongById(int songId) {
        Song song = null;
        String sql = "SELECT * FROM \"song\" " + "WHERE song_id = ?";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, songId);
            if (result.next()) {
                song = mapRowToSong(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return song;
    }

    @Override
    public Song getSongByTitle(String title) {
        Song song = null;
        String sql = "SELECT * FROM \"song\" " + "WHERE title = ?";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, title);
            if (result.next()) {
                song = mapRowToSong(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return song;
    }

    private Song mapRowToSong(SqlRowSet rowSet) {
        Song song = new Song();

        song.setId(rowSet.getInt("song_id"));
        song.setTitle(rowSet.getString("title"));
        song.setArtist(rowSet.getString("artist"));
        song.setTitle(rowSet.getString("genre"));
        song.setTitle(rowSet.getString("duration"));

        return song;
    }
}
