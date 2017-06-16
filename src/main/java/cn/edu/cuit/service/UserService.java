package cn.edu.cuit.service;

import cn.edu.cuit.pojo.Result;
import cn.edu.cuit.pojo.User;

import java.util.List;

/**
 * 用户相关service接口类
 * Created by admin on 2017/2/22.
 */
public interface UserService {

    /**
     * 用户登录方法
     * @param account 用户名或邮箱
     * @param password 密码
     * @return 如果成功则返回true，否则返回false
     */
    boolean login(String account,String password, String admin);

    /**
     * 用户注册
     * @param email 用户邮箱
     * @param username 用户名
     * @param password 密码
     * @return 如果成功则返回true，否则返回false
     */
    boolean register(String email,String username,String password);

    /**
     * 检查用户名或者邮箱是否存在
     * @param type 检查的类型，1为邮箱，2为用户名
     * @param account 账户信息
     * @return 存在返回false，否则返回true
     */
    boolean checkAccount(String type,String account);

    /**
     * 检查邮箱是否存在
     * @param email 用户邮箱
     * @return 存在返回true
     */
    boolean checkEmail(String email);

    /**
     * 重置密码--发送邮件
     * @param email 重置密码账户
     * @return
     */
    void sendEmail(String email) throws Exception;

    Result validateUrl(String email,String code);


    /**
     * 重置密码--更新密码
     * @param password 新密码
     * @param vertifypass 确认密码
     * @param email 用户邮箱
     * @return
     */
   Result resetPass(String password, String vertifypass,String email);

    /**
     * 后台管理系统
     * 查询用户信息
     * @return
     */
   List<User> findUser();

    /**
     * 根据id 删除用户信息
     * @param id
     * @return
     */
   boolean delUser(Integer id);


    void upUser(Integer id,String uname,String upswd,String state,String uemail);

    void addUser(String uname,String upswd,String state,String uemail);

}
