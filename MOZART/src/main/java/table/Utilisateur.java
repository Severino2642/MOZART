package table;

import cnx.Connex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Utilisateur {
    int id;
    String pdp;
    String pseudo;
    int idGenre;
    String email;
    String mdp;
    String dtAjout;

    public Utilisateur() {
    }

    public Utilisateur(int id, String pdp, String pseudo, int idGenre, String email, String mdp, String dtAjout) {
        this.id = id;
        this.pdp = pdp;
        this.pseudo = pseudo;
        this.idGenre = idGenre;
        this.email = email;
        this.mdp = mdp;
        this.dtAjout = dtAjout;
    }

    public Utilisateur(String pdp, String pseudo, int idGenre, String email, String mdp, String dtAjout) {
        this.pdp = pdp;
        this.pseudo = pseudo;
        this.idGenre = idGenre;
        this.email = email;
        this.mdp = mdp;
        this.dtAjout = dtAjout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPdp() {
        return pdp;
    }

    public void setPdp(String pdp) {
        this.pdp = pdp;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getDtAjout() {
        return dtAjout;
    }

    public void setDtAjout(String dtAjout) {
        this.dtAjout = dtAjout;
    }

    public String getGenre(){
        Genre g = new Genre().findById(this.id);
        return g.getType();
    }

    public void insert(){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            String format = "INSERT INTO utilisateur (pdp,pseudo,idGenre,email,mdp,dtAjout) VALUES ('%s','%s',%s,'%s','%s','%s')";
            String sql = String.format(format,this.getPdp(),this.getPseudo(),""+this.getIdGenre(),this.getEmail(),this.getMdp(),this.getDtAjout());
            st.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        finally {
            try {
                if (st != null) st.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
    }
    public void update(){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            String format = "UPDATE utilisateur set pdp='%s',pseudo='%s',idGenre=%s,email='%s',mdp='%s' WHERE id=%s";
            String sql = String.format(format,this.getPdp(),this.getPseudo(),""+this.getIdGenre(),this.getEmail(),this.getMdp(),this.getId());
            st.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        finally {
            try {
                if (st != null) st.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
    }

    public void delete(){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            String format = "DELETE FROM utilisateur WHERE id=%s";
            String sql = String.format(format,this.getId());
            st.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        finally {
            try {
                if (st != null) st.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
    }
    public Utilisateur findByPwd (String email, String mdp){
        Utilisateur result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String format = "SELECT * FROM utilisateur WHERE email='%s' AND mdp='%s'";
            String sql = String.format(format,email,mdp);
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                String pdp = res.getString("pdp");
                String pseudo = res.getString("pseudo");
                int idGenre = res.getInt("idGenre");
                String dtAjout = res.getDate("dtAjout").toString();

                result = new Utilisateur(id,pdp,pseudo,idGenre,email,mdp,dtAjout);
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
        return result;
    }
    public Utilisateur findById (int id){
        Utilisateur result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM utilisateur WHERE id="+id;
            res = st.executeQuery(sql);
            while(res.next()){
                String pdp = res.getString("pdp");
                String pseudo = res.getString("pseudo");
                int idGenre = res.getInt("idGenre");
                String email = res.getString("email");
                String mdp = res.getString("mdp");
                String dtAjout = res.getDate("dtAjout").toString();

                result = new Utilisateur(id,pdp,pseudo,idGenre,email,mdp,dtAjout);
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
        return result;
    }

    public Utilisateur [] findAll (){
        Vector<Utilisateur> result = new Vector<Utilisateur>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM utilisateur ORDER BY id DESC";
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                String pdp = res.getString("pdp");
                String pseudo = res.getString("pseudo");
                int idGenre = res.getInt("idGenre");
                String email = res.getString("email");
                String mdp = res.getString("mdp");
                String dtAjout = res.getDate("dtAjout").toString();
                result.add(new Utilisateur(id,pdp,pseudo,idGenre,email,mdp,dtAjout));
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
        return result.toArray(new Utilisateur []{});
    }

    public Utilisateur [] searchByName (String nom){
        Vector<Utilisateur> result = new Vector<Utilisateur>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM utilisateur WHERE LOWER(pseudo) LIKE LOWER('%"+nom+"%')";
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                String pdp = res.getString("pdp");
                String pseudo = res.getString("pseudo");
                int idGenre = res.getInt("idGenre");
                String email = res.getString("email");
                String mdp = res.getString("mdp");
                String dtAjout = res.getDate("dtAjout").toString();
                result.add(new Utilisateur(id,pdp,pseudo,idGenre,email,mdp,dtAjout));
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
        return result.toArray(new Utilisateur []{});
    }

    public Utilisateur [] searchByNameAndGenre (String nom,int genre){
        Vector<Utilisateur> result = new Vector<Utilisateur>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM utilisateur WHERE 1=1";
            if(nom.compareToIgnoreCase("")!=0){
                sql += " AND LOWER(pseudo) LIKE LOWER('%"+nom+"%')";
            }
            if(genre != -1){
                sql += " AND idGenre ="+genre;
            }
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                String pdp = res.getString("pdp");
                String pseudo = res.getString("pseudo");
                int idGenre = res.getInt("idGenre");
                String email = res.getString("email");
                String mdp = res.getString("mdp");
                String dtAjout = res.getDate("dtAjout").toString();
                result.add(new Utilisateur(id,pdp,pseudo,idGenre,email,mdp,dtAjout));
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
        return result.toArray(new Utilisateur []{});
    }


    public Song[] getRecentPlayed(){
        Vector<Song> result = new Vector<Song>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT song.* , stream.dt_listening as dt_listening FROM song JOIN stream ON stream.idSong = song.id WHERE stream.idUser ="+this.getId()+" GROUP BY song.id,stream.dt_listening ORDER BY stream.dt_listening DESC";
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
                Song music = new Song(id,idArtiste,idCategorie,couverture,title,author,contenue,dt_pub);
                result.add(music);
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