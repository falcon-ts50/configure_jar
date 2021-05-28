package configure_ups.controller;

import configure_ups.MainAppFX;
import configure_ups.model.Unit;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Region;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    Unit unit;

    private boolean isThereIsError = false;

//    Инициализируем все переменные - поля, которые будем использовать в нашем проекте
    @FXML
    private TextField mVAtZeroDeg;

    @FXML
    private TextField changingMVPerOneDeg;

    @FXML
    private TextField minTempFloat;

    @FXML
    private TextField tempFirstMidPointFloat;

    @FXML
    private TextField tempSecondMidPointFloat;

    @FXML
    private TextField maxTempFloat;

    @FXML
    private TextField minTempBoost;

    @FXML
    private TextField maxTempBoost;

    @FXML
    private TextField outputMaximum;

    @FXML
    private TextField outputMiddle;

    @FXML
    private TextField outputFloatMinimum;

    @FXML
    private TextField outputBoostMinimum;

    @FXML
    private TextField coefficientOfCalibration;

    @FXML
    private TextField maxVoltShunt;

    @FXML
    private TextField maxCurrentShunt;

    @FXML
    private TextField capacitanceOfBattery;

    @FXML
    private TextField numberBattery;

    @FXML
    private TextField coeffAnalogueAmplifier;

    @FXML
    private TextField chargingCurrent;

    @FXML
    private TextField maxChargCurrent;

    @FXML
    private TextField upThresholdCurrent;

    @FXML
    private TextField thresholdForBoost;

    @FXML
    private TextField thresholdBoostEnding;

    @FXML
    private TextField timeInBoost;

    @FXML
    private TextField delayBoost;

    @FXML
    private Button showGraph;

    @FXML
    private Button previousField;

    @FXML
    private Button clear;

    @FXML
    private Button write;

//    Ниже описаны пределы для системы алармов - защиты от некорректного ввода данных

//    Пределы данных по датчику температуры
//    пределеы значений мВ при нуле
    private final int lowBoundMVAtZeroDeg = 400;
    private final int upperBoundMVAtZeroDeg = 600;

//    пределы изменений мВ за один градус
    private final int lowBoundMVPerOneDeg = 9;
    private final int upperBoundMVPerOneDeg = 11;

//    Пределы данных заряда по температурно-вольтовой характеристике аккумулятора
//    Пределы значений температуры для режима Float
//    пределы минимальной температуры Float
    private final int lowTempFirstPointFloat = -15;
    private final int upTempFristPointFloat = 30;

//    пределы температуры первой средней точки Float
    private final int lowTempFirstMidPoint = 15;
    private final int upTempFirstMidPoint = 25;

//    пределы температуры второй средней точки Float
    private final int lowTempSecondMidPoint = 20;
    private final int upTempSecondMidPoint = 35;

//    пределы максимальной температуры режима Float
    private final int lowTempLastPointFloat = 40;
    private final int upTempLastPointFloat = 55;

//    Пределы значений режима Boost
//    пределы минимальной температуры режима Boost
    private final int lowFirstTempBoost = 15;
    private final int upFristTempBoost = 35;

//    пределы максимальной температуры режима Boost
    private final int lowLastTempBoost = 35;
    private final int upLastTempBoost = 50;

//    Пределы зарядного напряжения для режимов
//    пределы максимального значения для обоих режимов
    private final int lowOutputMaximum = 2350;
    private final int upperOutputMaximum = 2450;

//    пределы напряжения для средней точки режима Float
    private final int lowMidPointOutFloat = 2250;
    private final int upMidPointOutFloat = 2330;

//    пределы напряжения для минимального напряжения режима Float
    private final int lowMinPointOutFloat = 2130;
    private final int upMinPointOutFloat = 2250;

//    пределы напряжения для минимального напряжения режима Boost
    private final int lowMinPointOutBoost = 2215;
    private final int upperMinPointOutBoost = 2400;

//    пределы для коэффициента преобразования для базовой шкалы
    private final double lowBoundCoeffCalib = 1.000;
    private final double upBoundCoeffCalib = 1.131;

//    Раздел характеристик шунта
//    пределы для максимального напряжения шунта
    private final int lowBoundMaxVoltShunt = 50;
    private final int upBoundMaxVoltShunt = 100;

//    пределы для максимального тока шунта
    private final int lowBoundMaxCurrentShunt = 100;
    private final int upBoundMaxCurrentShunt = 500;

//    пределы по ёмкости батарей
    private final int lowBoundCapacitanceBatt = 10;
    private final int upBoundCapacitanceBatt = 300;

//    пределы по количеству батарей
    private final int lowBoundNumberBatt = 1;
    private final int upBoundNumberBatt = 10;

//    пределы по коэффициенту передачи аналогового усилителя
    private final int lowBoundCoefAnalogueAmplif = 20;
    private final int upBoundCoefAnalogueAmplif = 400;

//    пределы по зарядному току, множитель относительно номинального
    private final double lowBoundChargingCurrent = 1.00;
    private final double upBoundChargingCurrent = 3.50;

//    пределы по максимальному току заряда
    private final double lowBoundMaxChCurrent = 2.40;
    private final double upBoundMaxChCurrent = 5.01;

//    пределы по верхней границе заряда

    private final double lowBoundUpThresholdCurrent = 1.70;
    private final double upBoundUpThresholdCurrent = 4.20;

//    пределы по порогу для применения ускоренного заряда (включение режима Boost)
    private final double lowBoundThresholdForBoost = 0.60;
    private final double upBoundThresholdForBoost = 1.00;

//    пределы по порогу для окончания ускоренного заряда (выключение режима Boost)
    private final double lowBoundThresholdBoostEnding = 0.1;
    private final double upBoundThresholdBoostEnding = 0.6;

//    пределы по времени работы в режиме Boost в минутах
    private final int lowBoundTimeInBoost = 0;
    private final int upBoundTimeInBoost = 480;

