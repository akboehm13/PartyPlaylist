<template>
  <div class="container">
    <div class="header">
        <h2>My Events</h2>
            <div class="buttons">
              <router-link to="/global_list" class="buttons">
                <button>Global Playlist</button>
              </router-link>
              <button @click="toggleEventForm()" class="add-event-button">Add Event</button>
            </div>
    </div>

    <!-- Add the form for adding an event -->
    <div v-if="showEventForm" class="add-event-form">
  <h3>Add Event</h3>
  <form>
    <div class="form-group">
      <label for="eventName">Event Name:</label>
      <input type="text" id="eventName" v-model="newEvent.name" required />
    </div>
    <div class="form-group">
      <label for="eventDate">Event Date:</label>
      <input type="date" id="eventDate" v-model="newEvent.date" required />
    </div>
    <div class="form-group">
      <label for="eventDescription">Event Description:</label>
      <textarea id="eventDescription" v-model="newEvent.description" rows="3" required></textarea>
    </div>
    <div class="form-group">
      <label for="eventStartTime">Start Time:</label>
      <input type="time" id="eventStartTime" v-model="newEvent.startTime" required />
    </div>
    <div class="form-group">
      <label for="eventEndTime">End Time:</label>
      <input type="time" id="eventEndTime" v-model="newEvent.endTime" required />
    </div>
    <div class="form-group">
      <label for="eventLocation">Location:</label>
      <input type="text" id="eventLocation" v-model="newEvent.location" required />
    </div>

    <button @click="addEvent()">Add</button>
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
        <tr v-for="event in events" :key="event.id" class="clickable-row" @click="navigateToEvent(event.id)">
              <td>{{ event.name }}</td>
              <td>{{ event.date }}</td>
              <td>{{ event.description }}</td>
              <td>{{ event.startTime }}</td>
              <td>{{ event.endTime }}</td>
              <td>{{ event.location }}</td>
            <td>
              <button @click.stop="editEvent(event)">Edit</button>
            </td>
          </tr>
      </table>
    </div>
  </div>
</template>

<script>
import eventAPI from '../services/EventService.js';
export default {
  name: 'MyEventsView',
  data() {
    return {
      showEventForm: false,
      newEvent: {
        name: '',
        date: '',
        description: '',
        startTime: '',
        endTime: '',
        location: ''
      },
      events: []
    };
  },
  created() {
    eventAPI.list().then((response) => {
      this.events = response.data;
    });
  },
  methods: {
    toggleEventForm() {
      this.showEventForm = !this.showEventForm;
      if (this.showEventForm === false) {
        this.clearForm();
      }
    },
    clearForm() {
        this.newEvent.name = '';
        this.newEvent.date = '';
        this.newEvent.description = '';
        this.newEvent.startTime = '';
        this.newEvent.endTime = '';
        this.newEvent.location = '';
    },
    addEvent() {
      if (this.newEvent.name != '') {
        this.events.push(this.newEvent);
      }
    },
    cancelEdit() {
      this.editingEvent = {};
      this.editingEventIndex = -1;
      this.hideForm();
    },
    editEvent(event) {
      this.editingEvent = { ...event };
      this.editingEventIndex = this.events.findIndex(
        (e) => e.event_id === event.event_id
      );
      this.showForm();
    },
    navigateToEvent(eventId) {
  this.$router.push(`/event/${eventId}`);
}
  }
  
  
}
</script>


<style scoped>
/* Container styles */
.container {
  background-color: #ece2ee;
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

</style>