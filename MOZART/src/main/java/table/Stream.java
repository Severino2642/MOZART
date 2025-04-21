package table;

import cnx.Connex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stream {
    int id;
    int idSong;
    int idUser;
    String dt_listening;

    public Stream() {
    }

    public Stream(int id, int idSong, int idUser, String dt_listening) {
        this.id = id;
        this.idSong = idSong;
        this.idUser = idUser;
        this.dt_listening = dt_listening;
    }

    public Stream(int idSong, int idUser, String dt_listening) {
        this.idSong = idSong;
        this.idUser = idUser;
        this.dt_listening = dt_listening;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDt_listening() {
        return dt_listening;
    }

    public void setDt_listening(String dt_listening) {
        this.dt_listening = dt_listening;
    }

    public void insert(){
        Connection connect = null;
        Statement st = null;
        try{
            connect = Connex.PsqlConnect();
            st = connect.createStatement();
            String format = "INSERT INTO stream (idSong,idUser,dt_listening) VALUES (%s,%s,'%s')";
            String sql = String.format(format,this.getIdSong(),this.getIdUser(),this.getDt_listening());
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
            String format = "DELETE FROM stream WHERE id="+id;
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

    public Stream findByIdUserAndSong (int idUser,int idSong){
        Stream result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM stream WHERE idUser="+idUser+" AND idSong="+idSong;
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                String idItem = res.getString("idItem");
                String dt_listening = res.getDate("dt_listening").toString();
                result = new Stream(id,idSong,idUser,dt_listening);
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
