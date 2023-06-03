package model.concretes;

import model.abstracts.Insurance;

public class CarInsurance extends Insurance {
    public CarInsurance() {
        setPrice(8000);
        setName("Car Insurance");
    }

    @Override
    public double calculate() {
        return getPrice();
    }
}
