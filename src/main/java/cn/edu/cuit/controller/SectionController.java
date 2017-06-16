package cn.edu.cuit.controller;

import cn.edu.cuit.pojo.Hospital;
import cn.edu.cuit.pojo.Section;
import cn.edu.cuit.pojo.Work;
import cn.edu.cuit.service.HospitalService;
import cn.edu.cuit.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by admin on 2017/3/12.
 */
@Controller
public class SectionController {

    @Autowired
    private SectionService sectionService;
    @Autowired
    private HospitalService hospitalService;

    /**
     * section
     * 查找所有一级科室（左侧菜单）
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/section")
    public String toSection(Model model) {
        //读取医院全部一级科室列表
        model.addAttribute("sectionSet", sectionService.findSection());
        return "section";
    }

    /**
     * section
     * 查找所有一级科和二级科室及其对应关系(内容区域)
     *
     * @return
     */
    @RequestMapping(value = "/sectionList")
    @ResponseBody
    public List listSection() {
        List secList = sectionService.findSec();
        return secList;
    }

    /**
     * hosSection
     * 根据医院id查询科室信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/hosSecList")
    @ResponseBody
    public List hosSecList(Integer id) {
        List hosSecList = sectionService.selectByHosId(id);
        return hosSecList;
    }

    /**
     * 预约信息列表页
     * 根据医院id 科室id 查询医院科室信息
     *
     * @param hosId
     * @param secId
     * @param model
     * @return
     */
    @RequestMapping(value = "/order/{hosId}/{secId}")
    public String toHosInfo(@PathVariable Integer hosId, @PathVariable Integer secId, Model model) {
//        根据医院id  查询医院信息
        Hospital hospital = hospitalService.findHosById(hosId);
        model.addAttribute("hosInfo", hospital);
//        根据科室id，查询科室信息
        Section section = sectionService.selectBySecId(secId);
        model.addAttribute("secInfo", section);
//        根据医院和科室id查询科预约列表
        List<Work> order = sectionService.selectById(hosId, secId);
        model.addAttribute("orderInfo", order);
        return "/order";
    }


    /**
     * 根据科室挂号
     * 根据科室id  查询科室信息
     *
     * @param id    科室id
     * @param model
     * @return
     */
    @RequestMapping(value = "/hospital/{id}")
    public String toSec(@PathVariable Integer id, Model model) {
        Section sec = sectionService.selectBySecId(id);
        model.addAttribute("secInfo", sec);
        //读取医院全部等级列表
        model.addAttribute("rankSet", hospitalService.findRank());
        //读取医院全部地址列表
        model.addAttribute("areaSet", hospitalService.findArea());
        return "hospital";
    }

    /**
     * 快速预约
     * 获取科室信息
     *
     * @param hos 医院名称
     * @return
     */
    @RequestMapping(value = "/quickSec")
    @ResponseBody
    public List<Section> toSecInfo(String hos) {
        //获取科室信息
        List<Section> toSec = sectionService.selectByHos(hos);
        return toSec;
    }


    @RequestMapping(value = "/querySid")
    @ResponseBody
    public List querySid(String word) {
        System.out.println(word);
        List<Section> queryInfo = sectionService.findSid(word);
        return queryInfo;
    }


    /**
     * 后台管理
     * 查询科室信息
     *
     * @return
     */
    @RequestMapping(value = "/mSecList")
    @ResponseBody
    public List<Section> mSecList() {
        List<Section> secList = sectionService.mFindSec();
        return secList;
    }


    /**
     * 后台管理
     * 科室信息增删改操作
     *
     * @param id
     * @param oper
     * @return
     */
    @RequestMapping(value = "/editorSec")
    @ResponseBody
    public String mHos(String id, String oper, String sname, String type) {
        if (oper.equals("del")) {
            sectionService.delSec(Integer.parseInt(id));
        }
        if (oper.equals("add")) {
            sectionService.addSec(sname, type);
        }
        if (oper.equals("edit")) {
            sectionService.editSec(Integer.parseInt(id), sname, type);
        }
        return "mSec";
    }

}
