import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
    
    public static Connection getConnection(){
        
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost/pythonlogin","root","mussaed0566060299");
            
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return con;
    }
    
}
