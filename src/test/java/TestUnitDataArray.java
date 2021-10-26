
import configure_ups.model.Unit;
import configure_ups.model.UnitDataArray;
import org.junit.Assert;
import org.junit.Test;

public class TestUnitDataArray {

    @Test
    public void testDataArray(){
        UnitDataArray unitDataArray = UnitDataArray.getUnitDataArray();
        System.out.println(unitDataArray);
    }
}