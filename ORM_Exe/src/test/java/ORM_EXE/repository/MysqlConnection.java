package ORM_EXE.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class MysqlConnection {

    private final Connection connection;
    private static MysqlConnection instance = null;

    private MysqlConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/orm_exe", "mostafa", "Aa123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MysqlConnection getInstance() {
        if(MysqlConnection.instance == null) {
            instance = new MysqlConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    public void close(){
        instance=null;
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close connection",e);
        }
    }
}