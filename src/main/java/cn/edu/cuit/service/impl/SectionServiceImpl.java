package cn.edu.cuit.service.impl;

import cn.edu.cuit.mapper.SectionMapper;
import cn.edu.cuit.mapper.SectionTypeMapper;
import cn.edu.cuit.mapper.WorkMapper;
import cn.edu.cuit.pojo.Section;
import cn.edu.cuit.pojo.SectionType;
import cn.edu.cuit.pojo.Work;
import cn.edu.cuit.service.SectionService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on 2017/3/12.
 */
@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private SectionTypeMapper sectionTypeMapper;
    @Autowired
    private WorkMapper workMapper;

    /**
     *查找所有一级科室
     * @return
     */
    public List<SectionType> findSection(){
        List<SectionType> sectionSet = new ArrayList<SectionType>();
        sectionSet = sectionTypeMapper.selectSection();
        return sectionSet;
    }

    /**
     * 查找所有科室（以及科室和二级科室 组成json数据格式）
     * @return
     */
    public List findSec(){
        List result = new ArrayList();
        //1.查找父级科室
        for (SectionType sectionType : findSection()){
            JSONObject json = new JSONObject();
            json.put("parent",sectionType);
            //2.根据父级科室查找子集
            List<Section> sectionList = sectionMapper.selectByType(sectionType.getId());
            json.put("child",sectionList);
            result.add(json);
        }
        return result;
    }

    /**
     * 根据医院id查询科室信息
     * @param id 医院id
     * @return
     */
    public  List<SectionType> selectByHosId(Integer id){
        List<SectionType> parent = new ArrayList();
        List result = new ArrayList();
        if (id ==null){
            return null;
        }
        //查找父级
        parent = sectionTypeMapper.selectByHosId(id);
        for (SectionType sectionType: parent){
            JSONObject json = new JSONObject();
            json.put("hosParent",sectionType);
            //根据父级和医院ID查询子集
            List<Section>  hosChild =sectionMapper.selectByIdType(id,sectionType.getId());
            json.put("hosChild",hosChild);
            result.add(json);
        }
        return result;
    }

    /**
     * 根据科室id 查询科室信息
     * @param secId
     * @return
     */
    public Section selectBySecId(Integer secId){
        Section secInfo = sectionMapper.selectByPrimaryKey(secId);
        return secInfo;
    }

    public List<Work> selectById(Integer hosId, Integer secId){
        List<Work> orderInfo =new  ArrayList<Work>(); //符合条件的预约信息
        //根据医院科室id查询排班信息
        List<Work> orderList = workMapper.selectById(hosId,secId);
        //获取系统当前日期 cDate 系统当前日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date cDate = new Date();
        //获取当前日期15天后的日期 aDate系统15后日期
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE,5);
        Date aDate = c.getTime();
        //遍历排班信息
        for (int i=0;i<orderList.size();i++){
            //获取当前排班记录的日期 hDate 当前记录日期
            String date = orderList.get(i).getDate();
            try {
                Date oDate = format.parse(date);
                if (oDate.getTime() < aDate.getTime() && oDate.getTime()>cDate.getTime()){
                    orderInfo.add(orderList.get(i));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return orderInfo;
    }

    /**
     * 快速预约
     * 根据医院名称查询科室信息
     * @param hos
     * @return
     */
    public List<Section> selectByHos(String hos){
        List<Section> secInfo = sectionMapper.selectByHos(hos);
        return secInfo;
    }



    public List findSid(String word){
        List<Section> queryInfo = sectionMapper.selectByWord(word);
        return queryInfo;
    }

    /**
     * 后台管理
     * 查询科室信息
     * @return
     */
    public List<Section> mFindSec(){
        List<Section> result = sectionMapper.selectSec();
        return result;
    }

    /**
     * 后台管理系统
     * @param id
     */
    public void delSec(Integer id){
        if (id!=null){
            sectionMapper.deleteByPrimaryKey(id);
        }
    }

    public void addSec(String sname,String type){
        Section section = new Section();
        section.setSname(sname);
        section.setType(type);
        sectionMapper.insert(section);
    }

    public void editSec(Integer id,String sname,String type){
        if (id!=null){
            sectionMapper.upSec(id,sname,type);
        }
    }

}
