<template>
  <div id="event">
    <div id="event-name">
      <h2>{{ event.name }}</h2>
      <div id="event-description">
        <h3>{{ event.description }}</h3>
      </div>
    </div>

    <div id="event-img">
      <img
        src="data:image/jpeg;base64,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"
        alt="event-img"
      />
    </div>

    <div class="event-logistics">
      <h2>Event Logistics</h2>
      <div class="logistics-item">
        <h3>Date:</h3>
        <p>{{ event.date }}</p>
      </div>
      <div class="logistics-item">
        <h3>Address:</h3>
        <p>{{ event.location }}</p>
      </div>
      <div class="logistics-item">
        <h3>Start Time:</h3>
        <p>{{ event.start_time }}</p>
      </div>
      <div class="logistics-item">
        <h3>End Time:</h3>
        <p>{{ event.end_time }}</p>
      </div>
      <!-- <div class="logistics-item">
        <h3>Host ID:</h3>
        <p>{{ event.hostId }}</p>
      </div>
      <div class="logistics-item">
        <h3>DJ ID:</h3>
        <p>{{ event.djId }}</p>
      </div> -->
    </div>
    <div id="table-playlist">
      <table>
        <caption>
          <h3>{{ playlist.name }}</h3>
        </caption>
        <thead>
          <tr>
            <!-- <th>Cover Art</th> -->
            <th>Name</th>
            <th>Artist</th>
            <th>Genre</th>
            <th>Duration</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="song in songs" :key="song.songId">
            <!-- <td>
              <img :src="song.img_url" alt="Cover Art" />
            </td> -->
            <td>{{ song.title }}</td>
            <td>{{ song.artist }}</td>
            <td>{{ song.genre }}</td>
            <td>{{ song.duration }}</td>
            <!-- <td>
              <button @click="likeSong(song)">Edit</button>
              <button @click="dislikeSong(song)">Delete</button>
            </td> -->
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import eventAPI from "../services/EventService.js";
import playlistAPI from "../services/PlaylistService.js";
import playlistsongAPI from "../services/PlaylistSongService.js";

export default {
  name: "event",

  data() {
    return {
      event: {},
      playlist: {},
      songs: [],
    };
  },
  created() {
    eventAPI
      .get(this.$route.params.id)
      .then((response) => {
        this.event = response.data;
        // console.log(this.event);
        // console.log(this.event.eventId);
        return this.event;
      })
      .then((event) => playlistAPI.getByEventId(event.eventId))
      .then((response) => {
        this.playlist = response.data[0];
        console.log("getting playlist");
        return this.playlist;
      })
      .then((playlist) => {
        console.log("fetching songs");
        playlistsongAPI
          .listSongsByPlaylistId(playlist.playlist_id)

          .then((response) => {
            console.log("getting songs");
            console.log(response);
            this.songs = response.data;
          });
      });
    // .then((response) => {
    //   console.log("getting songs");
    //   console.log(response);
    //   this.songs = response.data;
    // });
  },
  // computed: {
  //   timeConverter(time){
  //       var timeArray = time.split(':');
  //       var miliHour = parseInt(timeArray[0]);
  //       var hour = miliHour%12;
  //       var timeOfDay = miliHour/12 >=
  //   }
  // }
};
</script>

<style scoped>
#router-view {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    "img title logi"
    "playlist playlist playlist";
  justify-items: center;
  background-color: #ece2ee;
  padding-bottom: 2em;
  font-family: "Source Sans Pro", sans-serif;
  color: #ffffff;
  border-radius: 10px;
  overflow: hidden;
}
#event-name {
  grid-area: title;
  justify-self: center;
  font-size: 35px;
  text-align: center;
}
#event-img {
  grid-area: img;
  align-self: center;
  justify-self: center;
}
#event-description {
  grid-area: description;
  font-size: 18px;
  text-align: center;
}
#event-description > h2 {
  text-align: center;
}
.event-logistics {
  padding: 20px;
  width: 300px;
  margin: 0 auto;
  border-radius: 10px;
  background-color: #ece2ee;
  color: #753d8b;
  margin-top: 20px;
}
.event-logistics h3 {
  color: #753d8b;
}
.event-logistics h2 {
  text-align: center;
}
.logistics-item {
  margin-bottom: 15px;
}
.logistics-item h3 {
  margin: 0;
}
.logistics-item p {
  margin: 0;
  margin-left: 20px;
}
#table-playlist {
  grid-area: playlist;
}
.table-container {
  max-height: calc(100vh - 230px);
  overflow-y: auto;
  margin-top: 20px;
  margin-bottom: 20px;
  margin: 0 auto;
  position: relative;
}
h3 {
  color: #ffffff;
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
</style>