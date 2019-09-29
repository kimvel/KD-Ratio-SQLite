import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.text.Normalizer;
import java.util.Formatter;

public class DBConnector {

    public static double killsSum = 0;
    public static double deathsSum = 0;

    public static Connection connect(){
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:E:/sqlite/kdratio.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void insert (int kills, int deaths){
        String sql = "INSERT INTO firstInput(kills, deaths) VALUES(?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, kills);
            pstmt.setInt(2, deaths);
            pstmt.executeUpdate();
            System.out.println("Correctly inserted to kdratio.db");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void getSumKills(){

        String sqlSum = "SELECT SUM(kills) FROM firstInput";

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sqlSum)){

            // loop through the result set
            while (rs.next()) {
                int c = rs.getInt(1);
                killsSum = killsSum + c;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getSumDeaths(){
        String sqlSum = "SELECT SUM(deaths) FROM firstInput";

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sqlSum)){

            // loop through the result set
            while (rs.next()) {
                int c = rs.getInt(1);
                deathsSum = deathsSum + c;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getKdRatio(){

        double kdRatio = killsSum / deathsSum;

        StringBuilder sb = new StringBuilder();
        Formatter fmt = new Formatter(sb);
        fmt.format("%.2f", kdRatio);
        System.out.println("KD-Ratio: " + sb.toString().replace(",", "."));
    }
}
