package affData;

import table.Album;
import table.Artiste;
import table.Song;
import table.Utilisateur;

public class ArtisteDetails {
    Artiste artiste;
    Utilisateur [] fans;
    SongDetails [] songs;
    Album [] Albums;

    public ArtisteDetails() {
    }

    public ArtisteDetails(Artiste artiste, Utilisateur[] fans, SongDetails[] songs, Album[] albums) {
        this.artiste = artiste;
        this.fans = fans;
        this.songs = songs;
        Albums = albums;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public Utilisateur[] getFans() {
        return fans;
    }

    public void setFans(Utilisateur[] fans) {
        this.fans = fans;
    }

    public SongDetails[] getSongs() {
        return songs;
    }

    public void setSongs(SongDetails[] songs) {
        this.songs = songs;
    }

    public Album[] getAlbums() {
        return Albums;
    }

    public void setAlbums(Album[] albums) {
        Albums = albums;
    }

    public ArtisteDetails findByIdArtiste(int idArtiste){
        Artiste artiste = new Artiste().findById(idArtiste);
        Utilisateur [] fans = artiste.getFollowers();
        SongDetails [] songs = new SongDetails().findByArtiste(artiste);
        Album [] albums = new Album().findByIdArtiste(idArtiste);
        return new ArtisteDetails(artiste,fans,songs,albums);
    }
}
