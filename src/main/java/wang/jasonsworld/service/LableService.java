package wang.jasonsworld.service;

import java.util.Map;

/**
 * Created by chenyongsheng on 15/11/22.
 */
public interface LableService {

    /**
     * @param lableName 标签的名字
     * @return map
     * @brief 添加标签
     */
    public Map<String, String> addLable(String lableName);

    /**
     * @param id 标签的id
     * @return map
     * @brief 添加标签
     */
    public Map<String, String> deletLable(int id);

    /**
     * @param ids 标签的ids
     * @return map
     * @brief 添加标签
     */
    public Map<String, Object> getAllLables(String ids);
}
