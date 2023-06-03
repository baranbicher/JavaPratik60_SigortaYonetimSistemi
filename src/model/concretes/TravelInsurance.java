package model.concretes;

import model.abstracts.Insurance;

public class TravelInsurance extends Insurance {
    public TravelInsurance() {
        setPrice(22000);
        setName("Travel Insurance");
    }

    @Override
    public double calculate() {
        return getPrice();
    }
}
