package com.example.carshowroom;

public class car_type {
    String CompanyName;
    String carName;
    int model;
    String maker;
    int carLogo;

    public car_type(String companyName, String carName, int model, String maker, int carLogo) {
        CompanyName = companyName;
        this.carName = carName;
        this.model = model;
        this.maker = maker;
        this.carLogo = carLogo;
    }

    public car_type() {
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getCarLogo() {
        return carLogo;
    }

    public void setCarLogo(int carLogo) {
        this.carLogo = carLogo;
    }

    @Override
    public String toString() {
//        return CompanyName; // Display car name
               return CompanyName + " " + carName + " (" + model + ")"; // More descriptive output
    }
}
