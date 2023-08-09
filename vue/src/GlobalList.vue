<template>

<div>
    <div class="input">
        <h2>Global Music List</h2>
        <input
        type="text"
        v-model="searchQuery"
        @input="handleSearch"
        placeholder="Search songs..."
        />
        <button @click="showAddForm = true">+ Add Song</button>
    </div>

    <div class="add-song-form" v-if="showAddForm">
      <form @submit.prevent="addNewSong">
          <input type="text" v-model="editingSong.name" placeholder="Name" />
          <input type="text" v-model="editingSong.artist" placeholder="Artist" />
          <input type="text" v-model="editingSong.genre" placeholder="Genre" />
          <input type="text" v-model="editingSong.duration" placeholder="Duration" />
          <input type="text" v-model="editingSong.coverArt" placeholder="Cover Art URL" />
          <button type="submit">{{ editingSongIndex === -1 ? 'Add' : 'Update' }}</button>
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
            <th> </th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="song in filteredSongs" :key="song.id">
            <td>
                <img :src="song.coverArt" alt="Cover Art" height="50" />
            </td>
            <td>{{ song.name }}</td>
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
export default {
    name: "global-list",
  components: {},

  data() {
    return {
      songs: [
        {
            id: 1,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Dancing in the Moonlight",
            artist: "Eleanor Summers",
            genre: "Pop",
            duration: "3:45",
            
        },
        {
            id: 2,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Rolling Thunder",
            artist: "Aiden Miller",
            genre: "Rock",
            duration: "4:12",
            
        },
        {
            id: 3,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Electric Dreams",
            artist: "Olivia Walker",
            genre: "Electronic",
            duration: "5:20",
            
        },
        {
            id: 4,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Midnight Serenade",
            artist: "Lucas Foster",
            genre: "Jazz",
            duration: "3:58",
            
        },
        {
            id: 5,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Sunny Side Up",
            artist: "Isabella Knight",
            genre: "Funk",
            duration: "4:30",
            
        },
        {
            id: 6,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Lost in Paradise",
            artist: "Gabriel Smith",
            genre: "Indie",
            duration: "3:15",
            
        },
        {
            id: 7,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Rhythm of the Rain",
            artist: "Sophia Carter",
            genre: "Country",
            duration: "4:05",
            
        },
        {
            id: 8,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Echoes of Eternity",
            artist: "Nathan Hill",
            genre: "Ambient",
            duration: "6:18",
            
        },
        {
            id: 9,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Urban Odyssey",
            artist: "Zoe Rivera",
            genre: "Hip Hop",
            duration: "3:50",
            
        },
        {
            id: 10,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Sunset Melodies",
            artist: "Liam Mitchell",
            genre: "Chill",
            duration: "4:30",
            
        },
        {
            id: 11,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Whispers in the Wind",
            artist: "Mason Reed",
            genre: "Acoustic",
            duration: "3:28",
            
        },
        {
            id: 12,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Starry Night Serenade",
            artist: "Aria Hughes",
            genre: "Classical",
            duration: "2:56",
            
        },
        {
            id: 13,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "City Lights",
            artist: "Evan Cooper",
            genre: "Pop",
            duration: "3:42",
            
        },
        {
            id: 14,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Euphoric Echoes",
            artist: "Zara Turner",
            genre: "Electronic",
            duration: "4:10",
            
        },
        {
            id: 15,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Mystic Voyage",
            artist: "Leo Ramirez",
            genre: "World",
            duration: "5:15",
            
        },
        {
            id: 16,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Sunrise Serenity",
            artist: "Ava Foster",
            genre: "Chill",
            duration: "3:20",
            
        },
        {
            id: 17,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Radiant Hearts",
            artist: "Owen Patel",
            genre: "Indie",
            duration: "4:05",
            
        },
        {
            id: 18,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Wanderlust",
            artist: "Luna Bennett",
            genre: "Folk",
            duration: "3:48",
            
        },
        {
            id: 19,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Eternal Embrace",
            artist: "Nora Parker",
            genre: "Ambient",
            duration: "6:30",
            
        },
        {
            id: 20,
            coverArt: "URL_TO_COVER_ART_IMAGE",
            name: "Celestial Dreams",
            artist: "Ella Baker",
            genre: "Electronic",
            duration: "5:10",
            
        }
        
      ],
      searchQuery: '',
      showAddForm: false,
      newSong: {
        name: '',
        artist: '',
        genre: '',
        duration: '',
        coverArt: '',
      },
      editingSong: null,
      editingSongIndex: -1,
    };
  },
  computed: {
    filteredSongs() {
      if (this.searchQuery === "") {
        return this.songs;
      }
      const query = this.searchQuery.toLowerCase();
      return this.songs.filter(
        song =>
          song.name.toLowerCase().includes(query) ||
          song.artist.toLowerCase().includes(query) ||
          song.genre.toLowerCase().includes(query) ||
          song.duration.toLowerCase().includes(query)
      );
    }
  },
  methods: {
    addNewSong() {
        if (this.editingSongIndex === -1) {
            this.songs.push({
                id: this.songs.length + 1,
                name: this.editingSong.name,
                artist: this.editingSong.artist,
                genre: this.editingSong.genre,
                duration: this.editingSong.duration,
                coverArt: this.editingSong.coverArt,
            });
        } else {
            this.songs[this.editingSongIndex] = { ...this.editingSong };
        }

        this.editingSong = {
            name: '',
            artist: '',
            genre: '',
            duration: '',
            coverArt: '',
        };
        this.editingSongIndex = -1;
        this.showAddForm = false;
    },

    cancelEdit() {
        this.editingSong = {
            name: '',
            artist: '',
            genre: '',
            duration: '',
            coverArt: '',
        };
        this.editingSongIndex = -1;
        this.showAddForm = false;
    },
    editSong(song) {
        this.editingSong = { ...song };
        this.editingSongIndex = this.songs.findIndex(s => s.id === song.id);
        this.showAddForm = true;
    },
    
    deleteSong(songId) {
        const index = this.songs.findIndex(s => s.id === songId);
        if (index !== -1) {
            this.songs.splice(index, 1);
        }
    }
  }
};
</script>

<style>
body {
    background-color: rgb(50, 50, 50);
}

h2 {
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
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
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    text-align: center;
    max-width: 800px;
    margin: 0 auto;
}

table {
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
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
  box-shadow: 0px 0px 10px  rgb(4, 92, 4);
  z-index: 1;
}
</style>

