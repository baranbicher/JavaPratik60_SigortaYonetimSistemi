package model.concretes;

import model.abstracts.Insurance;

public class HealthInsurance extends Insurance {
    public HealthInsurance() {
        setPrice(36000);
        setName("Health Insurance");
    }

    @Override
    public double calculate() {
        return getPrice();
    }
}
