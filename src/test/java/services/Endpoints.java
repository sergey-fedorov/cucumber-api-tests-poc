package services;

import static core.utils.PropertiesReader.getProperty;

public class Endpoints {
    public static final String BASE_HOST = getProperty("baseHost");
    public static final String BASE_URI = getProperty("protocol") + BASE_HOST;
    private static final String API_VERSION = getProperty("apiVersion");

    public static class User {
        private static final String USER_BASE_URI = API_VERSION + "/user";

        public static final String CREATE_USER = USER_BASE_URI;
        public static final String USER = USER_BASE_URI + "/{username}";
    }

    public static class Pet {
        public static final String PET_BASE_URI = API_VERSION + "/pet";

        public static final String CREATE_PET = PET_BASE_URI;
        public static final String PET = PET_BASE_URI + "/{petId}";
        public static final String FIND_BY_STATUS = PET_BASE_URI + "/findByStatus";
        public static final String DELETE = PET_BASE_URI + "/{petId}";
    }

}
