package com.techelevator.dao;
import com.techelevator.model.Playlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class JdbcPlaylistDao implements PlaylistDao {
        private final JdbcTemplate jdbcTemplate;

        public JdbcPlaylistDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }


        public List<Playlist> getPlaylistsByEventId(int eventId) {
            String sql = "SELECT * FROM playlist WHERE event_id = ?";
            return jdbcTemplate.query(sql, new PlaylistRowMapper(), eventId);
        }


        public List<Playlist> getAllPlaylists() {
            String sql = "SELECT * FROM playlist";
            return jdbcTemplate.query(sql, new PlaylistRowMapper());
        }


        public Playlist getPlaylistById(int id) {
            String sql = "SELECT * FROM playlist WHERE playlist_id = ?";
            return jdbcTemplate.queryForObject(sql, new PlaylistRowMapper(), id);
        }


        public Playlist updatePlaylist(Playlist playlist) {
            String sql = "UPDATE playlist SET name = ? WHERE playlist_id = ?";
            jdbcTemplate.update(sql, playlist.getName(), playlist.getId());
            return getPlaylistById(playlist.getId());
        }


        public Playlist createPlaylist(Playlist playlist) {
            String sql = "INSERT INTO playlist (event_id, name) VALUES (?, ?) RETURNING playlist_id";
            int generatedId = jdbcTemplate.queryForObject(sql, Integer.class, playlist.getEventId(), playlist.getName());
            return getPlaylistById(generatedId);
        }

        private class PlaylistRowMapper implements RowMapper<Playlist> {
            @Override
            public Playlist mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("playlist_id"));
                playlist.setEventId(resultSet.getInt("event_id"));
                playlist.setName(resultSet.getString("name"));
                return playlist;
            }
        }
}
