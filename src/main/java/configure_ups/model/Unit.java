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

    public int getmVAtZeroDeg() {
        return mVAtZeroDeg;
    }

    public void setmVAtZeroDeg(int mVAtZeroDeg) {
        Unit.mVAtZeroDeg = mVAtZeroDeg;
    }

    public int getChangingMVPerOneDeg() {
        return changingMVPerOneDeg;
    }

    public void setChangingMVPerOneDeg(int changingMVPerOneDeg) {
        Unit.changingMVPerOneDeg = changingMVPerOneDeg;
    }

    public int getMinTempFloat() {
        return minTempFloat;
    }

    public void setMinTempFloat(int minTempFloat) {
        Unit.minTempFloat = minTempFloat;
    }

    public int getTempFirstMidPointFloat() {
        return tempFirstMidPointFloat;
    }

    public void setTempFirstMidPointFloat(int tempFirstMidPointFloat) {
        Unit.tempFirstMidPointFloat = tempFirstMidPointFloat;
    }

    public int getTempSecondMidPointFloat() {
        return tempSecondMidPointFloat;
    }

    public void setTempSecondMidPointFloat(int tempSecondMidPointFloat) {
        Unit.tempSecondMidPointFloat = tempSecondMidPointFloat;
    }

    public int getMaxTempFloat() {
        return maxTempFloat;
    }

    public void setMaxTempFloat(int maxTempFloat) {
        Unit.maxTempFloat = maxTempFloat;
    }

    public int getMinTempBoost() {
        return minTempBoost;
    }

    public void setMinTempBoost(int minTempBoost) {
        Unit.minTempBoost = minTempBoost;
    }

    public int getMaxTempBoost() {
        return maxTempBoost;
    }

    public void setMaxTempBoost(int maxTempBoost) {
        Unit.maxTempBoost = maxTempBoost;
    }

    public int getOutputMaximum() {
        return outputMaximum;
    }

    public void setOutputMaximum(int outputMaximum) {
        Unit.outputMaximum = outputMaximum;
    }

    public int getOutputMiddle() {
        return outputMiddle;
    }

    public void setOutputMiddle(int outputMiddle) {
        Unit.outputMiddle = outputMiddle;
    }

    public int getOutputFloatMinimum() {
        return outputFloatMinimum;
    }

    public void setOutputFloatMinimum(int outputFloatMinimum) {
        Unit.outputFloatMinimum = outputFloatMinimum;
    }

    public int getOutputBoostMinimum() {
        return outputBoostMinimum;
    }

    public void setOutputBoostMinimum(int outputBoostMinimum) {
        Unit.outputBoostMinimum = outputBoostMinimum;
    }

    public double getCoefficientOfCalibration() {
        return coefficientOfCalibration;
    }

    public void setCoefficientOfCalibration(double coefficientOfCalibration) {
        Unit.coefficientOfCalibration = coefficientOfCalibration;
    }

    public int getMaxVoltShunt() {
        return maxVoltShunt;
    }

    public void setMaxVoltShunt(int maxVoltShunt) {
        Unit.maxVoltShunt = maxVoltShunt;
    }

    public int getMaxCurrentShunt() {
        return maxCurrentShunt;
    }

    public void setMaxCurrentShunt(int maxCurrentShunt) {
        Unit.maxCurrentShunt = maxCurrentShunt;
    }

    public int getCapacitanceOfBattery() {
        return capacitanceOfBattery;
    }

    public void setCapacitanceOfBattery(int capacitanceOfBattery) {
        Unit.capacitanceOfBattery = capacitanceOfBattery;
    }

    public int getNumberBattery() {
        return numberBattery;
    }

    public void setNumberBattery(int numberBattery) {
        Unit.numberBattery = numberBattery;
    }

    public int getCoeffAnalogueAmplifier() {
        return coeffAnalogueAmplifier;
    }

    public void setCoeffAnalogueAmplifier(int coeffAnalogueAmplifier) {
        Unit.coeffAnalogueAmplifier = coeffAnalogueAmplifier;
    }

    public double getChargingCurrent() {
        return chargingCurrent;
    }

    public void setChargingCurrent(double chargingCurrent) {
        Unit.chargingCurrent = chargingCurrent;
    }

    public double getMaxChargCurrent() {
        return maxChargCurrent;
    }

    public void setMaxChargCurrent(double maxChargCurrent) {
        Unit.maxChargCurrent = maxChargCurrent;
    }

    public double getThresholdForBoost() {
        return thresholdForBoost;
    }

    public void setThresholdForBoost(double thresholdForBoost) {
        Unit.thresholdForBoost = thresholdForBoost;
    }

    public double getThresholdBoostEnding() {
        return thresholdBoostEnding;
    }

    public int getTimeInBoost() {
        return timeInBoost;
    }

    public void setTimeInBoost(int timeInBoost) {
        Unit.timeInBoost = timeInBoost;
    }

    public int getDelayBoost() {
        return delayBoost;
    }

    public void setDelayBoost(int delayBoost) {
        Unit.delayBoost = delayBoost;
    }

    public void setThresholdBoostEnding(double thresholdBoostEnding) {
        Unit.thresholdBoostEnding = thresholdBoostEnding;
    }
}
