package crs.jcfpapp.configuration;

import crs.jcfpapp.services.Parser;
import crs.jcfpapp.utils.log.LogCreator;
import crs.jcfpapp.utils.reader.fileReader.TxtReader;
import crs.jcfpapp.utils.writer.fileWriter.ReportWriter;

import java.util.*;


public class Settings {
    private static String LANGUAGE = "RU";
    private static String DATABASE = "TEXT";
    private static final String RU = "RU";
    private static final String ENG = "ENG";
    private static final String TEXT = "TEXT";
    private static final String SQL = "SQL";
    private static final String TXT_DB = "src\\crs\\jcfpapp\\configuration\\db\\textDatabases.txt";

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


    public static HashMap<String, Double> start() {
        setLANGUAGE();

        //TODO: Временно отсутствует
        //setDATABASE();
        setTEXT();

        ReportWriter rw = new ReportWriter();

        return switch (DATABASE) {
            case TEXT -> {
                TxtReader txtReader = new TxtReader();
                rw.writeIoLog(LogCreator.logReadTxtDB());
                yield Parser.dbTxtParser(txtReader.readFile(TXT_DB).get());
            }
            case SQL -> {
                //TODO: базы ввод + log

                rw.writeIoLog(LogCreator.logReadSqlDB());
                yield new HashMap<>();
            }
            default -> {
                rw.writeIoLog(LogCreator.logNotReadDB());
                yield new HashMap<>();
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
