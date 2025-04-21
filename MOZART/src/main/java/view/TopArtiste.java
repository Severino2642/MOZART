package view;

import cnx.Connex;
import table.Artiste;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class TopArtiste {
    Artiste artiste;
    int nbListener;

    public TopArtiste() {
    }
    public TopArtiste(Artiste artiste, int nbListener) {
        this.artiste = artiste;
        this.nbListener = nbListener;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public int getNbListener() {
        return nbListener;
    }

    public void setNbListener(int nbListener) {
        this.nbListener = nbListener;
    }

    public TopArtiste [] findAll (){
        Vector<TopArtiste> result = new Vector<TopArtiste>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String format = "SELECT * FROM v_topArtiste ORDER BY nblistener DESC";
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
                int nblistener = res.getInt("nblistener");
                Artiste artiste = new Artiste(id,pdp,pdc,pseudo,idGenre,email,mdp,dtAjout);
                result.add(new TopArtiste(artiste,nblistener));
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
        return result.toArray(new TopArtiste[] {});
    }
}
