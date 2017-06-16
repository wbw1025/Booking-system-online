package cn.edu.cuit.task;

import cn.edu.cuit.mapper.OrderMapper;
import cn.edu.cuit.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/5/28.
 */
@Component
public class MyTask {
    @Autowired
    private OrderMapper orderMapper;

    @Scheduled(cron="0 0 0 * * *") //每天零点执行 [秒] [分] [小时] [日] [月] [周] [年]
    public void runTask(){
        List<Order> orderList = orderMapper.selectOrder();
        for(Order order : orderList){
            String time = order.getStime().split(" ")[0];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(time);
                Date now = new Date();
                if (date.getTime() < now.getTime()){
                    orderMapper.updateOrder(order.getOid());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

}
