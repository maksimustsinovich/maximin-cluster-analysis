module by.ustsinovich.kmeansclusteranalysis {
    requires javafx.controls;
    requires javafx.fxml;


    opens by.ustsinovich.maximinclusteranalysis to javafx.fxml;
    exports by.ustsinovich.maximinclusteranalysis;
    exports by.ustsinovich.maximinclusteranalysis.controller;
    opens by.ustsinovich.maximinclusteranalysis.controller to javafx.fxml;
}