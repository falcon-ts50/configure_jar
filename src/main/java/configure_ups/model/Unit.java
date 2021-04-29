package configure_ups.model;

public class Unit {

    private static Unit unit;

    private int mVAtZeroDeg;
    private int changingMVPerOneDeg;
    private int minTempFloat;
    private int tempFirstMidPointFloat;
    private int tempSecondMidPointFloat;
    private int maxTempFloat;
    private int minTempBoost;
    private int maxTempBoost;
    private int outputMaximum;
    private int outputMiddle;
    private int outputFloatMinimum;
    private int outputBoostMinimum;
    private double coefficientOfCalibration;
    private int maxVoltShunt;
    private int maxCurrentShunt;
    private int capacitanceOfBattery;
    private int numberBattery;
    private int coeffAnalogueAmplifier;
    private double chargingCurrent;
    private double maxChargCurrent;
    private double upThresholdCurrent;
    private double thresholdForBoost;
    private double thresholdBoostEnding;
    private int timeInBoost;
    private int delayBoost;
    

    private Unit(){

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
        if(unit == null){
            unit = new Unit();
        }
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
}
