package table;

import affData.AlbumDetails;
import affData.SongDetails;
import cnx.Connex;
import view.TopAlbum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Album {
    String id;
    int idArtiste;
    String couverture;
    String title;
    String dt_creation;

    public Album() {
    }

    public Album(String id, int idArtiste, String couverture, String title, String dt_creation) {
        this.id = id;
        this.idArtiste = idArtiste;
        this.couverture = couverture;
        this.title = title;
        this.dt_creation = dt_creation;
    }

    public Album(int idArtiste, String couverture, String title, String dt_creation) {
        this.idArtiste = idArtiste;
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

    public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
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

    public Artiste getArtiste(){
        Artiste artiste = new Artiste().findById(this.getIdArtiste());
        return artiste;
    }
    public Song [] getSong (){
        Vector<Song> result = new Vector<Song>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM song WHERE id IN (SELECT idSong FROM library WHERE LOWER(idItem) LIKE LOWER('%ALBUM%') AND idItem = '"+this.getId()+"')";
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
    public void removeSong(String idAlbum){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            String format = "DELETE FROM library WHERE idItem='%s'";
            String sql = String.format(format,idAlbum);
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
    public void addSong(String [] idSong,String idAlbum){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            for (int i = 0; i<idSong.length ; i++){
                if (idSong[i].compareToIgnoreCase("")!=0 && idSong[i].compareToIgnoreCase(" ")!=0){
                    String format = "INSERT INTO library (idItem,idSong) VALUES ('%s',%s)";
                    String sql = String.format(format,idAlbum,Integer.parseInt(idSong[i]));
                    st.executeUpdate(sql);
                }
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
            String format = "INSERT INTO album (id,idArtiste,couverture,title,dt_creation) VALUES ('%s',%s,'%s','%s','%s')";
            String sql = String.format(format,this.getId(),this.getIdArtiste(),this.getCouverture(),this.getTitle(),this.getDt_creation());
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
            String format = "UPDATE album set couverture='%s',title='%s',dt_creation='%s' WHERE id='%s'";
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
            String format = "DELETE FROM album WHERE id='%s'";
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

    public Album findById (String id){
        Album result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM album WHERE id='"+id+"'";
            res = st.executeQuery(sql);
            while(res.next()){
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

    public Album [] findByIdArtiste (int idArtiste){
        Vector<Album> result = new Vector<Album>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM album WHERE idArtiste="+idArtiste;
            res = st.executeQuery(sql);
            while(res.next()){
                String id = res.getString("id");
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

    public Album [] searchByTitleAndArtiste (String nom,int idArtiste){
        Vector<Album> result = new Vector<Album>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM album WHERE LOWER(title) LIKE LOWER('%"+nom+"%') AND idArtiste="+idArtiste;
            res = st.executeQuery(sql);
            while(res.next()){
                String id = res.getString("id");
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

    public Album [] searchByTitle (String nom){
        Vector<Album> result = new Vector<Album>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM album WHERE LOWER(title) LIKE LOWER('%"+nom+"%') ";
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

    public Album[] searchByTitleMaxMin (int idArtiste,String name,int min,int max){
        Vector<Album> result = new Vector<Album>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM album WHERE idArtiste="+idArtiste;
            if (min!=-1 || max!=-1){
                sql = "SELECT * FROM v_topAlbum WHERE idArtiste ="+idArtiste;
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
            res = st.executeQuery(sql);
            while(res.next()){
                String id = res.getString("id");
                String couverture = res.getString("couverture");
                String title = res.getString("title");
                String dt_creation = res.getDate("dt_creation").toString();
                Album a = new Album(id,idArtiste,couverture,title,dt_creation);
                result.add(a);
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
