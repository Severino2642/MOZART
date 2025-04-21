package view;

import affData.SongDetails;
import cnx.Connex;
import table.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class TopSong {
    Song song;
    int nbListener;

    public TopSong() {
    }
    public TopSong(Song song, int nbListener) {
        this.song = song;
        this.nbListener = nbListener;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public int getNbListener() {
        return nbListener;
    }

    public void setNbListener(int nbListener) {
        this.nbListener = nbListener;
    }

    public TopSong [] findAll(){
        Vector<TopSong> result = new Vector<TopSong>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM v_topSong ORDER BY nblistener DESC";
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                int idArtiste = res.getInt("idArtiste");
                int idCategorie = res.getInt("idCategorie");
                String couverture = res.getString("couverture");
                String title = res.getString("title");
                String author = res.getString("author");
                String contenue = res.getString("contenue");
                String dt_pub = res.getDate("dt_pub").toString();
                int nbListener = res.getInt("nblistener");
                Song music = new Song(id,idArtiste,idCategorie,couverture,title,author,contenue,dt_pub);
                result.add(new TopSong(music,nbListener));
            }
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        finally {
            try {
                if (st != null) st.close();
                if (con!= null) con.close();
                if (res!= null) res.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
        return result.toArray(new TopSong [] {});
    }
}
