package analyseur;

import cnx.Connex;
import table.Album;
import table.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class AnalyseAlbum {
    Utilisateur user;

    public AnalyseAlbum() {
    }

    public AnalyseAlbum(Utilisateur user) {
        this.user = user;
    }

    public Album[] getRandom (){
        Vector<Album> result = new Vector<Album>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM album ORDER BY RANDOM() LIMIT 8";
            res = st.executeQuery(sql);
            while(res.next()){
                String id = res.getString("id");
                int idArtiste = res.getInt("idArtiste");
                String couverture = res.getString("couverture");
                String title = res.getString("title");
                String dt_creation = res.getDate("dt_creation").toString();
                result.add(new Album(id,idArtiste,couverture,title,dt_creation));
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
        return result.toArray(new Album[] {});
    }
}
