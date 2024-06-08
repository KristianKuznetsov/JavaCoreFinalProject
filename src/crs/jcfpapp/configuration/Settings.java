package crs.jcfpapp.configuration;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;


public class Settings {
    private static String LANGUAGE;
    private static String DATABASE;
    private static final String RU = "RU";
    private static final String ENG = "ENG";
    private static final String TEXT = "TEXT";
    private static final String SQL = "SQL";

    public static String getLANGUAGE() {
        return LANGUAGE;
    }

    public static void setENG() {
        Settings.LANGUAGE = ENG;
    }

    public static void setRU() {
        Settings.LANGUAGE = RU;
    }

    public static String getENG() {
        return ENG;
    }

    public static String getRU() {
        return RU;
    }

    public static String getDATABASE() {
        return DATABASE;
    }

    public static void setTEXT() {
        DATABASE = TEXT;
    }

    public static void setSQL() {
        DATABASE = SQL;
    }


    public static Optional<ArrayList<String>> start() {
        //TODO: добавить логи, добавить загрузку файлов
        setLANGUAGE();
        setDATABASE();

        ArrayList<String> base = new ArrayList<>();
        return switch (DATABASE) {
            case TEXT -> {
                int a = 0;
                //TODO: текс базы ввод
                yield Optional.empty();
            }
            case SQL -> {
                int a = 0;
                //TODO: базы ввод
                yield Optional.empty();
            }
            default -> {
                yield Optional.empty();
            }
        };
    }

    private static void setLANGUAGE() {
        System.out.println("Выберите язык | Select a language");
        System.out.println("1. Русский | Russian");
        System.out.println("2. Английский | English");

        switch (getValue()) {
            case 1 -> setRU();
            case 2 -> setENG();
            default -> setRU();
        }
    }

    private static void setDATABASE() {
        switch (LANGUAGE) {
            case RU -> {
                System.out.println("Выберите откуда загружать начальную информацию");
                System.out.println("1. Текстовый файл");
                System.out.println("2. База данных");
            }
            case ENG -> {
                System.out.println("Choose where to download the initial information from");
                System.out.println("1. A text file");
                System.out.println("2. The database");
            }
        }

        switch (getValue()) {
            case 1 -> setTEXT();
            case 2 -> setSQL();
            default -> setTEXT();
        }
    }

    private static int getValue() {
        Scanner scanner = new Scanner(System.in);
        int userValue;
        do {
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            userValue = scanner.nextInt();
        } while (userValue != 1 && userValue != 2);

        return userValue;
    }
}
