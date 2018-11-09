package com.thecarapp.mycarsapp;

public class usercars {
    private String Uid, year, make, model, engine, regNo = "";


public usercars(String Uid,String year,String model,String make,String engine,String regNo){

    this.Uid=Uid;//this is set to be the primary key
    this.year=year;
    this.make=make;
    this.model=model;
    this.engine=engine;
    this.regNo=regNo;
}

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
}
