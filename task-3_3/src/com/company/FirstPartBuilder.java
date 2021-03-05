package com.company;

public class FirstPartBuilder implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        IProductPart body = new CarBody("Sedan", "black");
        System.out.println("Body has been made");
        return body;
    }
}
