package core.utils;

import com.google.gson.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {

    private static final String NUMBER_LIST = "0123456789";

    public static String getRandomSixNumbersString() {

        StringBuilder rndString = new StringBuilder();
        while (rndString.length() < 6) {
            int index = new Random().nextInt(NUMBER_LIST.length());
            rndString.append(NUMBER_LIST.charAt(index));
        }
        return rndString.toString();
    }

    // Reading JSON as a string allows to handle it as JSON Object or JSON Array
    public static String readJsonFileAsString(String filePath) {
        String jsonAsString;
        try {
            jsonAsString = Files.readString(Paths.get(filePath));
            if(!(JsonParser.parseString(jsonAsString).isJsonObject() || JsonParser.parseString(jsonAsString).isJsonArray()))
                throw new IllegalArgumentException("Not a JSON Object or JSON Array: " + filePath);
            return jsonAsString;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parseJsonToModelList(String json, Class<T[]> modelArray) {
        JsonParser.parseString(json).getAsJsonArray();
        return Arrays.asList(new Gson().fromJson(json, modelArray));
    }

    public static <T> T parseJsonToModel(String json, Class<T> model) {
        JsonParser.parseString(json).getAsJsonObject();
        return new Gson().fromJson(json, model);
    }


}
