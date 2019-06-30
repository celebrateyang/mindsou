package com.mindsou.vo;

import java.util.Date;

/**
 * Created by 竹庆 on 2019/6/23.
 */
public class Qiyue {

    private Integer id;
    private String name;
    private String position;
    private double salary;
    private Date start_date;
    private String office;
    private Integer extn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Integer getExtn() {
        return extn;
    }

    public void setExtn(Integer extn) {
        this.extn = extn;
    }
}
