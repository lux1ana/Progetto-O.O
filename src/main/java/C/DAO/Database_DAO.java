package C.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_DAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/Napolizon";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Granata1.";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}

