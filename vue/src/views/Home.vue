<template>
  <div>
    <title>Sync</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <body>
      <section id="main">
        <div class="container">
          <article class="box post">
            <header>
              <h3><strong>SYNC</strong></h3>
              <p>Your Ultimate DJ Companion and Event Playlist Manager!</p>
            </header>
            <p>
              <strong
                >Elevate your DJ experience to the next level with Sync, the
                groundbreaking application designed exclusively for DJs and
                event enthusiasts. Seamlessly manage global playlists, curate
                electrifying music selections, and bring people together like
                never before. Sync empowers DJs to create, collaborate, and
                craft unforgettable moments with ease.</strong
              >
            </p>
            <p>
              <strong
                >But Sync doesn't stop there. Party hosts are invited to the
                spotlight, enjoying a seamless interface to manage events and
                invite their guests. Hosts can effortlessly extend their event's
                reach, and guests can request songs, vote on playlist tracks,
                and actively participate in curating the musical atmosphere.
                Sync isn't just an app; it's a revolution in event management,
                fostering collaboration, creativity, and connection. Say goodbye
                to ordinary parties and welcome Sync into your world – where
                music unites, memories are made, and events are elevated to
                unprecedented heights.</strong
              >
            </p>
          </article>
        </div>
      </section>

      <section id="footer">
        <div class="container">
          <div class="row">
            <div class="col-8">
              <section>
                <header>
                  <h2>Upcoming events</h2>
                </header>
                <ul class="dates">
                  <li v-for="event in events" :key="event.event_id">
                    <span class="date">{{ event.date }}</span>
                    <span class="date">parseDate({{ event.date }})</span>
                    <div class="date-content">
                      <h3>
                        <router-link
                          :to="{
                            name: 'eventView',
                            params: { id: event.eventId },
                          }"
                        >
                          {{ event.name }}</router-link
                        >
                      </h3>
                      <p>{{ event.description }}</p>
                    </div>
                  </li>
                </ul>
              </section>
            </div>
            <div class="col-4">
              <section>
                <header>
                  <h2>What's Sync all about?</h2>
                </header>
                <p>
                  At <strong>Sync</strong>, we're on a mission to redefine the
                  way DJs, party hosts, and music enthusiasts connect and
                  collaborate. Our passion is fueled by the belief that music
                  has the power to bring people together, transcend barriers,
                  and create unforgettable memories. We've created a platform
                  that goes beyond traditional event management, offering a
                  seamless blend of technology and creativity that empowers DJs
                  to curate unforgettable playlists while giving hosts and
                  guests the tools to actively shape the musical experience.
                </p>
                <footer>
                  <img
                    src="https://media.istockphoto.com/id/1211193774/vector/friend-group-at-retro-dance-party-cartoon-people-dancing-and-having-fun.jpg?s=170667a&w=0&k=20&c=gIExOztj0eJF2VqNld03G_TBbRI3hyALvCJWmM_Lv1k="
                    alt="Sync Image"
                    class="rounded-image"
                  />
                </footer>
              </section>
            </div>
            <div class="col-4">
              <section>
                <header>
                  <h2>Let's get social!</h2>
                </header>
                <ul class="divided">
                  <li>
                    <a href="#" class="fa fa-snapchat"></a>
                    <a href="#" class="fa fa-twitter"></a>
                    <a href="#" class="fa fa-linkedin"></a>
                    <a href="#" class="fa fa-instagram"></a>
                  </li>
                </ul>
              </section>
            </div>

            <div class="party-section">
              <section>
                <header>
                  <h2>Where we like to party</h2>
                </header>
                <ul class="contact">
                  <li>
                    <img
                      src="https://static.vecteezy.com/system/resources/previews/017/784/773/non_2x/sync-icon-in-flat-design-style-server-signs-illustration-png.png"
                    />
                    <h3>Address</h3>
                    <p>
                      Sync Inc<br />
                      1776 Mentor Ave #361<br />
                      Norwood, Ohio 45212
                    </p>
                  </li>
                  <li>
                    <h3>Mail</h3>
                    <p><a href="#">support@sync.com</a></p>
                  </li>
                </ul>
              </section>
            </div>
          </div>
        </div>
      </section>
    </body>
  </div>
</template>

<script>
import eventAPI from "../services/EventService.js";

export default {
  name: "home",

  data() {
    return {
      events: [],
    };
  },
  created() {
    eventAPI.list().then((response) => {
      this.events = response.data;
    });
  },
};
</script>


<style scoped>
@import url("https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,900,300italic");

html,
body,
div,
h1,
h2,
h3,
h4,
h5,
h6,
p,
address,
ul,
li,
footer,
header,
menu,
nav,
summary {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
}

body {
  line-height: 1;
}

ul {
  list-style: none;
}

body {
  background: black;
}

body,
input,
textarea,
select {
  font-family: "Source Sans Pro";
  font-weight: 300;
  color: #753d8b;
  font-size: 14pt;
  line-height: 1.75em;
}

