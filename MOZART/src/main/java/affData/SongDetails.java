package affData;

import table.Album;
import table.Artiste;
import table.Song;
import table.Utilisateur;

import java.util.Vector;

public class SongDetails {
    Song music;
    int nbListener;
    Album album;
    Artiste artiste;
    public SongDetails() {
    }

    public SongDetails(Song music, int nbListener, Album album, Artiste artiste) {
        this.music = music;
        this.nbListener = nbListener;
        this.album = album;
        this.artiste = artiste;
    }

    public Song getMusic() {
        return music;
    }

    public void setMusic(Song music) {
        this.music = music;
    }

    public int getNbListener() {
        return nbListener;
    }

    public void setNbListener(int nbListener) {
        this.nbListener = nbListener;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public SongDetails [] findByListSong(Song [] listSong){
        Vector<SongDetails> result = new Vector<SongDetails>();
        for (int i=0 ; i<listSong.length ;i++){
            result.add(findBySong(listSong[i]));
        }
        SongDetails [] allSong = result.toArray(new SongDetails[] {});
        return allSong;
    }

    public SongDetails findBySong(Song music){
        int nblistener = music.getNbStream();
        Album album = music.getAlbum();
        Artiste artiste = new Artiste().findById(music.getIdArtiste());
        return new SongDetails(music,nblistener,album,artiste);
    }

    public SongDetails [] findByArtiste(Artiste artiste){
        Vector<SongDetails> result = new Vector<SongDetails>();
        Song [] listSong = new Song().findByIdArtiste(artiste.getId());
        for (int i=0 ; i<listSong.length ;i++){
            int nblistener = listSong[i].getNbStream();
            Album album = listSong[i].getAlbum();
            result.add(new SongDetails(listSong[i],nblistener,album,artiste));
        }
        SongDetails [] allSong = result.toArray(new SongDetails[] {});
        return allSong;
    }

    public SongDetails [] findByAlbum(Album album,Artiste artiste,Song [] listSong){
        Vector<SongDetails> result = new Vector<SongDetails>();
        for (int i=0 ; i<listSong.length ;i++){
            int nblistener = listSong[i].getNbStream();
            result.add(new SongDetails(listSong[i],nblistener,album,artiste));
        }
        SongDetails [] allSong = result.toArray(new SongDetails[] {});
        return allSong;
    }


//    public SongDetails [] triageByListener(SongDetails [] listSong){
//        SongDetails [] result = new SongDetails[listSong.length];
//        int count = 0;
//        Vector index = new Vector();
//        while(count < result.length){
//            int top = -1;
//            for (int i=0 ; i<listSong.length ; i++){
//                if (verifTable(index,i)==-1){
//                    int nbListener = listSong[i].getNbListener();
//                    int a = 0;
//                    for (int j=0 ; j<listSong.length ; j++){
//                        if (verifTable(index,i)==-1){
//                            if (nbListener < listSong[j].getNbListener()){
//                                a = 1;
//                            }
//                        }
//                    }
//                    if (a==0){
//                        top = i;
//                        break;
//                    }
//                }
//            }
//            if (top != -1){
//                index.add(top);
//                count++;
//            }
//        }
//        for (int i = 0; i<index.size() ;i++){
//            int indice = (int) index.get(i);
//            result[i] = listSong[indice];
//        }
//        return result;
//    }
}
