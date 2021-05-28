package configure_ups.model;

import java.util.Map;

public final class Unit {

    private static final Unit unit = new Unit();

    protected int mVAtZeroDeg;
    protected int changingMVPerOneDeg;
    protected int minTempFloat;
    protected int tempFirstMidPointFloat;
    protected int tempSecondMidPointFloat;
    protected int maxTempFloat;
    protected int minTempBoost;
    protected int maxTempBoost;
    protected int outputMaximum;
    protected int outputMiddle;
    protected int outputFloatMinimum;
    protected int outputBoostMinimum;
    protected double coefficientOfCalibration;
    protected int maxVoltShunt;
    protected int maxCurrentShunt;
    protected int capacitanceOfBattery;
    protected int numberBattery;
    protected int coeffAnalogueAmplifier;
    protected double chargingCurrent;
    protected double maxChargCurrent;
    protected double upThresholdCurrent;
    protected double thresholdForBoost;
    protected double thresholdBoostEnding;
    protected int timeInBoost;
    protected int delayBoost;
    

    private Unit(){
        setBasicParams();
    }

    public void setBasicParams(){
        this.setmVAtZeroDeg(500);
        this.setChangingMVPerOneDeg(10);
        this.setMinTempFloat(0);
        this.setTempFirstMidPointFloat(15);
        this.setTempSecondMidPointFloat(35);
        this.setMaxTempFloat(50);
        this.setMaxTempBoost(30);
        this.setMinTempBoost(45);
        this.setOutputMaximum(2400);
        this.setOutputMiddle(2300);
        this.setOutputFloatMinimum(2225);
        this.setOutputBoostMinimum(2300);
        this.setCoefficientOfCalibration(1.108);
        this.setMaxVoltShunt(60);
        this.setMaxCurrentShunt(400);
        this.setCapacitanceOfBattery(75);
        this.setNumberBattery(4);
        this.setCoeffAnalogueAmplifier(25);
        this.setChargingCurrent(2.7);
        this.setMaxChargCurrent(5.0);
        this.setUpThresholdCurrent(3.7);
        this.setThresholdForBoost(0.9);
        this.setThresholdBoostEnding(0.25);
        this.setTimeInBoost(480);
        this.setDelayBoost(15);
    }

    public static Unit getUnit() {
        return unit;
    }

    public synchronized int getmVAtZeroDeg() {
        return mVAtZeroDeg;
    }

    public synchronized void setmVAtZeroDeg(int mVAtZeroDeg) {
        this.mVAtZeroDeg = mVAtZeroDeg;
    }

    public synchronized int getChangingMVPerOneDeg() {
        return changingMVPerOneDeg;
    }

    public synchronized void setChangingMVPerOneDeg(int changingMVPerOneDeg) {
        this.changingMVPerOneDeg = changingMVPerOneDeg;
    }

    public synchronized int getMinTempFloat() {
        return minTempFloat;
    }

    public synchronized void setMinTempFloat(int minTempFloat) {
        this.minTempFloat = minTempFloat;
    }

    public synchronized int getTempFirstMidPointFloat() {
        return tempFirstMidPointFloat;
    }

    public synchronized void setTempFirstMidPointFloat(int tempFirstMidPointFloat) {
        this.tempFirstMidPointFloat = tempFirstMidPointFloat;
    }

    public synchronized int getTempSecondMidPointFloat() {
        return tempSecondMidPointFloat;
    }

    public synchronized void setTempSecondMidPointFloat(int tempSecondMidPointFloat) {
        this.tempSecondMidPointFloat = tempSecondMidPointFloat;
    }

    public synchronized int getMaxTempFloat() {
        return maxTempFloat;
    }

    public synchronized void setMaxTempFloat(int maxTempFloat) {
        this.maxTempFloat = maxTempFloat;
    }

    public synchronized int getMinTempBoost() {
        return minTempBoost;
    }

    public synchronized void setMinTempBoost(int minTempBoost) {
        this.minTempBoost = minTempBoost;
    }

    public synchronized int getMaxTempBoost() {
        return maxTempBoost;
    }

    public synchronized void setMaxTempBoost(int maxTempBoost) {
        this.maxTempBoost = maxTempBoost;
    }

    public synchronized int getOutputMaximum() {
        return outputMaximum;
    }

