package com.ketangpai.bean;

/**
 * Created by Administrator on 2016/4/15.
 */
public class Teacher {
    private int t_id;
    private String account;
    private String password;
    private String school;
    private String name;
    private int type;

    public Teacher(String account, String password, String school, String name, int type) {
        this.account = account;
        this.password = password;
        this.school = school;
        this.name = name;
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getSchool() {
        return school;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
