package cn.edu.cuit.controller;

import cn.edu.cuit.pojo.Hospital;
import cn.edu.cuit.pojo.Section;
import cn.edu.cuit.service.HospitalService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
@Controller
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private HttpSession session;

    /**
     * index
     * 读取地区信息和6条医院信息
     *
     * @param model 在jsp页面直接使用jstl
     * @return 返回首页
     */
    @RequestMapping(value = "/index")
    public String toIndex(Model model) {
        //读取医院全部地址列表
        model.addAttribute("areaSet", hospitalService.findArea());
        //读取指定数量医院列表
       // model.addAttribute("hospitalList", hospitalService.findHospitalByNum(6));
        //医院等级
        model.addAttribute("rank", hospitalService.findRank());
        return "index";

    }

    /**
     * index
     * 根据地区返回医院信息
     *
     * @param area 地区
     * @return 返回医院列表
     */
    @RequestMapping(value = "/hospital/filter")
    @ResponseBody
    public List<Hospital> hospitalFilter(String area) {
        List<Hospital> result = hospitalService.hospitalByArea(area);
        return result;
    }

    /**
     * hospital
     * 获取医院等级和医院地址
     *
     * @param model
     * @return 返回医院列表页
     */
    @RequestMapping(value = "/hospital")
    public String toHospital(Model model) {
        //读取医院全部等级列表
        model.addAttribute("rankSet", hospitalService.findRank());
        //读取医院全部地址列表
        model.addAttribute("areaSet", hospitalService.findArea());
        return "hospital";
    }

    /**
     *搜索 search
     * 搜索页面获取医院等级和地区
     * @param model
     * @return
     */

    @RequestMapping(value = "/search/")
    public String toSearch(Model model) {
        //读取医院全部等级列表
        model.addAttribute("rankSet", hospitalService.findRank());
        //读取医院全部地址列表
        model.addAttribute("areaSet", hospitalService.findArea());
        return "search";
    }


    /**
     * 搜索 search
     * 根据条件返回医院信息
     * @param type 搜索类别
     * @param word 搜索的关键字
     * @param area 地区
     * @param rank 等级
     * @param page 页码
     * @return
     */

    @RequestMapping(value="/searchList/{page}")
    @ResponseBody
    public PageInfo getHospitalByWord(String type,String word,String area,String rank,@PathVariable Integer page){
        PageInfo searchList = hospitalService. findHosByWord(type,word,area,rank,page,6);
        return  searchList;
    }


    /**
     *根据医院挂号
     *（快速预约）
     * 医院列表页
     * 根据等级 地区  页码  医院名称 返回医院信息
     *
     * @param rank 医院等级
     * @param area 医院地区
     * @param page 页码
     * @param  hos  医院名称
     * @return 返回
     */
    @RequestMapping(value = "/hospitalFilter/{page}")
    @ResponseBody
    public PageInfo getHospitalByFilter( String rank, String area, Integer secId, @PathVariable Integer page,String hos) {
        System.out.println(rank);
        PageInfo hosList = hospitalService.findHospitalByFilter(rank, area, secId, page,6,hos);
        return hosList;
    }


    /**
     * 根据医院挂号
     * hosSection
     * （科室信息页面 的医院信息）
     * 根据 用户传入的医院ID 获取医院信息
     * @param id 医院id
     * @param model
     * @return  返回相应医院信息
     */

    @RequestMapping("/hosSection/{id}")
    public String toHos_section(@PathVariable Integer id, Model model){
        Hospital hospital = hospitalService.findHosById(id);
        model.addAttribute("hosInfo",hospital);
        return "hosSection";
    }


    /**
     * 根据科室挂号
     *hospital
     * 根据id 查询科室信息
     * @param id 科室id
     * @param model
     * @return
     */

    @RequestMapping("/hospitalSec/{id}")
    public String toHos(@PathVariable Integer id, Model model){
        Hospital hospital = hospitalService.findHosById(id);
        model.addAttribute("hosInfo", hospital);
        return "hospital";
    }


    /**
     * 快速预约
     * 获取医院信息
     * @param area
     * @param rank
     * @return
     */
    @RequestMapping(value = "/quickHos")
    @ResponseBody
    public List<Hospital> toHosInfo(String area,String rank){
        //获取医院信息
        List<Hospital> toHos=hospitalService.findHosByTerms(area,rank);
        return toHos;
    }


    /**
     * 快速预约
     * 根据医院、科室名称查询id信息
     * @param hName
     * @param sName
     * @return 返回医院id 科室id
     */
    @RequestMapping(value = "/queryId")
    @ResponseBody
    public List idInfo(String hName,String sName){
        List idInfo=hospitalService.findByName(hName,sName);
        return idInfo;
    }

    /**
     * 后台管理
     *查询医院信息
     * @return 医院信息
     */
    @RequestMapping(value = "/mHosList")
    @ResponseBody
    public  List<Hospital> mHosList(){
        List<Hospital> hosList = hospitalService.findHos();
        return  hosList;
    }

    /**
     *后台管理
     * 医院信息增删改操作
     * @param id
     * @param oper
     * @return
     */
    @RequestMapping (value = "/editorHos")
    @ResponseBody
    public  String mHos(String id, String oper, String hname,String harea,
                            String hadr,String htell,String htime,String hrank,String himg){
        if (oper.equals("del")){
          hospitalService.delHos(Integer.parseInt(id));
        }
        if (oper.equals("add")){
            hospitalService.addHos(hname,harea,hadr,htell,htime,hrank,himg);
        }
        if (oper.equals("edit")){
           hospitalService.upHos(Integer.parseInt(id),hname,harea,hadr,htell,htime,hrank,himg);
        }
        return "mHos";
    }



}
