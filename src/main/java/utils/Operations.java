package utils;

import java.util.ArrayList;
import java.util.List;

public class Operations {

    public static String extractNumbersAndSignsFromString(String lane) {
        return lane.replaceAll("[^0-9+−]", "");
    }

    public static String extractStringDataFromArralList(List<String> incomeData) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < incomeData.size(); i++) {
            sb.append(incomeData.get(i));
        }

        return sb.toString();
    }

    public static List<String> collectDataFrom3ArrayLists(List<String> weather, List<String> currency, List<String> itEvents, List<String> cityEvents, List<String> news) {
        List<String> collectedData = new ArrayList<String>();
        addNewLinesToArrayList(weather, 2);
        addNewLinesToArrayList(currency, 2);
        addNewLinesToArrayList(itEvents, 2);
        addNewLinesToArrayList(cityEvents, 2);

        collectedData.addAll(weather);
        collectedData.addAll(currency);
        collectedData.addAll(itEvents);
        collectedData.addAll(cityEvents);
        collectedData.addAll(news);

        return collectedData;
    }

    public static List<String> addNewLinesToArrayList(List<String> list, int amountOfLines) {
        for (int i = 0; i < amountOfLines; i++) {
            list.add("\n");
        }
        return list;
    }
}
