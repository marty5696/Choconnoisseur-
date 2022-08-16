import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * This class creates a connection to the database. It uses hard coded values for the
 * DB name, user, and password. Change these for your DB if necessary. Users of this 
 * class need to call the getConnection method instead of calling a ctor. The 
 * getConnection method returns a Connection object to the database.
 */
public class DbConnection {
    private static Connection con = null; 
    
    private DbConnection() {
    	//ctor is private to prevent anyone from trying to create an 
    	//object from this class.
    }
    
    /*
     * Below is a static initializer block. It is executed when the class is loaded and can only
     * initialize static class members. It is used to avoid needing a ctor. The connection
     * is static so we only have one. 
     */
    static  { 
        String mySqlUrl = "jdbc:mysql://localhost:3306/chocolatemilk_review"; 
        String user = "root"; 
        String pass = "";

        try {
          con = DriverManager.getConnection(mySqlUrl, user, pass); 
        } 
        catch (SQLException e) {
			  System.out.println("************************");
			  System.out.println("Connection failed using credentials of " + user + "/" + pass);
			  System.out.println("************************");
			  e.printStackTrace(); 
        } 
    } 
    
    /*
     * Call this method to get a connection to the database. It is static so you need
     * to use the class name: DbConnection.getConnection();
     */
    public static Connection getConnection() { 
        return con; 
    }
}
