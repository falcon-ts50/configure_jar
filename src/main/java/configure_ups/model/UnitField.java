package configure_ups.model;

public class UnitField {

    String name;
    Number value;
    Number lowBoundValue;
    Number highBoundValue;

    public UnitField(String name, Number value) {
        this.name = name;
        this.value = value;
        this.lowBoundValue = 0;
        this.highBoundValue = 0;
    }

    public UnitField(String name, Number value, Number lowBoundValue, Number highBoundValue) {
        this.name = name;
        this.value = value;
        this.lowBoundValue = lowBoundValue;
        this.highBoundValue = highBoundValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    public Number getLowBoundValue() {
        return lowBoundValue;
    }

    public void setLowBoundValue(Number lowBoundValue) {
        this.lowBoundValue = lowBoundValue;
    }

    public Number getHighBoundValue() {
        return highBoundValue;
    }

    public void setHighBoundValue(Number highBoundValue) {
        this.highBoundValue = highBoundValue;
    }

    @Override
    public String toString() {
        return "UnitField{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", lowBoundValue=" + lowBoundValue +
                ", highBoundValue=" + highBoundValue +
                '}';
    }
}
