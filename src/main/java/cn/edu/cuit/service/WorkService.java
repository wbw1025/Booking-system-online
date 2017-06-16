package cn.edu.cuit.service;

import cn.edu.cuit.pojo.Work;

import java.util.List;

/**
 * Created by admin on 2017/4/26.
 */
public interface WorkService {

    List<Work> findWork();

    void delWork(Integer id);
    void addWork(String name,String date,Integer anum,Integer pnum);
    void editWork(Integer id, String name,String date,Integer anum,Integer pnum);
}
