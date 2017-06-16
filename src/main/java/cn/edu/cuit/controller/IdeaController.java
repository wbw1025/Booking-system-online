package cn.edu.cuit.controller;

import cn.edu.cuit.pojo.Idea;
import cn.edu.cuit.pojo.Order;
import cn.edu.cuit.pojo.Result;
import cn.edu.cuit.service.IdeaService;
import cn.edu.cuit.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/3/31.
 */
@Controller
public class IdeaController {

   @Autowired
   private IdeaService ideaService;
   @Autowired
   private  OrderService orderService;

    /**
     *个人中心
     * 获取预约信息和意见信息列表
     * @param model
     * @return
     */
   @RequestMapping(value = "/zom")
   public  String ideaList(Model model){
       List<Idea> idea = ideaService.selectIdea();
       List<Order> order = orderService.selectOrder();
       model.addAttribute("ideaInfo", idea);
       model.addAttribute("orderInfo",order);
       return "zom";
   }


    /**
     * 个人中心
     * 意见信息的提交
     * @param uid
     * @param idea
     * @return
     */
   @RequestMapping(value = "/idea")
   @ResponseBody
    public Result toIdea(Integer uid, String idea){
       if (uid == null || StringUtils.isBlank(idea)){
           return Result.build(400,"内容不能为空");
       }
       if (ideaService.toIdea(uid,idea)){
           return Result.ok();
       }else {
           return Result.build(400,"意见提交失败");
       }
   }
}
