package cn.edu.cuit.controller;

import cn.edu.cuit.pojo.Doctor;
import cn.edu.cuit.pojo.Doctorcopy;
import cn.edu.cuit.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/4/13.
 */
@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    /**
     * order
     * 根据条件查询医生信息
     * @param hosId 医院id
     * @param secId 科室id
     * @param date 日期
     * @param num 上午（1）或下午（-1）
     * @return
     */
    //是一个用来处理请求地址映射的注解
    @RequestMapping(value = "/doctorInfo")
    @ResponseBody
    public List<Doctorcopy> doctorInfo(Integer hosId, Integer secId, String date, Integer num ){
    List<Doctorcopy> doctor = doctorService.findDoctor(hosId,secId,date,num);
    return doctor;
    }

    /**
     * 后台管理
     * 查询科室信息
     * @return
     */

    @RequestMapping(value = "/mDoctorList")
    @ResponseBody
    public List<Doctor> mDoctorList(){
        List<Doctor> doctorList = doctorService.mFindDoctor();
        return doctorList;
    }
    /**
     * 后台管理
     * 增删改查操作
     * @return
     */

    @RequestMapping(value = "/editorDoctor")
    @ResponseBody
    public String mDoctor(String id,String oper,String dname,String dsex,
                          String dtitle,String hospitalHid,String sectionSid,String money){
        System.out.println("=================");
        System.out.println(hospitalHid);
        if(oper.equals("del")){
            doctorService.delDoctor(Integer.parseInt(id));
        }
        if (oper.equals("add")){
            doctorService.addDoctor(dname,dsex,dtitle,Integer.parseInt(hospitalHid),Integer.parseInt(sectionSid),money);
        }
        if (oper.equals("edit")){
            doctorService.editDoctor(Integer.parseInt(id),dname,dsex,dtitle,Integer.parseInt(hospitalHid),Integer.parseInt(sectionSid),money);
        }
        return "mDoctor";
    }


}
