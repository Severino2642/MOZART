package view;

import affData.SongDetails;
import cnx.Connex;
import table.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class FavoriteSong {
    SongDetails music;
    int idUser;
    String dt_added;

    public FavoriteSong() {
    }
    public FavoriteSong(SongDetails music, int idUser, String dt_added) {
        this.music = music;
        this.idUser = idUser;
        this.dt_added = dt_added;
    }

    public SongDetails getMusic() {
        return music;
    }

    public void setMusic(SongDetails music) {
        this.music = music;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDt_added() {
        return dt_added;
    }

    public void setDt_added(String dt_added) {
        this.dt_added = dt_added;
    }

    public FavoriteSong [] findByidUser(int idUser){
        Vector<FavoriteSong> result = new Vector<FavoriteSong>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM v_favoriteSong WHERE idUser="+idUser;
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
                String dt_added = res.getDate("dt_added").toString();
                SongDetails music = new SongDetails().findBySong(new Song(id,idArtiste,idCategorie,couverture,title,author,contenue,dt_pub));
                result.add(new FavoriteSong(music,idUser,dt_added));
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
        return result.toArray(new FavoriteSong [] {});
    }
}
