package by.ustsinovich.maximinclusteranalysis.utils;

import by.ustsinovich.maximinclusteranalysis.model.Cluster;
import by.ustsinovich.maximinclusteranalysis.model.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

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

    public static List<Cluster> clusterizeByMaximinWithIntermediateResults(List<Point> points) {
        List<Cluster> clusters = new ArrayList<>();
        List<Point> remainingPoints = new ArrayList<>(points);

        Point initialPoint = remainingPoints.remove(new Random().nextInt(remainingPoints.size()));
        clusters.add(new Cluster(initialPoint));

        // Отображаем начальное состояние
        displayClusters(clusters, 0);

        int iteration = 1;
        while (!remainingPoints.isEmpty()) {
            Map<Point, Double> pointToNearestClusterDistance = new HashMap<>();
            for (Point point : remainingPoints) {
                double minDistance = Double.MAX_VALUE;
                Cluster nearestCluster = null;
                for (Cluster cluster : clusters) {
                    double distance = PointsUtils.calculateDistance(point, cluster.getCentroid());
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestCluster = cluster;
                    }
                }
                pointToNearestClusterDistance.put(point, minDistance);
                if (nearestCluster != null) {
                    nearestCluster.addPoint(point);
                }
            }

            displayClusters(clusters, iteration);

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

                // Отображаем промежуточное состояние
                iteration++;
            } else {
                break;
            }
        }

        // Отображаем конечное состояние
        displayClusters(clusters, iteration);

        return clusters;
    }

    // Метод для отображения кластеров
    private static void displayClusters(List<Cluster> clusters, int iteration) {
        // Создаем изображение
        BufferedImage image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);

        // Задаем цвет фона
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, Color.BLACK.getRGB());
            }
        }

        // Задаем цвета для кластеров
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.PINK, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.GRAY};

        // Рисуем точки каждого кластера
        for (int i = 0; i < clusters.size(); i++) {
            Cluster cluster = clusters.get(i);
            int rgb = colors[i % colors.length].getRGB();

            for (Point point : cluster.getPoints()) {
                // Теперь каждая точка представляет собой один пиксель
                image.setRGB((int) point.getX(), (int) point.getY(), rgb);
            }
        }

        // Сохраняем изображение
        try {
            ImageIO.write(image, "PNG", new File(String.format("clusters-%d.png", iteration)));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

