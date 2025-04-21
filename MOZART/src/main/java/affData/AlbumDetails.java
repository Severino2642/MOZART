package affData;

import table.Album;
import table.Artiste;
import table.Song;

public class AlbumDetails {
    Artiste artiste;
    Album album;
    SongDetails[] songs;

    public AlbumDetails() {
    }

    public AlbumDetails(Artiste artiste, Album album, SongDetails[] songs) {
        this.artiste = artiste;
        this.album = album;
        this.songs = songs;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public SongDetails[] getSongs() {
        return songs;
    }

    public void setSongs(SongDetails[] songs) {
        this.songs = songs;
    }

    public AlbumDetails findByAlbum(Album album){
        Artiste artiste = new Artiste().findById(album.getIdArtiste());
        SongDetails [] songs = new SongDetails().findByAlbum(album,artiste,album.getSong());
        return new AlbumDetails(artiste,album,songs);
    }
}
