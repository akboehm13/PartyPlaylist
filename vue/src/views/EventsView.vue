<template>
  <div id="background_image">
    <div class="container">
      <div class="header">
        <h2>My Events</h2>
        <div class="buttons">
          <router-link
            to="/global_list"
            class="buttons"
            v-if="userAuthority === 'ROLE_ADMIN'"
          >
            <button>Global Playlist</button>
          </router-link>
          <button
            @click="toggleEventForm()"
            class="add-event-button"
            v-if="userAuthority === 'ROLE_ADMIN'"
          >
            Create Event
          </button>
        </div>
      </div>

      <!-- Add the form for adding an event -->
      <div v-if="showEventForm" class="add-event-form">
        <h3>Add Event</h3>
        <form>
          <div class="form-group">
            <label for="eventName">Event Name:</label>
            <input
              type="text"
              id="eventName"
              v-model="newEvent.name"
              required
            />
          </div>
          <div class="form-group">
            <label for="eventDate">Event Date:</label>
            <input
              type="date"
              id="eventDate"
              v-model="newEvent.date"
              required
            />
          </div>
          <div class="form-group">
            <label for="eventDescription">Event Description:</label>
            <textarea
              id="eventDescription"
              v-model="newEvent.description"
              rows="3"
              required
            ></textarea>
          </div>
          <div class="form-group">
            <label for="eventStartTime">Start Time:</label>
            <input
              type="time"
              id="eventStartTime"
              v-model="newEvent.start_time"
              required
            />
          </div>
          <div class="form-group">
            <label for="eventEndTime">End Time:</label>
            <input
              type="time"
              id="eventEndTime"
              v-model="newEvent.end_time"
              required
            />
          </div>
          <div class="form-group">
            <label for="eventLocation">Location:</label>
            <input
              type="text"
              id="eventLocation"
              v-model="newEvent.location"
              required
            />
          </div>
          <div v-show="!checkEdit()">
            <h3>Add Playlist</h3>
            <div class="form-group">
              <label for="playlistName">Playlist Name:</label>
              <input
                type="text"
                id="playlistName"
                v-model="playlist.name"
                required
              />
            </div>
            <div class="form-group genre-select" style="display: flex">
              <label for="genreGroup">Genres:</label>
              <div
                class="genre-select-button"
                v-for="genre in genreGroup"
                :key="genre"
              >
                <input
                  type="checkbox"
                  :id="genre"
                  :value="genre"
                  v-model="selectedGenres"
                />
                <label :for="genre">{{ genre }}</label>
              </div>
            </div>
            <div class="generateButton">
              <button id="generateButton" @click.prevent="generatePlaylist()">
                Generate Playlist
              </button>
              <img
                class="requiredLogo"
                v-show="showPlaylistRequired"
                src="../../public/Pictures/Required-Icon.png"
                alt="Please generate a playlist!"
              />
            </div>
          </div>
          <button v-if="!editingEvent" @click.prevent="addEvent()">Add</button>
          <button v-if="editingEvent" @click.prevent="editEvent()">Edit</button>
          <button @click="toggleEventForm()">Cancel</button>
        </form>
      </div>
      <div class="table-container">
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Date</th>
              <th>Description</th>
              <th>Start Time</th>
              <th>End Time</th>
              <th>Location</th>
              <th></th>
            </tr>
          </thead>
          <tr
            v-for="event in filteredEvents"
            :key="event.eventId"
            class="clickable-row"
            @click="navigateToEvent(event.eventId)"
          >
            <td>{{ event.name }}</td>
            <td>{{ event.date }}</td>
            <td>{{ event.description }}</td>
            <td>{{ event.start_time }}</td>
            <td>{{ event.end_time }}</td>
            <td>{{ event.location }}</td>
            <td>
              <button @click.stop="editEventForm(event)">Edit</button>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import eventAPI from "../services/EventService.js";
