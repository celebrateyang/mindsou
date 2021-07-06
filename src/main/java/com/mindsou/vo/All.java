package com.mindsou.vo;

import java.util.List;

/**
 * Created by 竹庆 on 2019/5/30.
 */
public class All {
    private String allName;
    private List<Items> itemses;
    private String[] box;

    public String getAllName() {
        return allName;
    }

    public void setAllName(String allName) {
        this.allName = allName;
    }

    public List<Items> getItemses() {
        return itemses;
    }

    public void setItemses(List<Items> itemses) {
        this.itemses = itemses;
    }

    public String[] getBox() {
        return box;
    }

    public void setBox(String[] box) {
        this.box = box;
    }
}
