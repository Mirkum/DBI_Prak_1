import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Benchmark_DB {
    public static void main(String[] args) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/benchmark-datenbank", "dbi", "dbi_pass");
            //conn.setAutoCommit(false);
            System.out.println("\nConnected to benchmark database!\n");


            stmt = conn.prepareStatement("");
            stmt.execute();


            conn.commit();
            stmt.close();

            conn.close();
            System.out.println("\nDisconnected!\n");
        }
        catch (SQLException e)
        {
            System.err.println(e.toString());
            System.exit(1);
        }
        finally // close used resources
        {
            if (stmt!=null) stmt.close();
            if (conn!=null) conn.close();
        }
    }
}