//    пределы по времени задержки между повторными включениями режима Boost в минутах
    private final int lowBoundDelayBoost = 0;
    private final int upBoundDelayBoost = 60;

    private MainAppFX main;

    private Map<TextField, Number[]> textFields = new LinkedHashMap<TextField, Number[]>();

    @FXML
    void initialize(){
        LinkedList<Number> numbersFields = new LinkedList<>();
        Arrays.stream(Controller.class.getDeclaredFields()).filter(x -> {
            try {
                if (x.get(this) != null) {
//                    logger.debug("Value " + x.get(this) + " Class " + x.get(this).getClass().getSimpleName());
                    return x.get(this) instanceof Number;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return false;
        }).forEach(s -> {
            try {
                numbersFields.add((Number) s.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        Arrays.stream(Controller.class.getDeclaredFields()).filter(x -> {
            try {
                if (x.get(this) != null) {
                    return x.get(this).getClass().equals(TextField.class);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return false;
        }).forEach(s ->
                {
                    try {
                        textFields.put((TextField) s.get(this), new Number[]{numbersFields.pollFirst(), numbersFields.pollFirst()});
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        logger.debug("---------------------------------------------------");
        logger.debug("Array of TextFields and boundary value");
        textFields.entrySet().stream().forEach(x -> logger.debug("TextField name-" + x.getKey().getId() + " value Low: " + x.getValue()[0] + " value High: " + x.getValue()[1]));

//Ниже идёт создание подсказок с текстовым наполнением. Краткие пояснения перед каждым для быстрой навигации по коду

// Харакетристика датчика температуры
        mVAtZeroDeg.setTooltip(new Tooltip(String.format("введите значение от %d до %d мВ, см характеристика датчика", lowBoundMVAtZeroDeg, upperBoundMVAtZeroDeg)));

        changingMVPerOneDeg.setTooltip(new Tooltip(String.format("введите значение от %d до %d мВ за один градус Цельсия", lowBoundMVPerOneDeg, upperBoundMVPerOneDeg)));
// Температурно-вольтовая характеристика
        //режим Флоат
        minTempFloat.setTooltip(new Tooltip(String.format("введите значение от %d до %d град Цельсия", lowTempFirstPointFloat, upTempFristPointFloat)));

        tempFirstMidPointFloat.setTooltip(new Tooltip(String.format("введите значение от %d до %d град Цельсия", lowTempFirstMidPoint, upTempFirstMidPoint)));

        tempSecondMidPointFloat.setTooltip(new Tooltip(String.format("введите значение от %d до %d град Цельсия", lowTempSecondMidPoint, upTempSecondMidPoint)));

        maxTempFloat.setTooltip(new Tooltip(String.format("введите значение от %d до %d град Цельсия", lowTempLastPointFloat, upTempLastPointFloat)));
        //режим Буст
        minTempBoost.setTooltip(new Tooltip(String.format("введите значение от %d до %d град Цельсия", lowFirstTempBoost, upFristTempBoost)));

        maxTempBoost.setTooltip(new Tooltip(String.format("введите значение от %d до %d град Цельсия", lowLastTempBoost, upLastTempBoost)));
        //выходное напряжение
        outputMaximum.setTooltip(new Tooltip(String.format("введите значение от %d до %d мВ на элемент", lowOutputMaximum, upperOutputMaximum)));

        outputMiddle.setTooltip(new Tooltip(String.format("введите значение от %d до %d мВ на элемент", lowMidPointOutFloat, upMidPointOutFloat)));

        outputFloatMinimum.setTooltip(new Tooltip(String.format("введите значение от %d до %d мВ на элемент", lowMinPointOutFloat, upMinPointOutFloat)));

        outputBoostMinimum.setTooltip(new Tooltip(String.format("введите значение от %d до %d мВ на элемент", lowMinPointOutBoost, upperMinPointOutBoost)));
        //коэффициент преобразования для базовой шкалы
        coefficientOfCalibration.setTooltip(new Tooltip(String.format(Locale.CANADA,"введите значение от %.3f до %.3f", lowBoundCoeffCalib, upBoundCoeffCalib)));
//Характеристика шунта
        maxVoltShunt.setTooltip(new Tooltip(String.format("введите значени от %d до %d мВ", lowBoundMaxVoltShunt, upBoundMaxVoltShunt)));

        maxCurrentShunt.setTooltip(new Tooltip(String.format("введите значение от %d до %d А", lowBoundMaxCurrentShunt, upBoundMaxCurrentShunt)));
//Ёмкость батарей
        capacitanceOfBattery.setTooltip(new Tooltip(String.format("введите значение от %d до %d", lowBoundCapacitanceBatt, upBoundCapacitanceBatt)));
//Количество батарей
        numberBattery.setTooltip(new Tooltip(String.format("введите значение от %d до %d", lowBoundNumberBatt, upBoundNumberBatt)));
//Коэффициент передачи аналогового усилителя
        coeffAnalogueAmplifier.setTooltip(new Tooltip(String.format("введите значние от %d до %d", lowBoundCoefAnalogueAmplif, upBoundCoefAnalogueAmplif)));
//Ток заряда
        chargingCurrent.setTooltip(new Tooltip(String.format(Locale.CANADA,"введите значние от %.2f до %.2f", lowBoundChargingCurrent, upBoundChargingCurrent)));
//Максимальный ток заряда
        maxChargCurrent.setTooltip(new Tooltip(String.format(Locale.CANADA,"введите значние от %.2f до %.2f", lowBoundMaxChCurrent, upBoundMaxChCurrent)));

//        Верхняя граница предельного тока заряда
        upThresholdCurrent.setTooltip(new Tooltip(String.format(Locale.CANADA, "введите значние от %.2f до %.2f", lowBoundUpThresholdCurrent, upBoundUpThresholdCurrent)));

//Порог применения ускоренного заряда
        thresholdForBoost.setTooltip(new Tooltip(String.format(Locale.CANADA,"введите значние от %.2f до %.2f", lowBoundThresholdForBoost, upBoundThresholdForBoost)));
//Порог окончания ускоренного заряда
        thresholdBoostEnding.setTooltip(new Tooltip(String.format(Locale.CANADA,"введите значние от %.2f до %.2f", lowBoundThresholdBoostEnding, upBoundThresholdBoostEnding)));
//Максимальное время работы в режиме Boost
        timeInBoost.setTooltip(new Tooltip(String.format("введите значние от %d до %d мин", lowBoundTimeInBoost, upBoundTimeInBoost)));
//Задержка повторного перехода в режим Буст
        delayBoost.setTooltip(new Tooltip(String.format("введите значние от %d до %d мин", lowBoundDelayBoost, upBoundDelayBoost)));
//Кнопка возврата предыдущих значений
        previousField.setTooltip(new Tooltip("Нажав на кнопку, Вы вернёте первоначальные значения"));
//Кнопка полной очистки всех полей
        clear.setTooltip(new Tooltip("Нажав на кнопку, Вы полностью очистите поля ввода значений"));
//Кнопка создания скетча для загрузки
        write.setTooltip(new Tooltip("Нажав на кнопку, Вы создадите скетч, который потом можно загрузить в Arduino"));

    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setMain(MainAppFX main){
        this.main=main;
    }

    public void handleDelete(){

//        Нажатие на кнопку Удалить

        unit.setmVAtZeroDeg(0);
        unit.setChangingMVPerOneDeg(0);
        unit.setMinTempFloat(0);
        unit.setTempFirstMidPointFloat(0);
        unit.setTempSecondMidPointFloat(0);
        unit.setMaxTempFloat(0);
        unit.setMaxTempBoost(0);
        unit.setMinTempBoost(0);
        unit.setOutputMaximum(0);
        unit.setOutputMiddle(0);
        unit.setOutputFloatMinimum(0);
        unit.setOutputBoostMinimum(0);
        unit.setCoefficientOfCalibration(0.0);
        unit.setMaxVoltShunt(0);
        unit.setMaxCurrentShunt(0);
        unit.setCapacitanceOfBattery(0);
        unit.setNumberBattery(0);
        unit.setCoeffAnalogueAmplifier(0);
        unit.setChargingCurrent(0.0);
        unit.setMaxChargCurrent(0.0);
        unit.setUpThresholdCurrent(0.0);
        unit.setThresholdForBoost(0.0);
        unit.setThresholdBoostEnding(0.0);
        unit.setTimeInBoost(0);
        unit.setDelayBoost(0);


        mVAtZeroDeg.setText("");
        changingMVPerOneDeg.setText("");
        minTempFloat.setText("");
        tempFirstMidPointFloat.setText("");
        tempSecondMidPointFloat.setText("");
        maxTempFloat.setText("");
        minTempBoost.setText("");
        maxTempBoost.setText("");
        outputMaximum.setText("");
        outputMiddle.setText("");
        outputFloatMinimum.setText("");
        outputBoostMinimum.setText("");
        coefficientOfCalibration.setText("");
        maxVoltShunt.setText("");
        maxCurrentShunt.setText("");
        capacitanceOfBattery.setText("");
        numberBattery.setText("");
        coeffAnalogueAmplifier.setText("");
        chargingCurrent.setText("");
        maxChargCurrent.setText("");
        upThresholdCurrent.setText("");
        thresholdForBoost.setText("");
        thresholdBoostEnding.setText("");
        timeInBoost.setText("");
        delayBoost.setText("");

    }

    public void setBasicParams(){
        unit.setBasicParams();


        mVAtZeroDeg.setText(String.valueOf(unit.getmVAtZeroDeg()));
        changingMVPerOneDeg.setText(String.valueOf(unit.getChangingMVPerOneDeg()));
        minTempFloat.setText(String.valueOf(unit.getMinTempFloat()));
        tempFirstMidPointFloat.setText(String.valueOf(unit.getTempFirstMidPointFloat()));
        tempSecondMidPointFloat.setText(String.valueOf(unit.getTempSecondMidPointFloat()));
        maxTempFloat.setText(String.valueOf(unit.getMaxTempFloat()));
        minTempBoost.setText(String.valueOf(unit.getMaxTempBoost()));
        maxTempBoost.setText(String.valueOf(unit.getMinTempBoost()));
        outputMaximum.setText(String.valueOf(unit.getOutputMaximum()));
        outputMiddle.setText(String.valueOf(unit.getOutputMiddle()));
        outputFloatMinimum.setText(String.valueOf(unit.getOutputFloatMinimum()));
        outputBoostMinimum.setText(String.valueOf(unit.getOutputBoostMinimum()));
        coefficientOfCalibration.setText(String.valueOf(unit.getCoefficientOfCalibration()));
        maxVoltShunt.setText(String.valueOf(unit.getMaxVoltShunt()));
        maxCurrentShunt.setText(String.valueOf(unit.getMaxCurrentShunt()));
        capacitanceOfBattery.setText(String.valueOf(unit.getCapacitanceOfBattery()));
        numberBattery.setText(String.valueOf(unit.getNumberBattery()));
        coeffAnalogueAmplifier.setText(String.valueOf(unit.getCoeffAnalogueAmplifier()));
        chargingCurrent.setText(String.valueOf(unit.getChargingCurrent()));
        maxChargCurrent.setText(String.valueOf(unit.getMaxChargCurrent()));
        upThresholdCurrent.setText(String.valueOf(unit.getUpThresholdCurrent()));
        thresholdForBoost.setText(String.valueOf(unit.getThresholdForBoost()));
        thresholdBoostEnding.setText(String.valueOf(unit.getThresholdBoostEnding()));
        timeInBoost.setText(String.valueOf(unit.getTimeInBoost()));
        delayBoost.setText(String.valueOf(unit.getDelayBoost()));

    }
    public void setWriteParams(){

        if(!writeParamToUnit()){
            if(!handleWrongData()) {
                makeFileIno();
                showInformationContent("Файл создан", "Файл успешно создан", "Вы обнаружите файл .ino в той же папке, что и данная программа");
            }
        }
    }

    public boolean writeParamToUnit(){
        try {
            unit.setmVAtZeroDeg(Integer.parseInt(mVAtZeroDeg.getText()));
            unit.setChangingMVPerOneDeg(Integer.parseInt(changingMVPerOneDeg.getText()));
            unit.setMinTempFloat(Integer.parseInt(minTempFloat.getText()));
            unit.setTempFirstMidPointFloat(Integer.parseInt(tempFirstMidPointFloat.getText()));
            unit.setTempSecondMidPointFloat(Integer.parseInt(tempSecondMidPointFloat.getText()));
            unit.setMaxTempFloat(Integer.parseInt(maxTempFloat.getText()));
            unit.setMinTempBoost(Integer.parseInt(minTempBoost.getText()));
            unit.setMaxTempBoost(Integer.parseInt(maxTempBoost.getText()));
            unit.setOutputMaximum(Integer.parseInt(outputMaximum.getText()));
            unit.setOutputMiddle(Integer.parseInt(outputMiddle.getText()));
            unit.setOutputFloatMinimum(Integer.parseInt(outputFloatMinimum.getText()));
            unit.setOutputBoostMinimum(Integer.parseInt(outputBoostMinimum.getText()));
            unit.setCoefficientOfCalibration(Double.parseDouble(coefficientOfCalibration.getText()));
            unit.setMaxVoltShunt(Integer.parseInt(maxVoltShunt.getText()));
            unit.setMaxCurrentShunt(Integer.parseInt(maxCurrentShunt.getText()));
            unit.setCapacitanceOfBattery(Integer.parseInt(capacitanceOfBattery.getText()));
            unit.setNumberBattery(Integer.parseInt(numberBattery.getText()));
            unit.setCoeffAnalogueAmplifier(Integer.parseInt(coeffAnalogueAmplifier.getText()));
            unit.setChargingCurrent(Double.parseDouble(chargingCurrent.getText()));
            unit.setMaxChargCurrent(Double.parseDouble(maxChargCurrent.getText()));
            unit.setUpThresholdCurrent(Double.parseDouble(upThresholdCurrent.getText()));
            unit.setThresholdForBoost(Double.parseDouble(thresholdForBoost.getText()));
            unit.setThresholdBoostEnding(Double.parseDouble(thresholdBoostEnding.getText()));
            unit.setTimeInBoost(Integer.parseInt(timeInBoost.getText()));
            unit.setDelayBoost(Integer.parseInt(delayBoost.getText()));
            return false;
        } catch (NumberFormatException e) {
            showAlert("Формат чисел", "Проверьте форматы чисел", "Где-то введён текст, вместо числа, либо запятая вместо точки в дробных числах");
            return true;
        }
    }

    private void makeFileIno(){
        String[] strings = new String[29];
        try {

            PrintStream filePrintStream = new PrintStream("configure_UPS.ino");


            strings[0] = "//создаём макроопределения для используемых пинов\n" +
                    "#define OUTPUT_SIGNAL     A0\n" +
                    "#define INPUT_TEMP        A2\n" +
                    "#define INPUT_SHUNT       A4\n" +
                    "#define INPUT_8SHUNT      A5\n" +
                    "#define INPUT_SUPPORT     A6";
            strings[1] = "//Характеристика датчика температуры ТМР-36\n" +
                    "int millivoltAtZeroDegrees = " + unit.getmVAtZeroDeg() + ";";
            strings[2] = "int changingMillivoltPerOneDegrees = " + unit.getChangingMVPerOneDeg() + ";";
            strings[3] = "//точка калибровки. Для датчика ТМР-36 задаём как минус 40 Цельсиев\n" +
                    "int tempCalibrationDeg = -40;\n" +
                    "\n" +
                    "//Характеристика входов/выходов MKR Zero\n" +
                    "\n" +
                    "//точность входного сигнала, мВ, при 10 битном АЦП при analogReference(AR_INTERNAL1V65);\n" +
                    "// 1650 мВ/ 1024 = 1,611\n" +
                    "double accuracyInput = 1.611;\n" +
                    "//точность выходного сигнала, мВ, при 10 битном ЦАП\n" +
                    "// 3300mV/1024 = 3.223 mV,\n" +
                    "double accuracyOutput = 3.223;";
            strings[4] = "//Температурно-вольтовая характеристика аккумулятора Тубор\n" +
                    "\n" +
                    "//Режим Float\n" +
                    "// задайте минимальную температуру в градусах Цельсия, ниже которой напряжение заряда не изменяется\n" +
                    "int minTempFloatDeg = " + unit.getMinTempFloat() + ";";
            strings[5] = "//задайте первую точку в градусах Цельсия, при которой не изменяется напряжение\n" +
                    "int tempFirstMidPointFloat = " + unit.getTempFirstMidPointFloat() + ";";
            strings[6] = "//Задайте вторую точку при которой температура не изменяется\n" +
                    "int tempSecondMidFloatDeg = " + unit.getTempSecondMidPointFloat() + ";";
            strings[7] = "//Задайте максимальную температуру в градусах Цельсия, после которой напряжение заряда не изменяется\n" +
                    "int maxTempFloatDeg = " + unit.getMaxTempFloat() + ";";
            strings[8] = "\n" +
                    "//Режим Boost\n" +
                    "// задайте минимальную температуру в градусах Цельсия, ниже которой напряжение заряда не изменяется\n" +
                    "int minTempBoostDeg = " + unit.getMinTempBoost() + ";";
            strings[9] = "// задайте максимальную температуру, после которой происходит переход к графику Float\n" +
                    "int maxTempBoostDeg = " + unit.getMaxTempBoost() + ";";
            strings[10] = "\n" +
                    "//задайте максимальное значение выходного напряжения в милливольтах для Boost и Float\n" +
                    "int outputMaximum = " + unit.getOutputMaximum() + ";";
            strings[11] = "// задайте уровень напряжения средних температурных точек в милливольтах для режима Float\n" +
                    "int outputMiddleFloat = " + unit.getOutputMiddle() + ";";
            strings[12] = "//задайте значение минимального выходного напряжения в милливольтах для режима Float\n" +
                    "int  outputFloatMinimum = " + unit.getOutputFloatMinimum() + ";";
            strings[13] = "//задайте значение минимального выходного напряжения в милливольтах для режима Boost\n" +
                    "int outputBoostMinimum = " + unit.getOutputBoostMinimum() + ";";
            strings[14] = "\n" +
                    "//задайте коэффициент преобразования для базовой шкалы \"мВ на элемент\" для получения калиброванного напряжения\n" +
                    "\n" +
                    "double coefficientOfCalibration = " + unit.getCoefficientOfCalibration() + ";\n";
            strings[15] = "\n" +
                    "//Характеристика шунта\n" +
                    "\n" +
                    "//Задайте максимальное напряжение шунта {U шунт макс}\n" +
                    "int maxVoltShunt = " + unit.getMaxVoltShunt() + ";\n";
            strings[16] = "//Задайте максимальный ток шунта {I шунт макс}\n" +
                    "int maxCurrentShunt = " + unit.getMaxCurrentShunt() + ";\n";
            strings[17] = "//Введите паспортную ёмкость для марки испольуемых аккумуляторов {C}\n" +
                    "int capacitanceOfBattery = " + unit.getCapacitanceOfBattery() + ";\n";
            strings[18] = "//Укажите число параллельных цепочек из аккумуляторов {N}\n" +
                    "int numberBattery = " + unit.getNumberBattery() + ";\n";
            strings[19] = "//Введите значение коэффициента передачи аналогового предусилителя {K}\n" +
                    "int coeffAnalogueAmplifier = " + unit.getCoeffAnalogueAmplifier() + ";\n";
            strings[20] = "//Задайте ток заряда {S}\n" +
                    "double chargingCurrent = " + unit.getChargingCurrent() + ";\n";
            strings[21] = "//Задайте предельный ток заряда {P}\n" +
                    "double maxChargCurrent = " + unit.getMaxChargCurrent() + ";\n";
            strings[22] = "//Задайте верхний порог ограничения тока {U reduce}\n" +
                    "double upThresholdCurrent = " + unit.getUpThresholdCurrent() +";\n";
            strings[23] = "//Задайте порог применения ускоренного заряда {B}\n" +
                    "double thresholdForBoost = " + unit.getThresholdForBoost() + ";\n";
            strings[24] = "//Задайте условие окончания ускоренного заряда {R}\n" +
                    "double thresholdBoostEnding = " + unit.getThresholdBoostEnding() + ";\n";
            strings[25] = "//Задайте максимальное время работы в режиме boost в минутах\n" +
                    "byte timeInBoost = " + unit.getTimeInBoost() + ";\n";
            strings[26] = "//Задайте время задержки между повторным включением режима boost в минутах\n" +
                    "byte delayBoost = " + unit.getDelayBoost() + ";\n";
            strings[27] = "\n" +
                    "// велечина напряжения сброса Ureset  в 10 битах\n" +
                    "\n" +
                    "int voltageReset = 575;\n";
            strings[28] = "\n" +
                    "/******************************************************************************************************************************************************************/\n" +
                    "//ВЫЧИСЛЯЕМЫЕ ЗНАЧЕНИЯ\n" +
                    "\n" +
                    "//Напряжение соответствующее номинальному току заряда мВ Uномин\n" +
                    "\n" +
                    "double valueOfNominalCurrentOnVoltage = 0.1 * capacitanceOfBattery * numberBattery * coeffAnalogueAmplifier * maxVoltShunt / maxCurrentShunt;\n" +
                    "\n" +
                    "//Напряжение, соответствующее выбранному току заряда U заряда  в мВ\n" +
                    "double voltageOfCharge = valueOfNominalCurrentOnVoltage * chargingCurrent;\n" +
                    "//Переводим в 10 бит\n" +
                    "int voltageOfChargeADC = voltageOfCharge/accuracyInput;\n" +
                    "\n" +
                    "//Напряжение, соответствующее предельному току заряда U max (в мВ)\n" +
                    "int limitVoltageOfCharge = valueOfNominalCurrentOnVoltage * maxChargCurrent;\n" +
                    "//Umax в 10-битном исчислении\n" +
                    "int limitVoltageChrgDAC = limitVoltageOfCharge/accuracyInput;\n" +
                    "\n" +
                    "//Напряжение, соответствующее верхнему порогу ограничения заряда\n" +
                    "int upThrCurrentVoltage = valueOfNominalCurrentOnVoltage * upThresholdCurrent;\n" +
                    "//U reduce в 10-битах\n" +
                    "int upThrCurrDAC = upThrCurrentVoltage/accuracyInput;\n" +
                    "\n" +
                    "//Вычисляем значение Ustart при котором происходит начало ускоренного заряда (переключение с Float на Boost) Ustart\n" +
                    "\n" +
                    "double switchFloatToBoost = valueOfNominalCurrentOnVoltage * thresholdForBoost;\n" +
                    "//пересчёт в 10 бит\n" +
                    "int switchFloatToBoostADC = switchFloatToBoost/accuracyInput;\n" +
                    "\n" +
                    "//Вычисляем пороговое значение Ustop, при котором происходит переключения с функции Boost на Float (окончание ускоренного заряда) Ustop\n" +
                    "\n" +
                    "double switchBoostToFloat = valueOfNominalCurrentOnVoltage * thresholdBoostEnding;\n" +
                    "//пересчёт в 10 бит\n" +
                    "int switchBoostToFloatADC = switchBoostToFloat/accuracyInput;\n" +
                    "\n" +
                    "//получение порога переключения, пересчитанного в 10 бит с усилением в 8 раз\n" +
                    "int switchBoostToFloat8ADC = 8 * switchBoostToFloat/accuracyInput;\n" +
                    "\n" +
                    "//режим работы\n" +
                    "String mode;\n" +
                    "\n" +
                    "//текущее событие\n" +
                    "String event;\n" +
                    "\n" +
                    "//таймер\n" +
                    "String timerMode;\n" +
                    "\n" +
                    "//Вычисляем температуры для 10-битного АЦП\n" +
                    "\n" +
                    "//Вычисление минимальной температуры графика Float для 10-битного АЦП\n" +
                    "int minTempFloatADC = (millivoltAtZeroDegrees + minTempFloatDeg*changingMillivoltPerOneDegrees) / accuracyInput;\n" +
                    "// вычисляем первую среднюю точку для 10-битного АЦП\n" +
                    "int tempFirstPointFloatADC = (millivoltAtZeroDegrees + tempFirstMidPointFloat*changingMillivoltPerOneDegrees) / accuracyInput;\n" +
                    "//вычисляем вторую среднюю точку для 10-битного АЦП\n" +
                    "int tempSecondPointFloatADC = (millivoltAtZeroDegrees + tempSecondMidFloatDeg*changingMillivoltPerOneDegrees) / accuracyInput;\n" +
                    "//вычисляем максимальну температуру для 10-битного АЦП\n" +
                    "int maxTempFloatADC = (millivoltAtZeroDegrees + maxTempFloatDeg*changingMillivoltPerOneDegrees) / accuracyInput;\n" +
                    "\n" +
                    "//Вычисление минимальной температуры графика Boost для 10-битного АЦП\n" +
                    "int minTempBoostADC = (millivoltAtZeroDegrees + minTempBoostDeg*changingMillivoltPerOneDegrees) / accuracyInput;\n" +
                    "//Вычисление максимальной температуры графика Boost после которого происходит переход на Float для 10 битного АЦП\n" +
                    "int maxTempBoostADC = (millivoltAtZeroDegrees + maxTempBoostDeg*changingMillivoltPerOneDegrees) / accuracyInput;\n" +
                    "\n" +
                    "//Вычисляем значения выходного напряжения управления для 10 битного ЦАП\n" +
                    "\n" +
                    "//вычисляем значение выходного напряжения для 10-битного ЦАП, увеличиваем на единицу, т.к. в данной версии для Тубор это необходимо\n" +
                    "//из-за грубого округления при отбрасывании десятых долей\n" +
                    "int outputMaxDAC = outputMaximum * coefficientOfCalibration / accuracyOutput + 1;\n" +
                    "//вычисляем значение выходного напряжение средних точек Float для 10-битного ЦАП\n" +
                    "int outputMidFloatDAC = outputMiddleFloat * coefficientOfCalibration / accuracyOutput;\n" +
                    "//вычисляем значение вых напряжени при минимальной температуре для Boost для 10-битного ЦАП\n" +
                    "int outputMinBoostDAC = outputBoostMinimum * coefficientOfCalibration / accuracyOutput;\n" +
                    "//вычисляем значение выходного напряжения при максимальной температуре для 10-битного ЦАП\n" +
                    "int outputMinFloatDAC = outputFloatMinimum * coefficientOfCalibration / accuracyOutput;\n" +
                    "\n" +
                    "//вычисляем точку калибровки для 10-битного АЦП\n" +
                    "int tempCalibrationADC = (millivoltAtZeroDegrees + tempCalibrationDeg*changingMillivoltPerOneDegrees) / accuracyInput;\n" +
                    "\n" +
                    "//Переменные времени\n" +
                    "\n" +
                    "//время в миллисекундах в boost\n" +
                    "unsigned long timeInBoostMillis = timeInBoost * 60 * 1000;\n" +
                    "//время в миллисекундах задержка между boost и float\n" +
                    "unsigned long delayBoostMillis = delayBoost * 60 * 1000;\n" +
                    "\n" +
                    "//Перменные, расчитываемые при старте программы\n" +
                    "\n" +
                    "//Созадём массив на 10 точек для скользящей средней по температуре без инициализации\n" +
                    "int arrayTemp[10];\n" +
                    "int arrayCurrent[10];\n" +
                    "int array8Shunt[10];\n"+
                    "int arraySupport[20];\n" +
                    "\n" +
                    "//Создаём переменные для выходного сигнала и средней температуры для их использования в функции отображения на экране.\n" +
                    "int outputSignal;\n" +
                    "int averageTemperature;\n" +
                    "int shuntCurrent;\n" +
                    "int valueOfCurrent;\n" +
                    "int value8Shunt; \n" +
                    "int voltageSupport;\n" +
                    "int voltageTemperature;\n" +
                    "\n" +
                    "boolean isUstart = false;\n" +
                    "boolean isLastSignalBoost = false;\n" +
                    "boolean isStart = true;\n" +
                    "\n" +
                    "//переменные для хранения времени таймеров\n" +
                    "unsigned long timerFloatBoost;\n" +
                    "unsigned long timerBoost;\n" +
                    "unsigned long timerVoltageShunt;\n" +
                    "unsigned long timer8Shunt;\n" +
                    "unsigned long timerTemperature;\n" +
                    "unsigned long timerVoltageSupport;\n" +
                    "unsigned long timerComparator;\n" +
                    "unsigned long timerDisplaying;\n" +
                    "\n" +
                    "\n" +
                    "/****************************************************************************************************************************************************************/\n" +
                    "\n" +
                    "void setup() {\n" +
                    "\n" +
                    "  // put your setup code here, to run once:\n" +
                    "  pinMode(INPUT_TEMP, INPUT);\n" +
                    "  pinMode(INPUT_SHUNT, INPUT);\n" +
                    "  pinMode(INPUT_8SHUNT, INPUT);\n" +
                    "  pinMode(INPUT_SUPPORT, INPUT);\n" +
                    "  pinMode(LED_BUILTIN, OUTPUT);\n" +
                    "  //Устанавливаем разрешение для работы с входным сигналом\n" +
                    "  analogReference(AR_INTERNAL1V65);\n" +
                    "\n" +
                    "  analogWriteResolution(10);\n" +
                    "  analogReadResolution(10);\n" +
                    "\n" +
                    "  // инициализация массива Температуры (ниже блок 2) для скользящей средней\n" +
                    "  for (int i = 0; i < 10; i++)\n" +
                    "  {\n" +
                    "    arrayTemp[i] = analogRead(INPUT_TEMP);\n" +
                    "    delay(10);\n" +
                    "  }\n" +
                    "\n" +
                    "  // инициализация массива Шунта (ниже блок 1) для скользящей средней\n" +
                    "  for (int j = 0; j < 10; j++)\n" +
                    "  {\n" +
                    "    arrayCurrent[j] = analogRead(INPUT_SHUNT);\n" +
                    "    delay(10);\n" +
                    "  }\n" +
                    "\n" +
                    "  // инициализация массива усиленного в 8 раз Шунта (ниже блок 1.1) для скользящей средней\n" +
                    "  for (int l = 0; l < 10; l++){\n" +
                    "    array8Shunt[l] = analogRead(INPUT_8SHUNT);\n" +
                    "    delay(10);\n" +
                    "  }\n" +
                    "\n"+
                    "  // инициализация массива Суппорта (ниже блок 3) для скользящей средней\n" +
                    "\n" +
                    "  for (int k = 0; k < 20; k++)\n" +
                    "  {\n" +
                    "    arraySupport[k] = analogRead(INPUT_SUPPORT);\n" +
                    "    delay(10);\n" +
                    "  }\n" +
                    "\n" +
                    "  // делаем первоначальную иницаиализацию выходного сигнала\n" +
                    "  outputSignal = voltageReset;\n" +
                    "\n" +
                    "  //инициализация таймеров\n" +
                    "  timerFloatBoost = millis();\n" +
                    "  timerBoost = millis();\n" +
                    "  timerVoltageShunt = millis();\n" +
                    "  timer8Shunt = millis();\n" +
                    "  timerTemperature = millis();\n" +
                    "  timerVoltageSupport = millis();\n" +
                    "  timerComparator = millis();\n" +
                    "  timerDisplaying = millis();\n" +
                    "  isStart = true;\n" +
                    "\n" +
                    "  //открываем передачу данных для мониторинга\n" +
                    "  Serial.begin(9600);\n" +
                    "}\n" +
                    "\n" +
                    "/*************************************************************************************************************************************************************************************/\n" +
                    "\n" +
                    "void loop() {\n" +
                    "\n" +
                    "  //БЛОК 1. получение данных по напряжению на шунте\n" +
                    "\n" +
                    "  if (!isTimerWork(timerVoltageShunt, 15)) {\n" +
                    "    valueOfCurrent = getMovAverageCurrent(arrayCurrent);\n" +
                    "    timerVoltageShunt = millis();\n" +
                    "  }\n" +
                    "\n" +
                    "  //БЛОК 1.1 получение данных с шунта, усиленные в 8 раз\n" +
                    "\n" +
                    "  if(!isTimerWork(timer8Shunt, 15)) {\n" +
                    "    value8Shunt = getMovAverage8Shunt(array8Shunt);\n" +
                    "    timer8Shunt = millis();\n" +
                    "  }\n" +
                    "\n" +
                    "  //БЛОК 2. получение данных по температуре\n" +
                    "  if (!isTimerWork(timerTemperature, 1000)) {\n" +
                    "    averageTemperature = getMovAverageTemp(arrayTemp);\n" +
                    "    timerTemperature = millis();\n" +
                    "  }\n" +
                    "\n" +
                    "  //БЛОК 3. получение данных по Usupport\n" +
                    "  if (!isTimerWork(timerVoltageSupport, 15)) {\n" +
                    "    voltageSupport = getMovAverageSupport(arraySupport);\n" +
                    "    timerVoltageSupport = millis();\n" +
                    "  }\n" +
                    "\n" +
                    "  //БЛОК 4. Выбор графика boost/float\n" +
                    "  //условие калибровки (если температура ниже минус 40, то включаем режим выходного сигнала, равный среднему значению при Флоат)\n" +
                    "  if (!isTimerWork(timerComparator, 330)) {\n" +
                    "    \n" +
                    "      //4.2 проверка на 15-ти мнунтный таймер\n" +
                    "      if (isTimerWork(timerFloatBoost, delayBoostMillis) && !isStart) {\n" +
                    "        mode = \"4.2 Float\";\n" +
                    "        voltageTemperature = outputFloat(averageTemperature);\n" +
                    "        timerMode = \"15-ти минутный таймер работает\";\n" +
                    "        \n" +
                    "      }\n" +
                    "      //4.3 проверка на значение выше 45 градусов Цельсия\n" +
                    "      else if (averageTemperature > maxTempBoostADC) {\n" +
                    "        mode = \"4.3 Float\";\n" +
                    "        voltageTemperature = outputFloat(averageTemperature);\n" +
                    "        timerFloatBoost = millis();\n" +
                    "        timerMode = \"15-ти минутный таймер запущен\";\n" +
                    "        isUstart = false;\n" +
                    "        isLastSignalBoost = false;\n" +
                    "        isStart = false;\n" +
                    "      }\n" +
                    "      //4.4 проверка Ushunt <= Ustop\n" +
                    "      else if (value8Shunt <= switchBoostToFloat8ADC) {\n" +
                    "        if (isLastSignalBoost) {\n" +
                    "          mode = \"4.4 Float\";\n" +
                    "          voltageTemperature = outputFloat(averageTemperature);\n" +
                    "          timerFloatBoost = millis();\n" +
                    "          timerMode = \"15-ти минутный таймер запущен\";\n" +
                    "          isUstart = false;\n" +
                    "          isLastSignalBoost = false;\n" +
                    "          isStart = false;\n" +
                    "        }\n" +
                    "        else {\n" +
                    "          mode = \"4.4 else Float\";\n" +
                    "          voltageTemperature = outputFloat(averageTemperature);\n" +
                    "          timerMode = \"таймеры не работают\";\n" +
                    "          isUstart = false;\n" +
                    "          isLastSignalBoost = false;\n" +
                    "        }\n" +
                    "      }\n" +
                    "      //4.5 проверка Ushunt >= Ustart\n" +
                    "      else if (valueOfCurrent >= switchFloatToBoostADC) {\n" +
                    "        mode = \"4.5 Boost\";\n" +
                    "        voltageTemperature = outputBoost(averageTemperature);\n" +
                    "        timerMode = \"таймеры не работают\";\n" +
                    "        isUstart = true;\n" +
                    "        isLastSignalBoost = true;\n" +
                    "      }\n" +
                    "      //4.6 проверка момента перехода Ustart в сторону Ustop\n" +
                    "      else if (isUstart) {\n" +
                    "        isUstart = false;\n" +
                    "        mode = \"4.6 Boost\";\n" +
                    "        timerBoost = millis();\n" +
                    "        timerMode = \"запущен 8-ми часовой таймер\";\n" +
                    "        voltageTemperature = outputBoost(averageTemperature);\n" +
                    "        isLastSignalBoost = true;\n" +
                    "      }\n" +
                    "      //4.7 проверка на работу 8 часового таймера\n" +
                    "      else if (isTimerWork(timerBoost, timeInBoostMillis)) {\n" +
                    "        mode = \"4.7 Boost\";\n" +
                    "        voltageTemperature = outputBoost(averageTemperature);\n" +
                    "        timerMode = \"8 часовой таймер работает\";\n" +
                    "        isLastSignalBoost = true;\n" +
                    "      }\n" +
                    "      //4.8 проверка последний сигнал был Boost?\n" +
                    "      else if(isLastSignalBoost) {\n" +
                    "         mode = \"4.8 Float\";\n" +
                    "         voltageTemperature = outputFloat(averageTemperature);\n" +
                    "         timerFloatBoost = millis();\n" +
                    "         timerMode = \"15-ти минутный таймер запущен\";\n" +
                    "         isLastSignalBoost = false;\n" +
                    "         isStart = false;\n" +
                    "      }\n" +
                    "      //4.9 предохранительная ветка\n" +
                    "      else {\n" +
                    "      mode = \"4.9 Float\";\n" +
                    "      voltageTemperature = outputFloat(averageTemperature);\n" +
                    "      timerMode = \"таймеры не работают\";\n" +
                    "      isLastSignalBoost = false;\n" +
                    "      }\n" +
                    "        \n" +
                    "      //БЛОК 5. Блок сравнения\n" +
                    "      //5.1 проверка на Ushunt > Umax\n" +
                    "      if (valueOfCurrent > limitVoltageChrgDAC) {\n" +
                    "        outputSignal = voltageReset;\n" +
                    "        event = \"5.1 Ushunt > Umax; Uout = Ureset\";\n" +
                    "      }\n" +
                    "      //5.2 проверка на Uout>Ut либо Ushunt > Ureset\n" +
                    "      else if (outputSignal > voltageTemperature || valueOfCurrent > upThrCurrDAC) {\n" +
                    "        outputSignal--;\n" +
                    "        event = \"5.2 Uout > Ut; Uout--\";\n" +
                    "      }\n" +
                    "      //5.3 проверка на Uout=Ut\n" +
                    "      else if (outputSignal == voltageTemperature) {\n" +
                    "        outputSignal = voltageTemperature;\n" +
                    "        event = \"5.3 Uout = Ut\";\n" +
                    "      }\n" +
                    "      //5.4 проверка на Ushunt>Usuppl \n" +
                    "      else if (valueOfCurrent > voltageOfChargeADC) {\n" +
                    "        //пустая операция\n" +
                    "        event = \"5.4 Ushunt > Usuppl; пустая операция\";\n" +
                    "      }\n" +
                    "      //5.5 проверка на Usupport <= Ureset и проверка через либо 5.6 проверка на Uout >= Usupport\n" +
                    "      else if (voltageSupport <= voltageReset || outputSignal >= voltageSupport) {\n" +
                    "        outputSignal++;\n" +
                    "        event = \"5.5/5.6 Usupport <= Ureset или Uout >= Usupport; Uout ++ \";\n" +
                    "      }\n" +
                    "      //5.7 последнее иначе\n" +
                    "      else {\n" +
                    "        outputSignal = voltageSupport;\n" +
                    "        event = \"5.7 Uout = Usupport\";\n" +
                    "      }\n" +
                    "    \n" +
                    "\n" +
                    "//    Проверка на уровень сигнала, чтобы понимать, что можно включить светодиод\n" +
                    "      if(outputSignal == outputMidFloatDAC){\n" +
                    "          digitalWrite(LED_BUILTIN, HIGH);\n" +
                    "        } else digitalWrite(LED_BUILTIN, LOW);\n" +
                    "    timerComparator = millis();\n" +
                    "\n" +
                    "  }\n" +
                    "\n" +
                    "\n" +
                    "  analogWrite (OUTPUT_SIGNAL, outputSignal);\n" +
                    "\n" +
                    "  if (!isTimerWork(timerDisplaying, 5000)) {\n" +
                    "    displayingDataTemp ();\n" +
                    "    timerDisplaying = millis();\n" +
                    "  }\n" +
                    "\n" +
                    "}\n" +
                    "/*********************************************************************************************************************************************************************************/\n" +
                    "\n" +
                    "//вычисление выходного сигнала по графику Float\n" +
                    "\n" +
                    "int outputFloat(int tempLevel) {\n" +
                    "  int outputSignalFloat;\n" +
                    "\n" +
                    "  if (tempLevel < tempCalibrationADC) {\n" +
                    "    outputSignalFloat = outputMidFloatDAC;\n" +
                    "    mode = \"Calibration\";\n" +
                    "  }\n" +
                    "  else if (tempLevel <= minTempFloatADC) {\n" +
                    "    outputSignalFloat = outputMaxDAC;\n" +
                    "  }\n" +
                    "  else if (tempLevel > minTempFloatADC && tempLevel <= tempFirstPointFloatADC) {\n" +
                    "    outputSignalFloat = map(tempLevel, minTempFloatADC, tempFirstPointFloatADC, outputMaxDAC, outputMidFloatDAC);\n" +
                    "  }\n" +
                    "  else if (tempLevel > tempFirstPointFloatADC && tempLevel <= tempSecondPointFloatADC) {\n" +
                    "    outputSignalFloat = outputMidFloatDAC;\n" +
                    "  }\n" +
                    "  else if (tempLevel > tempSecondPointFloatADC && tempLevel <= maxTempFloatADC) {\n" +
                    "    outputSignalFloat = map(tempLevel, tempSecondPointFloatADC, maxTempFloatADC, outputMidFloatDAC, outputMinFloatDAC);\n" +
                    "  }\n" +
                    "  else if (tempLevel > maxTempFloatADC) {\n" +
                    "    outputSignalFloat = outputMinFloatDAC;\n" +
                    "  }\n" +
                    "  return outputSignalFloat;\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "//вычисление выходного сигнала по графику Boost\n" +
                    "\n" +
                    "int outputBoost(int tempLevel) {\n" +
                    "  int outputSignalBoost;\n" +
                    "  if (tempLevel < tempCalibrationADC) {\n" +
                    "    outputSignalBoost = outputMidFloatDAC;\n" +
                    "    mode = \"Calibration\";\n" +
                    "  }\n" +
                    "  else if (tempLevel <= minTempBoostADC) {\n" +
                    "    outputSignalBoost = outputMaxDAC;\n" +
                    "  }\n" +
                    "  else if (tempLevel > minTempBoostADC && tempLevel <= maxTempBoostADC) {\n" +
                    "    outputSignalBoost = map (tempLevel, minTempBoostADC, maxTempBoostADC, outputMaxDAC, outputMinBoostDAC);\n" +
                    "  }\n" +
                    "  else outputSignalBoost = outputFloat (tempLevel);\n" +
                    "\n" +
                    "  return outputSignalBoost;\n" +
                    "}\n" +
                    "\n" +
                    "//вычисление скользящей средней на 10 точек для данных по температуре\n" +
                    "\n" +
                    "int getMovAverageTemp (int arrayTemp[10]) {\n" +
                    "  for (byte j = 0; j < 9; j++) {\n" +
                    "    arrayTemp[j] = arrayTemp[j + 1];\n" +
                    "  }\n" +
                    "\n" +
                    "  arrayTemp[9] = analogRead(INPUT_TEMP);\n" +
                    "\n" +
                    "  int sum = 0;\n" +
                    "  for (byte i = 0; i < 10 ; i++) {\n" +
                    "    sum += arrayTemp[i];\n" +
                    "  }\n" +
                    "\n" +
                    "  return sum / 10;\n" +
                    "}\n" +
                    "\n" +
                    "//вычисление скользящей средней на 10 точек для данных по току Шунта\n" +
                    "\n" +
                    "int getMovAverageCurrent (int arrayCur[10]) {\n" +
                    "  for (byte j = 0; j < 9; j++) {\n" +
                    "    arrayCur[j] = arrayCur[j + 1];\n" +
                    "  }\n" +
                    "\n" +
                    "  arrayCur[9] = analogRead(INPUT_SHUNT);\n" +
                    "\n" +
                    "  int sum = 0;\n" +
                    "  for (byte i = 0; i < 10 ; i++) {\n" +
                    "    sum += arrayCur[i];\n" +
                    "  }\n" +
                    "\n" +
                    "  return sum / 10;\n" +
                    "}\n" +
                    "\n" +
                    "//вычисление скользящей средней на 10 точек для данных по току усиленного Шунта\n" +
                    "\n" +
                    "int getMovAverage8Shunt (int arrayCur[10]) {\n" +
                    "  for (byte j = 0; j < 9; j++) {\n" +
                    "    arrayCur[j] = arrayCur[j + 1];\n" +
                    "  }\n" +
                    "\n" +
                    "  arrayCur[9] = analogRead(INPUT_8SHUNT);\n" +
                    "\n" +
                    "  int sum = 0;\n" +
                    "  for (byte i = 0; i < 10 ; i++) {\n" +
                    "    sum += arrayCur[i];\n" +
                    "  }\n" +
                    "\n" +
                    "  return sum / 10;\n" +
                    "}\n" +
                    "\n" +
                    "// вычисление сокльзящей средней на 20 точек для Usupport\n" +
                    "int getMovAverageSupport(int arraySup[20]) {\n" +
                    "  for (byte j = 0; j < 19; j++) {\n" +
                    "    arraySup[j] = arraySup[j + 1];\n" +
                    "  }\n" +
                    "\n" +
                    "  arraySup[19] = analogRead(INPUT_SUPPORT);\n" +
                    "\n" +
                    "  int sum = 0;\n" +
                    "  for (byte i = 0; i < 20 ; i++) {\n" +
                    "    sum += arraySup[i];\n" +
                    "  }\n" +
                    "\n" +
                    "  return sum / 20;\n" +
                    "}\n" +
                    "\n" +
                    "//проверка сколько времени прошло с момента старта программы, с обработкой переполнения\n" +
                    "\n" +
                    "boolean isTimerWork(unsigned long timeWork, unsigned long timeLimit) {\n" +
                    "\n" +
                    "  if (millis() - timeWork < timeLimit) {\n" +
                    "    return true;\n" +
                    "  }\n" +
                    "  else return false;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "//функция показа данных в мониторе порта\n" +
                    "\n" +
                    "void displayingDataTemp () {\n" +
                    "  //6.1 10 битный блок\n" +
                    "  Serial.print(\"Выходной сигнал в разрядности 10 бит: \");\n" +
                    "  Serial.println(outputSignal);\n" +
                    "\n" +
                    "  Serial.print(\"Температура в 10 битах: \");\n" +
                    "  Serial.println(averageTemperature);\n" +
                    "\n" +
                    "  Serial.print(\"Напряжение шунта в 10 битах: \");\n" +
                    "  Serial.println(valueOfCurrent);\n" +
                    "\n" +
                    "  Serial.print(\"Опорное напряжение в 10 битах: \");\n" +
                    "  Serial.println(voltageSupport);\n" +
                    "  Serial.println(\" \");\n" +
                    "\n" +
                    "  //6.2 блок фактических расчётных значений\n" +
                    "  Serial.print(\"Выходной сигнал в милливольтах:\");\n" +
                    "  Serial.println(outputSignal * accuracyOutput);\n" +
                    "\n" +
                    "  double tempDeg = (averageTemperature * 1650.0 / 1023.0 - 500.0) / 10.0;\n" +
                    "  Serial.print(\"Температура в градусах C: \");\n" +
                    "  Serial.println(tempDeg);\n" +
                    "\n" +
                    "  Serial.print(\"Напряжение шунта в милливольтах: \");\n" +
                    "  Serial.println(valueOfCurrent * accuracyInput);\n" +
                    "\n" +
                    "  Serial.print(\"Опорное напряжение в милливольтах: \");\n" +
                    "  Serial.println(voltageSupport * accuracyInput);\n" +
                    "\n" +
                    "  //6.3 блок\n" +
                    "  //выбранный режим расчёта\n" +
                    "  Serial.print(\"Текущий режим работы: \");\n" +
                    "  Serial.println(mode);\n" +
                    "\n" +
                    "  //текущее событие\n" +
                    "  Serial.print(\"Текущиее событие: \");\n" +
                    "  Serial.println(event);\n" +
                    "\n" +
                    "  //статус таймеров\n" +
                    "  Serial.print(\"Статус таймеров: \");\n" +
                    "  Serial.println(timerMode);\n" +
                    "\n" +
                    "  /*\n" +
                    "    for(int i = 0 ; i < 10 ; i ++) {\n" +
                    "    Serial.print(\"элемент массива: \");\n" +
                    "    Serial.println(i);\n" +
                    "    Serial.println(arrayTemp[i]);\n" +
                    "    }\n" +
                    "  */\n" +
                    "  Serial.println(\" \");\n" +
                    "  Serial.println(\" \");\n" +
                    "}";

            for (String string : strings) {
                filePrintStream.println(string);
            }

            filePrintStream.close();

        } catch (NumberFormatException e) {
            showAlert("Формат чисел", "Проверьте форматы чисел", "Где-то введён текст, вместо числа");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShowGraph() {
        writeParamToUnit();
        main.showGraph();
    }

    private boolean handleWrongData(){

        String title = "ошибка";
//        Обработка неправильного ввода данных по характеристике датчика
        if( unit.getmVAtZeroDeg() < lowBoundMVAtZeroDeg || unit.getmVAtZeroDeg() > upperBoundMVAtZeroDeg){
//            String title = "Ошибка";
            String header = "Неправильный ввод данных датчика температуры";
            String content = String.format("1.1 Напряжение при нуле гр. Цельсия должно быть от %d до %d мВ", lowBoundMVAtZeroDeg, upperBoundMVAtZeroDeg);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода поля приращение напряжения за 1 гр. С

        if(unit.getChangingMVPerOneDeg() < lowBoundMVPerOneDeg || unit.getChangingMVPerOneDeg() > upperBoundMVPerOneDeg){
//            String title = "";
            String header = "Неправильный ввод данных датчика температуры";
            String content = String.format("1.2 Приращение напряжения за 1 гр.Цельсия должно быть от %d до %d мВ/гр.", lowBoundMVPerOneDeg, upperBoundMVPerOneDeg);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода минимальной температуры для режима Float

        if (unit.getMinTempFloat() < lowTempFirstPointFloat || unit.getMinTempFloat() > upTempFristPointFloat) {
//            String title = ;
            String header = "Неправильный ввод температурных данных режима Float";
            String content = String.format("2.1 Минимальное значение температуры должно быть от %d до %d градусов С", lowTempFirstPointFloat, upTempFristPointFloat);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода температуры первой средней точки режима Float
        if (unit.getTempFirstMidPointFloat() < lowTempFirstMidPoint || unit.getTempFirstMidPointFloat() > upTempFirstMidPoint) {
//            String title = ;
            String header = "Неправильный ввод температурных данных режима Float";
            String content = String.format("2.2 Значение температуры первой ср. точки режима Float должно быть от %d до %d гр.С", lowTempFirstMidPoint, upTempFirstMidPoint);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода температуры второй средней точки режима Float
        if (unit.getTempSecondMidPointFloat() < lowTempSecondMidPoint || unit.getTempSecondMidPointFloat() > upTempSecondMidPoint) {
//            String title = ;
            String header = "Неправильный ввод температурных данных режима Float";
            String content = String.format("2.3 Значение температуры второй ср. точки режима Float должно быть от %d до %d гр.С", lowTempSecondMidPoint, upTempSecondMidPoint);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильно ввода температуры последней точки

        if (unit.getMaxTempFloat() < lowTempLastPointFloat || unit.getMaxTempFloat() > upTempLastPointFloat) {
            String header = "Неправильный ввод температурных данных режима Float";
            String content = String.format("2.4 Значение температуры последней точки режима Float должно быть от %d до %d гр.С", lowTempLastPointFloat, upTempLastPointFloat);
            showAlert(title, header, content);
            return true;
        }
//        Обработка неправильно ввода температуры режима Boost первой точки (минимальной)

        if(unit.getMinTempBoost() < lowFirstTempBoost || unit.getMinTempBoost() > upFristTempBoost){
            String header = "Неправильный ввод температурных данных режима Boost";
            String content = String.format("2.5 Значение минимальной температуры режима Boost должно быть от %d до %d гр.С", lowFirstTempBoost, upFristTempBoost);
            showAlert(title, header, content);
            return true;
        }
//        Обработка неправильно ввода температуры режима Boost второй точки (максимальной)

        if(unit.getMaxTempBoost() < lowLastTempBoost || unit.getMaxTempBoost() > upLastTempBoost){
            String header = "Неправильный ввод температурных данных режима Boost";
            String content = String.format("2.6 Значение минимальной температуры режима Boost должно быть от %d до %d гр.С", lowLastTempBoost, upLastTempBoost);
            showAlert(title, header, content);
            return true;
        }
//        Обработка неправильного ввода напряжения максимальной точки для обоих режимов

        if(unit.getOutputMaximum() < lowOutputMaximum || unit.getOutputMaximum() > upperOutputMaximum){
            String header = "Неправильный ввод данных по напряжению в разделе режимы";
            String content = String.format("3.1 Значение максимального напряжения должно быть в диапазоне от %d до %d мВ", lowOutputMaximum, upperOutputMaximum);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода напряжения средней точки Float
        if(unit.getOutputMiddle() < lowMidPointOutFloat || unit.getOutputMiddle() > upMidPointOutFloat){
            String header = "Неправильный ввод данных по напряжению в разделе режимы";
            String content = String.format("3.2 Значение напряжения средней точки режима Float должно быть в диапазоне от %d до %d мВ", lowMidPointOutFloat, upMidPointOutFloat);
            showAlert(title, header, content);
            return true;
        }
//        Обработка неправильного ввода напряжения минимальной точки режима Float
        if(unit.getOutputFloatMinimum() < lowMinPointOutFloat || unit.getOutputFloatMinimum() > upMinPointOutFloat){
            String header = "Неправильный ввод данных по напряжению в разделе режимы";
            String content = String.format("3.3 Значение минимального напряжения для Float должно быть в диапазоне от %d до %d мВ", lowMinPointOutFloat, upMinPointOutFloat);
            showAlert(title, header, content);
            return true;
        }
//       Обработка неправильного ввода напряжения минимальной точки режима Boost
        if(unit.getOutputBoostMinimum() < lowMinPointOutBoost || unit.getOutputBoostMinimum() > upperMinPointOutBoost){
            String header = "Неправильный ввод данных по напряжению в разделе режимы";
            String content = String.format("3.4 Значение минимального напряжения для Float должно быть в диапазоне от %d до %d мВ", lowMinPointOutBoost, upperMinPointOutBoost);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода значения коэффициента преобразования базовой шкалы
        if(unit.getCoefficientOfCalibration() < lowBoundCoeffCalib || unit.getCoefficientOfCalibration() > upBoundCoeffCalib){
            String header = "Неправильный ввод данных по коэффициента преобразования для базовой шкалы";
            String content = String.format(Locale.CANADA,"4. Значение коэффициента преобразования должно лежать в диапазоне от %.3f до %.3f", lowBoundCoeffCalib, upBoundCoeffCalib);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода значений для максимального напряжения шунта
        if(unit.getMaxVoltShunt() < lowBoundMaxVoltShunt || unit.getMaxVoltShunt() > upBoundMaxVoltShunt){
            String header = "Неправильный ввод данных по максимальному напряжению шунта";
            String content = String.format("5.1 Значение максимального напряжения шунта должно быть от %d до %d мВ", lowBoundMaxVoltShunt, upBoundMaxVoltShunt);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода значений для максимального тока шунта
        if(unit.getMaxCurrentShunt() < lowBoundMaxCurrentShunt || unit.getMaxCurrentShunt() > upBoundMaxCurrentShunt){
            String header = "Неправильный ввод данных по максимальному току шунта";
            String content = String.format("5.2 Значение максимального тока шунта должно быть в пределах от %d до %d А", lowBoundMaxCurrentShunt, upBoundMaxCurrentShunt);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода значений ёмкости батарей
        if(unit.getCapacitanceOfBattery() < lowBoundCapacitanceBatt || unit.getCapacitanceOfBattery() > upBoundCapacitanceBatt){
            String header = "Неправильный ввод данных значений ёмкости батарей";
            String content = String.format("6.1 Введите значение от %d до %d А*час", lowBoundCapacitanceBatt, upBoundCapacitanceBatt);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода количества батарей
        if(unit.getNumberBattery() < lowBoundNumberBatt || unit.getNumberBattery() > upBoundNumberBatt){
            String header = "Неправильный ввод данных значений количества батарей";
            String content = String.format("6.2 введите правильное количество батарей в диапазоне от %d до %d", lowBoundNumberBatt, upBoundNumberBatt);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода коэффициента аналогового предусилителя
        if(unit.getCoeffAnalogueAmplifier() < lowBoundCoefAnalogueAmplif || unit.getCoeffAnalogueAmplifier() > upBoundCoefAnalogueAmplif){
            String header = "Неправильный ввод данных коэффициента аналогового усилителя";
            String content = String.format("6.3 введите значние от %d до %d", lowBoundCoefAnalogueAmplif, upBoundCoefAnalogueAmplif);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода данных по току заряда
        if(unit.getChargingCurrent() < lowBoundChargingCurrent || unit.getChargingCurrent() > upBoundChargingCurrent){
            String header = "Неправильный ввод данных по множителю для зарядного тока";
            String content = String.format(Locale.CANADA,"6.4 введите значние от %.2f до %.2f", lowBoundChargingCurrent, upBoundChargingCurrent);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода данных по максимальному току заряда
        if(unit.getMaxChargCurrent() < lowBoundMaxChCurrent || unit.getMaxChargCurrent() > upBoundMaxChCurrent){
            String header = "Неправильный ввод данных по множителю для максимального тока заряда";
            String content = String.format(Locale.CANADA,"6.5 введите значние от %.2f до %.2f", lowBoundMaxChCurrent, upBoundMaxChCurrent);
            showAlert(title, header, content);
            return true;
        }

//        Обработка вводаа данных по верхней границе тока заряда
        if(unit.getUpThresholdCurrent() < lowBoundMaxChCurrent || unit.getUpThresholdCurrent() > upBoundMaxChCurrent){
            String header = "Неправильный ввод данных по множителю для верхней границы тока заряда";
            String content = String.format(Locale.CANADA,"6.6 введите значние от %.2f до %.2f", lowBoundUpThresholdCurrent, upBoundUpThresholdCurrent);
            showAlert(title, header, content);
            return true;
        }


//        Обработка неправильного ввода данных по порогу для переключения в режим Boost
        if(unit.getThresholdForBoost() < lowBoundThresholdForBoost || unit.getThresholdForBoost() > upBoundThresholdForBoost){
            String header = "Неправильный ввод данных по порогу для переключения в режиме ускоренного заряда";
            String content = String.format(Locale.CANADA,"6.7 введите значние от %.2f до %.2f", lowBoundThresholdForBoost, upBoundThresholdForBoost);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода данных по порогу окончания заряда в режиме Boost
        if(unit.getThresholdBoostEnding() < lowBoundThresholdBoostEnding || unit.getThresholdBoostEnding() > upBoundThresholdBoostEnding){
            String header = "Неправильный ввод данных по порогу окончания заряда в режиме ускоренного заряда";
            String content = String.format(Locale.CANADA,"6.8 введите значние от %.2f до %.2f", lowBoundThresholdBoostEnding, upBoundThresholdBoostEnding);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода данных по максимальному времени работы в режиме Boost
        if(unit.getTimeInBoost() < lowBoundTimeInBoost || unit.getTimeInBoost() > upBoundTimeInBoost){
            String header = "Неправильный ввод данных по максимальному времени работы в режиме ускоренного заряда";
            String content = String.format("6.9 введите время работы в минутах от %d до %d", lowBoundTimeInBoost, upBoundTimeInBoost);
            showAlert(title, header, content);
            return true;
        }

//        Обработка неправильного ввода данных по времени задержки для повторного включения режима Boost
        if(unit.getDelayBoost() < lowBoundDelayBoost || unit.getDelayBoost() > upBoundDelayBoost){
            String header = "Неправильный ввод данных по времени задержки повторного включения режима ускоренного заряда";
            String content = String.format("6.10 введите время работы в минутах от %d до %d", lowBoundDelayBoost, upBoundDelayBoost);
            showAlert(title, header, content);
            return true;
        }

//        Проверка значений больше-меньше для температурной характеристики в режиме Флоат
        if(unit.getMinTempFloat() > unit.getTempFirstMidPointFloat()){
            String header = "Неправильный ввод данных по температуре в режиме Float";
            String content = "Температура в пункте 2.1 должна быть меньше, чем в пункте 2.2";
            showAlert(title, header, content);
            return true;
        }

        if(unit.getTempFirstMidPointFloat() > unit.getTempSecondMidPointFloat()){
            String header = "Неправильный ввод данных по температуре в режиме Float";
            String content = "Температура в пункте 2.2 должна быть меньше, чем в пункте 2.3";
            showAlert(title, header, content);
            return true;
        }

        if(unit.getTempSecondMidPointFloat() > unit.getMaxTempFloat()){
            String header = "Неправильный ввод данных по температуре в режиме Float";
            String content = "Температура в пункте 2.3 должна быть меньше, чем в пункте 2.4";
            showAlert(title, header, content);
            return true;
        }

//        Проверка значений больше-меньше по температурам для режима Boost

        if(unit.getMinTempBoost() > unit.getMaxTempBoost()){
            String header = "Неправильный ввод данных по температуре в режиме Boost";
            String content = "Температура в пункте 2.5 должна быть меньше, чем в пункте 2.6";
            showAlert(title, header, content);
            return true;
        }

//        Проверка значений больше-меньше по значению зарядного напряжения

        if(unit.getOutputMaximum() < unit.getOutputMiddle()){
            String header = "Неправильный ввод данных по выходному сигналу";
            String content = "Напряжение в пункте 3.1  должно быть больше, чем в пункте 3.2";
            showAlert(title, header, content);
            return true;
        }

        if(unit.getOutputMaximum() < unit.getOutputBoostMinimum()) {
            String header = "Неправильный ввод данных по выходному сигналу";
            String content = "Напряжение в пункте 3.1  должно быть больше, чем в пункте 3.4";
            showAlert(title, header, content);
            return true;
        }

        if(unit.getOutputMiddle() < unit.getOutputFloatMinimum()){
            String header = "Неправильный ввод данных по выходному сигналу";
            String content = "Напряжение в пункте 3.2  должно быть больше, чем в пункте 3.3";
            showAlert(title, header, content);
            return true;
        }

//        Проверка больше-меньше по токам заряда
        if(unit.getMaxChargCurrent() < unit.getChargingCurrent()){
            String header = "Неправильный ввод данных по току заряда";
            String content = "Значение коэффициентов в пункте 6.5  должно быть больше, чем в пункте 6.4";
            showAlert(title, header, content);
            return true;
        }

        return false;
    }

    private void showAlert(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setResizable(true);

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    private void showInformationContent(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setResizable(true);

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }
}
