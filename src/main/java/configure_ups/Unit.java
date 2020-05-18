package configure_ups;

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
    private static int outputMinimum;
    private static double valueOfNominalCurrentVolt;

    public Unit(){
/*      this.mVAtZeroDeg.set(500);
        this.changingMVPerOneDeg.set(10);
        this.minTempFloat.set(0);
        this.tempFirstMidPointFloat.set(15);
        this.tempSecondMidPointFloat.set(35);
        this.maxTempFloat.set(50);
        this.maxTempBoost.set(30);
        this.minTempBoost.set(45);
        this.outputMaximum.set(2660);
        this.outputMiddle.set(2550);
        this.outputMinimum.set(2468);
        this.valueOfNominalCurrentVolt.set(30.0);

*/
    }

    public int getMVAtZeroDeg() {
        return mVAtZeroDeg;
    }

//    public IntegerProperty mVAtZeroDegProperty() {        return mVAtZeroDeg;    }

    public void setMVAtZeroDeg(int mVAtZeroDeg) {
        this.mVAtZeroDeg=mVAtZeroDeg;
    }

    public int getChangingMVPerOneDeg() {
        return changingMVPerOneDeg;
    }

    //  public IntegerProperty changingMVPerOneDegProperty() { return changingMVPerOneDeg; }

    public void setChangingMVPerOneDeg(int changingMVPerOneDeg) {
        this.changingMVPerOneDeg = changingMVPerOneDeg;
    }

    public int getMinTempFloat() {
        return minTempFloat;
    }

//    public IntegerProperty minTempFloatProperty() {        return minTempFloat;    }

    public void setMinTempFloat(int minTempFloat) {
        this.minTempFloat = minTempFloat;
    }

    public int getTempFirstMidPointFloat() {
        return tempFirstMidPointFloat;
    }

//    public IntegerProperty tempFirstMidPointFloatProperty() {     return tempFirstMidPointFloat;  }

    public void setTempFirstMidPointFloat(int tempFirstMidPointFloat) {
        this.tempFirstMidPointFloat = tempFirstMidPointFloat;
    }

    public int getTempSecondMidPointFloat() {
        return tempSecondMidPointFloat;
    }

//    public IntegerProperty tempSecondMidPointFloatProperty() {  return tempSecondMidPointFloat;  }

    public void setTempSecondMidPointFloat(int tempSecondMidPointFloat) {
        this.tempSecondMidPointFloat = tempSecondMidPointFloat;
    }

    public int getMaxTempFloat() {
        return maxTempFloat;
    }

//    public IntegerProperty maxTempFloatProperty() {  return maxTempFloat;    }

    public void setMaxTempFloat(int maxTempFloat) {
        this.maxTempFloat = maxTempFloat;
    }

    public int getMinTempBoost() {
        return minTempBoost;
    }

//    public IntegerProperty minTempBoostProperty() {  return minTempBoost; }

    public void setMinTempBoost(int minTempBoost) {
        this.minTempBoost=minTempBoost;
    }

    public int getMaxTempBoost() {
        return maxTempBoost;
    }

//    public IntegerProperty maxTempBoostProperty() {    return maxTempBoost;  }

    public void setMaxTempBoost(int maxTempBoost) {
        this.maxTempBoost = maxTempBoost;
    }

    public int getOutputMaximum() {
        return outputMaximum;
    }

//    public IntegerProperty outputMaximumProperty() {  return outputMaximum; }

    public void setOutputMaximum(int outputMaximum) {
        this.outputMaximum = outputMaximum;
    }

    public int getOutputMiddle() {
        return outputMiddle;
    }

//    public IntegerProperty outputMiddleProperty() {  return outputMiddle; }

    public void setOutputMiddle(int outputMiddle) {
        this.outputMiddle = outputMiddle;
    }

    public int getOutputMinimum() {
        return outputMinimum;
    }

//    public IntegerProperty outputMinimumProperty() { return outputMinimum; }

    public void setOutputMinimum(int outputMinimum) {
        this.outputMinimum = outputMinimum;
    }

    public double getValueOfNominalCurrentVolt() {
        return valueOfNominalCurrentVolt;
    }

//    public DoubleProperty valueOfNominalCurrentVoltProperty() {  return valueOfNominalCurrentVolt; }

    public void setValueOfNominalCurrentVolt(double valueOfNominalCurrentVolt) {
        this.valueOfNominalCurrentVolt = valueOfNominalCurrentVolt;
    }
}
