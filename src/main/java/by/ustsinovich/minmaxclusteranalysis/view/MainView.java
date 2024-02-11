package by.ustsinovich.minmaxclusteranalysis.view;

import by.ustsinovich.minmaxclusteranalysis.model.Cluster;
import by.ustsinovich.minmaxclusteranalysis.model.Point;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public class MainView {
    private final ScatterChart<Double, Double> chart;

    public MainView(ScatterChart<Double, Double> chart) {
        this.chart = chart;
    }

    public void updateChart(List<Cluster> clusters) {
        chart.getData().clear();

        int i = 1;

        for (Cluster cluster : clusters) {
            XYChart.Series<Double, Double> series = new XYChart.Series<>();
            series.setName(STR."Cluster \{i}");

            for (Point point : cluster.getPoints()) {
                double x = point.getX();
                double y = point.getY();

                XYChart.Data<Double, Double> data = new XYChart.Data<>(x, y);

                series.getData().add(data);
            }

            chart.getData().add(series);
            i++;
        }
    }
}

