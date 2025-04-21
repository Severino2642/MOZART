package analyseur;

import cnx.Connex;
import table.Artiste;
import table.Song;
import table.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class AnalyseArtiste {
    Utilisateur user;

    public AnalyseArtiste() {
    }

    public AnalyseArtiste(Utilisateur user) {
        this.user = user;
    }

    public Artiste [] getRandom (){
        Vector<Artiste> result = new Vector<Artiste>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String format = "SELECT * FROM artiste ORDER BY RANDOM() LIMIT 8";
            res = st.executeQuery(format);
            while(res.next()){
                int id = res.getInt("id");
                String pdp = res.getString("pdp");
                String pdc = res.getString("pdc");
                String pseudo = res.getString("pseudo");
                int idGenre = res.getInt("idGenre");
                String email = res.getString("email");
                String mdp = res.getString("mdp");
                String dtAjout = res.getDate("dtAjout").toString();

                result.add(new Artiste(id,pdp,pdc,pseudo,idGenre,email,mdp,dtAjout));
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
        return result.toArray(new Artiste[] {});
    }
}
