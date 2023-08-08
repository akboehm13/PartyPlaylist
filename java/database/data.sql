BEGIN TRANSACTION;

-- INSERT FOR USER TABLE

INSERT INTO "user" (username,password_hash,role,email) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER','randomuseremail@gmail.com');
INSERT INTO "user" (username,password_hash,role,email) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN','randomadminuser@gmail.com');

-- INSERT FOR SONG TABLE (FAKE SONGS) LIST OF GENRES TO USE (POP, HIP-HOP, ROCK, JAZZ, DANCE, HEAVY METAL, ALTERNATIVE, INSTRUMENTAL, INDIE)

INSERT INTO "song" (title, artist, genre, duration) VALUES ('Dance All Night', 'Sunny Beats', 'Pop', '3:45');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Groove Machine', 'MC Rhymes', 'Hip-Hop', '4:20');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Midnight Blues', 'Smooth Jazz Ensemble', 'Jazz', '5:10');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Rock Revolution', 'Power Chords', 'Rock', '3:30');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Echoes of Tomorrow', 'Alternate Realities', 'Alternative', '4:45');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Indie Visions', 'Lonely Wanderer', 'Indie', '3:55');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Disco Fever', 'Groovy Tunes', 'Disco', '4:00');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Eternal Melody', 'Harmonic Waves', 'Instrumental', '2:30');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Dancefloor Anthem', 'Electric Pulse', 'Dance', '3:15');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Metal Mayhem', 'Steel Storm', 'Heavy Metal', '4:40');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Sunset Serenade', 'Melody Lane', 'Pop', '4:10');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Urban Legends', 'City Stories', 'Hip-Hop', '3:20');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Jazzy Nights', 'Saxophone Solace', 'Jazz', '4:50');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Rock & Roll Dreams', 'Riff Masters', 'Rock', '3:40');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Shifting Realities', 'Parallel Universe', 'Alternative', '5:05');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Indie Heart', 'Emotional Echoes', 'Indie', '3:30');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Saturday Night Fever', 'Disco Dynamite', 'Disco', '4:25');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Enchanted Piano', 'Keys of Serenity', 'Instrumental', '3:20');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Electro Groove', 'Synth Magic', 'Dance', '4:15');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Metallic Thunder', 'Iron Titans', 'Heavy Metal', '5:00');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Summer Breeze', 'Beach Vibes', 'Pop', '3:30');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('City Lights', 'Urban Flow', 'Hip-Hop', '4:10');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Saxophone Serenade', 'Smooth Jazz Notes', 'Jazz', '5:20');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Wildfire Rock', 'Rebel Sound', 'Rock', '3:50');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Echoes of Rebellion', 'Alternative Echo', 'Alternative', '4:25');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Indie Echoes', 'Whispering Echoes', 'Indie', '3:45');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Neon Disco', 'Funky Grooves', 'Disco', '4:30');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Piano Dreams', 'Key Harmony', 'Instrumental', '2:50');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Electro Dancefloor', 'Synthetic Beats', 'Dance', '3:15');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Metal Mania', 'Steel Symphony', 'Heavy Metal', '4:40');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Sunset Groove', 'Harmonic Skies', 'Pop', '4:15');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Street Poetry', 'Urban Rhymes', 'Hip-Hop', '3:30');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Jazz Noir', 'Midnight Jazz Ensemble', 'Jazz', '4:40');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Rock Fusion', 'Electrified Sound', 'Rock', '3:55');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Parallel Realities', 'Alternate Dimension', 'Alternative', '4:50');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Indie Odyssey', 'Wandering Souls', 'Indie', '3:40');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Glowing Disco', 'Luminous Beats', 'Disco', '4:25');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Ethereal Keys', 'Heavenly Harmony', 'Instrumental', '3:20');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Electro Euphoria', 'Digital Dreams', 'Dance', '4:15');
INSERT INTO "song" (title, artist, genre, duration) VALUES ('Raging Metal', 'Thunderstorm', 'Heavy Metal', '5:10');

INSERT INTO "event" (name, dj_id, host_id, date, description, start_time, end_time) VALUES ('TechElevator Graduation', 2, 1, '12/18/2023', 'This marks the end of the 4 month boot camp! Congrats!', '12:00:00','14:00:00');

INSERT INTO "playlist" (event_id, name) VALUES (1, 'TEchnoElevator');

INSERT INTO "playlist_song" (playlist_id, song_id) VALUES (1, 5);
INSERT INTO "playlist_song" (playlist_id, song_id) VALUES (1, 7);
INSERT INTO "playlist_song" (playlist_id, song_id) VALUES (1, 10);
INSERT INTO "playlist_song" (playlist_id, song_id) VALUES (1, 19);
INSERT INTO "playlist_song" (playlist_id, song_id) VALUES (1, 22);
INSERT INTO "playlist_song" (playlist_id, song_id) VALUES (1, 23);
INSERT INTO "playlist_song" (playlist_id, song_id) VALUES(1, 35);
INSERT INTO "playlist_song" (playlist_id, song_id) VALUES (1, 39);

COMMIT TRANSACTION;
