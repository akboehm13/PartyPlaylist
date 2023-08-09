package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Song;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSongDao implements SongDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSongDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT song_id, title, artist, genre, duration, song_url, song_art FROM \"song\"";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Song song = mapRowToSong(results);
                songs.add(song);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return songs;
    }

    @Override
    public List<Song> getAllSongsByGenre(String genre) {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT song_id, title, artist, genre, duration, song_url, song_art FROM \"song\" " + "WHERE genre = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genre);
            while (results.next()) {
                Song song = mapRowToSong(results);
                songs.add(song);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return songs;
    }

    @Override
    public Song getSongById(int songId) {
        Song song = null;
        String sql = "SELECT song_id, title, artist, genre, duration, song_url, song_art FROM \"song\" " + "WHERE song_id = ?";

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
    public List<Song> getAllSongsByTitle(String title) {
        Song song = null;
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT song_id, title, artist, genre, duration, song_url, song_art FROM \"song\" " + "WHERE title = ?";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, title);
            if (result.next()) {
                song = mapRowToSong(result);
                songs.add(song);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return songs;
    }
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public void createSong(Song song) {

        String sql = "INSERT INTO \"song\" (title, artist, genre, duration, song_url, song_art) VALUES(?,?,?,?,?,?);";

        try {
            jdbcTemplate.update(sql,song.getTitle(),song.getArtist(),song.getGenre(),song.getDuration(),
                    song.getAudioURL(), song.getImageURL());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public Song updateSong(Song song) {


        return null;
    }

    public int deleteSongById(int songID) {

        int numberOfRows = 0;

        String sql = "DELETE FROM song WHERE song_id = ?;";

        try {
            numberOfRows = jdbcTemplate.update(sql, songID);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;

    }

    private Song mapRowToSong(SqlRowSet rowSet) {
        Song song = new Song();

        song.setId(rowSet.getInt("song_id"));
        song.setTitle(rowSet.getString("title"));
        song.setArtist(rowSet.getString("artist"));
        song.setGenre(rowSet.getString("genre"));
        song.setDuration(rowSet.getString("duration"));
        song.setAudioURL(rowSet.getString("song_url"));
        song.setImageURL(rowSet.getString("song_art"));

        return song;
    }
}
