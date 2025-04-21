package table;

import cnx.Connex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Artiste {
    int id;
    String pdp;
    String pdc;
    String pseudo;
    int idGenre;
    String email;
    String mdp;
    String dtAjout;
    public Artiste() {
    }

    public Artiste(int id, String pdp, String pdc, String pseudo, int idGenre, String email, String mdp, String dtAjout) {
        this.id = id;
        this.pdp = pdp;
        this.pdc = pdc;
        this.pseudo = pseudo;
        this.idGenre = idGenre;
        this.email = email;
        this.mdp = mdp;
        this.dtAjout = dtAjout;
    }

    public Artiste(String pdp, String pdc, String pseudo, int idGenre, String email, String mdp, String dtAjout) {
        this.pdp = pdp;
        this.pdc = pdc;
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

    public String getPdc() {
        return pdc;
    }

    public void setPdc(String pdc) {
        this.pdc = pdc;
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

    public void insert(){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            String format = "INSERT INTO artiste (pdp,pdc,pseudo,idGenre,email,mdp,dtAjout) VALUES ('%s','%s','%s',%s,'%s','%s','%s')";
            String sql = String.format(format,this.getPdp(),this.getPdc(),this.getPseudo(),""+this.getIdGenre(),this.getEmail(),this.getMdp(),this.getDtAjout());
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
            String format = "UPDATE artiste set pdp='%s',pdc='%s',pseudo='%s',idGenre=%s,email='%s',mdp='%s' WHERE id=%s";
            String sql = String.format(format,this.getPdp(),this.getPdc(),this.getPseudo(),""+this.getIdGenre(),this.getEmail(),this.getMdp(),this.getId());
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
            String format = "DELETE FROM artiste WHERE id=%s";
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
    public Artiste findByPwd (String email, String mdp){
        Artiste result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String format = "SELECT * FROM artiste WHERE email='%s' AND mdp='%s'";
            String sql = String.format(format,email,mdp);
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                String pdp = res.getString("pdp");
                String pdc = res.getString("pdc");
                String pseudo = res.getString("pseudo");
                int idGenre = res.getInt("idGenre");
                String dtAjout = res.getDate("dtAjout").toString();

                result = new Artiste(id,pdp,pdc,pseudo,idGenre,email,mdp,dtAjout);
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
    public Artiste findById (int id){
        Artiste result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String format = "SELECT * FROM artiste WHERE id=%s";
            String sql = String.format(format,id);
            res = st.executeQuery(sql);
            while(res.next()){
                String pdp = res.getString("pdp");
                String pdc = res.getString("pdc");
                String pseudo = res.getString("pseudo");
                int idGenre = res.getInt("idGenre");
                String email = res.getString("email");
                String mdp = res.getString("mdp");
                String dtAjout = res.getDate("dtAjout").toString();
                result = new Artiste(id,pdp,pdc,pseudo,idGenre,email,mdp,dtAjout);
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

    public Artiste [] findAll (){
        Vector<Artiste> result = new Vector<Artiste>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String format = "SELECT * FROM artiste ORDER BY id DESC";
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

    public Artiste [] searchByName (String nom){
        Vector<Artiste> result = new Vector<Artiste>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM artiste WHERE LOWER(pseudo) LIKE LOWER('%"+nom+"%')";
            res = st.executeQuery(sql);
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

    public Artiste [] searchByNameAndGenre (String nom,int genre){
        Vector<Artiste> result = new Vector<Artiste>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM artiste WHERE 1=1";
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

    public Utilisateur [] getFollowers (){
        Vector<Utilisateur> result = new Vector<Utilisateur>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM utilisateur WHERE id IN (SELECT idUser FROM follower WHERE idArtiste = "+this.getId()+")ORDER BY id DESC";
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

}
