package view;

import cnx.Connex;
import table.Album;
import table.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class FavoriteAlbum {
    Album album;
    int idUser;
    String dt_added;

    public FavoriteAlbum() {
    }

    public FavoriteAlbum(Album album, int idUser, String dt_added) {
        this.album = album;
        this.idUser = idUser;
        this.dt_added = dt_added;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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

    public FavoriteAlbum [] findByidUser (int idUser){
        Vector<FavoriteAlbum> result = new Vector<FavoriteAlbum>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM v_favoriteAlbum WHERE idUser="+idUser;
            res = st.executeQuery(sql);
            while(res.next()){
                String id = res.getString("id");
                int idArtiste = res.getInt("idArtiste");
                String couverture = res.getString("couverture");
                String title = res.getString("title");
                String dt_creation = res.getDate("dt_creation").toString();
                String dt_added = res.getDate("dt_added").toString();
                Album a = new Album(id,idArtiste,couverture,title,dt_creation);
                result.add(new FavoriteAlbum(a,idUser,dt_added));
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
        return result.toArray(new FavoriteAlbum[] {});
    }

}
