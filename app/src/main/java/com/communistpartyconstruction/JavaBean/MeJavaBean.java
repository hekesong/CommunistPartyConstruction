package com.communistpartyconstruction.JavaBean;

/**
 * Created by DerryChan on 2017/1/1 0001.
 */

public class MeJavaBean {
    String name,school;
    public MeJavaBean() {

    }

    public MeJavaBean(String name, String school) {
        this.name = name;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
