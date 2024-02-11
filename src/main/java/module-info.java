module by.ustsinovich.kmeansclusteranalysis {
    requires javafx.controls;
    requires javafx.fxml;


    opens by.ustsinovich.minmaxclusteranalysis to javafx.fxml;
    exports by.ustsinovich.minmaxclusteranalysis;
    exports by.ustsinovich.minmaxclusteranalysis.controller;
    opens by.ustsinovich.minmaxclusteranalysis.controller to javafx.fxml;
}