package wang.jasonsworld.daoutil;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @brief c3p0连接池获取connection
 */
public class ConnectionUtil {
    private static ComboPooledDataSource ds = null;

    static {
        ds = new ComboPooledDataSource("dbInfo");
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            //c3p0组件
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
}
