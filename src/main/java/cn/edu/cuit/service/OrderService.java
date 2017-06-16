package cn.edu.cuit.service;

import javax.persistence.criteria.Order;
import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */
public interface OrderService {


    /**
     * 预约信息
     * 保存预约信息
     * @return
     */
    boolean savaOrder();

    /**
     * 个人中心
     * 取消预约
     * @param id
     * @return
     */

    boolean upOrder(Integer id,String dname, String date);

    /**
     * 个人中心
     * 删除预约
     * @param id
     * @return
     */
    boolean delOrder(Integer id);

    /**
     * 个人中心
     * 查询预约信息
     * @return
     */

    List<cn.edu.cuit.pojo.Order> selectOrder();

    /**
     * 后台管理
     * 查询预约信息
     * @return
     */
    List<cn.edu.cuit.pojo.Order> findOrder();

//    void mDelOrder(Integer id);


}
