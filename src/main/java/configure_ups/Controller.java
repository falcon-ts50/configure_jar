package configure_ups;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Controller {

    Unit unit = new Unit();

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
    private TextField thresholdForBoost;

    @FXML
    private TextField thresholdBoostEnding;

    @FXML
    private TextField timeInBoost;

    @FXML
    private TextField delayBoost;

    @FXML
    private Button previousField;

    @FXML
    private Button clear;

    @FXML
    private Button write;

    private MainAppFX main;

    public void setMain(MainAppFX main){
        this.main=main;
    }

    public void handleDelete(){

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
        thresholdForBoost.setText("");
        thresholdBoostEnding.setText("");
        timeInBoost.setText("");
        delayBoost.setText("");

    }

    public void setBasicParams(){
        unit.setmVAtZeroDeg(500);
        unit.setChangingMVPerOneDeg(10);
        unit.setMinTempFloat(0);
        unit.setTempFirstMidPointFloat(15);
        unit.setTempSecondMidPointFloat(35);
        unit.setMaxTempFloat(50);
        unit.setMaxTempBoost(30);
        unit.setMinTempBoost(45);
        unit.setOutputMaximum(2660);
        unit.setOutputMiddle(2550);
        unit.setOutputFloatMinimum(2468);
        unit.setOutputBoostMinimum(2550);
        unit.setCoefficientOfCalibration(1.108);
        unit.setMaxVoltShunt(60);
        unit.setMaxCurrentShunt(400);
        unit.setCapacitanceOfBattery(75);
        unit.setNumberBattery(4);
        unit.setCoeffAnalogueAmplifier(25);
        unit.setChargingCurrent(2.8);
        unit.setMaxChargCurrent(3.5);
        unit.setThresholdForBoost(0.9);
        unit.setThresholdBoostEnding(0.25);
        unit.setTimeInBoost(480);
        unit.setDelayBoost(15);


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
        thresholdForBoost.setText(String.valueOf(unit.getThresholdForBoost()));
        thresholdBoostEnding.setText(String.valueOf(unit.getThresholdBoostEnding()));
        timeInBoost.setText(String.valueOf(unit.getTimeInBoost()));
        delayBoost.setText(String.valueOf(unit.getDelayBoost()));

    }
    public void setWriteParams() throws IOException {

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
        unit.setThresholdForBoost(Double.parseDouble(thresholdForBoost.getText()));
        unit.setThresholdBoostEnding(Double.parseDouble(thresholdBoostEnding.getText()));
        unit.setTimeInBoost(Integer.parseInt(timeInBoost.getText()));
        unit.setDelayBoost(Integer.parseInt(delayBoost.getText()));


        //    BufferedWriter writer = new BufferedWriter(new FileWriter("/home/kirill/test/UPSTuborTc_Boost/UPSTuborTc_Boost.ino"));

        PrintStream filePrintStream = new PrintStream(new File("configure_UPS.ino"));

        String[] strings = new String[28];
        strings[0]= "//создаём макроопределения для используемых пинов\n" +
                "#define OUTPUT_SIGNAL     A0\n" +
                "#define INPUT_TEMP        A2\n" +
                "#define INPUT_SHUNT       A4\n" +
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
                "int maxTempFloatDeg = " + unit.getMaxTempFloat() +";";
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
                "int  outputFloatMinimum = " + unit.getOutputFloatMinimum() +";";
        strings[13] = "//задайте значение минимального выходного напряжения в милливольтах для режима Boost\n" +
                "int outputBoostMinimum = " + unit.getOutputBoostMinimum() + ";";
        strings[14] = "\n" +
                "//задайте коэффициент преобразования для базовой шкалы \"мВ на элемент\" для получения калиброванного напряжения\n" +
                "\n" +
                "double coefficientOfCalibration = " + unit.getCoefficientOfCalibration() +";\n";
        strings[15] = "\n" +
                "//Характеристика шунта\n" +
                "\n" +
                "//Задайте максимальное напряжение шунта {U шунт макс}\n" +
                "int maxVoltShunt = " + unit.getMaxVoltShunt() +";\n";
        strings[16] = "//Задайте максимальный ток шунта {I шунт макс}\n" +
                "int maxCurrentShunt = " + unit.getMaxCurrentShunt() + ";\n";
        strings[17] = "//Введите паспортную ёмкость для марки испольуемых аккумуляторов {C}\n" +
                "int capacitanceOfBattery = " + unit.getCapacitanceOfBattery() + ";\n";
        strings[18] = "//Укажите число параллельных цепочек из аккумуляторов {N}\n" +
                "int numberBattery = "+ unit.getNumberBattery() +";\n";
        strings[19] = "//Введите значение коэффициента передачи аналогового предусилителя {K}\n" +
                "int coeffAnalogueAmplifier = "+ unit.getCoeffAnalogueAmplifier() +";\n";
        strings[20] = "//Задайте ток заряда {S}\n" +
                "double chargingCurrent = "+ unit.getChargingCurrent() +";\n";
        strings[21] = "//Задайте предельный ток заряда {P}\n" +
                "double maxChargCurrent = " + unit.getMaxChargCurrent() + ";\n";
        strings[22] = "//Задайте порог применения ускоренного заряда {B}\n" +
                "double thresholdForBoost = "+ unit.getThresholdForBoost() +";\n";
        strings[23] = "//Задайте условие окончания ускоренного заряда {R}\n" +
                "double thresholdBoostEnding = "+ unit.getThresholdBoostEnding() +";\n";
        strings[24] = "//Задайте максимальное время работы в режиме boost в минутах\n" +
                "byte timeInBoost = " + unit.getTimeInBoost() + ";\n";
        strings[25] = "//Задайте время задержки между повторным включением режима boost в минутах\n" +
                "byte delayBoost = "+ unit.getDelayBoost() +";\n";
        strings[26] = "\n" +
                "// велечина напряжения сброса Ureset  в 10 битах\n" +
                "\n" +
                "int voltageReset = 575;\n";
        strings[27] = "\n" +
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
                "int arraySupport[20];\n" +
                "\n" +
                "//Создаём переменные для выходного сигнала и средней температуры для их использования в функции отображения на экране.\n" +
                "int outputSignal;\n" +
                "int averageTemperature;\n" +
                "int shuntCurrent;\n" +
                "int valueOfCurrent;\n" +
                "int voltageSupport;\n" +
                "int voltageTemperature;\n" +
                "\n" +
                "boolean isUstart = false;\n" +
                "boolean isLastSignalBoost = false;\n" +
                "\n" +
                "//переменные для хранения времени таймеров\n" +
                "unsigned long timerFloatBoost;\n" +
                "unsigned long timerBoost;\n" +
                "unsigned long timerVoltageShunt;\n" +
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
                "  pinMode(INPUT_SUPPORT, INPUT);\n" +
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
                "  timerTemperature = millis();\n" +
                "  timerVoltageSupport = millis();\n" +
                "  timerComparator = millis();\n" +
                "  timerDisplaying = millis();\n" +
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
                "    valueOfCurrent = 2*getMovAverageCurrent(arrayCurrent);\n" +
                "    timerVoltageShunt = millis();\n" +
                "  }\n" +
                "\n" +
                "  //БЛОК 2. получение данных по температуре\n" +
                "  if (!isTimerWork(timerTemperature, 1000)) {\n" +
                "    averageTemperature = getMovAverageTemp(arrayTemp);\n" +
                "    timerVoltageShunt = millis();\n" +
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
                "      if (isTimerWork(timerFloatBoost, delayBoostMillis)) {\n" +
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
                "      }\n" +
                "      //4.4 проверка Ushunt <= Ustop\n" +
                "      else if (valueOfCurrent <= switchBoostToFloatADC) {\n" +
                "        if (isLastSignalBoost) {\n" +
                "          mode = \"4.4 Float\";\n" +
                "          voltageTemperature = outputFloat(averageTemperature);\n" +
                "          timerFloatBoost = millis();\n" +
                "          timerMode = \"15-ти минутный таймер запущен\";\n" +
                "          isUstart = false;\n" +
                "          isLastSignalBoost = false;\n" +
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
                "      //5.2 проверка на Uout>Ut\n" +
                "      else if (outputSignal > voltageTemperature) {\n" +
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

        for (int i = 0; i < strings.length; i++) {
            filePrintStream.println(strings[i]);
        }

        filePrintStream.close();
//        writer.close();
    }


}
