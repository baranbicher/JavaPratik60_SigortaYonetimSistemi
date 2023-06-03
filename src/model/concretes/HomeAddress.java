package model.concretes;

import model.abstracts.Address;

public class HomeAddress implements Address {

    private String homeAddress;

    public HomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public void setAddress(String addressStr) {
        this.homeAddress = addressStr;
    }

    @Override
    public String getAddress() {
        return homeAddress;
    }


}
