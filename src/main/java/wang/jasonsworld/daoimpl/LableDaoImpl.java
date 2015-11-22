package wang.jasonsworld.daoimpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import wang.jasonsworld.bean.Lable;
import wang.jasonsworld.dao.LableDao;
import wang.jasonsworld.daoutil.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * @brief lable表操作的相关实现
 */
@Repository
public class LableDaoImpl implements LableDao {

    public static final Log log = LogFactory.getLog(LableDaoImpl.class);

    @Override
    public boolean addLable(String name) {
        Connection connection = ConnectionManager.getConnection();
        boolean result = false;
        PreparedStatement pst = null;
        String sql = "insert ignore into lable (name) values (?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setObject(1, name);
            int count = pst.executeUpdate();
            if (count != 0) {
                result = true;
            }
        } catch (Exception e) {
            log.error("addLable() add Exception");
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    log.error("addLable() close statement Exception");
                    e.printStackTrace();
                }

            }
        }
        return result;
    }

    @Override
    public boolean deleteLale(int id) {
        boolean result = false;
        String sql = "delete from lable where id = ?";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setObject(1, id);
            int count = pst.executeUpdate();
            if (count != 0) {
                result = true;
            }
        } catch (Exception e) {
            log.error("deleteLable() delete Exception");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    log.error("deleteLable() close statement Exception");
                    e.printStackTrace();
                }

            }
        }
        return result;
    }

    @Override
    public LinkedList<Lable> selectLables(String ids) {
        LinkedList<Lable> result = new LinkedList<Lable>();
        String[] array = ids.split(",");
        String sql = "select id, name from lable where id not in (? ";
        for (int i = 1; i < array.length; i++) {
            sql += " , ? ";
        }
        sql += " ) ";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = connection.prepareStatement(sql);
            for (int i = 0; i < array.length; i++) {
                pst.setObject(i + 1, array[i]);
            }
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Lable lable = new Lable();
                lable.setId(id);
                lable.setName(name);
                result.add(lable);
            }
        } catch (Exception e) {
            log.error("selectLables() query Exception");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    log.error("selectLables() close resultSet Exception");
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    log.error("selectLables() close statement Exception");
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public LinkedList<Lable> selectAllLables() {
        LinkedList<Lable> result = new LinkedList<Lable>();

        String sql = "select id, name from lable";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Lable lable = new Lable();
                lable.setId(id);
                lable.setName(name);
                result.add(lable);
            }
        } catch (Exception e) {
            log.error("selectAllLables() query Exception");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    log.error("selectAllLables() close resultSet Exception");
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    log.error("selectAllLables() close statement Exception");
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public boolean doesHavaLable(String row, Object value) {
        boolean result = false;
        String sql = "select * from lable where " + row + " = ?";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setObject(1, value);
            rs = pst.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            log.error("doesHavaLable() query Exception");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    log.error("doesHavaLable() close resultSet Exception");
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    log.error("doesHavaLable() close statement Exception");
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
