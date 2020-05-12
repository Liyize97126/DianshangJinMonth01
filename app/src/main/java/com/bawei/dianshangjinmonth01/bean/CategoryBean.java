package com.bawei.dianshangjinmonth01.bean;

import java.util.List;

public class CategoryBean {
    private String clazz;
    private List<ChildsBean> childs;
    public String getClazz() {
        return clazz;
    }
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
    public List<ChildsBean> getChilds() {
        return childs;
    }
    public void setChilds(List<ChildsBean> childs) {
        this.childs = childs;
    }
}
