package cn.edu.cuit.service.impl;

import cn.edu.cuit.mapper.HospitalMapper;
import cn.edu.cuit.mapper.SectionMapper;
import cn.edu.cuit.pojo.Hospital;
import cn.edu.cuit.pojo.Result;
import cn.edu.cuit.service.HospitalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.objects.annotations.Function;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/3/9.
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalMapper hospitalMapper;
    @Autowired
    private SectionMapper sectionMapper;

    /**
     *根据医院挂号
     * 读取医院全部地址列表
     *
     * @return
     */
    public Set<String> findArea() {
        Set<String> addressSet = new HashSet<String>();
        addressSet = hospitalMapper.selectAllAddress();
        return addressSet;
    }

    /**
     * 查询全部医院等级列表
     * @return
     */
    public Set<String> findRank() {
        //Set不保存重复的元素
        Set<String> rankSet = new HashSet<String>();
        rankSet = hospitalMapper.selectRank();
        return rankSet;
    }

    /**
     * 根据科室id查询医院信息
     * @param id 科室id
     * @return 返回医院信息
     */
    public Hospital findHosById(Integer id){
        Hospital hosInfo = hospitalMapper.selectByPrimaryKey(id);
        return hosInfo;
    }


    /**
     * 根据医院id 或名称返回医院信息
     * @param id 医院ID
     * @param name 医院名称
     * @return
     */
    public Hospital findHosByHos(Integer id,String name){
        Hospital hosInfo = hospitalMapper.selectByHos(id,name);
        return hosInfo;
    }

    /**
     * 查询指定数量医院列表
     *
     * @param num  数量
     * @return  指定返回的医院数量，如果为-1则返回所有
     */
    public List<Hospital> findHospitalByNum(int num) {
        List<Hospital> result = new ArrayList<Hospital>();
        result = hospitalMapper.selectByNum(num);
        return result;
    }

    /**
     * 根据等级、地区、页码 查询医院信息
     *
     * @param page 页码
     * @param rows 每页显示的数量
     * @return 返回指定等级、地区、页码的医院列表
     */
    public PageInfo findHospitalByFilter(String rank,String area, Integer secId, Integer page, Integer rows,String hos){
        if(rank == ""){
            rank="全部";
        }
        if(area == ""){
            area="全部";
        }
        if(page==null){
            page=1;
        }
        if(rows == null){
            rows=6;
        }
        PageHelper .startPage(page,rows);
        List<Hospital> hosList = hospitalMapper.selectByFilter(rank,area,secId,hos);
        PageInfo pageInfo =new PageInfo(hosList);
        return pageInfo;
    }


    /**
     * 搜索
     * 根基条件返回医院信息
     * @param type 搜索类别
     * @param word 搜索关键字
     * @param area 地区
     * @param rank 等级
     * @param page 页码
     * @param rows 查询数据条数
     * @return
     */

    public PageInfo findHosByWord(String type,String word,String area, String rank,Integer page, Integer rows){
        List<Hospital> result = new ArrayList<Hospital>();
        PageHelper.startPage(page,rows);
        //根据医院名称   获取医院信息
       if(type.equals("医院")){
           result = hospitalMapper.selectByHosWord(word,area,rank);
        }
        //根据科室名称   获取医院信息
         if (type.equals("科室")){
           result = hospitalMapper.selectBySecWord(word,area,rank);
        }
        //根据疾病名称   获取医院信息
        if (type.equals("疾病")){
            result =hospitalMapper.selectBySickWord(word,area,rank);
        }
        PageInfo pageInfo = new PageInfo(result);
        return pageInfo;

    }

    /**
     * 根据地区查询医院信息
     * @param area 医院地区
     * @return
     */
    public List hospitalByArea(String area){
      List  result = hospitalMapper.selectByArea(area);
       return  result;
    }

    /**
     * 根据科室挂号
     * @param id 科室id
     * @return
     */

    public Hospital findHosBySecId(Integer id){
        Hospital hosInfo =hospitalMapper.selectByPrimaryKey(id);
        return hosInfo;
    }


    public  List<Hospital> findHosByTerms(String area,String rank){
        System.out.println(area);
        System.out.println(rank);

        List<Hospital> hosInfo = hospitalMapper.selectByTerms(area,rank);
        return  hosInfo;
    }


    public List findByName(String hName,String sName){
        List idInfo =new  ArrayList();
        String hid= hospitalMapper.selectByHname(hName);
        String sid=sectionMapper.selectBySname(sName);
        idInfo.add(hid);
        idInfo.add(sid);
        return  idInfo;
    }



    /**
     * 后台管理系统
     * @return 所有医院信息
     */
    public List<Hospital> findHos(){
        List<Hospital> result = hospitalMapper.selectHos();
        return result;
    }

    public void delHos(Integer id){
       if(id!=null){
           hospitalMapper.deleteByPrimaryKey(id);
       }
    }


    public void addHos(String hname,String harea,String hadr, String htell,String htime,String hrank,String himg){
        Hospital hospital = new Hospital();
        hospital.setHname(hname);
        hospital.setHarea(harea);
        hospital.setHadr(hadr);
        hospital.setHtell(htell);
        hospital.setHtime(htime);
        hospital.setHrank(hrank);
        hospital.setHimg(himg);
        System.out.println(hospital);
        if (hname!=null){
            hospitalMapper.insert(hospital);
        }
    }



    public void upHos(Integer id, String hname,String harea,String hadr, String htell,String htime,String hrank,String himg){
        if (id!=null){
            hospitalMapper.upHos(id,hname,harea,hadr,htell,htime,hrank,himg);
        }
    }

}
