package model.concretes;

import model.abstracts.Address;

public class BusinessAddress implements Address {

    String businessAddress;

    public BusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    @Override
    public void setAddress(String addressStr) {
        this.businessAddress = addressStr;
    }

    @Override
    public String getAddress() {
        return businessAddress;
    }
}
