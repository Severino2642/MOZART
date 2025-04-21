package analyseur;

import cnx.Connex;
import table.Song;
import table.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class AnalyseSong {
    Utilisateur user;
    public AnalyseSong() {
    }

    public AnalyseSong(Utilisateur user) {
        this.user = user;
    }

    public Song[] getRandom (){
        Vector<Song> result = new Vector<Song>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM song ORDER BY RANDOM() LIMIT 8";
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
                result.add(new Song(id,idArtiste,idCategorie,couverture,title,author,contenue,dt_pub));
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
        return result.toArray(new Song [] {});
    }
}
