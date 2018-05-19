package com.project.help.pojo;

//贫困生实体类
public class PoorStudent {
    private String stuNum;
    private String stuName;
    private String stuMajor;
    private double avgMoney;
    private double variance;
    private double rate;

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    public double getAvgMoney() {
        return avgMoney;
    }

    public void setAvgMoney(double avgMoney) {
        this.avgMoney = avgMoney;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
