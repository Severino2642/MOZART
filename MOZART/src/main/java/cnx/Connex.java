package cnx;

import java.sql.*;

public class Connex {
	public static Connection OracleConnect(){
            Connection c = null;
            try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                    String user = "Ticket";
                    String pwd = "ticket";
                    c = DriverManager.getConnection(url,user,pwd);
            }catch(Exception e){
                    System.out.println("Erreur de connexion");
                    e.printStackTrace();
            }
            if(c != null){
                    //System.out.println("Connexion etablie");
            }

            return c;
	}

	public static Connection PsqlConnect(String base, String password){
            Connection c = null;
            try
            {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+base,"postgres",password);
            }
            catch(Exception e){
                    System.out.println("Erreur de connexion");
                    e.printStackTrace();
            }
            return c;
	}
        
    public static Connection PsqlConnect(){
        Connection c = null;
        try
        {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mozart","postgres","samsoudine");
        }
        catch(Exception e){
                System.out.println("Erreur de connexion");
                e.printStackTrace();
        }
        return c;
	}

    public static String createId(Connection cnx, String nomsequence, String prefixe) throws Exception
    {
        String id = "";
        boolean closed = false;
        if(cnx.isClosed())
        {
            cnx = Connex.PsqlConnect();
            closed = true;
        }

        int seq = -1;
        String sql = "SELECT nextval('"+nomsequence+"') as sequence";
        System.out.println("SQL >>>>> "+sql);
        Statement stmt = cnx.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        while(res.next()){
            seq = res.getInt(1);
        }
        res.close();
        stmt.close();

        if (seq == -1)
        {
            if (closed)
            {
                cnx.close();
            }
            throw new Exception("Erreur lors de la recuperation de la valeur de la sequence: seq = -1");
        }
        else
        {
            id = prefixe + seq;
        }

        if (closed)
        {
            cnx.close();
        }
        return id;
    }
    
    public static Date getDate(Connection cnx) throws Exception{
        Date result = null;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT NOW() as T";
        Statement stmt = cnx.createStatement();
        System.out.println("SQL >>> "+sql);
        try(ResultSet res = stmt.executeQuery(sql);){
            if(res.next()){
                result=res.getDate("T");
            }
        }
        
        if(isclosed){
            cnx.close();
        }
        return result;
    }
}