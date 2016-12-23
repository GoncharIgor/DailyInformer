package utils;

/**
 * Created by i.gonchar on 22.12.2016.
 */
public class Operations {

    public static String extractNumbersAndSignsFromString (String lane){
        return lane.replaceAll("[^0-9+−]", "");
    }
}
