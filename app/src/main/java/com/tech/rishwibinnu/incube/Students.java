package com.tech.rishwibinnu.incube;

public class Students {

    public String name,htno,mobile,branch,year,section;

    public Students()
    {

    }

    public Students(String name, String htno, String mobile, String branch, String year, String section) {
        this.name = name;
        this.htno = htno;
        this.mobile = mobile;
        this.branch = branch;
        this.year = year;
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtno() {
        return htno;
    }

    public void setHtno(String htno) {
        this.htno = htno;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
