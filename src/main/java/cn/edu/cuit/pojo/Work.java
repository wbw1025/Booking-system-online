package cn.edu.cuit.pojo;

public class Work {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.wid
     *
     * @mbggenerated
     */
    private Integer wid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.doctor_did
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.date
     *
     * @mbggenerated
     */
    private String date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.anum
     *
     * @mbggenerated
     */
    private Integer anum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.pnum
     *
     * @mbggenerated
     */
    private Integer pnum;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.wid
     *
     * @return the value of work.wid
     *
     * @mbggenerated
     */
    public Integer getWid() {
        return wid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.wid
     *
     * @param wid the value for work.wid
     *
     * @mbggenerated
     */
    public void setWid(Integer wid) {
        this.wid = wid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.doctor_did
     *
     * @return the value of work.doctor_did
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.doctor_did
     *
     * @param name the value for work.doctor_did
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.date
     *
     * @return the value of work.date
     *
     * @mbggenerated
     */
    public String getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.date
     *
     * @param date the value for work.date
     *
     * @mbggenerated
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.anum
     *
     * @return the value of work.anum
     *
     * @mbggenerated
     */
    public Integer getAnum() {
        return anum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.anum
     *
     * @param anum the value for work.anum
     *
     * @mbggenerated
     */
    public void setAnum(Integer anum) {
        this.anum = anum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.pnum
     *
     * @return the value of work.pnum
     *
     * @mbggenerated
     */
    public Integer getPnum() {
        return pnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.pnum
     *
     * @param pnum the value for work.pnum
     *
     * @mbggenerated
     */
    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }
}