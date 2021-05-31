package configure_ups.controller;

import configure_ups.MainAppFX;
import configure_ups.model.UnitDataArray;
import configure_ups.model.UnitField;
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

    UnitDataArray unitDataArray = UnitDataArray.getUnitDataArray();

    LinkedHashMap<String, UnitField> unitFields = unitDataArray.getDataArray();

    private boolean isThereIsError = false;

//    Инициализируем все переменные - поля, которые будем использовать в нашем проекте
    @FXML
    private TextField mVAtZeroDeg = new TextField();

    @FXML
    private TextField changingMVPerOneDeg = new TextField();

    @FXML
    private TextField minTempFloat= new TextField();

    @FXML
    private TextField tempFirstMidPointFloat= new TextField();

    @FXML
    private TextField tempSecondMidPointFloat= new TextField();

    @FXML
    private TextField maxTempFloat= new TextField();

    @FXML
    private TextField minTempBoost= new TextField();

    @FXML
    private TextField maxTempBoost= new TextField();

    @FXML
    private TextField outputMaximum= new TextField();

    @FXML
    private TextField outputMiddle= new TextField();

    @FXML
    private TextField outputFloatMinimum= new TextField();

    @FXML
    private TextField outputBoostMinimum= new TextField();

    @FXML
    private TextField coefficientOfCalibration= new TextField();

    @FXML
    private TextField maxVoltShunt= new TextField();

    @FXML
    private TextField maxCurrentShunt= new TextField();

    @FXML
    private TextField capacitanceOfBattery= new TextField();

    @FXML
    private TextField numberBattery= new TextField();

    @FXML
    private TextField coeffAnalogueAmplifier= new TextField();

    @FXML
    private TextField chargingCurrent= new TextField();

    @FXML
    private TextField maxChargCurrent= new TextField();

    @FXML
    private TextField upThresholdCurrent= new TextField();

    @FXML
    private TextField thresholdForBoost= new TextField();

    @FXML
    private TextField thresholdBoostEnding= new TextField();

    @FXML
    private TextField timeInBoost= new TextField();

    @FXML
    private TextField delayBoost= new TextField();

    @FXML
    private Button showGraph = new Button();

    @FXML
    private Button previousField = new Button();

    @FXML
    private Button clear = new Button();

    @FXML
    private Button write = new Button();

    private MainAppFX main;

    public void setTooltip(){
       Arrays.stream(Controller.class.getDeclaredFields()).filter(x -> {
           try {
               return x.get(this).getClass().equals(TextField.class);
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           }
           return false;
       }).forEach(x -> {
                x.setAccessible(true);
           try {
               if(x.get(this).getClass().equals(TextField.class)){
                   TextField textField = (TextField) x.get(this);
                   UnitField unitField = unitFields.get(x.getName());
                   if(x.getName().equals("coefficientOfCalibration")){
                       textField.setTooltip(new Tooltip(String.format(Locale.CANADA,"введите значение от %.3f до %.3f", unitField.getLowBoundValue(), unitField.getHighBoundValue())));
                   } else if(unitField.getValue() instanceof Double){
                       textField.setTooltip(new Tooltip(String.format(Locale.CANADA,"введите значние от %.2f до %.2f", unitField.getLowBoundValue(), unitField.getHighBoundValue())));
                   } else
                   textField.setTooltip(new Tooltip(String.format("введите значение от %d до %d", unitField.getLowBoundValue(), unitField.getHighBoundValue())));
       }
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           }});

//Кнопка возврата предыдущих значений
        previousField.setTooltip(new Tooltip("Нажав на кнопку, Вы вернёте первоначальные значения"));
//Кнопка полной очистки всех полей
        clear.setTooltip(new Tooltip("Нажав на кнопку, Вы полностью очистите поля ввода значений"));
//Кнопка создания скетча для загрузки
        write.setTooltip(new Tooltip("Нажав на кнопку, Вы создадите скетч, который потом можно загрузить в Arduino"));
    }

    public void setMain(MainAppFX main){
        this.main=main;
    }

    public void handleDelete(){
//        Нажатие на кнопку Удалить
        Arrays.stream(Controller.class.getDeclaredFields()).forEach(x ->
        {
            try {
                if (x.get(this).getClass().equals(TextField.class)) {
                    TextField textField = (TextField) x.get(this);
                    textField.setText("");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        unitFields.entrySet().stream().forEach(x -> {
            x.getValue().setValue(0);
        });
    }

    public void setBasicParams(){
        unitDataArray.setBasicParams();
        Arrays.stream(Controller.class.getDeclaredFields()).forEach(x ->
        {
            try {
                if(x.get(this).getClass().equals(TextField.class)){
                    TextField textField = (TextField) x.get(this);
                    Number value = unitFields.get(x.getName()).getValue();
                    textField.setText(String.valueOf(value));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
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
            Arrays.stream(Controller.class.getDeclaredFields()).forEach(x ->
            {
                try {
                    if(x.get(this).getClass().equals(TextField.class)){
                        TextField textField = (TextField) x.get(this);
                        String value = textField.getText();
                        if(value.contains(".") || value.contains(",")){
                            if(value.contains(",")){
                                value = value.replaceAll("," , ".");
                            }
                            unitFields.get(x.getName()).setValue(Double.parseDouble(value));
                        }else unitFields.get(x.getName()).setValue(Integer.parseInt(value));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
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
                    "int millivoltAtZeroDegrees = " + unitFields.get("mVAtZeroDeg").getValue() + ";";
            strings[2] = "int changingMillivoltPerOneDegrees = " + unitFields.get("changingMVPerOneDeg").getValue() + ";";
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
                    "int minTempFloatDeg = " + unitFields.get("minTempFloat").getValue() + ";";
            strings[5] = "//задайте первую точку в градусах Цельсия, при которой не изменяется напряжение\n" +
                    "int tempFirstMidPointFloat = " + unitFields.get("tempFirstMidPointFloat").getValue() + ";";
            strings[6] = "//Задайте вторую точку при которой температура не изменяется\n" +
                    "int tempSecondMidFloatDeg = " + unitFields.get("tempSecondMidPointFloat").getValue() + ";";
            strings[7] = "//Задайте максимальную температуру в градусах Цельсия, после которой напряжение заряда не изменяется\n" +
                    "int maxTempFloatDeg = " + unitFields.get("maxTempFloat").getValue() + ";";
            strings[8] = "\n" +
                    "//Режим Boost\n" +
                    "// задайте минимальную температуру в градусах Цельсия, ниже которой напряжение заряда не изменяется\n" +
                    "int minTempBoostDeg = " + unitFields.get("minTempBoost").getValue() + ";";
            strings[9] = "// задайте максимальную температуру, после которой происходит переход к графику Float\n" +
                    "int maxTempBoostDeg = " + unitFields.get("maxTempBoost").getValue() + ";";
            strings[10] = "\n" +
                    "//задайте максимальное значение выходного напряжения в милливольтах для Boost и Float\n" +
                    "int outputMaximum = " + unitFields.get("outputMaximum").getValue() + ";";
            strings[11] = "// задайте уровень напряжения средних температурных точек в милливольтах для режима Float\n" +
                    "int outputMiddleFloat = " + unitFields.get("outputMiddle").getValue() + ";";
            strings[12] = "//задайте значение минимального выходного напряжения в милливольтах для режима Float\n" +
                    "int  outputFloatMinimum = " + unitFields.get("outputFloatMinimum").getValue() + ";";
            strings[13] = "//задайте значение минимального выходного напряжения в милливольтах для режима Boost\n" +
                    "int outputBoostMinimum = " + unitFields.get("outputBoostMinimum").getValue() + ";";
            strings[14] = "\n" +
                    "//задайте коэффициент преобразования для базовой шкалы \"мВ на элемент\" для получения калиброванного напряжения\n" +
                    "\n" +
                    "double coefficientOfCalibration = " + unitFields.get("coefficientOfCalibration").getValue() + ";\n";
            strings[15] = "\n" +
                    "//Характеристика шунта\n" +
                    "\n" +
                    "//Задайте максимальное напряжение шунта {U шунт макс}\n" +
                    "int maxVoltShunt = " + unitFields.get("maxVoltShunt").getValue() + ";\n";
            strings[16] = "//Задайте максимальный ток шунта {I шунт макс}\n" +
                    "int maxCurrentShunt = " + unitFields.get("maxCurrentShunt").getValue() + ";\n";
            strings[17] = "//Введите паспортную ёмкость для марки испольуемых аккумуляторов {C}\n" +
                    "int capacitanceOfBattery = " + unitFields.get("capacitanceOfBattery").getValue() + ";\n";
            strings[18] = "//Укажите число параллельных цепочек из аккумуляторов {N}\n" +
                    "int numberBattery = " + unitFields.get("numberBattery").getValue() + ";\n";
            strings[19] = "//Введите значение коэффициента передачи аналогового предусилителя {K}\n" +
                    "int coeffAnalogueAmplifier = " + unitFields.get("coeffAnalogueAmplifier").getValue() + ";\n";
            strings[20] = "//Задайте ток заряда {S}\n" +
                    "double chargingCurrent = " + unitFields.get("chargingCurrent").getValue() + ";\n";
            strings[21] = "//Задайте предельный ток заряда {P}\n" +
                    "double maxChargCurrent = " + unitFields.get("maxChargCurrent").getValue() + ";\n";
            strings[22] = "//Задайте верхний порог ограничения тока {U reduce}\n" +
                    "double upThresholdCurrent = " + unitFields.get("upThresholdCurrent").getValue() +";\n";
            strings[23] = "//Задайте порог применения ускоренного заряда {B}\n" +
                    "double thresholdForBoost = " + unitFields.get("thresholdForBoost").getValue() + ";\n";
            strings[24] = "//Задайте условие окончания ускоренного заряда {R}\n" +
                    "double thresholdBoostEnding = " + unitFields.get("thresholdBoostEnding").getValue() + ";\n";
            strings[25] = "//Задайте максимальное время работы в режиме boost в минутах\n" +
                    "byte timeInBoost = " + unitFields.get("timeInBoost").getValue() + ";\n";
            strings[26] = "//Задайте время задержки между повторным включением режима boost в минутах\n" +
                    "byte delayBoost = " + unitFields.get("delayBoost").getValue() + ";\n";
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
 //         Проверка на вхождение значения в диапазон границ
        String title = "ошибка";
        for (UnitField x : unitFields.values()) {
            if(x.getLowBoundValue() instanceof Double){
                Double value = Double.valueOf(x.getValue().toString());
                Double lowBoundValue = Double.valueOf(x.getLowBoundValue().toString());
                Double highBoundValue = Double.valueOf(x.getHighBoundValue().toString());
                if (value < lowBoundValue || value > highBoundValue) {
                    String header = "Неправильный ввод данных";
                    String content;
                    if(x.getName().equals("coefficientOfCalibration")){
                        content = String.format(Locale.CANADA, "Введите значние от %.3f до %.3f в пункте %.1f", lowBoundValue, highBoundValue, x.getPosition());
                    }else{
                        content = String.format(Locale.CANADA, "Введите значние от %.2f до %.2f в пункте %.1f", lowBoundValue, highBoundValue, x.getPosition());
                    }
                    showAlert(title, header, content);
                    return true;
                }
            } else {
                Integer value = (Integer) x.getValue();
                Integer lowBoundValue = (Integer) x.getLowBoundValue();
                Integer highBoundValue = (Integer) x.getHighBoundValue();
                if (value < lowBoundValue || value > highBoundValue) {
                    String header = "Неправильный ввод данных";
                    String content = String.format(Locale.CANADA, "Введите значение от %d до %d в пункте %.1f", lowBoundValue, highBoundValue, x.getPosition());
                    showAlert(title, header, content);
                    return true;
                }
            }
        }

//        Проверка значений больше-меньше для температурной характеристики в режиме Флоат
        int minTempFloat = (int) unitFields.get("minTempFloat").getValue();
        int tempFirstMidPointFloat = (int) unitFields.get("tempFirstMidPointFloat").getValue();
        int tempSecondMidPointFloat = (int) unitFields.get("tempSecondMidPointFloat").getValue();
        int maxTempFloat = (int) unitFields.get("maxTempFloat").getValue();
        int minTempBoost = (int) unitFields.get("minTempBoost").getValue();
        int maxTempBoost = (int) unitFields.get("maxTempBoost").getValue();
        int outputMaximum = (int) unitFields.get("outputMaximum").getValue();
        int outputMiddle = (int) unitFields.get("outputMiddle").getValue();
        int outputFloatMinimum = (int) unitFields.get("outputFloatMinimum").getValue();
        int outputBoostMinimum = (int) unitFields.get("outputBoostMinimum").getValue();
        double chargingCurrent = (double) unitFields.get("chargingCurrent").getValue();
        double maxChargCurrent = (double) unitFields.get("maxChargCurrent").getValue();

        if(minTempFloat > tempFirstMidPointFloat){
            String header = "Неправильный ввод данных по температуре в режиме Float";
            String content = "Температура в пункте 2.1 должна быть меньше, чем в пункте 2.2";
            showAlert(title, header, content);
            return true;
        }

        if(tempFirstMidPointFloat > tempSecondMidPointFloat){
            String header = "Неправильный ввод данных по температуре в режиме Float";
            String content = "Температура в пункте 2.2 должна быть меньше, чем в пункте 2.3";
            showAlert(title, header, content);
            return true;
        }

        if(tempSecondMidPointFloat > maxTempFloat){
            String header = "Неправильный ввод данных по температуре в режиме Float";
            String content = "Температура в пункте 2.3 должна быть меньше, чем в пункте 2.4";
            showAlert(title, header, content);
            return true;
        }

//        Проверка значений больше-меньше по температурам для режима Boost

        if(minTempBoost > maxTempBoost){
            String header = "Неправильный ввод данных по температуре в режиме Boost";
            String content = "Температура в пункте 2.5 должна быть меньше, чем в пункте 2.6";
            showAlert(title, header, content);
            return true;
        }

//        Проверка значений больше-меньше по значению зарядного напряжения

        if(outputMaximum < outputMiddle){
            String header = "Неправильный ввод данных по выходному сигналу";
            String content = "Напряжение в пункте 3.1  должно быть больше, чем в пункте 3.2";
            showAlert(title, header, content);
            return true;
        }

        if(outputMaximum < outputBoostMinimum) {
            String header = "Неправильный ввод данных по выходному сигналу";
            String content = "Напряжение в пункте 3.1  должно быть больше, чем в пункте 3.4";
            showAlert(title, header, content);
            return true;
        }

        if(outputMiddle < outputFloatMinimum){
            String header = "Неправильный ввод данных по выходному сигналу";
            String content = "Напряжение в пункте 3.2  должно быть больше, чем в пункте 3.3";
            showAlert(title, header, content);
            return true;
        }

//        Проверка больше-меньше по токам заряда
        if(maxChargCurrent < chargingCurrent){
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