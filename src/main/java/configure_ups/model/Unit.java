package configure_ups.model;

public class Unit {

    private static int mVAtZeroDeg;
    private static int changingMVPerOneDeg;
    private static int minTempFloat;
    private static int tempFirstMidPointFloat;
    private static int tempSecondMidPointFloat;
    private static int maxTempFloat;
    private static int minTempBoost;
    private static int maxTempBoost;
    private static int outputMaximum;
    private static int outputMiddle;
    private static int outputFloatMinimum;
    private static int outputBoostMinimum;
    private static double coefficientOfCalibration;
    private static int maxVoltShunt;
    private static int maxCurrentShunt;
    private static int capacitanceOfBattery;
    private static int numberBattery;
    private static int coeffAnalogueAmplifier;
    private static double chargingCurrent;
    private static double maxChargCurrent;
    private static double thresholdForBoost;
    private static double thresholdBoostEnding;
    private static int timeInBoost;
    private static int delayBoost;
    

    public Unit(){

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
        this.setChargingCurrent(2.8);
        this.setMaxChargCurrent(3.5);
        this.setThresholdForBoost(0.9);
        this.setThresholdBoostEnding(0.25);
        this.setTimeInBoost(480);
        this.setDelayBoost(15);

    }

    public synchronized int getmVAtZeroDeg() {
        return mVAtZeroDeg;
    }

    public synchronized void setmVAtZeroDeg(int mVAtZeroDeg) {
        Unit.mVAtZeroDeg = mVAtZeroDeg;
    }

    public synchronized int getChangingMVPerOneDeg() {
        return changingMVPerOneDeg;
    }

    public synchronized void setChangingMVPerOneDeg(int changingMVPerOneDeg) {
        Unit.changingMVPerOneDeg = changingMVPerOneDeg;
    }

    public synchronized int getMinTempFloat() {
        return minTempFloat;
    }

    public synchronized void setMinTempFloat(int minTempFloat) {
        Unit.minTempFloat = minTempFloat;
    }

    public synchronized int getTempFirstMidPointFloat() {
        return tempFirstMidPointFloat;
    }

    public synchronized void setTempFirstMidPointFloat(int tempFirstMidPointFloat) {
        Unit.tempFirstMidPointFloat = tempFirstMidPointFloat;
    }

    public synchronized int getTempSecondMidPointFloat() {
        return tempSecondMidPointFloat;
    }

    public synchronized void setTempSecondMidPointFloat(int tempSecondMidPointFloat) {
        Unit.tempSecondMidPointFloat = tempSecondMidPointFloat;
    }

    public synchronized int getMaxTempFloat() {
        return maxTempFloat;
    }

    public synchronized void setMaxTempFloat(int maxTempFloat) {
        Unit.maxTempFloat = maxTempFloat;
    }

    public synchronized int getMinTempBoost() {
        return minTempBoost;
    }

    public synchronized void setMinTempBoost(int minTempBoost) {
        Unit.minTempBoost = minTempBoost;
    }

    public synchronized int getMaxTempBoost() {
        return maxTempBoost;
    }

    public synchronized void setMaxTempBoost(int maxTempBoost) {
        Unit.maxTempBoost = maxTempBoost;
    }

    public synchronized int getOutputMaximum() {
        return outputMaximum;
    }

    public synchronized void setOutputMaximum(int outputMaximum) {
        Unit.outputMaximum = outputMaximum;
    }

    public synchronized int getOutputMiddle() {
        return outputMiddle;
    }

    public synchronized void setOutputMiddle(int outputMiddle) {
        Unit.outputMiddle = outputMiddle;
    }

    public synchronized int getOutputFloatMinimum() {
        return outputFloatMinimum;
    }

    public synchronized void setOutputFloatMinimum(int outputFloatMinimum) {
        Unit.outputFloatMinimum = outputFloatMinimum;
    }

    public synchronized int getOutputBoostMinimum() {
        return outputBoostMinimum;
    }

    public synchronized void setOutputBoostMinimum(int outputBoostMinimum) {
        Unit.outputBoostMinimum = outputBoostMinimum;
    }

    public synchronized double getCoefficientOfCalibration() {
        return coefficientOfCalibration;
    }

    public synchronized void setCoefficientOfCalibration(double coefficientOfCalibration) {
        Unit.coefficientOfCalibration = coefficientOfCalibration;
    }

    public synchronized int getMaxVoltShunt() {
        return maxVoltShunt;
    }

    public synchronized void setMaxVoltShunt(int maxVoltShunt) {
        Unit.maxVoltShunt = maxVoltShunt;
    }

    public synchronized int getMaxCurrentShunt() {
        return maxCurrentShunt;
    }

    public synchronized void setMaxCurrentShunt(int maxCurrentShunt) {
        Unit.maxCurrentShunt = maxCurrentShunt;
    }

    public synchronized int getCapacitanceOfBattery() {
        return capacitanceOfBattery;
    }

    public synchronized void setCapacitanceOfBattery(int capacitanceOfBattery) {
        Unit.capacitanceOfBattery = capacitanceOfBattery;
    }

    public synchronized int getNumberBattery() {
        return numberBattery;
    }

    public synchronized void setNumberBattery(int numberBattery) {
        Unit.numberBattery = numberBattery;
    }

    public synchronized int getCoeffAnalogueAmplifier() {
        return coeffAnalogueAmplifier;
    }

    public synchronized void setCoeffAnalogueAmplifier(int coeffAnalogueAmplifier) {
        Unit.coeffAnalogueAmplifier = coeffAnalogueAmplifier;
    }

    public synchronized double getChargingCurrent() {
        return chargingCurrent;
    }

    public synchronized void setChargingCurrent(double chargingCurrent) {
        Unit.chargingCurrent = chargingCurrent;
    }

    public synchronized double getMaxChargCurrent() {
        return maxChargCurrent;
    }

    public synchronized void setMaxChargCurrent(double maxChargCurrent) {
        Unit.maxChargCurrent = maxChargCurrent;
    }

    public synchronized double getThresholdForBoost() {
        return thresholdForBoost;
    }

    public synchronized void setThresholdForBoost(double thresholdForBoost) {
        Unit.thresholdForBoost = thresholdForBoost;
    }

    public synchronized double getThresholdBoostEnding() {
        return thresholdBoostEnding;
    }

    public synchronized int getTimeInBoost() {
        return timeInBoost;
    }

    public synchronized void setTimeInBoost(int timeInBoost) {
        Unit.timeInBoost = timeInBoost;
    }

    public synchronized int getDelayBoost() {
        return delayBoost;
    }

    public synchronized void setDelayBoost(int delayBoost) {
        Unit.delayBoost = delayBoost;
    }

    public synchronized void setThresholdBoostEnding(double thresholdBoostEnding) {
        Unit.thresholdBoostEnding = thresholdBoostEnding;
    }
}
