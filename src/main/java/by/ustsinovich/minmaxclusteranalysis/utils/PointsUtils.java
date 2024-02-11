package by.ustsinovich.minmaxclusteranalysis.utils;

import by.ustsinovich.minmaxclusteranalysis.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class PointsUtils {
    public static double calculateDistance(Point p1, Point p2) {
        return sqrt(pow(p1.getX() - p2.getX(), 2) + pow(p1.getY() - p2.getY(), 2));
    }

    public static List<Point> initializePointsListByRandom(int size, double maxAbscissa, double maxOrdinate) {
        List<Point> points = new ArrayList<>(size);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            double x = random.nextDouble(maxAbscissa);
            double y = random.nextDouble(maxOrdinate);

            Point point = new Point(x, y);

            points.add(point);
        }

        return points;
    }
}
