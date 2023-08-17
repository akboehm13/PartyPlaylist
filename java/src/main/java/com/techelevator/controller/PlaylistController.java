package com.techelevator.controller;
import com.techelevator.dao.JdbcPlaylistDao;
import com.techelevator.dao.PlaylistDao;
import com.techelevator.model.Playlist;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.techelevator.exception.DaoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;


@RestController
@RequestMapping("/playlists")
@CrossOrigin
public class PlaylistController {

        private JdbcPlaylistDao playlistDao;

        public PlaylistController(JdbcPlaylistDao playlistDao) {
            this.playlistDao = playlistDao;
        }

        @GetMapping(path = "")
        public List<Playlist> list(@RequestParam(defaultValue = "0") int eventId) {
            if (eventId != 0) {
                return playlistDao.getPlaylistsByEventId(eventId);
            }
            return playlistDao.getAllPlaylists();
        }

        @GetMapping("/{id}")
        public Playlist get(@PathVariable int id) {
            Playlist playlist = playlistDao.getPlaylistById(id);
            if (playlist == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist Not Found");
            } else {
                return playlist;
            }
        }

        @ResponseStatus(HttpStatus.CREATED)
        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("")
        public void createPlaylist(@Valid @RequestBody Playlist playlist) {
            try {
                playlistDao.createPlaylist(playlist);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Playlist creation failed");
            }
        }
        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/{id}")
        public void deletePlaylist(@PathVariable int id) {
            if (playlistDao.getPlaylistById(id) == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist was not found.");
            }
        }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
        public Playlist updatePlaylist(@Valid @RequestBody Playlist playlist, @PathVariable int id) {
            playlist.setPlaylistId(id);
            try {
                return playlistDao.updatePlaylist(playlist);
            } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found.");
            }
        }
}
