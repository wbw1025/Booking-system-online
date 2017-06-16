package cn.edu.cuit.service.impl;

import cn.edu.cuit.mapper.UserMapper;
import cn.edu.cuit.pojo.Result;
import cn.edu.cuit.pojo.User;
import cn.edu.cuit.service.UserService;
import cn.edu.cuit.utils.MailUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户service实现类
 * Created by admin on 2017/2/22.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired //spring自动注入
    private UserMapper userMapper;

    @Autowired
    private HttpSession session;

    /**
     * 用户登录方法
     *
     * @param account  用户名或邮箱
     * @param password 密码
     * @return 如果成功则返回true，否则返回false
     */
    public boolean login(String account, String password,String admin) {
        User loginuser = new User();
        User user = null;
        loginuser.setUpswd(DigestUtils.md5DigestAsHex(password.getBytes()));
        //1.先假设输入的为用户名进行登录验证,如果成功则返回true，如果失败再假设为邮箱进行验证
        loginuser.setUname(account);
        loginuser.setState(admin);
        user = userMapper.selectByUser(loginuser);
        if (user != null) {
            session.setAttribute("loginUser", user);
            return true;
        }
        //2.邮箱验证
        loginuser.setUname(null);
        loginuser.setUemail(account);
        user = userMapper.selectByUser(loginuser);
        if (user != null) {
            session.setAttribute("loginUser", user);
            return true;
        }
        return false;
    }

    /**
     * 用户注册
     *
     * @param email    用户邮箱
     * @param username 用户名
     * @param password 密码
     * @return 如果成功则返回true，否则返回false
     */
    public boolean register(String email, String username, String password) {
        String state = "0";
        User register = new User();
        register.setUname(username);
        register.setUpswd(DigestUtils.md5DigestAsHex(password.getBytes()));
        register.setState(state);
        register.setUemail(email);

        if (userMapper.insert(register) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 检查用户名或者邮箱是否存在
     *
     * @param type  检查的类型，1为邮箱，2为用户名
     * @param account 账户信息
     * @return 存在返回false，否则返回true
     */
    public boolean checkAccount(String type, String account) {
        if (type.equals("1")) {
            if (userMapper.selectByEmail(account).size() > 0) {
                return false;
            } else {
                return true;
            }
        } else if (type.equals("2")) {
            if (userMapper.selectByUsername(account).size() > 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查邮箱是否存在
     * @param email 用户邮箱
     * @return 存在返回true 否则false
     */

    public  boolean checkEmail(String email){
        if (userMapper.selectByEmail(email).size() >0 ){
            return true;
        }
        return  false;
    }

    /**
     * 重置密码--发送邮件
     * @param email 重置密码账户
     * @return
     */
    public void sendEmail(String email) throws Exception{
        //生成uuid
        String code = UUID.randomUUID().toString();
        long time = new Date().getTime();
        session.setAttribute(email,code+";"+time);
        String url = "http://192.168.1.105:8080/user/validatepass?"+"email="+email+"&"+"code="+code;
        MailUtils.sendEmail(email,url);
    }


    /**
     * 验证url
     * @param email
     * @param code
     * @return
     */
    public Result validateUrl(String email, String code) {
        String code_time = (String) session.getAttribute(email);
        if (StringUtils.isBlank(code_time)){
            return Result.build(400,"链接无效");
        }
        String oldCode = code_time.split(";")[0];
        String oldTime = code_time.split(";")[1];
        long now = new Date().getTime();
        //如果和现在的时间间隔大于30分钟，则过期，退出
        if (now - Long.parseLong(oldTime) > 30*60*1000){
            return Result.build(400,"链接已过期");
        }
        if (oldCode.equals(code)){
            session.setAttribute("flag",true);
            return Result.ok();
        }
        return null;
    }


    /**
     * 重置密码 -- 更新密码
     * @param password 新密码
     * @param vertifypass 确认密码
     * @param email 用户邮箱
     * @return
     */
    public Result resetPass(String password, String vertifypass,String email){
        String pass= DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(vertifypass)){
            return  Result.build(400,"两次密码不一致");
        }
        if (userMapper.updateByEmail(pass,email) >0){
            return  Result.ok();
        }
        return Result.build(400,"密码修改失败");
    }


    /**
     * 后台管理
     * 查询用户信息
     * @return
     */
    public List<User> findUser(){
        List<User> result = userMapper.selectUser();
        return  result;
    }

    /**
     * 根据id 删除用户
     * @param id
     * @return
     */
    public boolean delUser(Integer id){
        if (userMapper.deleteByPrimaryKey(id) > 0){
            return true;
        }
        return false;
    }


    public void upUser(Integer id,String uname,String upswd,String state,String uemail){
        String pass = DigestUtils.md5DigestAsHex(upswd.getBytes());
        if (id!=null&& uname!=null&& pass!=null&& state!=null){
            userMapper.upUser(id,uname,pass,state,uemail);
        }
    }


    public void addUser(String uname,String upswd,String state,String uemail){
        User user= new User();
        user.setUname(uname);
        user.setUpswd(DigestUtils.md5DigestAsHex(upswd.getBytes()));
        user.setState(state);
        user.setUemail(uemail);
        if (uname!=null&& upswd!=null&& state!=null) {
            userMapper.insert(user);
        }
    }

}











