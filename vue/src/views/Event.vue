<template>
  <div class="container">
    <div class="header">
      <h2>Upcoming Event</h2>
      <div class="buttons">
        <button @click="showForm()">Join</button>
        <button @click="showForm()">Invite</button>
      </div>
    </div>

    <div class="description">
      <h5>
        Join us for Caleb's 5th birthday this upcoming Saturday the 4th at 1234
        Arroyo Ln. Pizza and drinks will be provided.
      </h5>
    </div>

    <div class="table-container">
      <table>
        <thead>
          <tr>
            <th>Cover Art</th>
            <th>Name</th>
            <th>Artist</th>
            <th>Genre</th>
            <th>Duration</th>
            <th>
              <button @click="playPlaylist()">Play</button>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="song in filteredSongs" :key="song.id">
            <td>
              <img :src="song.coverArt" alt="Cover Art" height="50" />
            </td>
            <td>{{ song.title }}</td>
            <td>{{ song.artist }}</td>
            <td>{{ song.genre }}</td>
            <td>{{ song.duration }}</td>
            <td>
              <button @click="editSong(song)">Edit</button>
              <button @click="deleteSong(song.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import songAPI from "../services/SongService.js";

export default {
  name: "event",
  components: {},

  data() {
    return {
      song: {
        title: "",
        artist: "",
        genre: "",
        duration: "",
        songURL: "",
        coverArt: "",
      },
      songs: [],
      searchQuery: "",
      showAddForm: false,
      newSong: {
        title: "",
        artist: "",
        genre: "",
        duration: "",
        songURL: "",
        coverArt: "",
      },
      editingSong: {},
      editingSongIndex: -1,
    };
  },
  created() {
    songAPI.list().then((response) => {
      console.log(response.data);
      this.songs = response.data;
      console.log(this.songs);
    });
    
  },
  computed: {
    filteredSongs() {
      if (this.searchQuery === "") {
        return this.songs;
      }
      const query = this.searchQuery.toLowerCase();
      return this.songs.filter(
        (song) =>
          song.title.toLowerCase().includes(query) ||
          song.artist.toLowerCase().includes(query) ||
          song.genre.toLowerCase().includes(query) ||
          song.duration.toLowerCase().includes(query)
      );
    },
  },
  methods: {
    showForm() {
      this.showAddForm = true;
    },
    hideForm() {
      this.showAddForm = false;
    },
    addNewSong() {
      if (this.editingSongIndex === -1) {
        this.songs.push({
          id: this.songs.length + 1,
          title: this.editingSong.title,
          artist: this.editingSong.artist,
          genre: this.editingSong.genre,
          duration: this.editingSong.duration,
          coverArt: this.editingSong.coverArt,
        });
      } else {
        this.songs[this.editingSongIndex] = { ...this.editingSong };
      }

      this.editingSong = {
        title: "",
        artist: "",
        genre: "",
        duration: "",
        coverArt: "",
      };
      this.editingSongIndex = -1;
      this.hideForm();
    },

    playPlaylist() {
      // Logic to play the playlist
    },

    cancelEdit() {
      this.editingSong = {
        title: "",
        artist: "",
        genre: "",
        duration: "",
        coverArt: "",
      };
      this.editingSongIndex = -1;
      this.hideForm();
    },
    editSong(song) {
      this.editingSong = { ...song };
      this.editingSongIndex = this.songs.findIndex((s) => s.id === song.id);
      this.showForm();
    },

    deleteSong(songId) {
      const index = this.songs.findIndex((s) => s.id === songId);
      if (index !== -1) {
        this.songs.splice(index, 1);
      }
    },
  },
};
</script>


<style scoped>
/* Container styles */
.container {
  background-color: #ece2ee;
  padding: 20px;
  border-radius: 10px;
  margin: 0 auto;
}
#background_image {
  background-image: URL("https://pbs.twimg.com/media/FOMC59BaMAEuCh_.jpg:large");
  background-position: fixed;
  background-repeat: no-repeat;
  height: 72.5vh;
  padding: 50px;
}
/* Input and button styles */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

h2 {
  color: #753d8b;
  opacity: 0.8;
  font-family: "Source Sans Pro";
}

.buttons {
  margin-left: 100px; /* Add this line */
  font-family: "Source Sans Pro";
}

.buttons button {
  padding: 10px 20px;
  background-color: #753d8b;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-family: "Source Sans Pro";
}

.description {
  color: #753d8b;
  opacity: 0.8;
  font-family: "Source Sans Pro";
}
.genre-select,
.genre-select-button {
  display: flex;
  justify-content: space-between;
  align-content: center;
  align-items: center;
}
/* Table styles */
.table-container {
  max-height: calc(100vh - 230px);
  overflow-y: auto;
  margin-top: 20px;
  margin-bottom: 20px;
  margin: 0 auto;
  position: relative;
}

table {
  font-family: "Source Sans Pro";
  color: #753d8b;
  background-color: #f6ebfa;
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  padding: 15px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #753d8b;
  color: white;
  font-weight: bold;
  position: sticky;
  top: 0;
}

.clickable-row {
  cursor: pointer;
  /* Add other styling as needed */
}

/* Button styles */
button {
  padding: 5px 10px;
  margin-right: 5px;
  background-color: #753d8b;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.add-event-button {
  margin-bottom: 10px;
  padding: 10px 20px;
  background-color: #753d8b;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-family: "Source Sans Pro";
}

.add-event-form {
  background-color: #ece2ee;
  padding: 20px;
  border-radius: 5px;
  margin-bottom: 20px;
  /* Additional styling for the form */
  display: flex;
  flex-direction: column;
}

.add-event-form h3 {
  color: #753d8b;
  font-family: "Source Sans Pro";
  margin-bottom: 10px;
}

.add-event-form label {
  color: #753d8b;
  font-family: "Source Sans Pro";
  margin-bottom: 5px;
}

.add-event-form input,
.add-event-form textarea {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-bottom: 10px;
  width: 99%; /* Make inputs stretch to full width */
}

/* Adjust margin between form groups */
.add-event-form .form-group {
  margin-bottom: 20px;
}

.add-event-form button {
  padding: 10px 20px;
  background-color: #753d8b;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}

.add-event-form button:last-child {
  background-color: #ddd;
  color: #333;
  margin-left: 10px;
}

#generateButton {
  display: block;
}
</style>