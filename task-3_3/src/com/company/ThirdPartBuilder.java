package com.company;

public class ThirdPartBuilder implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        IProductPart engine = new Engine(2.0, 125);
        System.out.println("Engine has been made");
        return engine;
    }
}
