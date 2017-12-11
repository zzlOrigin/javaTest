package com.kaishengit.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Record implements Serializable {
    private Integer id;

    private Date setup;

    private Integer chanceId;

    private String context;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSetup() {
        return setup;
    }

    public void setSetup(Date setup) {
        this.setup = setup;
    }

    public Integer getChanceId() {
        return chanceId;
    }

    public void setChanceId(Integer chanceId) {
        this.chanceId = chanceId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}