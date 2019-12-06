package active_manager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
    
    public static Connection getConnection(){
        
        Connection con = null;
        try{
        	//jdbc:mysql://localhost/DatabseName","Username","Password"
            Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost/pythonlogin","root","434816");
            
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return con;
    }
    
}
