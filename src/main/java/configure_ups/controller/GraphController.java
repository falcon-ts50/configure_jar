package configure_ups.controller;

import configure_ups.model.Unit;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraphController {

    private static int lowerBound = -10;
    private static int upperBound = 50;

    @FXML
    final NumberAxis xAxis = new NumberAxis(lowerBound, upperBound, 10);

    @FXML
    final NumberAxis yAxis = new NumberAxis();

    @FXML
    final AreaChart<Number, Number> areaChart = new AreaChart<>(xAxis, yAxis);

    @FXML
    public void initialize(){
        areaChart.setTitle("Зависимость напряжения от температуры для режимов");
        areaChart.setLegendSide(Side.LEFT);

    }

    public void setData(Unit unit){

        XYChart.Series<Number,Number> seriesFloat = new XYChart.Series<>();

        seriesFloat.setName("Float");

        seriesFloat.getData().add(new XYChart.Data<>(lowerBound, unit.getOutputMaximum()));
        seriesFloat.getData().add(new XYChart.Data<>(unit.getMinTempFloat(), unit.getOutputMaximum()));
        seriesFloat.getData().add(new XYChart.Data<>(unit.getTempFirstMidPointFloat(), unit.getOutputMiddle()));
        seriesFloat.getData().add(new XYChart.Data<>(unit.getTempSecondMidPointFloat(), unit.getOutputMiddle()));
        seriesFloat.getData().add(new XYChart.Data<>(unit.getMaxTempFloat(), unit.getOutputFloatMinimum()));

        XYChart.Series<Number, Number> seriesBoost = new XYChart.Series<>();

        seriesBoost.setName("Boost");

        seriesBoost.getData().add(new XYChart.Data<>(lowerBound, unit.getOutputMaximum()));
        seriesBoost.getData().add(new XYChart.Data<>(unit.getMinTempBoost(), unit.getOutputMaximum()));
        seriesBoost.getData().add(new XYChart.Data<>(unit.getMaxTempBoost(), unit.getOutputBoostMinimum()));

        areaChart.getData().addAll(seriesFloat, seriesBoost);


    }

}
