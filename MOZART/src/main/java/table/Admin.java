package table;

import cnx.Connex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin {
    int id;
    String pseudo;
    String email;
    String mdp;

    public Admin() {
    }

    public Admin(int id, String pseudo, String email, String mdp) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

    public Admin findByPwd (String email, String mdp){
        Admin result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            con = Connex.PsqlConnect();
            st = con.createStatement();
            String format = "SELECT * FROM admin WHERE email='%s' AND mdp='%s'";
            String sql = String.format(format,email,mdp);
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                String pseudo = res.getString("pseudo");

                result = new Admin(id,pseudo,email,mdp);
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
