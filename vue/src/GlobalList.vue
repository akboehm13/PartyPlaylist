<template>
  <div>
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
          <tr v-for="song in filteredSongs" :key="song.song_id">
            <td>
              <img :src="song.coverArt" alt="Cover Art" height="50" />
            </td>
            <td>{{ song.title }}</td>
            <td>{{ song.artist }}</td>
            <td>{{ song.genre }}</td>
            <td>{{ song.duration }}</td>
            <td>
              <button @click="editSong(song)">Edit</button>
              <button @click="deleteSong(song)">Delete</button>
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

    deleteSong(song) {
      songAPI.delete(song.song_id).then((response)=> {
        if (response.status === 200){
      const index = this.songs.findIndex((s) => s.song_id === song.song_id);
      if (index !== -1) {
        this.songs.splice(index, 1);
      }
      }
    });
  }
}
}

</script>

<style>
body {
  background-color: rgb(50, 50, 50);
}

h2 {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  text-align: center;
  color: silver;
  background-color: rgb(4, 92, 4);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  margin-bottom: 20px;
}

.input {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  text-align: center;
  max-width: 800px;
  margin: 0 auto;
}

table {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  color: silver;
  background-color: rgb(100, 100, 100);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  margin: 0 auto;
  border-collapse: collapse;
}

th {
  padding: 10px;
  text-align: left;
}

td {
  padding: 10px;
}

.table-container {
  max-height: 350px;
  overflow-y: auto;
  margin-top: 20px;
  position: relative;
}

table thead {
  position: sticky;
  top: 0;
  background-color: rgb(4, 92, 4);
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgb(4, 92, 4);
  z-index: 1;
}
</style>

