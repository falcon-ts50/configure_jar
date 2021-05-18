import configure_ups.controller.Controller;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

public class TestController {
    Controller controller;

    @Before
    public void createController(){
        controller = new Controller();
    }

    @Test
    public void getTextFieldsMap(){
        try {
            Field field = Controller.class.getDeclaredField("textFields");
            field.setAccessible(true);
            Map<TextField, Number[]> map = (Map<TextField, Number[]>) field.get(controller);

            map.forEach((x, y) -> {
                for (Number n: y) {
                    System.out.println(n);
                }
            });

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
