package configure_ups.model;

public final class Unit {

    private static final Unit unit = new Unit();

    protected final int mVAtZeroDeg = 500;
    protected final int changingMVPerOneDeg = 10;
    protected final int minTempFloat = 0;
    protected final int tempFirstMidPointFloat = 15;
    protected final int tempSecondMidPointFloat = 35;
    protected final int maxTempFloat = 50;
    protected final int minTempBoost = 30;
    protected final int maxTempBoost = 45;
    protected final int outputMaximum = 2400;
    protected final int outputMiddle = 2300;
    protected final int outputFloatMinimum = 2225;
    protected final int outputBoostMinimum = 2300;
    protected final double coefficientOfCalibration = 1.108;
    protected final int maxVoltShunt = 60;
    protected final int maxCurrentShunt = 400;
    protected final int capacitanceOfBattery = 75;
    protected final int numberBattery = 4;
    protected final int coeffAnalogueAmplifier = 25;
    protected final double chargingCurrent = 2.7;
    protected final double maxChargCurrent = 5.0;
    protected final double upThresholdCurrent = 3.7;
    protected final double thresholdForBoost = 0.9;
    protected final double thresholdBoostEnding = 0.25;
    protected final int timeInBoost = 480;
    protected final int delayBoost = 15;
    

    private Unit(){
    }


    public static Unit getUnit() {
        return unit;
    }


    protected static final class UnitBoundaryValues{
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

    public static final class NumberOfPosition{
        protected static final double MV_AT_ZERO_DEG = 1.1;
        protected static final double CHANGING_MV_PER_ONE_DEG = 1.2;
        protected static final double MIN_TEMP_FLOAT = 2.1;
        protected static final double TEMP_FIRST_MID_POINT_FLOAT = 2.2;
        protected static final double TEMP_SECOND_MID_POINT_FLOAT = 2.3;
        protected static final double MAX_TEMP_FLOAT = 2.4;
        protected static final double MIN_TEMP_BOOST = 2.5;
        protected static final double MAX_TEMP_BOOST = 2.6;
        protected static final double OUTPUT_MAXIMUM = 3.1;
        protected static final double OUTPUT_MIDDLE = 3.2;
        protected static final double OUTPUT_FLOAT_MINIMUM = 3.3;
        protected static final double OUTPUT_BOOST_MINIMUM = 3.4;
        protected static final double COEFFICIENT_OF_CALIBRATION = 4;
        protected static final double MAX_VOLT_SHUNT = 5.1;
        protected static final double MAX_CURRENT_SHUNT = 5.2;
        protected static final double CAPACITANCE_OF_BATTERY = 6.1;
        protected static final double NUMBER_BATTERY = 6.2;
        protected static final double COEFF_ANALOGUE_AMPLIFIER = 6.3;
        protected static final double CHARGING_CURRENT = 6.4;
        protected static final double MAX_CHARG_CURRENT = 6.5;
        protected static final double UP_THRESHOLD_CURRENT = 6.6;
        protected static final double THRESHOLD_FOR_BOOST = 6.7;
        protected static final double THRESHOLD_BOOST_ENDING = 6.8;
        protected static final double TIME_IN_BOOST = 6.9;
        protected static final double DELAY_BOOST = 6.10;
    }

}