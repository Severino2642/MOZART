package table;

import cnx.Connex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Genre {
    int id;
    String type;

    public Genre() {
    }

    public Genre(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Genre(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Genre [] findAll (){
        List<Genre> result = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM genre ORDER BY id ASC";
            res = st.executeQuery(sql);
            while(res.next()){
                result.add(new Genre(res.getInt("id"),res.getString("type")));
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
        return result.toArray( new Genre[] {});
    }

    public Genre findById (int id){
        Genre result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String sql = "SELECT * FROM genre WHERE id="+id;
            res = st.executeQuery(sql);
            while(res.next()){
                result = new Genre(res.getInt("id"),res.getString("type"));
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
