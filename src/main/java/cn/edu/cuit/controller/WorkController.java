package cn.edu.cuit.controller;

import cn.edu.cuit.mapper.WorkMapper;
import cn.edu.cuit.pojo.Work;
import cn.edu.cuit.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/4/4.
 */
@Controller
public class WorkController {
    @Autowired
    private WorkService workService;

    /**
     * 后台管理系统
     * 查询排班信息
     * @return
     */

    @RequestMapping(value = "/mWorkList")
    @ResponseBody
    public List<Work> mWorkList(){
        List<Work> workList= workService.findWork();
        return workList;
    }


    @RequestMapping(value = "/editorWork")
    @ResponseBody
    public String mWork(String id,String oper,String name,String date,String anum,String pnum){
        if (oper.equals("del")){
            workService.delWork(Integer.parseInt(id));
        }
        if (oper.equals("add")){
            workService.addWork(name,date,Integer.parseInt(anum),Integer.parseInt(pnum));
        }
        if (oper.equals("edit")){
            workService.editWork(Integer.parseInt(id),name,date,Integer.parseInt(anum),Integer.parseInt(pnum));
        }
        return "/mWork";
    }
}
