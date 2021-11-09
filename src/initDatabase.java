import java.sql.*;

public class initDatabase {
    public static void main(String[] args) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs   = null;

        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/benchmark-datenbank", "dbi", "dbi_pass");
            conn.setAutoCommit(false);
            System.out.println("\nConnected to benchmark database!\n");

            stmt = conn.prepareStatement(
                    "create schema 'benchmark-datenbank'");

            stmt = conn.prepareStatement(
                    "create table branches\n" +
                            "( branchid int not null,\n" +
                            " branchname char(20) not null,\n" +
                            " balance int not null,\n" +
                            " address char(72) not null,\n" +
                            " primary key (branchid) );"
                            );
            conn.commit();
            stmt.execute();

            stmt = conn.prepareStatement(
                    "create table accounts\n" +
                            "( accid int not null,\n" +
                            " name char(20) not null,\n" +
                            " balance int not null,\n" +
                            "branchid int not null,\n" +
                            "address char(68) not null,\n" +
                            "primary key (accid),\n" +
                            "foreign key (branchid) references `benchmark-datenbank`.branches(branchid) );");

            stmt.execute();
            stmt = conn.prepareStatement(
                    "create table accounts\n" +
                            "                ( accid int not null,\n" +
                            "                name char(20) not null,\n" +
                            "                balance int not null,\n" +
                            "                branchid int not null,\n" +
                            "                address char(68) not null,\n" +
                            "                primary key (accid),\n" +
                            "                foreign key (branchid) references `benchmark-datenbank`.branches(branchid) );");
                    stmt.execute();


            stmt = conn.prepareStatement(
                    "create table tellers\n" +
                            "( tellerid int not null,\n" +
                            " tellername char(20) not null,\n" +
                            " balance int not null,\n" +
                            " branchid int not null,\n" +
                            " address char(68) not null,\n" +
                            " primary key (tellerid),\n" +
                            " foreign key (branchid) references `benchmark-datenbank`.branches(branchid) ); ");
            stmt.execute();

            stmt = conn.prepareStatement(
                    "create table history\n" +
                            "( accid int not null,\n" +
                            " tellerid int not null,\n" +
                            " delta int not null,\n" +
                            " branchid int not null,\n" +
                            " accbalance int not null,\n" +
                            " cmmnt char(30) not null,\n" +
                            " foreign key (accid) references `benchmark-datenbank`.accounts(accid),\n" +
                            " foreign key (tellerid) references `benchmark-datenbank`.tellers(tellerid),\n" +
                            " foreign key (branchid) references `benchmark-datenbank`.branches(branchid) );"
                             );
            stmt.execute();


            /*while ((productID!=null) && (productID.length()!=0))
            {


                System.out.println();
                System.out.println("AID|        ANAME|    DOLLARS");
                System.out.println("---|-------------|-----------");

                while (rs.next())
                {
                    System.out.println(rs.getString(1) + "\t\t" +
                            rs.getString(2) + "\t\t" + rs.getDouble(3));
                }
                rs.close();
*/
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
