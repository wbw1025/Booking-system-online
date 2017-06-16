package cn.edu.cuit.service;

import cn.edu.cuit.pojo.Section;
import cn.edu.cuit.pojo.SectionType;
import cn.edu.cuit.pojo.Work;

import java.util.List;


/**
 * Created by admin on 2017/3/12.
 */
public interface SectionService {

    /**
     *读取父级科室列表
     * @return
     */
    List<SectionType> findSection();

    /**
     * 查找科室列表
     * @return
     */
    List findSec();

    /**
     * 根据医院id查询一级科室
     * @return
     */
    List selectByHosId(Integer id);

    /**
     *根据科室id 查询科室信息
     * @param secId
     * @return
     */

    Section selectBySecId(Integer secId);


    /**
     * 根据医院查询科室信息
     * @param hos
     * @return
     */
    List<Section> selectByHos(String hos);

    /**
     * 根据医院和科室id查询可预约列表
     * @param hosId
     * @param secId
     * @return
     */

    List<Work> selectById(Integer hosId, Integer secId);

    /**
     *
     * 根据关键字查询科室id
     * @param word
     * @return
     */

    List<Section> findSid(String word);

    /**
     * 后台管理
     * 查询科室信息
     * @return
     */
    List<Section> mFindSec();

    void delSec(Integer id);
    void addSec(String sname, String type);
    void editSec(Integer id,String sname,String type);


}