import songAPI from "../services/SongService.js";
import playlistAPI from "../services/PlaylistService.js";
import playlistSongAPI from "../services/PlaylistSongService.js";
export default {
  name: "MyEventsView",
  data() {
    return {
      editingEvent: false,
      editedID: 0,
      showEventForm: false,
      showPlaylistRequired: false,
      newEvent: {
        name: "",
        date: "",
        description: "",
        dj_id: 2,
        host_id: 1,
        start_time: "",
        end_time: "",
        location: "",
      },
      selectedGenres: [],
      playlist: {
        name: "",
        event_id: "",
      },
      songs: [],
      genreGroup: [
        "Rock",
        "Pop",
        "Indie",
        "Alternative",
        "Heavy Metal",
        "Hip-Hop",
        "Jazz",
        "Dance",
        "Instrumental",
      ],
      events: [],
      // userEvents: [],
      userId: JSON.parse(localStorage.getItem("user")).id,
      userAuthority: JSON.parse(localStorage.getItem("user")).authorities[0]
        .name,
    };
  },
  created() {
    eventAPI.list().then((response) => {
      this.events = response.data;
    });
  },
  methods: {
    toggleEventForm() {
      this.editingEvent = false;
      this.showPlaylistRequired = false;
      this.showEventForm = !this.showEventForm;
      if (this.showEventForm === false) {
        this.clearForm();
      }
    },
    selectRandomSongs() {
      this.songs = [];
      this.selectedGenres.forEach(async (genre) => {
        let currentSelected = await songAPI.listByGenre(genre);
        let scale = Math.floor(Math.random() * currentSelected.data.length) + 1;

        for (let i = 0; i < scale; i++) {
          let randomSong =
            currentSelected.data[
              Math.floor(Math.random() * currentSelected.data.length)
            ];
          if (!this.songs.includes(randomSong)) {
            this.songs.push(randomSong);
          }
        }
      });
      console.log(this.songs);
    },
    generatePlaylist() {
      if (this.showPlaylistRequired) this.showPlaylistRequired = false;
      if (this.selectedGenres.length < 1) {
        this.selectedGenres = this.genreGroup;
        this.selectRandomSongs();
      } else {
        this.selectRandomSongs();
      }
    },
    checkEdit() {
      console.log(this.editingEvent);
      return this.editingEvent;
    },
    clearForm() {
      this.newEvent.name = "";
      this.newEvent.date = "";
      this.newEvent.description = "";
      this.newEvent.start_time = "";
      this.newEvent.end_time = "";
      this.newEvent.location = "";
      this.showPlaylistRequired = false;
    },
    async addEvent() {
      if (this.songs.length < 1) {
        this.showPlaylistRequired = true;
      } else {
        if (!this.editingEvent) {
          if (this.newEvent.name != "") {
            console.log(this.newEvent);

            let createdEvent = await eventAPI.add(this.newEvent);
            this.events.push(this.newEvent);

            console.log(createdEvent.data);

            this.playlist.event_id = createdEvent.data.eventId;

            this.addPlaylist();

            location.reload();
          }
        }
      }
    },
    async addPlaylist() {
      let index = await playlistAPI.add(this.playlist);
      console.log(index.data);

      this.songs.forEach((song) => {
        let playlistSong = {
          playlist_id: index.data.playlist_id,
          song_id: song.song_id,
        };

        playlistSongAPI.add(playlistSong);
      });
    },
    editEvent() {
      console.log(this.newEvent.eventId);

      eventAPI.update(this.newEvent.eventId, this.newEvent);
      this.clearForm();

      location.reload();
    },
    cancelEdit() {
      this.editingEvent = false;
      this.editingEvent = {};
      this.editingEventIndex = -1;
      this.hideForm();
    },
    editEventForm(event) {
      this.editingEvent = true;
      console.log(event);
      this.newEvent = { ...event };
      this.editingEventIndex = this.events.findIndex(
        (e) => e.event_id === event.event_id
      );
      this.showEventForm = true;
    },
    navigateToEvent(eventId) {
      this.$router.push(`/events/${eventId}`);
    },
  },
  computed: {
    filteredEvents() {
      if (this.userAuthority === "ROLE_USER") {
        return this.events.filter((event) => {
          return event.host_id == this.userId;
        });
      } else if (this.userAuthority === "ROLE_ADMIN") {
        return this.events.filter((event) => {
          return event.dj_id == this.userId;
        });
      } else return [];
    },
  },
};
</script>


<style scoped>
/* Container styles */
.container {
  background-color: #ece2ee;
  background: #f7f7f7
    url("https://pbs.twimg.com/media/FOMC59BaMAEuCh_.jpg:large");
  height: 74vh;
  padding: 20px;
  border-radius: 10px;
  margin: 0 auto;
}
/* Input and button styles */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

h2 {
  color: #ffffff;
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
.generateButton {
  display: flex;
  justify-content: center;
  align-items: center;
}
#generateButton {
  background-color: #753d8b;
  height: 50px;
  width: 150px;
}
.description {
  color: #753d8b;
  opacity: 0.8;
  font-family: "Source Sans Pro";
}
.requiredLogo {
  height: 60px;
  width: 30px;
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