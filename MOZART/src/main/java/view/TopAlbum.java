package view;

import cnx.Connex;
import table.Album;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class TopAlbum {
    Album album;
    int nbListener;

    public TopAlbum() {
    }
    public TopAlbum(Album album, int nbListener) {
        this.album = album;
        this.nbListener = nbListener;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getNbListener() {
        return nbListener;
    }

    public void setNbListener(int nbListener) {
        this.nbListener = nbListener;
    }

    public TopAlbum [] findAll (){
        Vector<TopAlbum> result = new Vector<TopAlbum>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM v_topAlbum ORDER BY nblistener DESC";
            res = st.executeQuery(sql);
            while(res.next()){
                String id = res.getString("id");
                int idArtiste = res.getInt("idArtiste");
                String couverture = res.getString("couverture");
                String title = res.getString("title");
                String dt_creation = res.getDate("dt_creation").toString();
                int nbListener = res.getInt("nblistener");
                Album a = new Album(id,idArtiste,couverture,title,dt_creation);
                result.add(new TopAlbum(a,nbListener));
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
        return result.toArray(new TopAlbum[] {});
    }
}
