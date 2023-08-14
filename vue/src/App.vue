<template>
  <div id="app">
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
    </div>
    <router-view />
  </div>
</template>

<style>
@import url("https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,900,300italic");
.nav-list {
  
  list-style: none;
  display: flex;
  justify-content: space-around;
  color: #a264bb;
  background-color: #ece2ee;
  padding: 20px;
  border-radius: 10px;
  font-size: 1em;
}

.nav-item a {
  text-decoration: none;
  color: #a264bb;
  transition: color 0.3s ease-in-out;
  font-family: "Source Sans Pro";
}

.nav-item a:hover {
  color: lightgray;
}

body {
  background-color: rgb(226, 218, 226);
}
</style>
