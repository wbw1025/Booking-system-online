package cn.edu.cuit.service;

import cn.edu.cuit.pojo.Hospital;
import cn.edu.cuit.pojo.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/3/9.
 */
public interface HospitalService {

    /**
     * 读取医院全部地址列表
     * @return 返回去重后的地址列表
     */
    Set<String> findArea();

    /**
     * 读取医院全部等级列表
     * @return 返回去重后的等级列表
     */
    Set<String> findRank();

    /**
     * 根据科室id 查询医院信息
     * @param id 科室id
     * @return 返回医院信息
     */
    Hospital findHosById(Integer id);



    Hospital findHosByHos(Integer id,String name);

    /**
     * 读取指定数量医院列表
     * @param num 数量
     * @return 返回指定数量的医院，如果为-1则返回所有
     */
    List<Hospital> findHospitalByNum(int num);

    /**
     * 医院列表页
     *根据等级 地区 页码  查询医院信息
     * @param page 页码
     * @param rows 每页显示的数量
     * @return 返回指定等级、地区、页码的医院列表
     */
    PageInfo findHospitalByFilter(String rank,String area, Integer secId, Integer page, Integer rows,String hos);


    /**
     * 搜索
     * 根据条件返回医院信息
     * @param type 搜索类别
     * @param word 搜索关键字
     * @param area 地区
     * @param rank 等级
     * @param page 页码
     * @param rows 查询数据条数
     * @return
     */
    PageInfo  findHosByWord(String type,String word,String area,String rank, Integer page ,Integer rows);

    /**
     * 根据地区 查询医院信息
     * @param area 医院地区
     * @return 返回指定地区的医院列表
     */
    List<Hospital> hospitalByArea(String area);

    /**
     *
     * 按科室挂号
     *
     *  根据科室id查询医院信息
     * @param id 科室id
     * @return
     */
    Hospital findHosBySecId(Integer id);


    /**
     * 快速预约
     * 根据条件查询医院信息
     * @param area 地区
     * @param rank 等级
     * @return
     */
    List<Hospital> findHosByTerms(String area,String rank);


    /**
     * 根据医院科室名称查询id信息
     * @param hName 医院名称
     * @param sname 科室名称
     * @return
     */
    List findByName(String hName,String sname);

    /**
     * 后台管理系统
     * @return 所有医院信息
     */
    List<Hospital> findHos();
//    删除
    void delHos(Integer id);
    //添加
    void addHos(String hname,String harea,String hadr, String htell,String htime,String hrank,String himg);
    //添加
    void upHos(Integer id, String hname,String harea,String hadr, String htell,String htime,String hrank,String himg);



}
