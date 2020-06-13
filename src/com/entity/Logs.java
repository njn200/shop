package com.entity;

import java.util.Date;

public class Logs {
	
    private Integer id;

    private String broswer;

    private Short loglevel;

    private String ip;

    private Date operatetime;

    private Short operatetype;

    private String userid;

    private Integer roleid;

    private String logcontent;
    
    
    private Admins admins;
     
     
    private Users users;
     
     
     

    public Admins getAdmins() {
		return admins;
	}

	public void setAdmins(Admins admins) {
		this.admins = admins;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBroswer() {
        return broswer;
    }

    public void setBroswer(String broswer) {
        this.broswer = broswer == null ? null : broswer.trim();
    }

    public Short getLoglevel() {
        return loglevel;
    }

    public void setLoglevel(Short loglevel) {
        this.loglevel = loglevel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public Short getOperatetype() {
        return operatetype;
    }

    public void setOperatetype(Short operatetype) {
        this.operatetype = operatetype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getLogcontent() {
        return logcontent;
    }

    public void setLogcontent(String logcontent) {
        this.logcontent = logcontent == null ? null : logcontent.trim();
    }
}