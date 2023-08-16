<template>
  <section id="header">
   <!-- <h1> -->
       <div class="header-logo">
        <img
          src="@/Sync Logo.png"
          alt="Logo"
        />
      </div>
      <div class="centered-img">
        <img id="sync-img"
          src="@/Sync.png"
          alt="Logo"
        />
      </div>
    <!-- </h1> -->
    <nav id="nav">
      <ul>
        <li>
          <router-link to="/login">Log In</router-link>
        </li>
        <li><router-link to="/">Home</router-link></li>
        <li><router-link to="/events"> My Events</router-link></li>
      </ul>
      <!-- <router-view /> -->
    </nav>
  </section>
</template>

<script>
export default {};
</script>

<style scoped>
#header {
  
  background: #ece2ee; 
  border-bottom: solid 1px #ccc;
  text-align: center;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#header h1 {
  color: #252122;
  font-weight: 900;
  font-size: 2em;
  letter-spacing: -0.035em;
  line-height: 1;
  margin-bottom: 0.5em;
}

#header img {
  max-width: 100%;
  height: auto;
  width: 80px;
  position: relative;
  left: 1em;
}

#sync-img {
  max-width: 100%;
  height: auto;
  width: 300px;
}



#nav {
  /* margin: 2.5em 0 0 0; */
  align-items: center;
}

#nav > ul {
  margin: 0;
}

#nav > ul > li {
  display: inline-block;
  font-weight: bold;
  margin: 0 0.35em 0 0.35em;
  font-size: 1.3em;
}

#nav > ul > li > a {
  border-radius: 5px;
  color: purple;
  text-decoration: none;
  padding: 0.6em 1.2em 0.6em 1.2em;
  -moz-transition: background-color 0.25s ease-in-out;
  -webkit-transition: background-color 0.25s ease-in-out;
  -ms-transition: background-color 0.25s ease-in-out;
  transition: background-color 0.25s ease-in-out;
  outline: 0;
}

#nav > ul > li:hover > a,
#nav > ul > li.active > a {
  background: #0de4f3;
}

#nav > ul > li.current > a {
  background: #0de4f3;
  color: #fff !important;
  font-weight: 700;
}
</style>