package ru.ltz.general;

import java.util.Objects;

public record Triangle (double side1, double side2, double side3) {

    public Triangle {
        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw new IllegalArgumentException("Сторона треугольника не может быть отрицательной");
        }
        if ((side1 + side2) < side3 || (side1 + side3) < side2 || (side2 + side3) < side1) {
            throw new IllegalArgumentException("Сумма 2-х любых сторон треугольника должна быть не меньше третьей стороны");
        }
    }

    public double calculatePerimeter() {
        return this.side1 + this.side2 + this.side3;
    }

    public double calculateArea() {
        double halfPerimeter = calculatePerimeter() / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - this.side1) * (halfPerimeter - this.side2) * (halfPerimeter - this.side3));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.side1, triangle.side1) == 0 && Double.compare(this.side2, triangle.side2) == 0 && Double.compare(this.side3, triangle.side3) == 0) ||

                (Double.compare(this.side1, triangle.side1) == 0 && Double.compare(this.side2, triangle.side3) == 0 && Double.compare(this.side3, triangle.side2) == 0) ||
                (Double.compare(this.side1, triangle.side3) == 0 && Double.compare(this.side2, triangle.side2) == 0 && Double.compare(this.side3, triangle.side1) == 0) ||
                (Double.compare(this.side3, triangle.side1) == 0 && Double.compare(this.side2, triangle.side2) == 0 && Double.compare(this.side1, triangle.side3) == 0) ||
                (Double.compare(this.side1, triangle.side2) == 0 && Double.compare(this.side2, triangle.side1) == 0 && Double.compare(this.side3, triangle.side3) == 0) ||
                (Double.compare(this.side2, triangle.side1) == 0 && Double.compare(this.side1, triangle.side2) == 0 && Double.compare(this.side3, triangle.side3) == 0) ||

                (Double.compare(this.side1, triangle.side2) == 0 && Double.compare(this.side2, triangle.side3) == 0 && Double.compare(this.side3, triangle.side1) == 0) ||
                (Double.compare(this.side1, triangle.side3) == 0 && Double.compare(this.side2, triangle.side1) == 0 && Double.compare(this.side3, triangle.side2) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side1, side2, side3);
    }
}