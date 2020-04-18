#define OUTPUT_SIGNAL A0
#define INPUT_TEMP A2
#define INPUT_SHUNT A4
int millivoltAtZeroDegrees = 500;
int changingMillivoltPerOneDegrees = 10;
double accuracyInput = 1.611;
double accuracyOutput = 3.223;
int minTempFloatDeg = 0;
int tempFirstMidPointFloat = 15;
int tempSecondMidFloatDeg = 35;
int maxTempFloatDeg = 50;
int minTempBoostDeg = 30;
int maxTempBoostDeg = 45;
int outputMaximum = 2660;
int outputMiddleFloat = 2550;
int  outputMinimum = 2468;
double valueOfNominalCurrentOnVoltage = 30.0;
//Вычисляемые значения

//Вычисляем значение показаний шунта для 10-битного АЦП, пока не округляя

double nominalCurrentADC = valueOfNominalCurrentOnVoltage/accuracyInput;

//Вычисляем пороговое значение, при котором происходит переключения с функции boost на Термокомпенсацию

int switchBoostTcThresholdValue = nominalCurrentADC/2;

//Вычисляем температуры для 10-битного АЦП

//Вычисление минимальной температуры графика Float для 10-битного АЦП
int minTempFloatADC = (millivoltAtZeroDegrees + minTempFloatDeg*changingMillivoltPerOneDegrees)/accuracyInput;
// вычисляем первую среднюю точку для 10-битного АЦП
int tempFirstPointFloatADC = (millivoltAtZeroDegrees + tempFirstMidPointFloat*changingMillivoltPerOneDegrees)/accuracyInput;
//вычисляем вторую среднюю точку для 10-битного АЦП
int tempSecondPointFloatADC = (millivoltAtZeroDegrees + tempSecondMidFloatDeg*changingMillivoltPerOneDegrees)/accuracyInput;
//вычисляем максимальну температуру для 10-битного АЦП
int maxTempFloatADC= (millivoltAtZeroDegrees + maxTempFloatDeg*changingMillivoltPerOneDegrees)/accuracyInput;

//Вычисление минимальной температуры графика Boost для 10-битного АЦП
int minTempBoostADC = (millivoltAtZeroDegrees + minTempBoostDeg*changingMillivoltPerOneDegrees)/accuracyInput;
//Вычисление максимальной температуры графика Boost после которого происходит переход на Float для 10 битного АЦП
int maxTempBoostADC = (millivoltAtZeroDegrees + maxTempBoostDeg*changingMillivoltPerOneDegrees)/accuracyInput;

//Вычисляем значения выходного напряжения управления для 10 битного ЦАП

//вычисляем значение выходного напряжения для 10-битного ЦАП, увеличиваем на единицу, т.к. в данной версии для Тубор это необходимо
//из-за грубого округления при отбрасывании десятых долей
int outputMaxDAC = outputMaximum/accuracyOutput + 1;
//вычисляем значение выходного напряжение средних точек Float для 10-битного ЦАП
int outputMidFloatDAC = outputMiddleFloat/accuracyOutput;
//вычисляем значение выходно напряжения макисмальной точки для Boost, но в данном случае оно равно значению средних точек Float
int outputMaxBoostDAC = outputMidFloatDAC;
//вычисляем значение выходного напряжения при максимальной температуре для 10-битного ЦАП
int outputMinDAC = outputMinimum/accuracyOutput;

//Перменные

//Созадём массив на 10 точек для скользящей средней по температуре без инициализации
int arrayTemp[10];

//Создаём переменные для выходного сигнала и средней температуры для их использования в функции отображения на экране.
int outputSignal;
int averageTemperature;
int shuntCurrent;

