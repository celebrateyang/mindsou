package com.mindsou.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 竹庆 on 2016/9/20.
 */
@javax.persistence.Entity
@Table(name="t_mind")
public class Mind {
    Long uid;
    String content;

    public Mind(){}

    public Mind(Long uid, String content) {
        this.uid = uid;
        this.content = content;
    }

    @Id
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
