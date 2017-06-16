package cn.edu.cuit.pojo;

/**
 * Created by admin on 2017/5/25.
 */
public class Doctorcopy {
    private String dname;
    private Integer anum;
    private  Integer pnum;
    private String dtitle;
    private  String money;
    public String getDname() {
        return dname;
    }
    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    public Integer getAnumc() {
        return anum;
    }
    public void setAnumc(Integer did) {
        this.anum = anum;
    }

    public Integer getPnumc() {
        return pnum;
    }
    public void setPnumc(Integer did) {
        this.pnum = pnum;
    }

    public String getDtitle() {
        return dtitle;
    }
    public void setDtitle(String dtitle) {
        this.dtitle = dtitle == null ? null : dtitle.trim();
    }

    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {this.money = money == null ? null : money.trim();}

}
