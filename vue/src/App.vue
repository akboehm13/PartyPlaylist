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
            v-bind:to="{ name: 'global_list' }"
            v-if="$store.state.token != ''"
            >Global List</router-link
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
  color: #dc9d00;
  background-color: rgb(100, 100, 100);
  padding: 20px;
  border-radius: 10px;
  font-size: 1em;
}

.nav-item a {
  text-decoration: none;
  color: #dc9d00;
  transition: color 0.3s ease-in-out;
  font-family: Arial, Helvetica, sans-serif;
}

.nav-item a:hover {
  color: lightgray;
}

body {
  background-color: rgb(50, 50, 50);
}
h2 {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  text-align: center;
  color: #dc9d00;
  background-color: rgb(100, 100, 100);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  margin-bottom: 20px;
  margin: 0 auto;
}
</style>
