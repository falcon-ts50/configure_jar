package configure_ups;

import configure_ups.model.Unit;
import configure_ups.view.Controller;
import configure_ups.view.GraphController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainAppFX extends Application {

    private Stage primaryStage;
    private Unit unit = new Unit();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(MainAppFX.class.getResource("/first_screen.fxml"));

    Parent root = loader.load();

    primaryStage.setTitle("configure_ups");
    primaryStage.setScene(new Scene(root, 1055, 750));
    primaryStage.show();


    Controller controller = loader.getController();
    controller.setUnit(unit);
    controller.setMain(this);


}

    public void showGraph(){
        try {

            Stage dialogStage = new Stage();
            dialogStage.setTitle("График заряда в зависимости от режима");
            dialogStage.initOwner(primaryStage);

            int lowerBound = -10;
            int upperBound = 50;

            final NumberAxis xAxis = new NumberAxis(lowerBound, upperBound, 10);


            final int gap = 50;

            final int yMaximum = unit.getOutputMaximum() + gap;
            final int yMinimum = unit.getOutputBoostMinimum()<unit.getOutputFloatMinimum()? unit.getOutputBoostMinimum() - gap : unit.getOutputFloatMinimum()-gap ;

            final NumberAxis yAxis = new NumberAxis(yMinimum, yMaximum , 500);
            yAxis.setMinorTickCount(10);
            yAxis.setTickUnit(100);

            final AreaChart<Number, Number> areaChart = new AreaChart<>(xAxis, yAxis);


                areaChart.setTitle("Зависимость напряжения от температуры для режимов");
                areaChart.setLegendSide(Side.LEFT);


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

            Scene scene = new Scene(areaChart, 600,600);
            dialogStage.setScene(scene);

            dialogStage.show();

/*
Код, приведённый ниже плохо работал, за что был безжалостно выпилен в комментарии из этой прогрммы мной 11.02.2021

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppFX.class.getResource("/graph_charge_modes.fxml"));
            Parent page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("График заряда в зависимости от режима");
            dialogStage.initOwner(primaryStage);

            GraphController graphController = loader.getController();
            graphController.setData(unit);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.show();*/




        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
    }

