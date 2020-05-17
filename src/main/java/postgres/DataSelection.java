package postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


public class DataSelection {

    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;

        try {
            String url = "jdbc:postgresql://localhost/test";
            Properties props = new Properties();
            props.setProperty("user","mesher");
            props.setProperty("password","kavabanka");
            props.setProperty("ssl","false");
            props.setProperty("sslmode","require");
            Connection conn = DriverManager.getConnection(url, props);

            Class.forName("org.postgresql.Driver");
            //c.setAutoCommit(false);
            System.out.println("Successfully Connected.");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from public.\"Album\" ;" );
            while ( rs.next() ) {
                int albumid = rs.getInt("AlbumId");
                String  title = rs.getString("Title");
                int artistid = rs.getInt("ArtistId");
                System.out.printf( "AlbumId = %s , Title = %s, ArtistId = %s ", albumid,title, artistid );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println(" Data Retrieved Successfully ..");
    }
}