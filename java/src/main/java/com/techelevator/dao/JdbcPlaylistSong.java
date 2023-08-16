package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Event;
import com.techelevator.model.PlaylistSong;
import com.techelevator.model.Song;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcPlaylistSong implements PlaylistSongDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPlaylistSong(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Song> getSongsByPlaylistId(int id) {
        List<Song> songs = new ArrayList<>();
        String sql = "select s.song_id, title, artist, genre, duration, song_url, song_art " +
                "from playlist_song p " +
                "join song s on p.song_id = s.song_id " +
                "join playlist pl on p.playlist_id = pl.playlist_id " +
                "where pl.playlist_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
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
    public List<PlaylistSong> getAll() {
        List<PlaylistSong> playlists = new ArrayList<>();
        String sql = "select * from playlist_song;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                PlaylistSong playlistSong = mapRowToPlaylistSong(results);
                playlists.add(playlistSong);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return playlists;

    }

    @Override
    public void createPlaylistSong(PlaylistSong playlistSong) {
        String sql = "INSERT INTO playlist_song (playlist_id, song_id) VALUES(?,?);";

        try {
            jdbcTemplate.update(sql,playlistSong.getPlaylistId(), playlistSong.getSongId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private PlaylistSong mapRowToPlaylistSong(SqlRowSet rowSet){
        PlaylistSong playlistSong = new PlaylistSong();

        playlistSong.setPlaylistId(rowSet.getInt("playlist_id"));
        playlistSong.setSongId(rowSet.getInt("song_id"));

        return playlistSong;
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
