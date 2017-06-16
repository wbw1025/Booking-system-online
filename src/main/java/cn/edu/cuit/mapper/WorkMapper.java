package cn.edu.cuit.mapper;

import cn.edu.cuit.pojo.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer wid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work
     *
     * @mbggenerated
     */
    int insert(Work record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work
     *
     * @mbggenerated
     */
    int insertSelective(Work record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work
     *
     * @mbggenerated
     */
    Work selectByPrimaryKey(Integer wid);

    /**
     * 根据科室id 和医院id  查询可预约信息
     */

    List<Work> selectById(@Param("hosId") Integer hosId, @Param("secId") Integer secId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Work record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Work record);

    int  upAmNum(@Param("time") String time,@Param("dname") String dname);
    int  upPmNum(@Param("time") String time,@Param("dname") String dname);

    int  upAnum(@Param("dname") String dname,@Param("date") String date);
    int  upPnum(@Param("dname") String dname,@Param("date") String date);

    List<Work> selectWork();

}