h1,
h2,
h4,
h5,
h6 {
  color: #252122;
  font-weight: 700;
}

h2 {
  font-size: 1.5em;
}

h3 {
  font-size: 3em;
}

.container {
  margin: 0 auto;
  max-width: 100%;
  width: 70em;
}

.row {
  display: flex;
  flex-wrap: wrap;
  box-sizing: border-box;
  align-items: stretch;
}

.row > * {
  box-sizing: border-box;
}

.row.aln-left {
  justify-content: flex-start;
}

.row.aln-center {
  justify-content: center;
}

.row.aln-right {
  justify-content: flex-end;
}

.row.aln-top {
  align-items: flex-start;
}

.row.aln-middle {
  align-items: center;
}

.row.aln-bottom {
  align-items: flex-end;
}

.row > .col-4 {
  width: 33.33333%;
}

.row > .off-4 {
  margin-left: 33.33333%;
}

.col-4 {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.col-4 section {
  margin-bottom: 30px;
}

.row {
  margin-top: -50px;
  margin-left: -50px;
}

.row > * {
  padding: 50px 0 0 50px;
}

.box {
  background: #ece2ee;
  border-radius: 10px;
  border-bottom: solid 1px #ccc;
  padding: 2.75em 1.75em 2.75em 1.75em;
}

.box.post header {
  padding-top: 1em;
  margin: 0 0 2em 0;
}

.box.post h2 {
  font-size: 3em;
  letter-spacing: -0.015em;
}

.box.post header > p {
  margin-top: 1.25em;
  font-size: 1.25em;
}

ul.links {
  list-style: none;
  padding-left: 0;
}

ul.links li {
  display: inline-block;
  border-left: solid 1px rgba(255, 255, 255, 0.05);
  padding: 0 0 0 1em;
  margin: 0 0 0 1em;
}

ul.contact {
  list-style: none;
  padding-left: 0;
}

ul.contact li {
  position: relative;
  border-top: solid 1px #ddd;
  padding: 1em 0 1.3em 7em;
}

ul.contact li h3 {
  position: absolute;
  left: 0;
  top: 1.3em;
  font-size: 1.1em;
}

ul.contact li img {
  position: absolute;
  left: 0;
  top: 3.2em;
  width: 50px;
  height: auto;
}

ul.contact li:first-child {
  padding-top: 0;
  border-top: 0;
}

ul.contact li:first-child h3 {
  top: 0;
}

.party-section {
  margin-left: 157px;
}
ul.dates {
  list-style: none;
  padding-left: 0;
}

ul.dates li {
  position: relative;
  border-top: solid 1px #ddd;
  padding: 0.9em 0 1.1em 6.75em;
}

ul.dates .date {
  display: block;
  position: absolute;
  left: 0;
  top: 1.3em;
  background-color: #753d8b;
  height: 3em;
  width: 5em;
  text-align: center;
  color: #fff;
  font-style: bold;
  line-height: 1em;
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
}

ul.dates .date-content {
  background-color: #753d8b;
  text-align: center;
  color: #fff;
  line-height: 2em;
  padding: 0.5em 0.75em;
  border-radius: 5px;
}

ul.dates .date:after {
  content: "";
  position: absolute;
  bottom: 0;
  right: -1.2em;
  border-left: solid 1.25em #f3f3f3;
  border-top: solid 1.8em transparent;
  border-bottom: solid 1.8em transparent;
}

.date-container {
  display: flex;
  align-items: center;
}

ul.dates .date strong {
  display: block;
  font-size: 1.75em;
  padding-top: 0.15em;
}

ul.dates h3 {
  font-size: 1.1em;
}

ul.dates p {
  margin: 0;
}

ul.dates li:first-child {
  border-top: 0;
  padding-top: 0;
}

#main {
  position: relative;
  background: #f7f7f7
    url("https://pbs.twimg.com/media/FOMC59BaMAEuCh_.jpg:large");
  padding: 4em 0 4em 0;
}

#footer {
  position: relative;
  padding: 4em 0;
}

#footer a {
  color: white;
}

#footer h2,
#footer h3,
#footer h4,
#footer h5,
#footer h6,
#footer {
  color: white;
}

#footer ul.contact li,
#footer ul.dates li,
#footer ul.divided li {
  border-top-color: #353434;
  border-top-color: rgba(255, 255, 255, 0.05);
}

#footer header {
  margin: 0 0 3em 0;
}

.rounded-image {
  border-radius: 10px;
}

section p {
  margin-bottom: 30px;
}

.fa {
  padding: 10px;
  font-size: 30px;
  width: 50px;
  text-align: center;
  text-decoration: none;
  border-radius: 20px;
}
.fa-twitter {
  background: #55acee;
  color: white;
}
.fa-snapchat {
  background: yellow;
  color: white;
}

.fa-linkedin {
  background: #0a66c2;
  color: white;
}

.fa-instagram {
  background: red;
  color: white;
}
</style>