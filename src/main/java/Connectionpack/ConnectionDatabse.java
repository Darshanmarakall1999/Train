package Connectionpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabse {
    private static Connection con;
    public static Connection getconnection()
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/traindatabase", "root", "Darshan@1999");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }
}
