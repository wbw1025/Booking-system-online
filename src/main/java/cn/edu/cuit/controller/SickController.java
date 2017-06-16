package cn.edu.cuit.controller;

import cn.edu.cuit.pojo.Sick;
import cn.edu.cuit.service.SickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */
@Controller
public class SickController {
    @Autowired
    private SickService sickService;

    /**
     * 按疾病挂号
     * 获取疾病信息
     * @return
     */
    @RequestMapping(value = "/sickList")
    @ResponseBody
    public List sickList(String body){
        List result= sickService.findSick(body);
        return result;
    }
}
