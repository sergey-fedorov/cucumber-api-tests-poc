package base;

public class Endpoints {

    public static class User {
        private static final String USER_BASE_URI = "/user";

        public static final String CREATE_USER = USER_BASE_URI;
        public static final String USER = USER_BASE_URI + "/{username}";
    }

    public static class Pet {
        private static final String USER_BASE_URI = "/pet";

        public static final String CREATE_PET = USER_BASE_URI;
        public static final String PET = USER_BASE_URI + "/{petId}";
        public static final String FIND_BY_STATUS = USER_BASE_URI + "/findByStatus";
        public static final String DELETE = USER_BASE_URI + "/{petId}";
    }

}
