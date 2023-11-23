package core;

import com.github.javafaker.Faker;
import core.Model;

import java.lang.reflect.InvocationTargetException;

public class TestDataFactory {

    public static Faker getFaker(){
        return new Faker();
    }

    public static <T extends Model> T getModel(Class<T> model){
        try {
            return (T) model.getConstructor().newInstance().defaultBuilder(getFaker());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

}
