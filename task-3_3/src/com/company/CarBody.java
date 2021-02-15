package com.company;

public class CarBody implements IProductPart{
    private String bodyShape;
    private String color;

    public CarBody(String bodyShape, String color) {
        this.bodyShape = bodyShape;
        this.color = color;
    }

    public String getBodyShape() {
        return bodyShape;
    }

    public String getColor() {
        return color;
    }

    public void setBodyShape(String bodyShape) {
        this.bodyShape = bodyShape;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
