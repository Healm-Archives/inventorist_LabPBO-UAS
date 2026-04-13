package source;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Db {
    static Connection connection;
    static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    static final String dbName = "inventorist_java";
    static final String dbUsername = "root";
    static final String dbPassword = "";
    static final String dbUrl = "jdbc:mysql://localhost/" + dbName;
    
    public static void dbConn() {
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            if(connection != null){
                System.out.println("Connection Established");
            }
        }
        catch (Exception e){
            // System.out.println("Connection Failed");
            // System.out.println(e.getLocalizedMessage());
            Alert dbAlert = new Alert(AlertType.ERROR);
            dbAlert.setTitle("Connection Failed");
            dbAlert.setHeaderText("Database is offline, please enable a database conenction.");
            dbAlert.showAndWait();
        }
    }
}
