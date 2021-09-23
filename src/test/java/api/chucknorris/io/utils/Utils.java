package api.chucknorris.io.utils;

import java.util.Random;

public class Utils {

    public static String getURL() {
        String baseUrl = "https://api.chucknorris.io/jokes/";

        return baseUrl;
    }


    public static String getContractsBasePath(String pack, String contract) {
        return  System.getProperty("user.dir")
                + "/src/test/java/api/chucknorris/io/tests/"
                + pack
                + "/contracts/"
                + contract
                + ".json";
    }

    public static String randomCategoriesForSearch(){
        String[] words = {"animal", "career", "celebrity", "dev", "explicit", "fashion", "food", "history", "money", "movie", "music", "political", "religion", "science", "sport", "travel"};
        int randomId = new Random().nextInt(words.length);
        return words[randomId];
    }

    public static String randomTextForSearch(){
        String[] words = {"carro", "fight", "arma", "cor"};
        int randomId = new Random().nextInt(words.length);
        return words[randomId];
    }


}
