package table;

import cnx.Connex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Follower {
    int id;
    int idArtiste;
    int idUser;
    String dt_follow;

    public Follower() {
    }

    public Follower(int id, int idArtiste, int idUser, String dt_follow) {
        this.id = id;
        this.idArtiste = idArtiste;
        this.idUser = idUser;
        this.dt_follow = dt_follow;
    }

    public Follower(int idArtiste, int idUser, String dt_follow) {
        this.idArtiste = idArtiste;
        this.idUser = idUser;
        this.dt_follow = dt_follow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDt_follow() {
        return dt_follow;
    }

    public void setDt_follow(String dt_follow) {
        this.dt_follow = dt_follow;
    }

    public void insert(){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            String format = "INSERT INTO follower (idArtiste,idUser,dt_follow) VALUES (%s,%s,'%s')";
            String sql = String.format(format,this.getIdArtiste(),this.getIdUser(),this.getDt_follow());
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
            String format = "DELETE FROM follower WHERE id="+id;
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

    public Follower findById (int id){
        Follower result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM follower WHERE id="+id;
            res = st.executeQuery(sql);
            while(res.next()){
                int idArtiste = res.getInt("idArtiste");
                int idUser = res.getInt("idUser");
                String dt_follow = res.getDate("dt_follow").toString();
                result = new Follower(id,idArtiste,idUser,dt_follow);
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

    public Follower findByIdArtisteAndUser (int idArtiste,int idUser){
        Follower result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM follower WHERE idArtiste="+idArtiste+" AND idUser="+idUser;
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                String dt_follow = res.getDate("dt_follow").toString();
                result = new Follower(id,idArtiste,idUser,dt_follow);
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
