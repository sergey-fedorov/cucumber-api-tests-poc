package core;

import java.lang.reflect.InvocationTargetException;

public class StepsFactory {

    public static <T> T get(Class<T> stepClass){

        try {
            return stepClass.getConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