    public synchronized void setOutputMaximum(int outputMaximum) {
        this.outputMaximum = outputMaximum;
    }

    public synchronized int getOutputMiddle() {
        return outputMiddle;
    }

    public synchronized void setOutputMiddle(int outputMiddle) {
        this.outputMiddle = outputMiddle;
    }

    public synchronized int getOutputFloatMinimum() {
        return outputFloatMinimum;
    }

    public synchronized void setOutputFloatMinimum(int outputFloatMinimum) {
        this.outputFloatMinimum = outputFloatMinimum;
    }

    public synchronized int getOutputBoostMinimum() {
        return outputBoostMinimum;
    }

    public synchronized void setOutputBoostMinimum(int outputBoostMinimum) {
        this.outputBoostMinimum = outputBoostMinimum;
    }

    public synchronized double getCoefficientOfCalibration() {
        return coefficientOfCalibration;
    }

    public synchronized void setCoefficientOfCalibration(double coefficientOfCalibration) {
        this.coefficientOfCalibration = coefficientOfCalibration;
    }

    public synchronized int getMaxVoltShunt() {
        return maxVoltShunt;
    }

    public synchronized void setMaxVoltShunt(int maxVoltShunt) {
        this.maxVoltShunt = maxVoltShunt;
    }

    public synchronized int getMaxCurrentShunt() {
        return maxCurrentShunt;
    }

    public synchronized void setMaxCurrentShunt(int maxCurrentShunt) {
        this.maxCurrentShunt = maxCurrentShunt;
    }

    public synchronized int getCapacitanceOfBattery() {
        return capacitanceOfBattery;
    }

    public synchronized void setCapacitanceOfBattery(int capacitanceOfBattery) {
        this.capacitanceOfBattery = capacitanceOfBattery;
    }

    public synchronized int getNumberBattery() {
        return numberBattery;
    }

    public synchronized void setNumberBattery(int numberBattery) {
        this.numberBattery = numberBattery;
    }

    public synchronized int getCoeffAnalogueAmplifier() {
        return coeffAnalogueAmplifier;
    }

    public synchronized void setCoeffAnalogueAmplifier(int coeffAnalogueAmplifier) {
        this.coeffAnalogueAmplifier = coeffAnalogueAmplifier;
    }

    public synchronized double getChargingCurrent() {
        return chargingCurrent;
    }

    public synchronized void setChargingCurrent(double chargingCurrent) {
        this.chargingCurrent = chargingCurrent;
    }

    public synchronized double getMaxChargCurrent() {
        return maxChargCurrent;
    }

    public synchronized void setMaxChargCurrent(double maxChargCurrent) {
        this.maxChargCurrent = maxChargCurrent;
    }

    public synchronized double getUpThresholdCurrent() {
        return upThresholdCurrent;
    }

    public synchronized void setUpThresholdCurrent(double upThresholdCurrent) {
        this.upThresholdCurrent = upThresholdCurrent;
    }

    public synchronized double getThresholdForBoost() {
        return thresholdForBoost;
    }

    public synchronized void setThresholdForBoost(double thresholdForBoost) {
        this.thresholdForBoost = thresholdForBoost;
    }

    public synchronized double getThresholdBoostEnding() {
        return thresholdBoostEnding;
    }

    public synchronized int getTimeInBoost() {
        return timeInBoost;
    }

    public synchronized void setTimeInBoost(int timeInBoost) {
        this.timeInBoost = timeInBoost;
    }

    public synchronized int getDelayBoost() {
        return delayBoost;
    }

    public synchronized void setDelayBoost(int delayBoost) {
        this.delayBoost = delayBoost;
    }

    public synchronized void setThresholdBoostEnding(double thresholdBoostEnding) {
        this.thresholdBoostEnding = thresholdBoostEnding;
    }

    protected static class UnitBoundaryValues{
        //    Ниже описаны пределы для системы алармов - защиты от некорректного ввода данных

        //    Пределы данных по датчику температуры
//    пределеы значений мВ при нуле
        protected static final int lowBoundMVAtZeroDeg = 400;
        protected static final int upperBoundMVAtZeroDeg = 600;

        //    пределы изменений мВ за один градус
        protected static final int lowBoundMVPerOneDeg = 9;
        protected static final int upperBoundMVPerOneDeg = 11;

