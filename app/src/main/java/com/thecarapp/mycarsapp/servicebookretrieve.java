package com.thecarapp.mycarsapp;

public class servicebookretrieve {

    private String Uid ,name,email,tel,vehice,reg,date,time,branch,info;

    public servicebookretrieve(String uid, String name, String email, String tel, String vehice, String reg, String date, String time, String branch, String info) {
        Uid = uid;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.vehice = vehice;
        this.reg = reg;
        this.date = date;
        this.time = time;
        this.branch = branch;
        this.info = info;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getVehice() {
        return vehice;
    }

    public void setVehice(String vehice) {
        this.vehice = vehice;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
