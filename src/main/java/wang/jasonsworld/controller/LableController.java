package wang.jasonsworld.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.jasonsworld.serviceimpl.LableServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("lable")
public class LableController {

    public static final Log log = LogFactory.getLog(LableController.class);

    @Autowired
    LableServiceImpl lableService;


    /**
     * @param name 标签名字
     * @return json
     * @brief 添加标签
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, String> addLable(@RequestParam String name) {
        log.debug("/lable/add");
        Map<String, String> result = null;
        if (name == null || name.trim().equals("")) {
            result = new HashMap<String, String>();
            result.put("errorcode", "-1"); // PARAM ERROR
        } else {
            result = lableService.addLable(name);
        }
        return result;
    }

    /**
     * @param id 标签id
     * @return json
     * @brief 删除标签
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> deleteLable(@RequestParam String id) {
        log.debug("/lable/delete");
        Map<String, String> map = null;
        try {
            int intid = Integer.valueOf(id);
            map = lableService.deletLable(intid);
        } catch (NumberFormatException e) {
            map = new HashMap<String, String>();
            map.put("errorcode", "-1"); // PARAM ERROR
        } finally {
            return map;
        }
    }

    /**
     * @param ids 现有所有标签的id,标签的id之间以,（英文逗号）分割
     * @return json
     * @brief 添加标签
     */
    @RequestMapping("get")
    @ResponseBody
    public Map<String, Object> getLables(@RequestParam String ids) {
        log.debug("/lable/get");
        Map<String, Object> result = null;
        ids = ids.trim();
        result = lableService.getAllLables(ids);
        return result;
    }

}
