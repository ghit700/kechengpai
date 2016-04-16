package com.ketangpai.bean;

/**
 * Created by Administrator on 2016/4/15.
 */
public class Student {
    private int s_id;
    private String account;
    private String password;
    private String name;
    private int number;
    private String School;

    public Student(String account, String password, String name, int number, String school, int type) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.number = number;
        School = school;
        this.type = type;
    }

    /**
     * 类型,0为老师,1为学生
     */
    private int type;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public void setSchool(String school) {
        School = school;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }



    public String getSchool() {
        return School;
    }

    public int getType() {
        return type;
    }
}
