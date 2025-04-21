package table;

import cnx.Connex;
import view.TopSong;

import java.sql.*;
import java.util.Vector;

public class Song {
    int id;
    int idArtiste;
    int idCategorie;
    String couverture;
    String title;
    String Author;
    String contenue;
    String dt_pub;

    public Song() {
    }

    public Song(int id, int idArtiste, int idCategorie, String couverture, String title, String author, String contenue, String dt_pub) {
        this.id = id;
        this.idArtiste = idArtiste;
        this.idCategorie = idCategorie;
        this.couverture = couverture;
        this.title = title;
        Author = author;
        this.contenue = contenue;
        this.dt_pub = dt_pub;
    }

    public Song(int idArtiste, int idCategorie, String couverture, String title, String author, String contenue, String dt_pub) {
        this.idArtiste = idArtiste;
        this.idCategorie = idCategorie;
        this.couverture = couverture;
        this.title = title;
        Author = author;
        this.contenue = contenue;
        this.dt_pub = dt_pub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCouverture() {
        return couverture;
    }

    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public String getDt_pub() {
        return dt_pub;
    }

    public void setDt_pub(String dt_pub) {
        this.dt_pub = dt_pub;
    }

    public String getCategorie(){
        Categorie c = new Categorie().findById(this.getIdCategorie());
        return c.getNom();
    }
    public void insert(){
        Connection connect = null;
        PreparedStatement st = null;
        try{
            connect = Connex.PsqlConnect();
            String sql = "INSERT INTO song (idArtiste,idCategorie,couverture,title,author,contenue,dt_pub) VALUES (?,?,?,?,?,?,?)";
            st = connect.prepareStatement(sql);
            st.setInt(1,this.getIdArtiste());
            st.setInt(2,this.getIdCategorie());
            st.setString(3,this.getCouverture());
            st.setString(4,this.getTitle());
            st.setString(5,this.getAuthor());
            st.setString(6,this.getContenue());
            st.setDate(7,Date.valueOf(this.getDt_pub()));
//            String sql = String.format(format,this.getIdArtiste(),this.getIdCategorie(),this.getCouverture(),this.getTitle(),this.getAuthor(),this.getContenue(),this.getDt_pub());
            st.executeUpdate();
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
        PreparedStatement st = null;
        try{
            connect = Connex.PsqlConnect();
            String sql = "UPDATE song set idCategorie=?,couverture=?,title=?,author=?,contenue=?,dt_pub=? WHERE id=?";
            st = connect.prepareStatement(sql);
            st.setInt(1,this.getIdCategorie());
            st.setString(2,this.getCouverture());
            st.setString(3,this.getTitle());
            st.setString(4,this.getAuthor());
            st.setString(5,this.getContenue());
            st.setDate(6,Date.valueOf(this.getDt_pub()));
            st.setInt(7,this.getId());
//            String sql = String.format(format,this.getIdCategorie(),this.getCouverture(),this.getTitle(),this.getAuthor(),this.getContenue(),this.getDt_pub(),this.getId());
            st.executeUpdate();
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
        PreparedStatement st = null;
        try{
            connect = Connex.PsqlConnect();
            String format = "DELETE FROM song WHERE id=?";
            st = connect.prepareStatement(format);
            st.setInt(1,this.getId());
//            String sql = String.format(format,this.getId());
            st.executeUpdate();
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

    public Song findById (int id){
        Song result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM song WHERE id="+id;
            res = st.executeQuery(sql);
            while(res.next()){
                int idArtiste = res.getInt("idArtiste");
                int idCategorie = res.getInt("idCategorie");
                String couverture = res.getString("couverture");
                String title = res.getString("title");
                String author = res.getString("author");
                String contenue = res.getString("contenue");
                String dt_pub = res.getDate("dt_pub").toString();
                result = new Song(id,idArtiste,idCategorie,couverture,title,author,contenue,dt_pub);
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

    public Song [] findByIdArtiste (int idArtiste){
        Vector<Song> result = new Vector<Song>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM song WHERE idArtiste="+idArtiste;
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
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

    public Song [] GetSingle (int idArtiste){
        Vector<Song> result = new Vector<Song>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM song WHERE id NOT IN (SELECT idSong FROM library WHERE LOWER(idItem) LIKE LOWER('%ALBUM%')) AND idArtiste="+idArtiste;
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
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

    public Song [] searchByTitleAndArtiste (String name,int idArtiste){
        Vector<Song> result = new Vector<Song>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM song WHERE LOWER(title) LIKE LOWER('%"+name+"%') AND idArtiste="+idArtiste;
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
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

    public Song[] searchByTitleMaxMin(int idArtiste,String name,int min,int max,int idCateg){
        Vector<Song> result = new Vector<Song>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM song WHERE idArtiste ="+idArtiste;
            if (min!=-1 || max!=-1){
                sql = "SELECT * FROM v_topSong WHERE idArtiste ="+idArtiste;
            }
            if (name.compareTo("")!=0){
                sql+=" AND LOWER(title) LIKE LOWER('%"+name+"%')";
            }
            if (min!=-1){
                sql+=" AND nblistener>="+min;
            }
            if (max!=-1){
                sql+=" AND nblistener<="+max;
            }
            if (idCateg!=-1){
                sql+=" AND idCategorie ="+idCateg;
            }
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
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
    public Song [] searchByTitle (String name){
        Vector<Song> result = new Vector<Song>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM song WHERE LOWER(title) LIKE LOWER('%"+name+"%')";
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

    public Utilisateur [] getListener (){
        Vector<Utilisateur> result = new Vector<Utilisateur>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM utilisateur WHERE id IN (SELECT idUser FROM stream WHERE idSong = "+this.getId()+")ORDER BY id ASC";
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

    public int getNbStream (){
        int result = 0;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT COUNT(idUser) as nbStream FROM stream WHERE idSong="+this.getId();
            res = st.executeQuery(sql);
            while(res.next()){
                result = res.getInt("nbStream");
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

    public Album getAlbum (){
        Album result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM album WHERE id IN ( SELECT idItem FROM library WHERE idSong ="+this.getId()+")";
            res = st.executeQuery(sql);
            while(res.next()){
                String id = res.getString("id");
                int idArtiste = res.getInt("idArtiste");
                String couverture = res.getString("couverture");
                String title = res.getString("title");
                String dt_creation = res.getDate("dt_creation").toString();
                result = new Album(id,idArtiste,couverture,title,dt_creation);
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
}
