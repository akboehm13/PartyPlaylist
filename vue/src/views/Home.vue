<template>
  <div class="home">
    <h2>Home</h2>
    <router-link class="login-link" v-bind:to="{ name: 'login' }">Login</router-link>

  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style scoped>
.login-link {
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  font-size: 1.5em;
  color: #DC9D00; 
  background-color: rgb(100, 100, 100);
  padding: 10px;
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}
</style>