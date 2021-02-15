package com.company;

public class AssembleLine implements IAssemblyLine {
    @Override
    public IProduct assembleProduct(IProductPart first, IProductPart second, IProductPart third) {
        Car car = new Car();
        car.installParts(first, second, third);
        return car;
    }
}
