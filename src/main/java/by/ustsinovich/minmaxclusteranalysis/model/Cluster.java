package by.ustsinovich.minmaxclusteranalysis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cluster {
    private Point centroid;
    private List<Point> points;

    public Cluster(Point centroid) {
        this.centroid = centroid;
        this.points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void clearPoints() {
        points.clear();
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
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

        Cluster cluster = (Cluster) object;

        return Objects.equals(centroid, cluster.centroid) && Objects.equals(points, cluster.points);
    }

    @Override
    public int hashCode() {
        return 31 * centroid.hashCode() + points.hashCode();
    }

    @Override
    public String toString() {
        return STR."""
                Cluster={
                    Centroid=\{centroid};
                    Points(size=\{points.size()})=\{points};
                }
                """;
    }
}
