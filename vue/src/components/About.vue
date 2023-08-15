<template>
  <div id="about">
    <header>
      <h2>What's Sync all about?</h2>
    </header>
    <p>
      At <strong>Sync</strong>, we're on a mission to redefine the way DJs,
      party hosts, and music enthusiasts connect and collaborate. Our passion is
      fueled by the belief that music has the power to bring people together,
      transcend barriers, and create unforgettable memories. We've created a
      platform that goes beyond traditional event management, offering a
      seamless blend of technology and creativity that empowers DJs to curate
      unforgettable playlists while giving hosts and guests the tools to
      actively shape the musical experience.
    </p>
    <section id="img">
      <img
        src="https://media.istockphoto.com/id/1211193774/vector/friend-group-at-retro-dance-party-cartoon-people-dancing-and-having-fun.jpg?s=170667a&w=0&k=20&c=gIExOztj0eJF2VqNld03G_TBbRI3hyALvCJWmM_Lv1k="
        alt="Sync Image"
        class="rounded-image"
      />
    </section>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#about {
  display: grid;
  grid-template-columns: 1fr, 1fr;
  grid-template-rows: 1fr, 6fr;
  grid-template-areas:
    "img header"
    "img p";
  background: #252122;
  height: 100vh;
  justify-items: center;
}

#img {
  grid-area: img;
}

p {
  grid-area: p;
  color: white;
}

header {
  grid-area: header;
  color: white;
  justify-content: center;
}
</style>