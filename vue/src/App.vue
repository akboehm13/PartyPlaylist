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
.nav-list {
  list-style: none;
  display: flex;
  justify-content: space-around;
  color: #007aff;
  background-color: #f4f4f4;
  padding: 20px;
  border-radius: 10px;
  font-size: 1em;
}

.nav-item a {
  text-decoration: none;
  color: #007aff;
  transition: color 0.3s ease-in-out;
  font-family: Arial, Helvetica, sans-serif;
}

.nav-item a:hover {
  color: lightgray;
}

body {
  background-color: rgb(224, 224, 224);
}
</style>
