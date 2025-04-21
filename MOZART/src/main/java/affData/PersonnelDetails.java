package affData;

import table.Album;
import table.Artiste;
import table.Song;

import java.util.Vector;

public class PersonnelDetails {
    public int verifStringTable (Vector table,String content){
        int result = -1;
        for (int i = 0; i<table.size() ; i++){
            String nb = (String) table.get(i);
            if (content.compareToIgnoreCase(nb)==0){
                result = i;
                break;
            }
        }
        return result;
    }
    public int verifIntTable (Vector table,int content){
        int result = -1;
        for (int i = 0; i<table.size() ; i++){
            int nb = (int) table.get(i);
            if (content==nb){
                result = i;
                break;
            }
        }
        return result;
    }
    public Album[] getRelatedAlbum (Song[] listSong){
        Vector<Album> result = new Vector<Album>();
        Vector indice = new Vector();
        for (int i=0 ; i<listSong.length ;i++) {
            Album album = listSong[i].getAlbum();
            if(album != null){
                if (verifStringTable(indice,album.getId())==-1){
                    result.add(album);
                    indice.add(album.getId());
                }
            }
        }
        return result.toArray(new Album[] {});
    }
    public Artiste[] getRelatedArtiste (Album[] listAlbum){
        Vector<Artiste> result = new Vector<Artiste>();
        Vector indice = new Vector();
        for (int i=0 ; i<listAlbum.length ;i++) {
            int idArtiste = listAlbum[i].getIdArtiste();
            if (verifIntTable(indice,idArtiste)==-1){
                result.add(new Artiste().findById(idArtiste));
                indice.add(idArtiste);
            }
        }
        return result.toArray(new Artiste[] {});
    }


}
