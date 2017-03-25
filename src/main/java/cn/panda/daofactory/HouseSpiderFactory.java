package cn.panda.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class HouseSpiderFactory {

    private String DRIVER = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://localhost:3306/agenthouse?useUnicode=true&amp;characterEncoding=UTF-8";
    private String USERNAME = "root";
    private String PASSWORD = "123456";


    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Connection connection = null;
        Class.forName(DRIVER);

        if (null != connection && !connection.isClosed()){

            return connection;
        }else {

            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return connection;
        }
    }


}
