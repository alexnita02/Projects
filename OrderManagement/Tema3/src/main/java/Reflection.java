import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
public class Reflection {
    public List<String> generateHeader(Object object) {

            List<String> list=new ArrayList<>();

            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.getName();
                    list.add((String) value);

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            return list;
        }
    }


