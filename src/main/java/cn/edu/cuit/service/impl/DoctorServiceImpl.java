package cn.edu.cuit.service.impl;


import cn.edu.cuit.mapper.DoctorMapper;
import cn.edu.cuit.pojo.Doctor;
import cn.edu.cuit.pojo.Doctorcopy;
import cn.edu.cuit.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/4/13.
 */
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;

    /**
     * 预约表
     * 根据条件查询医生信息
     * @param hosId 医院id
     * @param secId 科室 id
     * @param date 日期
     * @param num  上午（1） 下午（-1）
     * @return
     */
    public List<Doctorcopy> findDoctor(Integer hosId, Integer secId, String date, Integer num){
        List<Doctorcopy> doctor = doctorMapper.selectDoctorInfo(hosId,secId,date,num);
        return  doctor;
    }

    /**
     * 后台管理
     * 查询医生信息
     * @return
     */
    public List<Doctor> mFindDoctor(){
        List<Doctor> result= doctorMapper.selectDoctor();
        return result;
    }


    public void delDoctor(Integer id){
        if (id!=null){
            doctorMapper.deleteByPrimaryKey(id);
        }
    }

    public void addDoctor(String dname,String dsex, String dtitle,Integer hid,Integer sid,String money){
        Doctor doctor = new Doctor();
        doctor.setDname(dname);
        doctor.setDsex(dsex);
        doctor.setDtitle(dtitle);
        doctor.setHospitalHid(hid);
        doctor.setSectionSid(sid);
        doctor.setMoney(money);
        doctorMapper.insert(doctor);
    }

    public void editDoctor(Integer id, String dname,String dsex, String dtitle,Integer hid,Integer sid,String money){
        if (id!=null){
            Doctor doctor = new Doctor();
            doctor.setDname(dname);
            doctor.setDsex(dsex);
            doctor.setDtitle(dtitle);
            doctor.setHospitalHid(hid);
            doctor.setSectionSid(sid);
            doctor.setMoney(money);
            doctor.setDid(id);
            doctorMapper.updateByPrimaryKey(doctor);
        }
    }


}
