<template>
  <div class="container">
    <div class="input">
      <h2>Global Music List</h2>
      <input type="text" v-model="searchQuery" placeholder="Search songs..." />
      <button @click="showForm()">+ Add Song</button>
    </div>

    <div class="add-song-form" v-if="showAddForm">
      <form @submit.prevent="addNewSong">
        <input type="text" v-model="editingSong.title" placeholder="Name" />
        <input type="text" v-model="editingSong.artist" placeholder="Artist" />
        <input type="text" v-model="editingSong.genre" placeholder="Genre" />
        <input
          type="text"
          v-model="editingSong.duration"
          placeholder="Duration"
        />
        <input
          type="text"
          v-model="editingSong.coverArt"
          placeholder="Cover Art URL"
        />
        <button type="submit">
          {{ editingSongIndex === -1 ? "Add" : "Update" }}
        </button>
        <button type="button" @click="cancelEdit">Cancel</button>
      </form>
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
            <th></th>
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
import songAPI from "../src/services/SongService.js";

export default {
  name: "global-list",
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
  background-color: #f4f4f4;
  padding: 20px;
  border-radius: 10px;
  margin: 0 auto;
}

/* Input and button styles */
.input {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.input h2 {
  color: #007aff;
  opacity: 0.8;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
}

.input input[type="text"] {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 70%;
}

.input button {
  padding: 10px 20px;
  background-color: #007aff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

/* Table styles */
.table-container {
  max-height: 690px;
  overflow-y: auto;
  margin-top: 20px;
  margin: 0 auto;
  position: relative;
}

table {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  color: #333;
  background-color: #fff;
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
  background-color: #007aff;
  color: white;
  font-weight: bold;
  position: sticky;
  top: 0;
}

/* Button styles */
button {
  padding: 5px 10px;
  margin-right: 5px;
  background-color: #007aff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button.delete {
  background-color: #ff3b30;
}

/* Form styles */
.add-song-form {
  display: none;
  margin-top: 20px;
}

.add-song-form form {
  display: flex;
  flex-direction: column;
}

.add-song-form input {
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>

