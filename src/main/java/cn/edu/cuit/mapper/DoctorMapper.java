package cn.edu.cuit.mapper;

import cn.edu.cuit.pojo.Doctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer did);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbggenerated
     */
    int insert(Doctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbggenerated
     */
    int insertSelective(Doctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbggenerated
     */
    Doctor selectByPrimaryKey(Integer did);


    //根据条件查询医生信息
    List selectDoctorInfo(@Param("hosId") Integer hosId, @Param("secId") Integer secId, @Param("date") String date,@Param("num") Integer num);



   List<Doctor> selectDoctor();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Doctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Doctor record);
}