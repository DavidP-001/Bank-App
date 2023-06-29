package com.example.bankapp.utility;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class JsonInner {
    public String code;
   public String alphaCode;
   public String numericCode;
   public String name;

   public String rate;

   public String date;

   public String inverseRate;

    public JsonInner(String code, String alphaCode, String numericCode, String name, String rate, String date, String inverseRate) {
        this.code = code;
        this.alphaCode = alphaCode;
        this.numericCode = numericCode;
        this.name = name;
        this.rate = rate;
        this.date = date;
        this.inverseRate = inverseRate;
    }

    @Override
    public String toString() {
        return "JsonInner{" +
                "code='" + code + '\'' +
                ", alphaCode='" + alphaCode + '\'' +
                ", numericCode='" + numericCode + '\'' +
                ", name='" + name + '\'' +
                ", rate='" + rate + '\'' +
                ", date='" + date + '\'' +
                ", inverseRate='" + inverseRate + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlphaCode() {
        return alphaCode;
    }

    public void setAlphaCode(String alphaCode) {
        this.alphaCode = alphaCode;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInverseRate() {
        return inverseRate;
    }

    public void setInverseRate(String inverseRate) {
        this.inverseRate = inverseRate;
    }

    public JsonInner() {
    }
}
