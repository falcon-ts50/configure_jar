package configure_ups.model;

import java.util.*;
import java.util.stream.Collectors;

public class UnitDataArray {
    private Unit unit = Unit.getUnit();
    private volatile LinkedHashMap<String, UnitField> dataArray = new LinkedHashMap<>();
    private static final UnitDataArray unitDataArray = new UnitDataArray();

    private UnitDataArray() {
        setBasicParams();
    }

    public LinkedHashMap<String, UnitField> getDataArray() {
        return dataArray;
    }

    public static UnitDataArray getUnitDataArray() {
        return unitDataArray;
    }

    public synchronized void setBasicParams(){
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
                dataArray.put(x.getName(), new UnitField(x.getName(), (Number) x.get(unit)));
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

        dataArray.entrySet().stream().forEach(x -> {
            x.getValue().setLowBoundValue(boundaryValues.pollFirst());
            x.getValue().setHighBoundValue(boundaryValues.pollFirst());
        });
    }

    @Override
    public String toString() {
        return "UnitDataArray{" +
                "dataArray: \n" + dataArray.entrySet().stream().map(x -> x.getValue().toString() + "\n").collect(Collectors.toList()) +
                '}';
    }

}