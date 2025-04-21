package table;

import affData.SongDetails;
import cnx.Connex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Favorite {
    int id;
    int idUser;
    String idItem;
    String type;
    String dt_favorite;

    public Favorite() {
    }

    public Favorite(int id, int idUser, String idItem, String type, String dt_favorite) {
        this.id = id;
        this.idUser = idUser;
        this.idItem = idItem;
        this.type = type;
        this.dt_favorite = dt_favorite;
    }

    public Favorite(int idUser, String idItem, String type, String dt_favorite) {
        this.idUser = idUser;
        this.idItem = idItem;
        this.type = type;
        this.dt_favorite = dt_favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDt_favorite() {
        return dt_favorite;
    }

    public void setDt_favorite(String dt_favorite) {
        this.dt_favorite = dt_favorite;
    }

    public void insert(){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            String format = "INSERT INTO favorite (idUser,idItem,type,dt_favorite) VALUES (%s,'%s','%s','%s')";
            String sql = String.format(format,this.getIdUser(),this.getIdItem(),this.getType(),this.getDt_favorite());
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
            String format = "DELETE FROM favorite WHERE id="+id;
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

    public Favorite findById (int id){
        Favorite result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM favorite WHERE id="+id;
            res = st.executeQuery(sql);
            while(res.next()){
                int idUser = res.getInt("idUser");
                String idItem = res.getString("idItem");
                String type = res.getString("type");
                String dt_favorite = res.getDate("dt_favorite").toString();
                result = new Favorite(id,idUser,idItem,type,dt_favorite);
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

    public Favorite findByIdItemAndUser (String idItem,int idUser){
        Favorite result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM favorite WHERE idItem='"+idItem+"' AND idUser="+idUser;
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                String type = res.getString("type");
                String dt_favorite = res.getDate("dt_favorite").toString();
                result = new Favorite(id,idUser,idItem,type,dt_favorite);
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

    public Album [] getFavoriteAlbumForUser (int idUser){
        Vector<Album> result = new Vector<Album>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM album WHERE id IN (SELECT idItem FROM favorite WHERE type='ALBUM' AND idUser="+idUser+")";
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

    public Playlist [] getFavoritePlaylistForUser (int idUser){
        Vector<Playlist> result = new Vector<>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM playlist WHERE id IN (SELECT idItem FROM favorite WHERE type='PLAYLIST' AND idUser="+idUser+")";
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

    public SongDetails [] getFavoriteSongForUser (int idUser){
        Vector<Song> result = new Vector<Song>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM song WHERE id IN (SELECT idItem FROM favorite WHERE type='SONG' AND idUser="+idUser+")";
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
        SongDetails [] allSong = new SongDetails().findByListSong(result.toArray(new Song [] {}));
        return allSong;
    }
}
