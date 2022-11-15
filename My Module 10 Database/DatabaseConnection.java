import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    String url;
    String db_user;
    String db_password;

    Connection connection;

    public DatabaseConnection(String db_user, String db_password, String schema) {
        this.url = "jdbc:mysql://localhost:3306/" + schema;
        this.db_user = db_user;
        this.db_password = db_password;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(this.url, this.db_user, this.db_password);
            System.out.println("Successfully connected to database");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fail to connect to database");
        }
    }
}
