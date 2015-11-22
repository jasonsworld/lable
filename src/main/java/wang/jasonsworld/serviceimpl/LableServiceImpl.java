package wang.jasonsworld.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.jasonsworld.bean.Lable;
import wang.jasonsworld.daoimpl.LableDaoImpl;
import wang.jasonsworld.daoutil.ConnectionManager;
import wang.jasonsworld.service.LableService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * Created by chenyongsheng on 15/11/22.
 */
@Service
public class LableServiceImpl implements LableService {
    @Autowired
    LableDaoImpl lableDao;

    @Override
    public Map<String, String> addLable(String lableName) {
        Map<String, String> result = new HashMap<String, String>();
        if (lableDao.doesHavaLable("name", lableName)) {
            result.put("errorcode", "2");  //此标签存在了
        } else {
            if (lableDao.addLable(lableName)) {
                result.put("errorcode", "0"); //添加成功
            } else {
                result.put("errorcode", "1"); //添加失败
            }
        }
        ConnectionManager.closeConnection();
        return result;
    }


    @Override
    public Map<String, String> deletLable(int id) {
        Map<String, String> result = new HashMap<String, String>();
        if (lableDao.doesHavaLable("id", id)) {
            if (lableDao.deleteLale(id)) {
                result.put("errorcode", "0"); //删除成功
            } else {
                result.put("errorcode", "1"); //删除失败
            }
        } else {
            result.put("errorcode", "2"); //此标签已经不存在了，即删除也是成功的
        }
        ConnectionManager.closeConnection();
        return result;
    }

    @Override
    public Map<String, Object> getAllLables(String ids) {
        Map<String, Object> result = new HashMap<String, Object>();
        LinkedList<Lable> lables = null;
        if (ids == null || ids.equals("")) {
            lables = lableDao.selectAllLables();
        } else {
            lables = lableDao.selectLables(ids);
        }
        result.put("errorcode", "0");
        result.put("lables", lables);
        return result;
    }

}
