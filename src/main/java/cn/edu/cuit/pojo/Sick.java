package cn.edu.cuit.pojo;

public class Sick {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sick.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sick.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sick.section_sid
     *
     * @mbggenerated
     */
    private Integer section_sid;


    private  String type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sick.id
     *
     * @return the value of sick.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sick.id
     *
     * @param id the value for sick.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sick.name
     *
     * @return the value of sick.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sick.name
     *
     * @param name the value for sick.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sick.section_sid
     *
     * @return the value of sick.section_sid
     *
     * @mbggenerated
     */
    public Integer getSection_sid() {
        return section_sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sick.section_sid
     *
     * @param section_sid the value for sick.section_sid
     *
     * @mbggenerated
     */
    public void setSection_sid(Integer section_sid) {
        this.section_sid = section_sid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}