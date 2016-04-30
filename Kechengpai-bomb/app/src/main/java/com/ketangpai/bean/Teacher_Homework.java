package com.ketangpai.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by nan on 2016/4/24.
 */
public class Teacher_Homework extends BmobObject {
    private Integer c_id;
    private String title;
    private String content;
    private Long p_time;
    private Long e_time;
    private Integer check_count;
    private Integer no_check_count;
    private List<BmobFile> files;

    public List<BmobFile> getFiles() {
        return files;
    }

    public void setFiles(List<BmobFile> files) {
        this.files = files;
    }

    public Integer getNo_header_count() {
        return no_header_count;
    }

    public void setNo_header_count(Integer no_header_count) {
        this.no_header_count = no_header_count;
    }

    public Integer getNo_check_count() {
        return no_check_count;
    }

    public void setNo_check_count(Integer no_check_count) {
        this.no_check_count = no_check_count;
    }

    public Integer getCheck_count() {
        return check_count;
    }

    public void setCheck_count(Integer check_count) {
        this.check_count = check_count;
    }

    public Long getE_time() {
        return e_time;
    }

    public void setE_time(Long e_time) {
        this.e_time = e_time;
    }

    public Long getP_time() {
        return p_time;
    }

    public void setP_time(Long p_time) {
        this.p_time = p_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    private Integer no_header_count;

}
