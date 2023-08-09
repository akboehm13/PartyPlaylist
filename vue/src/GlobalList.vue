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
        <input type="text" v-model="newSong.name" placeholder="Name" />
        <input type="text" v-model="newSong.artist" placeholder="Artist" />
        <input type="text" v-model="newSong.genre" placeholder="Genre" />
        <input type="text" v-model="newSong.duration" placeholder="Duration" />
        <input type="text" v-model="newSong.coverArt" placeholder="Cover Art URL" />
        <button type="submit">Add</button>
        <button type="button" @click="showAddForm = false">Cancel</button>
      </form>
    </div>

    <div class="table-container">
        <table>
        <thead>
            <tr>
            <th>Name</th>
            <th>Artist</th>
            <th>Genre</th>
            <th>Duration</th>
            <th>Cover Art</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="song in filteredSongs" :key="song.id">
            <td>{{ song.name }}</td>
            <td>{{ song.artist }}</td>
            <td>{{ song.genre }}</td>
            <td>{{ song.duration }}</td>
            <td>
                <img :src="song.coverArt" alt="Cover Art" height="50" />
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
            name: "Dancing in the Moonlight",
            artist: "Eleanor Summers",
            genre: "Pop",
            duration: "3:45",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 2,
            name: "Rolling Thunder",
            artist: "Aiden Miller",
            genre: "Rock",
            duration: "4:12",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 3,
            name: "Electric Dreams",
            artist: "Olivia Walker",
            genre: "Electronic",
            duration: "5:20",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 4,
            name: "Midnight Serenade",
            artist: "Lucas Foster",
            genre: "Jazz",
            duration: "3:58",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 5,
            name: "Sunny Side Up",
            artist: "Isabella Knight",
            genre: "Funk",
            duration: "4:30",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 6,
            name: "Lost in Paradise",
            artist: "Gabriel Smith",
            genre: "Indie",
            duration: "3:15",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 7,
            name: "Rhythm of the Rain",
            artist: "Sophia Carter",
            genre: "Country",
            duration: "4:05",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 8,
            name: "Echoes of Eternity",
            artist: "Nathan Hill",
            genre: "Ambient",
            duration: "6:18",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 9,
            name: "Urban Odyssey",
            artist: "Zoe Rivera",
            genre: "Hip Hop",
            duration: "3:50",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 10,
            name: "Sunset Melodies",
            artist: "Liam Mitchell",
            genre: "Chill",
            duration: "4:30",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 11,
            name: "Whispers in the Wind",
            artist: "Mason Reed",
            genre: "Acoustic",
            duration: "3:28",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 12,
            name: "Starry Night Serenade",
            artist: "Aria Hughes",
            genre: "Classical",
            duration: "2:56",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 13,
            name: "City Lights",
            artist: "Evan Cooper",
            genre: "Pop",
            duration: "3:42",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 14,
            name: "Euphoric Echoes",
            artist: "Zara Turner",
            genre: "Electronic",
            duration: "4:10",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 15,
            name: "Mystic Voyage",
            artist: "Leo Ramirez",
            genre: "World",
            duration: "5:15",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 16,
            name: "Sunrise Serenity",
            artist: "Ava Foster",
            genre: "Chill",
            duration: "3:20",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 17,
            name: "Radiant Hearts",
            artist: "Owen Patel",
            genre: "Indie",
            duration: "4:05",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 18,
            name: "Wanderlust",
            artist: "Luna Bennett",
            genre: "Folk",
            duration: "3:48",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 19,
            name: "Eternal Embrace",
            artist: "Nora Parker",
            genre: "Ambient",
            duration: "6:30",
            coverArt: "URL_TO_COVER_ART_IMAGE"
        },
        {
            id: 20,
            name: "Celestial Dreams",
            artist: "Ella Baker",
            genre: "Electronic",
            duration: "5:10",
            coverArt: "URL_TO_COVER_ART_IMAGE"
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
      }
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
        this.songs.push({
        id: this.songs.length + 1,
        name: this.newSong.name,
        artist: this.newSong.artist,
        genre: this.newSong.genre,
        duration: this.newSong.duration,
        coverArt: this.newSong.coverArt,
    });
      this.newSong = {
        name: '',
        artist: '',
        genre: '',
        duration: '',
        coverArt: '',
      };
      this.showAddForm = false;
    },
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
    color: #DC9D00;
    background-color: rgb(100, 100, 100);
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
    color: #DC9D00;
    background-color: rgb(100, 100, 100);
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    max-width: 800px;
    margin: 0 auto;
}

.table-container {
  max-height: 350px;
  overflow-y: auto;
  margin-top: 20px;
  
}
</style>

