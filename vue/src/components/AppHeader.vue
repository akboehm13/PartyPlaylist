<template>
  <section id="header">
    <h1>
      <img
        src="https://static.vecteezy.com/system/resources/previews/017/784/773/non_2x/sync-icon-in-flat-design-style-server-signs-illustration-png.png"
        alt="Logo"
      />
    </h1>
    <span>SYNC</span>
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
  /* position: relative; */
  background: #ece2ee;
  border-bottom: solid 1px #ccc;
  /* padding: 1em 0 1em 0; */
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
  width: 100px;
  position: relative;
  left: 1em;
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
  font-style: italic;
  font-weight: bold;
  margin: 0 0.35em 0 0.35em;
}

#nav > ul > li > a {
  border-radius: 5px;
  color: #000000;
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