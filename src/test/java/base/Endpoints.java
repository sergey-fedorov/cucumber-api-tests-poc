package base;

public class Endpoints {
    public  static final String BASE_URI = "https://petstore.swagger.io/v2";

    public static class User {
        private static final String BASE_URI = "/user";

        public static final String CREATE_USER = BASE_URI;
        public static final String USER = BASE_URI + "/{username}";
    }

    public static class Pet {
        public static final String BASE_URI = "/pet";

        public static final String CREATE_PET = BASE_URI;
        public static final String PET = BASE_URI + "/{petId}";
        public static final String FIND_BY_STATUS = BASE_URI + "/findByStatus";
        public static final String DELETE = BASE_URI + "/{petId}";
    }

}
