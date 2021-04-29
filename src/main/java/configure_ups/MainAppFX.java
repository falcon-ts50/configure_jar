package configure_ups;

import configure_ups.model.Unit;
import configure_ups.view.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class MainAppFX extends Application {

    private Stage primaryStage;
    private Unit unit = Unit.getUnit();

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
    controller.setUnit(unit);
    controller.setMain(this);


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
            int lowerBound = unit.getMinTempFloat() < unit.getMinTempBoost()? unit.getMinTempFloat()-tickX: unit.getMinTempBoost() - tickX;
            int upperBound = unit.getMaxTempFloat() > unit.getMaxTempBoost()? unit.getMaxTempFloat() + tickX : unit.getMaxTempBoost() + tickX;

            final NumberAxis xAxis = new NumberAxis(lowerBound, upperBound, tickX);

            int yMaximum = unit.getOutputMaximum() + gapY;
            int yMinimum = unit.getOutputBoostMinimum()<unit.getOutputFloatMinimum()? unit.getOutputBoostMinimum() - gapY : unit.getOutputFloatMinimum()-gapY ;
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

