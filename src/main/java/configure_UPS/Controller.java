package configure_UPS;

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
    private TextField outputMinimum;

    @FXML
    private TextField valueOfNominalCurrentVolt;

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

        unit.setMVAtZeroDeg(0);
        unit.setChangingMVPerOneDeg(0);
        unit.setMinTempFloat(0);
        unit.setTempFirstMidPointFloat(0);
        unit.setTempSecondMidPointFloat(0);
        unit.setMaxTempFloat(0);
        unit.setMaxTempBoost(0);
        unit.setMinTempBoost(0);
        unit.setOutputMaximum(0);
        unit.setOutputMiddle(0);
        unit.setOutputMinimum(0);
        unit.setValueOfNominalCurrentVolt(0.0);

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
        outputMinimum.setText("");
        valueOfNominalCurrentVolt.setText("");
    }

    public void setBasicParams(){
        unit.setMVAtZeroDeg(500);
        unit.setChangingMVPerOneDeg(10);
        unit.setMinTempFloat(0);
        unit.setTempFirstMidPointFloat(15);
        unit.setTempSecondMidPointFloat(35);
        unit.setMaxTempFloat(50);
        unit.setMaxTempBoost(30);
        unit.setMinTempBoost(45);
        unit.setOutputMaximum(2660);
        unit.setOutputMiddle(2550);
        unit.setOutputMinimum(2468);
        unit.setValueOfNominalCurrentVolt(30.0);

        mVAtZeroDeg.setText(String.valueOf(unit.getMVAtZeroDeg()));
        changingMVPerOneDeg.setText(String.valueOf(unit.getChangingMVPerOneDeg()));
        minTempFloat.setText(String.valueOf(unit.getMinTempFloat()));
        tempFirstMidPointFloat.setText(String.valueOf(unit.getTempFirstMidPointFloat()));
        tempSecondMidPointFloat.setText(String.valueOf(unit.getTempSecondMidPointFloat()));
        maxTempFloat.setText(String.valueOf(unit.getMaxTempFloat()));
        minTempBoost.setText(String.valueOf(unit.getMaxTempBoost()));
        maxTempBoost.setText(String.valueOf(unit.getMinTempBoost()));
        outputMaximum.setText(String.valueOf(unit.getOutputMaximum()));
        outputMiddle.setText(String.valueOf(unit.getOutputMiddle()));
        outputMinimum.setText(String.valueOf(unit.getOutputMinimum()));
        valueOfNominalCurrentVolt.setText(String.valueOf(unit.getValueOfNominalCurrentVolt()));

    }
    public void setWriteParams() throws IOException {

        unit.setMVAtZeroDeg(Integer.parseInt(mVAtZeroDeg.getText()));
        unit.setChangingMVPerOneDeg(Integer.parseInt(changingMVPerOneDeg.getText()));
        unit.setMinTempFloat(Integer.parseInt(minTempFloat.getText()));
        unit.setTempFirstMidPointFloat(Integer.parseInt(tempFirstMidPointFloat.getText()));
        unit.setTempSecondMidPointFloat(Integer.parseInt(tempSecondMidPointFloat.getText()));
        unit.setMaxTempFloat(Integer.parseInt(maxTempFloat.getText()));
        unit.setMinTempBoost(Integer.parseInt(minTempBoost.getText()));
        unit.setMaxTempBoost(Integer.parseInt(maxTempBoost.getText()));
        unit.setOutputMaximum(Integer.parseInt(outputMaximum.getText()));
        unit.setOutputMiddle(Integer.parseInt(outputMiddle.getText()));
        unit.setOutputMinimum(Integer.parseInt(outputMinimum.getText()));
        unit.setValueOfNominalCurrentVolt(Double.parseDouble(valueOfNominalCurrentVolt.getText()));

        //    BufferedWriter writer = new BufferedWriter(new FileWriter("/home/kirill/test/UPSTuborTc_Boost/UPSTuborTc_Boost.ino"));

        PrintStream filePrintStream = new PrintStream(new File("configure_UPS.ino"));

        String[] strings = new String[15];
        strings[0]= "#define OUTPUT_SIGNAL A0\n" +
                "#define INPUT_TEMP A2\n" +
                "#define INPUT_SHUNT A4";
        strings[1] = "int millivoltAtZeroDegrees = " + unit.getMVAtZeroDeg() + ";";
        strings[2] = "int changingMillivoltPerOneDegrees = " + unit.getChangingMVPerOneDeg() + ";";
        strings[3] = "double accuracyInput = 1.611;\n" +
                "double accuracyOutput = 3.223;";
        strings[4] = "int minTempFloatDeg = " + unit.getMinTempFloat() + ";";
        strings[5] = "int tempFirstMidPointFloat = " + unit.getTempFirstMidPointFloat() + ";";
        strings[6] = "int tempSecondMidFloatDeg = " + unit.getTempSecondMidPointFloat() + ";";
        strings[7] = "int maxTempFloatDeg = " + unit.getMaxTempFloat() +";";
        strings[8] = "int minTempBoostDeg = " + unit.getMinTempBoost() + ";";
        strings[9] = "int maxTempBoostDeg = " + unit.getMaxTempBoost() + ";";
        strings[10] = "int outputMaximum = " + unit.getOutputMaximum() + ";";
        strings[11] = "int outputMiddleFloat = " + unit.getOutputMiddle() + ";";
        strings[12] = "int  outputMinimum = " + unit.getOutputMinimum() + ";";
        strings[13] = "double valueOfNominalCurrentOnVoltage = " + unit.getValueOfNominalCurrentVolt() + ";";
        strings[14] = "//Вычисляемые значения\n" +
                "\n" +
                "//Вычисляем значение показаний шунта для 10-битного АЦП, пока не округляя\n" +
                "\n" +
                "double nominalCurrentADC = valueOfNominalCurrentOnVoltage/accuracyInput;\n" +
                "\n" +
                "//Вычисляем пороговое значение, при котором происходит переключения с функции boost на Термокомпенсацию\n" +
                "\n" +
                "int switchBoostTcThresholdValue = nominalCurrentADC/2;\n" +
                "\n" +
                "//Вычисляем температуры для 10-битного АЦП\n" +
                "\n" +
                "//Вычисление минимальной температуры графика Float для 10-битного АЦП\n" +
                "int minTempFloatADC = (millivoltAtZeroDegrees + minTempFloatDeg*changingMillivoltPerOneDegrees)/accuracyInput;\n" +
                "// вычисляем первую среднюю точку для 10-битного АЦП\n" +
                "int tempFirstPointFloatADC = (millivoltAtZeroDegrees + tempFirstMidPointFloat*changingMillivoltPerOneDegrees)/accuracyInput;\n" +
                "//вычисляем вторую среднюю точку для 10-битного АЦП\n" +
                "int tempSecondPointFloatADC = (millivoltAtZeroDegrees + tempSecondMidFloatDeg*changingMillivoltPerOneDegrees)/accuracyInput;\n" +
                "//вычисляем максимальну температуру для 10-битного АЦП\n" +
                "int maxTempFloatADC= (millivoltAtZeroDegrees + maxTempFloatDeg*changingMillivoltPerOneDegrees)/accuracyInput;\n" +
                "\n" +
                "//Вычисление минимальной температуры графика Boost для 10-битного АЦП\n" +
                "int minTempBoostADC = (millivoltAtZeroDegrees + minTempBoostDeg*changingMillivoltPerOneDegrees)/accuracyInput;\n" +
                "//Вычисление максимальной температуры графика Boost после которого происходит переход на Float для 10 битного АЦП\n" +
                "int maxTempBoostADC = (millivoltAtZeroDegrees + maxTempBoostDeg*changingMillivoltPerOneDegrees)/accuracyInput;\n" +
                "\n" +
                "//Вычисляем значения выходного напряжения управления для 10 битного ЦАП\n" +
                "\n" +
                "//вычисляем значение выходного напряжения для 10-битного ЦАП, увеличиваем на единицу, т.к. в данной версии для Тубор это необходимо\n" +
                "//из-за грубого округления при отбрасывании десятых долей\n" +
                "int outputMaxDAC = outputMaximum/accuracyOutput + 1;\n" +
                "//вычисляем значение выходного напряжение средних точек Float для 10-битного ЦАП\n" +
                "int outputMidFloatDAC = outputMiddleFloat/accuracyOutput;\n" +
                "//вычисляем значение выходно напряжения макисмальной точки для Boost, но в данном случае оно равно значению средних точек Float\n" +
                "int outputMaxBoostDAC = outputMidFloatDAC;\n" +
                "//вычисляем значение выходного напряжения при максимальной температуре для 10-битного ЦАП\n" +
                "int outputMinDAC = outputMinimum/accuracyOutput;\n" +
                "\n" +
                "//Перменные\n" +
                "\n" +
                "//Созадём массив на 10 точек для скользящей средней по температуре без инициализации\n" +
                "int arrayTemp[10];\n" +
                "\n" +
                "//Создаём переменные для выходного сигнала и средней температуры для их использования в функции отображения на экране.\n" +
                "int outputSignal;\n" +
                "int averageTemperature;\n" +
                "int shuntCurrent;\n" +
                "\n" +
                "void setup() {\n" +
                "  // put your setup code here, to run once:\n" +
                "  pinMode(INPUT_TEMP, INPUT);\n" +
                "  pinMode(INPUT_SHUNT, INPUT);\n" +
                "  analogReference(AR_INTERNAL1V65);\n" +
                "  analogWriteResolution(10);\n" +
                "  analogReadResolution(10);\n" +
                "  //first initialisation for array for Moving average\n" +
                "  \n" +
                "  for (int i=0; i<10; i++)\n" +
                "  {\n" +
                "  arrayTemp[i] = analogRead(INPUT_TEMP);\n" +
                "  delay(100);\n" +
                "  }\n" +
                "  outputSignal = outputMidFloatDAC;\n" +
                "   Serial.begin(9600);\n" +
                "}\n" +
                "\n" +
                "\n" +
                "void loop() {\n" +
                "\n" +
                "  \n" +
                "  averageTemperature = getMovingAverageTen(arrayTemp);\n" +
                "  int valueOfCurrent = analogRead(INPUT_SHUNT);\n" +
                "  if(valueOfCurrent < switchBoostTcThresholdValue){\n" +
                "    outputSignal = outputFloat(averageTemperature);\n" +
                "  }\n" +
                "  else {\n" +
                "    if(outputSignal < outputBoost(averageTemperature) - 10) {\n" +
                "      int numberOfSteps = (outputBoost(averageTemperature)-outputSignal)/3;\n" +
                "      for(int i=0; i<numberOfSteps; i++) {\n" +
                "        outputSignal += 3;\n" +
                "        analogWrite (OUTPUT_SIGNAL, outputSignal);\n" +
                "        delay(1000);      \n" +
                "      }\n" +
                "    }\n" +
                "    else \n" +
                "    outputSignal = outputBoost(averageTemperature);\n" +
                "  }\n" +
                "  \n" +
                "  displayingDataTemp (); \n" +
                "  \n" +
                "  analogWrite (OUTPUT_SIGNAL, outputSignal);\n" +
                "  \n" +
                "  delay(1000);\n" +
                "\n" +
                "}\n" +
                "\n" +
                "int outputFloat(int tempLevel){\n" +
                "   int outputSignalFloat;\n" +
                "   if(tempLevel <= minTempFloatADC){\n" +
                "    outputSignalFloat = outputMaxDAC;\n" +
                "  } \n" +
                "  else if (tempLevel > minTempFloatADC && tempLevel <= tempFirstPointFloatADC){\n" +
                "    outputSignalFloat = map(tempLevel, minTempFloatADC, tempFirstPointFloatADC, outputMaxDAC, outputMidFloatDAC); \n" +
                "  }\n" +
                "  else if (tempLevel > tempFirstPointFloatADC && tempLevel <= tempSecondPointFloatADC) {\n" +
                "    outputSignalFloat = outputMidFloatDAC;\n" +
                "  }\n" +
                "  else if (tempLevel > tempSecondPointFloatADC && tempLevel <= maxTempFloatADC){\n" +
                "    outputSignalFloat = map(tempLevel, tempSecondPointFloatADC, maxTempFloatADC, outputMidFloatDAC, outputMinDAC);\n" +
                "  }\n" +
                "  else if (tempLevel > maxTempFloatADC){\n" +
                "    outputSignalFloat = outputMinDAC;\n" +
                "  }\n" +
                "  return outputSignalFloat;\n" +
                "  \n" +
                "}\n" +
                "\n" +
                "int outputBoost(int tempLevel){\n" +
                "   int outputSignalBoost;\n" +
                "   if(tempLevel <= minTempBoostADC) {\n" +
                "    outputSignalBoost = outputMaxDAC;\n" +
                "   }\n" +
                "   else if(tempLevel > minTempBoostADC && tempLevel <= maxTempBoostADC) {\n" +
                "     outputSignalBoost = map (tempLevel, minTempBoostADC, maxTempBoostADC, outputMaxDAC, outputMaxBoostDAC);    \n" +
                "   }\n" +
                "   else outputSignalBoost = outputFloat (tempLevel);\n" +
                "   \n" +
                "   return outputSignalBoost;\n" +
                "}\n" +
                "\n" +
                "int getMovingAverageTen (int arrayTemp[10]) {\n" +
                "\n" +
                "  for(byte j = 0; j < 9; j++){\n" +
                "    arrayTemp[j] = arrayTemp[j+1];\n" +
                "  }\n" +
                "\n" +
                "  arrayTemp[9] = analogRead(INPUT_TEMP);\n" +
                "\n" +
                "  int sum = 0;\n" +
                "  for(byte i = 0; i < 10 ; i++){\n" +
                "    sum += arrayTemp[i];\n" +
                "   }\n" +
                "  \n" +
                "  return sum/10;\n" +
                "}\n" +
                "\n" +
                "void displayingDataTemp () {\n" +
                "  \n" +
                "  Serial.print(\"Output signal: \");\n" +
                "  Serial.println(outputSignal);\n" +
                "  \n" +
                "  Serial.print(\"Выходной сигнал в миллиВольтах:\");\n" +
                "  Serial.println(outputSignal*accuracyOutput);\n" +
                "  Serial.print(\"Temperature in 10 bit: \");\n" +
                "  Serial.println(averageTemperature);\n" +
                "  double tempDeg = (averageTemperature*1650.0/1023.0-500.0)/10.0;\n" +
                "  Serial.print(\"Temperature in grad C: \");\n" +
                "  Serial.println(tempDeg);\n" +
                "\n" +
                "  \n" +
                "  /*\n" +
                "  for(int i = 0 ; i < 10 ; i ++) {\n" +
                "  Serial.print(\"элемент массива: \");\n" +
                "  Serial.println(i);\n" +
                "  Serial.println(arrayTemp[i]);\n" +
                "  }\n" +
                "  */\n" +
                "  Serial.println(\" \");\n" +
                "}";

        for (int i = 0; i < strings.length; i++) {
            filePrintStream.println(strings[i]);
        }

        filePrintStream.close();
//        writer.close();
    }


}
