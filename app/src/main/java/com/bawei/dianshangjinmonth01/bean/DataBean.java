package com.bawei.dianshangjinmonth01.bean;

import java.util.List;

public class DataBean {
    private int code;
    private String message;
    private List<CategoryBean> category;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<CategoryBean> getCategory() {
        return category;
    }
    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }
}
