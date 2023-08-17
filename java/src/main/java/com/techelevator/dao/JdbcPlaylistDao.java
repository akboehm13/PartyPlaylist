package com.techelevator.dao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Event;
import com.techelevator.model.Playlist;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlaylistDao implements PlaylistDao {
        private final JdbcTemplate jdbcTemplate;

        public JdbcPlaylistDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }


        public List<Playlist> getPlaylistsByEventId(int eventId) {
            List<Playlist> playlists = new ArrayList<>();
            String sql = "SELECT * FROM playlist WHERE event_id = ?";

            try {
                SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
                while (results.next()) {
                    Playlist playlist = mapRowToPlaylist(results);
                    playlists.add(playlist);
                }
            } catch(CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);

            }
            return playlists;
        }


        public List<Playlist> getAllPlaylists() {
            List<Playlist> playlists = new ArrayList<>();
            String sql = "SELECT * FROM playlist";

            try {
                SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
                while (results.next()) {
                    Playlist playlist = mapRowToPlaylist(results);
                    playlists.add(playlist);
                }
            } catch(CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);

            }
            return playlists;
        }


        public Playlist getPlaylistById(int id) {
            Playlist playlist = null;
            String sql = "SELECT * FROM playlist " + "WHERE playlist_id = ?;";

            try {
                SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
                if (result.next()) {
                    playlist = mapRowToPlaylist(result);
                }
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            }
            return playlist;
        }


        public Playlist updatePlaylist(Playlist playlist) {
            Playlist newPlaylist = null;

            String sql = "UPDATE playlist SET name = ?, event_id =? " + "WHERE playlist_id = ?";

            try {
                jdbcTemplate.update(sql,playlist.getName(),playlist.getEventId(),playlist.getPlaylistId());
                newPlaylist = getPlaylistById(playlist.getEventId());
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Cannot connect to database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
            return playlist;
        }


        public Playlist createPlaylist(Playlist playlist) {
            Playlist newPlaylist;
            String sql = "INSERT INTO playlist (event_id, name) VALUES(?,?) RETURNING playlist_id;";

            try {
                int id = jdbcTemplate.queryForObject(sql,Integer.class,playlist.getEventId(),playlist.getName());
                newPlaylist = getPlaylistById(id);
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
            return newPlaylist;
        }

        private Playlist mapRowToPlaylist(SqlRowSet rowSet){
            Playlist playlist = new Playlist();

            playlist.setPlaylistId(rowSet.getInt("playlist_id"));
            playlist.setEventId(rowSet.getInt("event_id"));
            playlist.setName(rowSet.getString("name"));

            return playlist;
        }

}
