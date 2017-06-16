package cn.edu.cuit.service.impl;

import cn.edu.cuit.mapper.SickMapper;
import cn.edu.cuit.mapper.SickTypeMapper;
import cn.edu.cuit.pojo.Sick;
import cn.edu.cuit.pojo.SickType;
import cn.edu.cuit.service.SickService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */
@Service
public class SickServerImpl implements SickService{
    @Autowired
    private SickMapper sickMapper;
    @Autowired
    private SickTypeMapper sickTypeMapper;


/*一次获取所有疾病信息
    public List findSick1(){
        List result = new ArrayList();
        List<SickType> parentBody = new ArrayList();
        List<SickType> parentName= new ArrayList();
        parentBody = sickTypeMapper.findBody();
        for (SickType sickType:parentBody){
            JSONObject json = new JSONObject();
            List list = new ArrayList();
            json.put("first",sickType);
            parentName = sickTypeMapper.selectByBody(sickType.getBody());
            for (SickType sickType1:parentName){
                JSONObject json1 = new JSONObject();
                json1.put("body",sickType1);
                List<Sick> sick = sickMapper.selectByType(sickType1.getId());
                json1.put("sick",sick);
                list.add(json1);
            }
            json.put("second",list);
            result.add(json);
        }
        return result;
    }*/


    public List findSick(String body){
        List result = new ArrayList();
        List<SickType> parent = new ArrayList();
        if (StringUtils.isBlank(body)){
            return null;
        }
        //查找父级
        parent = sickTypeMapper.selectByBody(body);
        for (SickType sickType:parent){
            JSONObject json = new JSONObject();
            json.put("body",sickType);
            //根据父级和医院ID查询子集
            List<Sick> sickList =sickMapper.selectByType(sickType.getId());
            json.put("sick",sickList);
            result.add(json);
        }
        return result;
    }

}
