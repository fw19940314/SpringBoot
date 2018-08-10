package com.cache.domain;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/9
 * @Description:
 */
public class Cust {
    private Integer id;
    private String custName;
    private Integer custAge;
    private String custGender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getCustAge() {
        return custAge;
    }

    public void setCustAge(Integer custAge) {
        this.custAge = custAge;
    }

    public String getCustGender() {
        return custGender;
    }

    public void setCustGender(String custGender) {
        this.custGender = custGender;
    }

    @Override
    public String toString() {
        return "Cust{" +
                "id=" + id +
                ", custName='" + custName + '\'' +
                ", custAge=" + custAge +
                ", custGender='" + custGender + '\'' +
                '}';
    }
}
