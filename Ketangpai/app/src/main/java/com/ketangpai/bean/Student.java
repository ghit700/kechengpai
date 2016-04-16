package com.ketangpai.bean;

/**
 * Created by Administrator on 2016/4/15.
 */
public class Student extends User {
    private int s_id;

    private int number;


    /**
     * 类型,0为老师,1为学生
     */


    public Student(String account, String password, String school, String name, int type, int number) {
        super(account, password, school, name, type);
        this.number = number;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public int getType() {
        return type;
    }
}
