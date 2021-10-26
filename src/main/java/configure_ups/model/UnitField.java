package configure_ups.model;

public class UnitField {

    String name;
    Number value;
    Number lowBoundValue;
    Number highBoundValue;
    Double position;

    public UnitField(String name, Number value) {
        this.name = name;
        this.value = value;
        this.lowBoundValue = 0;
        this.highBoundValue = 0;
        this.position = 0.0;
    }

    public UnitField(String name, Number value, Number lowBoundValue, Number highBoundValue, Double position) {
        this.name = name;
        this.value = value;
        this.lowBoundValue = lowBoundValue;
        this.highBoundValue = highBoundValue;
        this.position = position;
    }

    public String getName() {
        return name;
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

    public Double getPosition() {
        return position;
    }

    public void setPosition(Double position) {
        this.position = position;
    }


    @Override
    public String toString() {
        return "UnitField{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", position=" + position +
                ", lowBoundValue=" + lowBoundValue +
                ", highBoundValue=" + highBoundValue +
                '}';
    }
}
