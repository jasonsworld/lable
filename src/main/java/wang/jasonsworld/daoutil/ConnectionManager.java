package wang.jasonsworld.daoutil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @brief 用ThreadLocal封装Connection
 */
public class ConnectionManager {
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();

    /**
     * @return Connection
     * @brief 获得数据库连接connection
     */
    public static Connection getConnection() {
        Connection conn = connectionHolder.get();
        if (conn == null) {
            conn = ConnectionUtil.getConnection();
            connectionHolder.set(conn);
        }
        return conn;
    }

    /**
     * @brief 关闭数据库连接connection
     */
    public static void closeConnection() {
        Connection conn = connectionHolder.get();
        if (conn != null) {
            try {
                conn.close();
                connectionHolder.remove();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @param conn
     * @brief 开启事务
     */
    public static void beginTransaction(Connection conn) {
        try {
            if (conn != null) {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param conn
     * @brief 关闭事务
     */
    public static void commitTransaction(Connection conn) {
        try {
            if (conn != null) {
                if (!conn.getAutoCommit()) {
                    conn.commit();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param conn
     * @brief 回滚事务
     */
    public static void rollbackTransaction(Connection conn) {
        try {
            if (conn != null) {
                if (!conn.getAutoCommit()) {
                    conn.commit();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
