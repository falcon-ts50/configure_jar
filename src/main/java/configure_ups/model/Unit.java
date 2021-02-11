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

    public static int getmVAtZeroDeg() {
        return mVAtZeroDeg;
    }

    public static void setmVAtZeroDeg(int mVAtZeroDeg) {
        Unit.mVAtZeroDeg = mVAtZeroDeg;
    }

    public static int getChangingMVPerOneDeg() {
        return changingMVPerOneDeg;
    }

    public static void setChangingMVPerOneDeg(int changingMVPerOneDeg) {
        Unit.changingMVPerOneDeg = changingMVPerOneDeg;
    }

    public static int getMinTempFloat() {
        return minTempFloat;
    }

    public static void setMinTempFloat(int minTempFloat) {
        Unit.minTempFloat = minTempFloat;
    }

    public static int getTempFirstMidPointFloat() {
        return tempFirstMidPointFloat;
    }

    public static void setTempFirstMidPointFloat(int tempFirstMidPointFloat) {
        Unit.tempFirstMidPointFloat = tempFirstMidPointFloat;
    }

    public static int getTempSecondMidPointFloat() {
        return tempSecondMidPointFloat;
    }

    public static void setTempSecondMidPointFloat(int tempSecondMidPointFloat) {
        Unit.tempSecondMidPointFloat = tempSecondMidPointFloat;
    }

    public static int getMaxTempFloat() {
        return maxTempFloat;
    }

    public static void setMaxTempFloat(int maxTempFloat) {
        Unit.maxTempFloat = maxTempFloat;
    }

    public static int getMinTempBoost() {
        return minTempBoost;
    }

    public static void setMinTempBoost(int minTempBoost) {
        Unit.minTempBoost = minTempBoost;
    }

    public static int getMaxTempBoost() {
        return maxTempBoost;
    }

    public static void setMaxTempBoost(int maxTempBoost) {
        Unit.maxTempBoost = maxTempBoost;
    }

    public static int getOutputMaximum() {
        return outputMaximum;
    }

    public static void setOutputMaximum(int outputMaximum) {
        Unit.outputMaximum = outputMaximum;
    }

    public static int getOutputMiddle() {
        return outputMiddle;
    }

    public static void setOutputMiddle(int outputMiddle) {
        Unit.outputMiddle = outputMiddle;
    }

    public static int getOutputFloatMinimum() {
        return outputFloatMinimum;
    }

    public static void setOutputFloatMinimum(int outputFloatMinimum) {
        Unit.outputFloatMinimum = outputFloatMinimum;
    }

    public static int getOutputBoostMinimum() {
        return outputBoostMinimum;
    }

    public static void setOutputBoostMinimum(int outputBoostMinimum) {
        Unit.outputBoostMinimum = outputBoostMinimum;
    }

    public static double getCoefficientOfCalibration() {
        return coefficientOfCalibration;
    }

    public static void setCoefficientOfCalibration(double coefficientOfCalibration) {
        Unit.coefficientOfCalibration = coefficientOfCalibration;
    }

    public static int getMaxVoltShunt() {
        return maxVoltShunt;
    }

    public static void setMaxVoltShunt(int maxVoltShunt) {
        Unit.maxVoltShunt = maxVoltShunt;
    }

    public static int getMaxCurrentShunt() {
        return maxCurrentShunt;
    }

    public static void setMaxCurrentShunt(int maxCurrentShunt) {
        Unit.maxCurrentShunt = maxCurrentShunt;
    }

    public static int getCapacitanceOfBattery() {
        return capacitanceOfBattery;
    }

    public static void setCapacitanceOfBattery(int capacitanceOfBattery) {
        Unit.capacitanceOfBattery = capacitanceOfBattery;
    }

    public static int getNumberBattery() {
        return numberBattery;
    }

    public static void setNumberBattery(int numberBattery) {
        Unit.numberBattery = numberBattery;
    }

    public static int getCoeffAnalogueAmplifier() {
        return coeffAnalogueAmplifier;
    }

    public static void setCoeffAnalogueAmplifier(int coeffAnalogueAmplifier) {
        Unit.coeffAnalogueAmplifier = coeffAnalogueAmplifier;
    }

    public static double getChargingCurrent() {
        return chargingCurrent;
    }

    public static void setChargingCurrent(double chargingCurrent) {
        Unit.chargingCurrent = chargingCurrent;
    }

    public static double getMaxChargCurrent() {
        return maxChargCurrent;
    }

    public static void setMaxChargCurrent(double maxChargCurrent) {
        Unit.maxChargCurrent = maxChargCurrent;
    }

    public static double getThresholdForBoost() {
        return thresholdForBoost;
    }

    public static void setThresholdForBoost(double thresholdForBoost) {
        Unit.thresholdForBoost = thresholdForBoost;
    }

    public static double getThresholdBoostEnding() {
        return thresholdBoostEnding;
    }

    public static int getTimeInBoost() {
        return timeInBoost;
    }

    public static void setTimeInBoost(int timeInBoost) {
        Unit.timeInBoost = timeInBoost;
    }

    public static int getDelayBoost() {
        return delayBoost;
    }

    public static void setDelayBoost(int delayBoost) {
        Unit.delayBoost = delayBoost;
    }

    public static void setThresholdBoostEnding(double thresholdBoostEnding) {
        Unit.thresholdBoostEnding = thresholdBoostEnding;
    }
}
