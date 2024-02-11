package by.ustsinovich.maximinclusteranalysis.utils;

import by.ustsinovich.maximinclusteranalysis.model.Cluster;
import by.ustsinovich.maximinclusteranalysis.model.Point;

import java.util.*;

public class ClusteringUtils {
    public static List<Cluster> clusterizeByMaximin(List<Point> points) {
        List<Cluster> clusters = new ArrayList<>();
        List<Point> remainingPoints = new ArrayList<>(points);

        Point initialPoint = remainingPoints.remove(new Random().nextInt(remainingPoints.size()));
        clusters.add(new Cluster(initialPoint));

        while (!remainingPoints.isEmpty()) {
            Map<Point, Double> pointToNearestClusterDistance = new HashMap<>();
            for (Point point : remainingPoints) {
                double minDistance = Double.MAX_VALUE;
                for (Cluster cluster : clusters) {
                    double distance = PointsUtils.calculateDistance(point, cluster.getCentroid());
                    minDistance = Math.min(minDistance, distance);
                }
                pointToNearestClusterDistance.put(point, minDistance);
            }

            Point farthestPoint = null;
            double maxDistance = Double.MIN_VALUE;
            for (Map.Entry<Point, Double> entry : pointToNearestClusterDistance.entrySet()) {
                if (entry.getValue() > maxDistance) {
                    maxDistance = entry.getValue();
                    farthestPoint = entry.getKey();
                }
            }

            double averageInterClusterDistance = calculateAverageInterClusterDistance(clusters);
            if (maxDistance > averageInterClusterDistance / 2) {
                remainingPoints.remove(farthestPoint);
                clusters.add(new Cluster(farthestPoint));
            } else {
                break;
            }
        }

        for (Point point : remainingPoints) {
            Cluster nearestCluster = null;
            double minDistance = Double.MAX_VALUE;
            for (Cluster cluster : clusters) {
                double distance = PointsUtils.calculateDistance(point, cluster.getCentroid());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCluster = cluster;
                }
            }
            if (nearestCluster != null) {
                nearestCluster.addPoint(point);
            }
        }

        return clusters;
    }

    private static double calculateAverageInterClusterDistance(List<Cluster> clusters) {
        double totalDistance = 0;
        int count = 0;
        for (int i = 0; i < clusters.size(); i++) {
            for (int j = i + 1; j < clusters.size(); j++) {
                totalDistance += PointsUtils.calculateDistance(
                        clusters.get(i).getCentroid(),
                        clusters.get(j).getCentroid()
                );
                count++;
            }
        }
        return count == 0 ? 0 : totalDistance / count;
    }
}

