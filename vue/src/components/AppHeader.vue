<template>
  <section id="header">
   <!-- <h1> -->
       <div class="header-logo">
        <img id="main-logo"
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
          <router-link v-if="$store.state.token == ''" to="/login"
            >Log In</router-link
          >
        </li>
        <li>
          <router-link v-if="$store.state.token != ''" to="/logout"
            >Logout</router-link
          >
        </li>
        <!-- <li>
          <router-link to="/about">About Us</router-link>
        </li> -->
        <li>
          <router-link to="/">Home</router-link>
        </li>
        <li>
          <router-link v-if="$store.state.token != ''" to="/events">
            My Events</router-link
          >
        </li>
      </ul>
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
  border-radius: 10px;
  margin-bottom: 10px;
  border-color: #fff;
  font-family: "Source Sans Pro";
}

span {
  color: hwb(271 24% 2%);
  font-style: italic;
  font-size: 400%;
}

#header h1 {
  color: #252122;
  font-weight: 900;
  font-size: 2em;
  letter-spacing: -0.035em;
  line-height: 1;
  margin-bottom: 0.5em;
}

#main-logo {
  max-width: 100%;
  height: auto;
  width: 80px;
  position: relative;
  left: 1em;
}

#sync-img {
  max-width: 100%;
  height: 50px;
  width: 240px;
  margin-left: 220px;
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
  color: #753d8b;
  text-decoration: none;
  font-size: 115%;
  padding: 0.6em 1.2em 0.6em 1.2em;
  -moz-transition: background-color 0.25s ease-in-out;
  -webkit-transition: background-color 0.25s ease-in-out;
  -ms-transition: background-color 0.25s ease-in-out;
  transition: background-color 0.25s ease-in-out;
  outline: 0;
}

#nav > ul > li:hover > a,
#nav > ul > li.active > a {
  background: #dd9ddd;
}

#nav > ul > li.current > a {
  background: #dd9ddd;
  color: #fff !important;
  font-weight: 700;
}
</style>