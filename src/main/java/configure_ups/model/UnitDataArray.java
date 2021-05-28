package configure_ups.model;

import java.util.*;
import java.util.stream.Collectors;

public class UnitDataArray {
    Unit unit;
    ArrayList<UnitField> dataArray = new ArrayList<>();

    public UnitDataArray(Unit unit) {
        this.unit = unit;

        Arrays.stream(Unit.class.getDeclaredFields()).filter(s -> {
            try {
                s.setAccessible(true);
                return !s.get(unit).getClass().equals(Unit.class);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return false;
        }).forEach(x -> {
            try {
                dataArray.add(new UnitField(x.getName(), (Number) x.get(unit)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        LinkedList<Number> boundaryValues = new LinkedList<>();
        Arrays.stream(Unit.UnitBoundaryValues.class.getDeclaredFields()).forEach(x -> {
            try {
                x.setAccessible(true);
                boundaryValues.add((Number)x.get(new Unit.UnitBoundaryValues()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        dataArray.forEach(x -> {
            x.setLowBoundValue(boundaryValues.pollFirst());
            x.setHighBoundValue(boundaryValues.pollFirst());
        });
    }

    public ArrayList<UnitField> getDataArray() {
        return dataArray;
    }

    @Override
    public String toString() {
        return "UnitDataArray{" +
                "dataArray: \n" + dataArray.stream().map(x -> x.toString() + "\n").collect(Collectors.toList()) +
                '}';
    }

}
