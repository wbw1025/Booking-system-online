package cn.edu.cuit.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */
public interface  SickService {

//    获取疾病信息
    List findSick( String body);

}
