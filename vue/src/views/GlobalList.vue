<template>
  <div class="container">
    <div class="input">
      <h2>Global Music List</h2>
      <input type="text" v-model="searchQuery" placeholder="Search songs..." />
      <button @click="showForm">+ Add Song</button>
    </div>

    <div class="add-song-form" v-if="showAddForm">
      <form @submit.prevent="saveSong">
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
          v-model="editingSong.song_url"
          placeholder="Song URL"
        />
        <input
          type="text"
          v-model="editingSong.img_url"
          placeholder="Cover Art URL"
        />
        <div id="form-buttons">
          <button type="submit">
            {{ editingSongIndex === -1 ? "Add" : "Update" }}
          </button>
          <button type="button" @click="cancelEdit">Cancel</button>
        </div>
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
              <button @click="deleteSong(song)">Delete</button>
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
  name: "global_list",
  components: {},

  data() {
    return {
      songs: [],
      searchQuery: "",
      showAddForm: false,
      editingSong: {},
      editingSongIndex: -1,
    };
  },
  created() {
    songAPI.list().then((response) => {
      this.songs = response.data;
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
    saveSong() {
      if (this.editingSongIndex === -1) {
        console.log("adding...");
        songAPI
          .add(this.editingSong)
          .then((response) => {
            console.log(response.data);
            this.songs.push(response.data);
          })
          .then(() => {
            this.editingSong = {};
            this.editingSongIndex = -1;
            this.hideForm();
          });
      } else {
        console.log("updating...");
        songAPI
          .update(this.editingSong.song_id, this.editingSong)
          .then((response) => {
            if (response.status === 200) {
              this.editingSongIndex = this.songs.findIndex(
                (s) => s.song_id === this.editingSong.song_id
              );
              this.songs[this.editingSongIndex] = { ...this.editingSong };
            }
          })
          .then(() => {
            this.editingSong = {};
            this.editingSongIndex = -1;
            this.hideForm();
          });
      }
    },

    cancelEdit() {
      this.editingSong = {};
      this.editingSongIndex = -1;
      this.hideForm();
    },
    editSong(song) {
      this.editingSong = { ...song };
      this.editingSongIndex = this.songs.findIndex(
        (s) => s.song_id === song.song_id
      );
      this.showForm();
    },

    deleteSong(song) {
      songAPI.delete(song.song_id).then((response) => {
        if (response.status === 200) {
          const index = this.songs.findIndex((s) => s.song_id === song.song_id);
          if (index !== -1) {
            this.songs.splice(index, 1);
          }
        }
      });
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
  display: flex;
  flex-direction: column;
  gap: 20px;
}

#form-buttons {
  display: flex;
  justify-content: flex-end;
}
/* Input and button styles */
.input {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.input h2 {
  color: #a264bb;
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
  background-color: #a264bb;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
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
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  color: #a264bb;
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
  background-color: #a264bb;
  color: white;
  font-weight: bold;
  position: sticky;
  top: 0;
}

/* Button styles */
button {
  padding: 5px 10px;
  margin-right: 5px;
  background-color: #a264bb;
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

