package enums;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public final class EnumUtils {
    private EnumUtils() {
    }

    /**
     * Converts a given string to an Elf Enum
     * @param elf -> given string
     * @return an Elf Enum
     */
    public static Elf stringToElf(final String elf) {
        return switch (elf) {
            case "yellow" -> Elf.YELLOW;
            case "black" -> Elf.BLACK;
            case "pink" -> Elf.PINK;
            case "white" -> Elf.WHITE;
            default -> null;
        };
    }

    /**
     * Converts a given string to a Cities Enum
     * @param city -> given string
     * @return a Cities Enum
     */
    public static Cities stringToCity(final String city) {
        return switch (city) {
            case "Bucuresti" -> Cities.BUCURESTI;
            case "Constanta" -> Cities.CONSTANTA;
            case "Buzau" -> Cities.BUZAU;
            case "Timisoara" -> Cities.TIMISOARA;
            case "Cluj-Napoca", "Cluj" -> Cities.CLUJ;
            case "Iasi" -> Cities.IASI;
            case "Craiova" -> Cities.CRAIOVA;
            case "Brasov" -> Cities.BRASOV;
            case "Braila" -> Cities.BRAILA;
            case "Oradea" -> Cities.ORADEA;
            default -> null;
        };
    }

    /**
     * Converts a given string to a Category Enum
     * @param category -> given string
     * @return a Category Enum
     */
    public static Category stringToCategory(final String category) {
        return switch (category) {
            case "Board Games" -> Category.BOARD_GAMES;
            case "Books" -> Category.BOOKS;
            case "Clothes" -> Category.CLOTHES;
            case "Sweets" -> Category.SWEETS;
            case "Technology" -> Category.TECHNOLOGY;
            case "Toys" -> Category.TOYS;
            default -> null;
        };
    }

    /**
     * Transforms a JSONArray into a list of Category Enums
     * @param jsonCategories -> JSONArray
     * @return a list of Category Enums
     */
    public static List<Category> jsonArrayToCategories(final JSONArray jsonCategories) {
        List<Category> categories = new ArrayList<>();

        for (Object jsonCategory : jsonCategories) {
            categories.add(stringToCategory(jsonCategory.toString()));
        }

        return  categories;
    }

    /**
     * Transforms a JSONArray into a list of Cities Enums
     * @param jsonCities -> JSONArray
     * @return a list of Cities Enums
     */
    public static List<Cities> jsonArrayToCities(final JSONArray jsonCities) {
        List<Cities> cities = new ArrayList<>();

        for (Object jsonCity : jsonCities) {
            cities.add(stringToCity(jsonCity.toString()));
        }

        return  cities;
    }

}
