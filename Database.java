package CodePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
// import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    static Database d = new Database();

    private String db(String s, int n) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/console", "root","root");
            Statement stmt = con.createStatement();
            if (n == 1)
            {
                stmt.executeUpdate(s);
                return "Successfully Updated";
            } 
            
            else 
            {
                ResultSet result = stmt.executeQuery(s);
                String ret = "";
                while (result.next()) 
                {
                    ret="";
                    if (n == 2) {
                        ret = result.getString(1);
                    }
                    else if (n == 3) {
                        
                        // ret += result.getInt(1)+"|"+result.getString(2)+"|"+result.getString(3);
                        int id=result.getInt("id");
                        String title=result.getString("TITLE");
                        String snippet=result.getString("snippet");
                        System.out.printf("|%-7s|%-15s|%-178s|\n",id,title,snippet);
                        System.out.println("+-------+---------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
                    }
                    
                    
                }
                return ret;
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR" + e.getMessage());
            return null;
        }
    }

    public boolean check(String name2,String pass)
    {
        return (pass.equals(d.db("SELECT PASSWORD from customer where USERNAME = '"+name2+"' ;",2)));
    }

    public void index(String name)
    {
        System.out.println("+-------+---------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|   Id  |     TITLE     |                                                                                 SNIPPET                                                                                          |");
        System.out.println("+-------+---------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
        d.db("SELECT * FROM "+name+" ;", 3);
    }

    public boolean unique(String name)
    {
        if(d.db("SELECT password from customer where username = '"+name+"' ;",2)==null)
        return false;
        else
        return true;
    }

    public String adduser(String name,String pass)
    {
        d.db("INSERT INTO customer (`USERNAME`, `PASSWORD`) VALUES ('"+name+"','"+pass+"');", 1);
        return d.db("CREATE TABLE "+name+" (`ID` INT NOT NULL,`TITLE` VARCHAR(45) NOT NULL,`SNIPPET` MEDIUMTEXT NULL,PRIMARY KEY (`ID`));", 1);

    }

    public String addSnip(String t,String snip,String name)
    {
        return d.db("INSERT INTO "+name+" (`TITLE`, `SNIPPET`) VALUES ('"+t+"','"+snip+"');", 1);
    }

    public void delete(String name,int id)
    {
        d.db("DELETE FROM "+name+" where ID = "+id+";",1);
        System.out.println("Sucessfully Removed");
    }
}