void setup() {
  // put your setup code here, to run once:
  pinMode(INPUT_TEMP, INPUT);
  pinMode(INPUT_SHUNT, INPUT);
  analogReference(AR_INTERNAL1V65);
  analogWriteResolution(10);
  analogReadResolution(10);
  //first initialisation for array for Moving average
  
  for (int i=0; i<10; i++)
  {
  arrayTemp[i] = analogRead(INPUT_TEMP);
  delay(100);
  }
  outputSignal = outputMidFloatDAC;
   Serial.begin(9600);
}


void loop() {

  
  averageTemperature = getMovingAverageTen(arrayTemp);
  int valueOfCurrent = analogRead(INPUT_SHUNT);
  if(valueOfCurrent < switchBoostTcThresholdValue){
    outputSignal = outputFloat(averageTemperature);
  }
  else {
    if(outputSignal < outputBoost(averageTemperature) - 10) {
      int numberOfSteps = (outputBoost(averageTemperature)-outputSignal)/3;
      for(int i=0; i<numberOfSteps; i++) {
        outputSignal += 3;
        analogWrite (OUTPUT_SIGNAL, outputSignal);
        delay(1000);      
      }
    }
    else 
    outputSignal = outputBoost(averageTemperature);
  }
  
  displayingDataTemp (); 
  
  analogWrite (OUTPUT_SIGNAL, outputSignal);
  
  delay(1000);

}

int outputFloat(int tempLevel){
   int outputSignalFloat;
   if(tempLevel <= minTempFloatADC){
    outputSignalFloat = outputMaxDAC;
  } 
  else if (tempLevel > minTempFloatADC && tempLevel <= tempFirstPointFloatADC){
    outputSignalFloat = map(tempLevel, minTempFloatADC, tempFirstPointFloatADC, outputMaxDAC, outputMidFloatDAC); 
  }
  else if (tempLevel > tempFirstPointFloatADC && tempLevel <= tempSecondPointFloatADC) {
    outputSignalFloat = outputMidFloatDAC;
  }
  else if (tempLevel > tempSecondPointFloatADC && tempLevel <= maxTempFloatADC){
    outputSignalFloat = map(tempLevel, tempSecondPointFloatADC, maxTempFloatADC, outputMidFloatDAC, outputMinDAC);
  }
  else if (tempLevel > maxTempFloatADC){
    outputSignalFloat = outputMinDAC;
  }
  return outputSignalFloat;
  
}

int outputBoost(int tempLevel){
   int outputSignalBoost;
   if(tempLevel <= minTempBoostADC) {
    outputSignalBoost = outputMaxDAC;
   }
   else if(tempLevel > minTempBoostADC && tempLevel <= maxTempBoostADC) {
     outputSignalBoost = map (tempLevel, minTempBoostADC, maxTempBoostADC, outputMaxDAC, outputMaxBoostDAC);    
   }
   else outputSignalBoost = outputFloat (tempLevel);
   
   return outputSignalBoost;
}

int getMovingAverageTen (int arrayTemp[10]) {

  for(byte j = 0; j < 9; j++){
    arrayTemp[j] = arrayTemp[j+1];
  }

  arrayTemp[9] = analogRead(INPUT_TEMP);

  int sum = 0;
  for(byte i = 0; i < 10 ; i++){
    sum += arrayTemp[i];
   }
  
  return sum/10;
}

void displayingDataTemp () {
  
  Serial.print("Output signal: ");
  Serial.println(outputSignal);
  
  Serial.print("Выходной сигнал в миллиВольтах:");
  Serial.println(outputSignal*accuracyOutput);
  Serial.print("Temperature in 10 bit: ");
  Serial.println(averageTemperature);
  double tempDeg = (averageTemperature*1650.0/1023.0-500.0)/10.0;
  Serial.print("Temperature in grad C: ");
  Serial.println(tempDeg);

  
  /*
  for(int i = 0 ; i < 10 ; i ++) {
  Serial.print("элемент массива: ");
  Serial.println(i);
  Serial.println(arrayTemp[i]);
  }
  */
  Serial.println(" ");
}
