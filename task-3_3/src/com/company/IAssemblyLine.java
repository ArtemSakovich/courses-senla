package com.company;

public interface IAssemblyLine {
    IProduct assembleProduct(IProductPart first, IProductPart second, IProductPart third);
}
