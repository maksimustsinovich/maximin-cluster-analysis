package by.ustsinovich.maximinclusteranalysis.controller;

import by.ustsinovich.maximinclusteranalysis.model.Cluster;
import by.ustsinovich.maximinclusteranalysis.model.Point;
import by.ustsinovich.maximinclusteranalysis.utils.ClusteringUtils;
import by.ustsinovich.maximinclusteranalysis.utils.PointsUtils;
import by.ustsinovich.maximinclusteranalysis.view.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

import java.util.List;

public class MainController {
    @FXML
    public Spinner<Integer> spnPointsNum;
    @FXML
    public Button btnClusterize;
    @FXML
    public ScatterChart<Double, Double> scPoints;
    @FXML
    public Spinner<Integer> spnMaxAbscissa;
    @FXML
    public Spinner<Integer> spnMaxOrdinate;

    @FXML
    public void onBtnClusterizeAction(ActionEvent event) {
        MainView view = new MainView(scPoints);

        int pointsNum = spnPointsNum.getValue();

        int maxAbscissa = spnMaxAbscissa.getValue();
        int maxOrdinate = spnMaxOrdinate.getValue();

        List<Point> points = PointsUtils.initializePointsListByRandom(pointsNum, maxAbscissa, maxOrdinate);

//        List<Cluster> clusters = ClusteringUtils.clusterizeByMaximin(points);

        var clusters = ClusteringUtils.clusterizeByMaximinWithIntermediateResults(points);

        view.updateChart(clusters);
    }
}