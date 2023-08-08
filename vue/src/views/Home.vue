<template>
  <div class="home">
    <h1>Home</h1>
    <router-link v-bind:to="{ name: 'login' }">Login</router-link>

  </div>
</template>

<script>
export default {
  name: "home"
};
</script>
