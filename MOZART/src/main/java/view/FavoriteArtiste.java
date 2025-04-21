package view;

import cnx.Connex;
import table.Artiste;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class FavoriteArtiste {
    Artiste artiste;
    int idUser;
    String dt_added;

    public FavoriteArtiste() {
    }

    public FavoriteArtiste(Artiste artiste, int idUser, String dt_added) {
        this.artiste = artiste;
        this.idUser = idUser;
        this.dt_added = dt_added;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
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

    public FavoriteArtiste [] findByidUser (int idUser){
        Vector<FavoriteArtiste> result = new Vector<FavoriteArtiste>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String format = "SELECT * FROM v_favoriteArtiste WHERE idUser="+idUser;
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
                String dtAdded = res.getDate("dt_added").toString();
                Artiste artiste = new Artiste(id,pdp,pdc,pseudo,idGenre,email,mdp,dtAjout);
                result.add(new FavoriteArtiste(artiste,idUser,dtAdded));
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
        return result.toArray(new FavoriteArtiste[] {});


    }
}
