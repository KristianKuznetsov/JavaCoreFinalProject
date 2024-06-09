package crs.jcfpapp.build;

import crs.jcfpapp.configuration.Settings;
import crs.jcfpapp.services.Parser;
import crs.jcfpapp.services.Record;
import crs.jcfpapp.services.TransferHandler;
import crs.jcfpapp.utils.log.LogCreator;
import crs.jcfpapp.utils.reader.fileReader.TxtReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class AppBuilder {
    private static final String INPUT_FOLDER = "src\\crs\\resources\\input";
    private static final String TRANSACTION_REPORT_PATH = "src\\crs\\report\\transactionsStatus.txt";

    private static final String RULES_RU = """
            Введите:
            1 -> Парсинг файлов перевода каталога input и перенос их в каталог archive
            2 -> Вывод списка всех переводов из файла отчета
            3 -> Вывод истории обработанных записей по датам с... по...
            4 -> Завершить программу
            """;

    private static final String RULES_ENG = """
            Enter:
            1 -> Parsing the input directory translation files and transferring them to the archive directory
            2 -> Output a list of all translations from the report file
            3 -> Output the history of processed records by dates from... to...
            4 -> End the program
            """;

    private static final String START_DATE_RU = "Введите дату начала периода (в формате dd.MM.yyyy)";
    private static final String START_DATE_ENG = "Enter the start date of the period (in dd.MM.yyyy format)";
    private static final String FINISH_DATE_RU = "Введите дату окончания периода (в формате dd.MM.yyyy)";
    private static final String FINISH_DATE_ENG = "Enter the end date of the period (in dd.MM.yyyy format)";
    private static final String ERROR_DATE_RU = "Неверный формат даты. Попробуйте еще раз.";
    private static final String ERROR_DATE_ENG = "The date format is incorrect. Try again.";
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public static void build() {
        TxtReader reader = new TxtReader();
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Double> db = Settings.start();

        boolean status = true;
        while (status) {
            getRule();
            switch (getValue()) {
                case 1 -> {
                    Optional<ArrayList<String>> folderStringRecords = reader.readFolder(INPUT_FOLDER);
                    if (folderStringRecords.isPresent()) {
                        ArrayList<Record> operations = Parser.inputTxtParser(folderStringRecords.get());

                        //TODO: сделать загрузку в файл отчет или базу данных
                        db = TransferHandler.transfer(operations, db);
                    }
                }
                case 2 -> {
                    Optional<ArrayList<String>> optionalList = reader.readFile(TRANSACTION_REPORT_PATH);
                    if (optionalList.isPresent()) {
                        for (String line : optionalList.get()) {
                            System.out.println(line);
                        }
                    }
                }
                case 3 -> {
                    Optional<ArrayList<String>> optionalList = reader.readFile(TRANSACTION_REPORT_PATH);
                    if (optionalList.isPresent()) {
                        LocalDate startDate = getDate(START_DATE_RU, START_DATE_ENG, scanner);
                        LocalDate finishDate = getDate(FINISH_DATE_RU, FINISH_DATE_ENG, scanner);

                        for (String line : optionalList.get()) {
                            Optional<LocalDate> optionalCheckDate = Parser.getParseDate(line);

                            if (optionalCheckDate.isPresent()) {
                                boolean isBetween = optionalCheckDate.get().isAfter(startDate) &&
                                        optionalCheckDate.get().isBefore(finishDate);

                                if (isBetween) {
                                    System.out.println(line);
                                }
                            }

                        }
                    }
                }
                case 4 -> {
                    status = false;
                }
            }
        }

    }

    private static int getValue() {
        Scanner scanner = new Scanner(System.in);
        int val;
        do {
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            val = scanner.nextInt();
        } while (val != 1 && val != 2 && val != 3 && val != 4);

        return val;
    }

    private static LocalDate getDate(String ru, String eng, Scanner scanner) {
        LocalDate date = null;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.println(getLanguageRes(ru, eng));
            String dateString = scanner.nextLine();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
            try {
                date = LocalDate.parse(dateString, dateFormat);
                inputValid = true;
            } catch (DateTimeParseException e) {
                System.out.println(getLanguageRes(ERROR_DATE_RU, ERROR_DATE_ENG));
            }
        }

        return date;
    }

    private static void getRule() {
        System.out.println(getLanguageRes(RULES_RU, RULES_ENG));
    }

    private static String getLanguageRes(String ru, String eng) {
        return switch (Settings.getLANGUAGE()) {
            case "RU" -> ru;
            case "ENG" -> eng;
            default -> ru;
        };
    }
}