        //    Пределы данных заряда по температурно-вольтовой характеристике аккумулятора
//    Пределы значений температуры для режима Float
//    пределы минимальной температуры Float
        protected static final int lowTempFirstPointFloat = -15;
        protected static final int upTempFristPointFloat = 30;

        //    пределы температуры первой средней точки Float
        protected static final int lowTempFirstMidPoint = 15;
        protected static final int upTempFirstMidPoint = 25;

        //    пределы температуры второй средней точки Float
        protected static final int lowTempSecondMidPoint = 20;
        protected static final int upTempSecondMidPoint = 35;

        //    пределы максимальной температуры режима Float
        protected static final int lowTempLastPointFloat = 40;
        protected static final int upTempLastPointFloat = 55;

        //    Пределы значений режима Boost
//    пределы минимальной температуры режима Boost
        protected static final int lowFirstTempBoost = 15;
        protected static final int upFristTempBoost = 35;

        //    пределы максимальной температуры режима Boost
        protected static final int lowLastTempBoost = 35;
        protected static final int upLastTempBoost = 50;

        //    Пределы зарядного напряжения для режимов
//    пределы максимального значения для обоих режимов
        protected static final int lowOutputMaximum = 2350;
        protected static final int upperOutputMaximum = 2450;

        //    пределы напряжения для средней точки режима Float
        protected static final int lowMidPointOutFloat = 2250;
        protected static final int upMidPointOutFloat = 2330;

        //    пределы напряжения для минимального напряжения режима Float
        protected static final int lowMinPointOutFloat = 2130;
        protected static final int upMinPointOutFloat = 2250;

        //    пределы напряжения для минимального напряжения режима Boost
        protected static final int lowMinPointOutBoost = 2215;
        protected static final int upperMinPointOutBoost = 2400;

        //    пределы для коэффициента преобразования для базовой шкалы
        protected static final double lowBoundCoeffCalib = 1.000;
        protected static final double upBoundCoeffCalib = 1.131;

        //    Раздел характеристик шунта
//    пределы для максимального напряжения шунта
        protected static final int lowBoundMaxVoltShunt = 50;
        protected static final int upBoundMaxVoltShunt = 100;

        //    пределы для максимального тока шунта
        protected final int lowBoundMaxCurrentShunt = 100;
        protected static final int upBoundMaxCurrentShunt = 500;

        //    пределы по ёмкости батарей
        protected static final int lowBoundCapacitanceBatt = 10;
        protected static final int upBoundCapacitanceBatt = 300;

        //    пределы по количеству батарей
        protected static final int lowBoundNumberBatt = 1;
        protected static final int upBoundNumberBatt = 10;

        //    пределы по коэффициенту передачи аналогового усилителя
        protected static final int lowBoundCoefAnalogueAmplif = 20;
        protected static final int upBoundCoefAnalogueAmplif = 400;

        //    пределы по зарядному току, множитель относительно номинального
        protected static final double lowBoundChargingCurrent = 1.00;
        protected static final double upBoundChargingCurrent = 3.50;

        //    пределы по максимальному току заряда
        protected static final double lowBoundMaxChCurrent = 2.40;
        protected static final double upBoundMaxChCurrent = 5.01;

//    пределы по верхней границе заряда

        protected static final double lowBoundUpThresholdCurrent = 1.70;
        protected static final double upBoundUpThresholdCurrent = 4.20;

        //    пределы по порогу для применения ускоренного заряда (включение режима Boost)
        protected static final double lowBoundThresholdForBoost = 0.60;
        protected static final double upBoundThresholdForBoost = 1.00;

        //    пределы по порогу для окончания ускоренного заряда (выключение режима Boost)
        protected static final double lowBoundThresholdBoostEnding = 0.1;
        protected static final double upBoundThresholdBoostEnding = 0.6;

        //    пределы по времени работы в режиме Boost в минутах
        protected static final int lowBoundTimeInBoost = 0;
        protected static final int upBoundTimeInBoost = 480;

        //    пределы по времени задержки между повторными включениями режима Boost в минутах
        protected static final int lowBoundDelayBoost = 0;
        protected static final int upBoundDelayBoost = 60;
    }

}
