BEGIN TRANSACTION;

DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS "song";
DROP TABLE IF EXISTS "event";
DROP TABLE IF EXISTS "playlist";
DROP TABLE IF EXISTS "playlist_song";

CREATE TABLE "user" (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(30) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE "song" (
    song_id SERIAL PRIMARY KEY,
    title VARCHAR(75) NOT NULL,
    artist VARCHAR(75) NOT NULL,
    genre VARCHAR(25) NOT NULL,
    duration INTERVAL NOT NULL
);

CREATE TABLE "event" (
    event_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    dj_id INT NOT NULL,
    host_id INT NOT NULL,
    date DATE NOT NULL,
    description TEXT,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL
);

CREATE TABLE "playlist" (
    playlist_id SERIAL PRIMARY KEY,
    event_id INT NOT NULL,
    name VARCHAR(75) NOT NULL,
    CONSTRAINT playlist_fk_event FOREIGN KEY (event_id) REFERENCES "event"(event_id)
);

CREATE TABLE "playlist_song" (
    playlist_id INT NOT NULL,
    song_id INT NOT NULL,
    CONSTRAINT playlist_song_fk_playlist FOREIGN KEY (playlist_id) REFERENCES "playlist"(playlist_id),
    CONSTRAINT playlist_song_fk_song FOREIGN KEY (song_id) REFERENCES "song"(song_id),
    CONSTRAINT playlist_song_pk PRIMARY KEY (playlist_id, song_id)
);

ALTER TABLE "event"
    ADD CONSTRAINT dj_fk FOREIGN KEY (dj_id) REFERENCES "user"(user_id),
    ADD CONSTRAINT host_fk FOREIGN KEY (host_id) REFERENCES "user"(user_id);

COMMIT TRANSACTION;
