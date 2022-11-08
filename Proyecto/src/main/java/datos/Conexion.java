
package datos;
import java.sql.*;

/**
 *
 * @author jonat
 */
public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bd_insgaming?useSSL=false"+
                    "&useTimezone=true&serverTimezone=UTC"+
                    "&allowPublickeyRetrival=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1234";
    
    //Metodod para establecer la conexion
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(Statement stm) throws SQLException{
        stm.close();
    }
    
    public static void close(PreparedStatement stm) throws SQLException{
        stm.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
