package table;

import cnx.Connex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Playlist {
    String id;
    int idAuthor;
    String couverture;
    String title;
    String dt_creation;

    public Playlist() {
    }

    public Playlist(String id, int idAuthor, String couverture, String title, String dt_creation) {
        this.id = id;
        this.idAuthor = idAuthor;
        this.couverture = couverture;
        this.title = title;
        this.dt_creation = dt_creation;
    }

    public Playlist(int idAuthor, String couverture, String title, String dt_creation) {
        this.idAuthor = idAuthor;
        this.couverture = couverture;
        this.title = title;
        this.dt_creation = dt_creation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getCouverture() {
        return couverture;
    }

    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDt_creation() {
        return dt_creation;
    }

    public void setDt_creation(String dt_creation) {
        this.dt_creation = dt_creation;
    }

    public Song [] getSong (){
        Vector<Song> result = new Vector<Song>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM song WHERE id IN (SELECT idSong FROM library WHERE LOWER(idItem) LIKE LOWER('%PLAYLIST%') AND idItem = '"+this.getId()+"')";
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
    public void removeAllSong(String idPlaylist){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            String format = "DELETE FROM library WHERE idItem='%s'";
            String sql = String.format(format,idPlaylist);
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
    public void removeSong(String idPlaylist,int idSong){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            String format = "DELETE FROM library WHERE idItem='%s' AND idSong="+idSong;
            String sql = String.format(format,idPlaylist);
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
    public void addSong(String idSong,String idPlaylist){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            if (idSong.compareToIgnoreCase("")!=0 && idSong.compareToIgnoreCase(" ")!=0){
                String format = "INSERT INTO library (idItem,idSong) VALUES ('%s',%s)";
                String sql = String.format(format,idPlaylist,Integer.parseInt(idSong));
                st.executeUpdate(sql);
            }
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

    public void insert(){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            String format = "INSERT INTO playlist (id,idAuthor,couverture,title,dt_creation) VALUES ('%s',%s,'%s','%s','%s')";
            String sql = String.format(format,this.getId(),this.getIdAuthor(),this.getCouverture(),this.getTitle(),this.getDt_creation());
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
            String format = "UPDATE playlist set couverture='%s',title='%s',dt_creation='%s' WHERE id='%s'";
            String sql = String.format(format,this.getCouverture(),this.getTitle(),this.getDt_creation(),this.getId());
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
            String format = "DELETE FROM playlist WHERE id='%s'";
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

    public Playlist findById (String id){
        Playlist result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM playlist WHERE id='"+id+"'";
            res = st.executeQuery(sql);
            while(res.next()){
                int idAuthor = res.getInt("idAuthor");
                String couverture = res.getString("couverture");
                String title = res.getString("title");
                String dt_creation = res.getDate("dt_creation").toString();
                result = new Playlist(id,idAuthor,couverture,title,dt_creation);
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

    public Playlist [] findByIdUser (int idUser){
        Vector<Playlist> result = new Vector<>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM playlist WHERE idAuthor="+idUser;
            res = st.executeQuery(sql);
            while(res.next()){
                String id = res.getString("id");
                String couverture = res.getString("couverture");
                String title = res.getString("title");
                String dt_creation = res.getDate("dt_creation").toString();
                result.add(new Playlist(id,idUser,couverture,title,dt_creation));
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
        return result.toArray(new Playlist[] {});
    }

    public Playlist [] searchByTitleAndIdUser (String nom,int idUser){
        Vector<Playlist> result = new Vector<>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM playlist WHERE LOWER(title) LIKE LOWER('%"+nom+"'%) AND idAuthor="+idUser;
            res = st.executeQuery(sql);
            while(res.next()){
                String id = res.getString("id");
                String couverture = res.getString("couverture");
                String title = res.getString("title");
                String dt_creation = res.getDate("dt_creation").toString();
                result.add(new Playlist(id,idUser,couverture,title,dt_creation));
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
        return result.toArray(new Playlist[] {});
    }

    public Playlist [] searchByTitle (String nom){
        Vector<Playlist> result = new Vector<>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM playlist WHERE LOWER(title) LIKE LOWER('%"+nom+"'%)";
            res = st.executeQuery(sql);
            while(res.next()){
                String id = res.getString("id");
                int idAuthor = res.getInt("idAuthor");
                String couverture = res.getString("couverture");
                String title = res.getString("title");
                String dt_creation = res.getDate("dt_creation").toString();
                result.add(new Playlist(id,idAuthor,couverture,title,dt_creation));
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
        return result.toArray(new Playlist[] {});
    }
}
