package by.ustsinovich.maximinclusteranalysis.model;

import static java.util.FormatProcessor.FMT;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (hashCode() != object.hashCode()) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        Point point = (Point) object;

        return Double.compare(x, point.x) == 0 && Double.compare(y, point.y) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(x) + Double.hashCode(y);
    }

    @Override
    public String toString() {
        return FMT."Point(%3.5f\{x}, %3.5f\{y})";
    }
}
