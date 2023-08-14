BEGIN TRANSACTION;

INSERT INTO "user" (username,password_hash,role,email) VALUES ('user1','user1','ROLE_USER','testemail@gmail.com');
INSERT INTO "user" (username,password_hash,role,email) VALUES ('user2','user2','ROLE_USER','testemail2@gmail.com');
INSERT INTO "user" (username,password_hash,role,email) VALUES ('user3','user3','ROLE_USER','testemail3@gmail.com');

INSERT INTO "event" (name,dj_id,host_id,date,description,start_time,end_time,location) VALUES ('Event Test One',1,2,'1-1-2024','New Years Day',
    '00:00','00:00','810 Matson Place Unit 1205, Cincinnati, OH 45204');
INSERT INTO "event" (name,dj_id,host_id,date,description,start_time,end_time,location) VALUES ('Event Test Two',1,3,'5-12-2025','May the 12th',
    '08:00','12:00','Testing Location');

COMMIT TRANSACTION;
