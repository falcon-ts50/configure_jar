package configure_ups;

import configure_ups.model.Unit;
import configure_ups.controller.Controller;
import configure_ups.model.UnitDataArray;
import configure_ups.model.UnitField;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.LinkedHashMap;

public class MainAppFX extends Application {

    private Stage primaryStage;
    private UnitDataArray unitDataArray = UnitDataArray.getUnitDataArray();
    private LinkedHashMap<String, UnitField> unitFields = unitDataArray.getDataArray();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(MainAppFX.class.getResource("/first_screen.fxml"));

    Parent root = loader.load();

    primaryStage.setTitle("Конфигуратор файлов .ino для Arduino MKR ZERO для ИБП.");
    primaryStage.setScene(new Scene(root, 1080, 750));
    primaryStage.show();


    Controller controller = loader.getController();
    controller.setMain(this);
    controller.setTooltip();

}

    public javafx.stage.Stage getPrimaryStage() {
        return primaryStage;
    }

    public void showGraph(){
        try {

            Stage dialogStage = new Stage();
            dialogStage.setTitle("График заряда в зависимости от режима");
            dialogStage.initOwner(primaryStage);

            final int gapY = 50;
            final int tickX = 10;
            int minTempFloat = (Integer) unitFields.get("minTempFloat").getValue();
            int minTempBoost = (Integer) unitFields.get("minTempBoost").getValue();
            int maxTempBoost = (Integer) unitFields.get("maxTempBoost").getValue();
            int maxTempFloat = (Integer) unitFields.get("maxTempFloat").getValue();
            int outputMaximum = (Integer) unitFields.get("outputMaximum").getValue();
            int outputMiddle = (Integer) unitFields.get("outputMiddle").getValue();
            int outputBoostMinimum = (Integer) unitFields.get("outputBoostMinimum").getValue();
            int outputFloatMinimum = (Integer) unitFields.get("outputFloatMinimum").getValue();

            int lowerBound = minTempFloat < minTempBoost ? minTempFloat - tickX: minTempBoost - tickX;
            int upperBound = maxTempFloat > maxTempBoost? maxTempFloat + tickX : maxTempBoost + tickX;

            final NumberAxis xAxis = new NumberAxis(lowerBound, upperBound, tickX);

            int yMaximum = outputMaximum + gapY;
            int yMinimum = outputBoostMinimum < outputFloatMinimum? outputBoostMinimum - gapY : outputFloatMinimum-gapY ;
            yMinimum = (int) (Math.round((double) yMinimum/100)*100);
            final NumberAxis yAxis = new NumberAxis(yMinimum, yMaximum , 500);
            xAxis.setLabel("Градусы Цельсия");
            yAxis.setMinorTickCount(5);
            yAxis.setTickUnit(50);
            yAxis.setMinorTickVisible(true);
            yAxis.setLabel("мВ");

            final AreaChart<Number, Number> areaChart = new AreaChart<>(xAxis, yAxis);


                areaChart.setTitle("Зависимость напряжения от температуры для режимов");
                areaChart.setLegendSide(Side.BOTTOM);


                XYChart.Series<Number,Number> seriesFloat = new XYChart.Series<>();

                seriesFloat.setName("Float");
                int tempFirstMidPointFloat = (Integer) unitFields.get("tempFirstMidPointFloat").getValue();
                int tempSecondMidPointFloat = (Integer) unitFields.get("tempSecondMidPointFloat").getValue();


                seriesFloat.getData().add(new XYChart.Data<>(lowerBound, outputMaximum));
                seriesFloat.getData().add(new XYChart.Data<>(minTempFloat, outputMaximum));
                seriesFloat.getData().add(new XYChart.Data<>(tempFirstMidPointFloat, outputMiddle));
                seriesFloat.getData().add(new XYChart.Data<>(tempSecondMidPointFloat, outputMiddle));
                seriesFloat.getData().add(new XYChart.Data<>(maxTempFloat, outputFloatMinimum));

                XYChart.Series<Number, Number> seriesBoost = new XYChart.Series<>();

                seriesBoost.setName("Boost");

                seriesBoost.getData().add(new XYChart.Data<>(lowerBound, outputMaximum));
                seriesBoost.getData().add(new XYChart.Data<>(minTempBoost, outputMaximum));
                seriesBoost.getData().add(new XYChart.Data<>(maxTempBoost, outputBoostMinimum));

                areaChart.getData().addAll(seriesFloat, seriesBoost);

            Scene scene = new Scene(areaChart, 600,600);
            dialogStage.setScene(scene);

            dialogStage.show();

/*
Код, приведённый ниже плохо работал, за что был безжалостно выпилен в комментарии из этой прогрммы мной 11.02.2021
также не нужен класс GraphController. Я пока не смог сделать работу в этом методе через XML, возможно позже...

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

