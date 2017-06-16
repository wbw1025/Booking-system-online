package cn.edu.cuit.controller;

import cn.edu.cuit.pojo.Result;
import cn.edu.cuit.pojo.User;
import cn.edu.cuit.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2017/2/23.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    /**
     * 用户登录
     *
     * @param account
     * @param passwd
     * @return
     */

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(String account, String passwd, String admin) {
        if (StringUtils.isBlank(account) && StringUtils.isBlank(passwd)) {
            return Result.build(400, "用户名或密码不能为空！");
        }
        if (StringUtils.isBlank(admin)){
            admin = "0";
        }
        System.out.println(admin);
        if (userService.login(account, passwd,admin)) {
            return Result.ok();
        } else {
            return Result.build(400, "用户名或密码错误！");
        }
    }

    /**
     * 用户注册
     *
     * @param email      邮箱
     * @param username   用户名
     * @param password   密码
     * @param verifypass 确认密码
     * @return
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(String email, String username, String password, String verifypass) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(verifypass)) {
            return Result.build(400, "内容不能为空！");
        }
        if (!password.equals(verifypass)) {
            return Result.build(400, "两次密码输入不一致！");
        }
        if (userService.register(email, username, password)) {
            return Result.ok();
        } else {
            return Result.build(400, "注册失败 请重新注册！");
        }
    }


    /**
     * 检查用户名或者邮箱是否存在（注册页面）
     * @param type 检查的类型，1为邮箱，2为用户名
     * @param account 账户信息
     * @return 存在返回false，否则返回true
     */
    @RequestMapping("/user/checkaccount/{type}")
    @ResponseBody
    public  boolean checkAccount(@PathVariable String type,String account){
        if (StringUtils.isBlank(type) || StringUtils.isBlank(account)){
            return false;
        }
        return userService.checkAccount(type,account);
    }


    /**
     * 重置密码--发送邮件
     * @param email 重置密码账户
     * @return
     */
    @RequestMapping("/user/sendemail")
    @ResponseBody
    public Result sendEmail(String email){
        if (StringUtils.isBlank(email)){
            return Result.build(400,"邮箱不能为空");
        }
        try {
            userService.sendEmail(email);
        }catch (Exception e){
            e.printStackTrace();
            return Result.build(500,"发送邮件失败");
        }
        return Result.ok();
    }

    /**
     * 验证url
     * @param code
     * @param email
     * @param model
     * @return
     */
    @RequestMapping("/user/validatepass")
    public String validateUrl(String code, String email, Model model){
        if (StringUtils.isBlank(code)||StringUtils.isBlank(email)){
            model.addAttribute("result",Result.build(400,"验证URL无效"));
            return "/findpwd";
        }
        Result result = userService.validateUrl(email,code);
        if (result.getStatus() == 200){
            return "/resetpwd";
        }
        return "/findpwd";
    }


    /**
     * 检查邮箱是否存在(findpwd发送邮件页面）
     * @param email 用于重置密码的邮箱
     * @return 存在返回true 不存在返回false
     */

    @RequestMapping("/user/checkemail")
    @ResponseBody
    public boolean checkEmail(String email){
        if (StringUtils.isBlank(email)){
            return  false;
        }
        return  userService.checkEmail(email);
    }

    /**
     * 重置密码--更新密码
     * @param password 新密码
     * @param vertifypass 确认新密码
     * @return
     */
    @RequestMapping("/user/resetpass")
    @ResponseBody
    public Result resetPass(String password, String vertifypass,String email){
            System.out.println(password+" "+vertifypass+" "+email);
        if (StringUtils.isBlank(password)||StringUtils.isBlank(vertifypass) || StringUtils.isBlank(email)){
            return Result.build(400,"字段不能为空");
        }
        return userService.resetPass(password,vertifypass,email);
    }

    /**
     * 后台管理
     * 查询用户信息
     * @return
     */
    @RequestMapping(value = "/mUserList")
    @ResponseBody
    public List<User> mUserList(){
        List<User> userList = userService.findUser();
        return userList;
    }

    /**
     *后台管理
     * 医院信息增删改操作
     * @param id
     * @param oper
     * @return
     */
    @RequestMapping (value = "/editorUser")
    @ResponseBody
    public  String mHos( String oper,String id, String uname,String upswd, String state,String uemail) {

        if(oper.equals("del")){
             userService.delUser(Integer.parseInt(id));
        }
        if (oper.equals("add")){
            userService.addUser(uname,upswd,state,uemail);
        }
        if (oper.equals("edit")){
             userService.upUser(Integer.parseInt(id),uname,upswd,state,uemail);
        }
        return  "mUser";

    }



    }





















