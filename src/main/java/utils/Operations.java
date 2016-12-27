package utils;

import java.util.List;

public class Operations {

    public static String extractNumbersAndSignsFromString(String lane) {
        return lane.replaceAll("[^0-9+−]", "");
    }

    public static String extractStringDataFromArralList(List<String> incomeData) {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
