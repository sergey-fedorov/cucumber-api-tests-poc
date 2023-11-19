package core.utils;

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
}
