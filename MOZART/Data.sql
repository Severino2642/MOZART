create database mozart;
\c mozart;

create table genre (
    id Serial PRIMARY KEY,
    type VARCHAR(100)
);

create table utilisateur (
    id Serial PRIMARY KEY,
    pdp VARCHAR(255),
    pseudo VARCHAR(255),
    idGenre int,
    email VARCHAR(255),
    mdp VARCHAR(255),
    dtAjout date
);

create table admin (
    id Serial PRIMARY KEY,
    pseudo VARCHAR(255),
    email VARCHAR(255),
    mdp VARCHAR(255)
);

create table artiste (
    id Serial PRIMARY KEY,
    pdp VARCHAR(255),
    pdc VARCHAR(255),
    pseudo VARCHAR(255),
    idGenre int,
    email VARCHAR(255),
    mdp VARCHAR(255),
    dtAjout date
);

create table follower (
	id Serial PRIMARY KEY,
	idArtiste int,
	idUser int,
	dt_follow date
);



create table categorie (
	id Serial PRIMARY KEY,
	nom VARCHAR (255)
);

create table song (
	id Serial PRIMARY KEY,
	idArtiste int,
	idCategorie int,
	couverture VARCHAR(255),
	title VARCHAR(255),
	author VARCHAR(255),
	contenue VARCHAR(255),
	dt_pub date
);

create table favorite (
	id Serial PRIMARY KEY,
	idUser int,
	idItem VARCHAR(255),
	type VARCHAR(255),
	dt_favorite date
);
 
create table stream (
	id Serial PRIMARY KEY,
	idSong int,
	idUser int,
	dt_listening date
);

create table album (
	id VARCHAR(255) PRIMARY KEY,
	idArtiste int,
	couverture VARCHAR(255),
	title VARCHAR(255),
	dt_creation date
);

create table playlist (
	id VARCHAR(255) PRIMARY KEY,
	idAuthor int,
	couverture VARCHAR(255),
	title VARCHAR(255),
	dt_creation date
);

create table library(
	id Serial PRIMARY KEY,
	idItem VARCHAR(255),
	idSong int
);


SELECT * FROM song WHERE id NOT IN (SELECT idSong FROM library WHERE LOWER(idItem) LIKE LOWER('%ALBUM%'));

-- CREATE OR REPLACE VIEW v_libAlbum AS
-- SELECT a.id as idAlbum , l.idSong as idSong
-- FROM library as l
-- JOIN album as a ON a.id = l.idItem
-- GROUP BY a.id,l.idSong;

-- Top Song
CREATE OR REPLACE VIEW v_topSong AS
SELECT song.* , COUNT(stream.idUser) as nblistener
FROM song 
LEFT JOIN stream ON stream.idSong = song.id
GROUP BY song.id ORDER BY nblistener DESC;

-- Top album
CREATE OR REPLACE VIEW v_topAlbum AS
SELECT a.* , sum(s.nblistener) as nblistener
FROM album as a
JOIN library as l ON a.id = l.idItem
JOIN ( SELECT song.* , COUNT(stream.idUser) as nblistener
		FROM song 
		LEFT JOIN stream ON stream.idSong = song.id
		GROUP BY song.id ORDER BY nblistener DESC ) as s ON s.id = l.idSong
GROUP BY a.id;

-- Top artiste
CREATE OR REPLACE VIEW v_topArtiste AS
SELECT a.* , sum(s.nblistener) as nblistener
FROM artiste as a
         JOIN ( SELECT song.* , COUNT(stream.idUser) as nblistener
                FROM song
                         LEFT JOIN stream ON stream.idSong = song.id
                GROUP BY song.id ORDER BY nblistener DESC ) as s ON s.idArtiste = a.id
GROUP BY a.id;


CREATE OR REPLACE VIEW v_favoriteSong AS
SELECT s.* , f.idUser as idUser , f.dt_favorite as dt_added
FROM song as s
JOIN favorite as f ON f.idItem = CAST(s.id AS VARCHAR)
ORDER BY f.id DESC;

CREATE OR REPLACE VIEW v_favoriteAlbum AS
SELECT a.* , f.idUser as idUser , f.dt_favorite as dt_added
FROM album as a
JOIN favorite as f ON f.idItem = CAST(a.id AS VARCHAR)
ORDER BY f.id DESC;

CREATE OR REPLACE VIEW v_favoriteArtiste AS
SELECT a.*, f.idUser as idUser , f.dt_follow as dt_added
FROM artiste as a
JOIN follower as f ON f.idArtiste = a.id
ORDER BY f.id DESC;

create sequence seq_pdp;
create sequence seq_pdc;

create sequence seq_couverture;
create sequence seq_contenue;
create sequence seq_album;
create sequence seq_playlist;

INSERT INTO genre (type) VALUES ('Homme');
INSERT INTO genre (type) VALUES ('Femme');

INSERT INTO categorie (nom) VALUES ('Acoustique');
INSERT INTO categorie (nom) VALUES ('Pop');
INSERT INTO categorie (nom) VALUES ('Dancehall');
INSERT INTO categorie (nom) VALUES ('Rap');
INSERT INTO categorie (nom) VALUES ('R&B');