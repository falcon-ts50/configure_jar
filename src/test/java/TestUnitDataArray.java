
import configure_ups.model.Unit;
import configure_ups.model.UnitDataArray;
import org.junit.Assert;
import org.junit.Test;

public class TestUnitDataArray {

    @Test
    public void testDataArray(){
        UnitDataArray unitDataArray = new UnitDataArray(Unit.getUnit());
        System.out.println(unitDataArray);
    }
}
