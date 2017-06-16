package cn.edu.cuit.controller;

import cn.edu.cuit.service.OrderService;
import javafx.scene.input.DataFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.Order;
import javax.servlet.http.HttpSession;
import javax.swing.text.SimpleAttributeSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpSession session;

    /**
     * 取消操作
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateOrder")
    @ResponseBody
    public boolean upOrder(Integer id,String dname,String date) {
        if (id == null) {
            return false;
        }
        return orderService.upOrder(id,dname,date);
    }

    /**
     * 删除预约
     * @param id
     * @return
     */

    @RequestMapping(value = "/delOrder")
    @ResponseBody
    public boolean delOrder(Integer id) {
        if (id == null) {
            return false;
        }
        return orderService.delOrder(id);
    }

    /**
     * 把用户选择预约的信息保存在session里
     *
     * @param hname
     * @param sname
     * @param dname
     * @param money
     * @param time
     * @param hadr
     * @return
     */
    @RequestMapping(value = "/gOrder")
    @ResponseBody
    public boolean orderInfo(String hname, String sname, String dname,
                             String money, String time, String hadr,String ap) {
        if (StringUtils.isBlank(hname) && StringUtils.isBlank(sname) && StringUtils.isBlank(dname) &&
                StringUtils.isBlank(money) && StringUtils.isBlank(time) && StringUtils.isBlank(hadr)) {
            return false;
        }
        session.setAttribute("hname", hname);
        session.setAttribute("dname", dname);
        session.setAttribute("sname", sname);
        session.setAttribute("money", money);
        session.setAttribute("time", time);
        session.setAttribute("hadr", hadr);
        session.setAttribute("ap",ap);
        return true;
    }

    /**
     * 确认预约信息
     * 将预约信息保存到数据库
     *
     * @return 保存是否成功
     */
    @RequestMapping(value = "/order")
    @ResponseBody
    public boolean orderList() {
        return orderService.savaOrder();

    }

    /**
     * 后台管理
     * 查询预约信息
     *
     * @return
     */
    @RequestMapping(value = "/mOrderList")
    @ResponseBody
    public List<cn.edu.cuit.pojo.Order> mOrderList() {
        List<cn.edu.cuit.pojo.Order> orderList = orderService.findOrder();
        return orderList;
    }

    /**
     * 后台管理
     * 预约增删改操作
     *
     * @return
     *//*
    @RequestMapping(value = "/editorOrder")
    @ResponseBody
    public String editOrder(String id,String oper){
        if(oper.equals("del")){
            orderService.mDelOrder(Integer.parseInt(id));
        }
        return "mOrder";
    }*/

}
