<template>
  <!-- <div id="app">
    <div id="navbar">
      <ul class="nav-list">
        <li class="nav-item">
          <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        </li>
        &nbsp;|&nbsp;
        <li class="nav-item">
          <router-link v-bind:to="{ name: 'events' }">Events</router-link>
        </li>
        &nbsp;|&nbsp;

        <li class="nav-item">
          <router-link
            v-bind:to="{ name: 'event' }"
            v-if="$store.state.token != ''"
            >Event</router-link
          >
        </li>
        &nbsp;|&nbsp;
        <li class="nav-item">
          <router-link
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
            >Logout</router-link
          >
        </li>
      </ul>
    </div> -->
  <div id="app">
    <app-header />
    <!-- <home /> -->
    <router-view id="router-view" />
  </div>
  <!-- </div> -->
</template>

<script>
import AppHeader from "./components/AppHeader.vue";

export default {
  components: {
    AppHeader,
  },
};
</script>

<style>
body {
  background-color: #202020;
}
/* #router-view {
  background-image: url("./assets/background.jpg");
} */
/* .nav-list {
  list-style: none;
  display: flex;
  justify-content: space-around;
  color: #753d8b;
  background-color: #ece2ee;
  padding: 20px;
  border-radius: 10px;
  font-size: 1em;
}

.nav-item a {
  text-decoration: none;
  color: #753d8b;
  transition: color 0.3s ease-in-out;
  font-family: "Source Sans Pro";
}

.nav-item a:hover {
  color: lightgray;
}

body {
  background-color: rgb(224, 224, 224);
} */
</style>
