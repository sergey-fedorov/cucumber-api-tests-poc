package core;

import com.github.javafaker.Faker;

public abstract class Model {

    public abstract Model defaultBuilder(Faker faker);

}
