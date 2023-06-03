package model.concretes;

import model.abstracts.Insurance;

public class ResidenceInsurance extends Insurance {
    public ResidenceInsurance() {
        setPrice(60000);
        setName("Residence Insurance");
    }

    @Override
    public double calculate() {
        return getPrice();
    }
}
