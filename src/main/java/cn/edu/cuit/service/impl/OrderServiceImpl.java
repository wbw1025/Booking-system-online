package cn.edu.cuit.service.impl;

import cn.edu.cuit.mapper.OrderMapper;
import cn.edu.cuit.mapper.WorkMapper;
import cn.edu.cuit.pojo.Order;
import cn.edu.cuit.pojo.Section;
import cn.edu.cuit.pojo.User;
import cn.edu.cuit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by admin on 2017/4/11.
 */
@Service
    public class OrderServiceImpl implements OrderService {
        @Autowired
        private OrderMapper orderMapper;
        @Autowired
        private HttpSession session;
        @Autowired
        private  WorkMapper workMapper;


        /**
         * 保存预约信息
         * @return
         */
        public boolean savaOrder() {
            Order record = new Order();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String otime = sdf.format(date); //系统当前日期（用户预约时间）
            System.out.println("==========");
            System.out.println(otime);
            User user =(User) session.getAttribute("loginUser");
            String uname = user.getUname();
            String time = (String) session.getAttribute("time"); //就诊时间
            String sname = (String) session.getAttribute("sname");
            String dname = (String) session.getAttribute("dname");
            String hname = (String) session.getAttribute("hname");
            String state = "正常";
            String ap=(String) session.getAttribute("ap");
            String apm="";
            if (ap.equals("1")){
                apm="上午";
            }
            if (ap.equals("-1")){
                apm="下午";
            }
            String stime=time+" "+apm;

            int result;
            record.setOtime(otime);
            record.setStime(stime);
            record.setDname(dname);
            record.setHname(hname);
            record.setDname(dname);
            record.setSname(sname);
            record.setState(state);
            record.setUname(uname);
            result = orderMapper.insertOrder(record);
            if (result > 0) {
                boolean tf;
                tf =  upOrderNum(dname,time,ap);
                if (tf){
                    return true;
                }
            }
            return false;
        }

        /**
         * 对可挂号数量进行操作 减少
         * @param time 就诊时间
         * @param ap 就诊时间是上午还是下午  1表上午  -1 表示下午
         * @return
         */
        public boolean upOrderNum(String dname,String time,String ap){
            System.out.println("============");
            System.out.println(ap);
            System.out.println(time);
            int result=0;
            if (ap.equals("1")){
                result= workMapper.upAmNum(time,dname);
            }
            if (ap.equals("-1")){
                result= workMapper.upPmNum(time,dname);
            }
            if (result>0){
                return  true;
            }
            return false;
        }

        /**
         * 取消预约
         * @param id
         * @param dname
         * @param date
         * @return
         */
        public boolean upOrder(Integer id,String dname,String date){
            int result = orderMapper.updateOrder(id);
            int num = upNum(dname,date);
            if (result > 0 && num>0){
                return true;
            }
            return false;

        }

        /**
         * 对可挂号数量进行操作 增加
         * @param date 就诊时间
         * @param dname 就诊时间是上午还是下午
         * @return
         */
        public int upNum(String dname,String date){
            String [] arr = date.split("\\s+");
            String dt=arr[0];
            String ap = arr[1];
            int rt=0;
            if (ap.equals("上午")){
                rt = workMapper.upAnum(dname,dt);
            }
            if (ap.equals("下午")){
                rt = workMapper.upPnum(dname,dt);
            }
            return rt;
        }

        public boolean delOrder(Integer id){
            int result = orderMapper.deleteByPrimaryKey(id);
            if (result >0){
                return true;
            }
            return false;
        }

        public List selectOrder() {
            User user =(User) session.getAttribute("loginUser");
            String uName = user.getUname();
            List result = orderMapper.selectOrderByUname(uName);
            return result;
        }

        /**
         * 后台管理
         * 查询预约信息
         *
         * @return
         */

        public List findOrder() {
            List result = orderMapper.selectOrder();
            return result;
        }
    /**
     * 后台管理
     * 查询预约信息
     *
     * @return
     *//*
    public void mDelOrder(Integer id){
        if (id!=null){
            orderMapper.deleteByPrimaryKey(id);
        }
    }*/
}
