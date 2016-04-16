package com.ketangpai.bean;

/**
 * Created by Administrator on 2016/4/15.
 */
public class Teacher extends User {
    private int t_id;

    public Teacher(String account, String password, String school, String name, int type) {
        super(account, password, school, name, type);
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
