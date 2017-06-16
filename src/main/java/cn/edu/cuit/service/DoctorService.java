package cn.edu.cuit.service;

import cn.edu.cuit.pojo.Doctor;
import cn.edu.cuit.pojo.Doctorcopy;

import java.util.List;

/**
 * Created by admin on 2017/4/13.
 */
public interface DoctorService {
    /**
     * 查找医师信息
     * @param hosId 医院id
     * @param secId 科室 id
     * @param date 日期
     * @param num  上午（1） 下午（-1）
     * @return
     */
    List<Doctorcopy> findDoctor(Integer hosId, Integer secId, String date, Integer num );

    /**
     * 后台管理
     * 查询医生信息
     *
     */
    List<Doctor> mFindDoctor();

    /**
     * 后台管理增删改操作
     */
    void delDoctor(Integer id);
    void addDoctor(String dname,String dsex, String dtitle,Integer hid,Integer sid,String money);
    void editDoctor(Integer id, String dname,String dsex, String dtitle,Integer hid,Integer sid,String money);
}
