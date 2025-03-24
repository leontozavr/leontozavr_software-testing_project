package ru.ltz.general;

public record Triangle (double side1, double side2, double side3) {

    public double calculatePerimeter() {
        return this.side1 + this.side2 +this.side3;
    }

    public double calculateArea() {
        double halfPerimeter = calculatePerimeter() / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - this.side1) * (halfPerimeter - this.side2) * (halfPerimeter - this.side3));
    }
}
