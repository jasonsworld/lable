package wang.jasonsworld.dao;

import wang.jasonsworld.bean.Lable;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * @brief label表操作
 */
public interface LableDao {

    /**
     * @param name 标签的名字
     * @return boolean
     * @brief 增加标签
     */
    public boolean addLable(String name);

    /**
     * @param id
     * @return boolean
     * @brief 删除标签
     */
    public boolean deleteLale(int id);

    /**
     * @return
     * @brief 查找不包含ids的所有的标签
     */
    public LinkedList<Lable> selectLables(String ids);

    /**
     * @return LinkedList
     * @brief 查找所有的标签
     */
    public LinkedList<Lable> selectAllLables();

    /**
     * @param row lable表的列名称
     * @param value 值
     * @return boolean
     * @brief 查看是否已经存在这个标签
     */
    public boolean doesHavaLable(String row, Object value);
}
