package crs.jcfpapp.utils.log;

import crs.jcfpapp.configuration.Settings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogCreator {
    private static final String RU = "RU";
    private static final String ENG = "ENG";

    private static final String LOG_DEFAULT = "DEFAULT MESSAGE";
    private static final String LOG_FOLDER_NOT_READ_RU = "Не удалось прочитать содержимое папки";
    private static final String LOG_FOLDER_NOT_READ_ENG = "The contents of the folder could not be read";

    private static final String LOG_FILE_READ_RU = "Успешно прочитан файл: ";
    private static final String LOG_FILE_READ_ENG = "The file was read successfully: ";

    private static final String[] LOG_FILE_IN_FOLDER_RU = {"В папке", "найдено", "файлов формата"};
    private static final String[] LOG_FILE_IN_FOLDER_ENG = {"There are", "files found in the folder"};

    public static String getLocalDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public static String logFolderNotRead(String folder) {
        StringBuilder log = new StringBuilder(getLocalDateTime());
        log.append(" | ");
        log.append(switch (Settings.getLANGUAGE()) {
            case RU -> LOG_FOLDER_NOT_READ_RU;
            case ENG -> LOG_FOLDER_NOT_READ_ENG;
            default -> LOG_DEFAULT;
        });
        log.append(": ");
        log.append(folder);
        return log.toString();
    }

    public static String logFindFilesInFolder(String folder, String format, int n) {
        StringBuilder log = new StringBuilder(getLocalDateTime());
        log.append(" | ");
        switch (Settings.getLANGUAGE()) {
            case RU -> {
                log.append(LOG_FILE_IN_FOLDER_RU[0]);
                log.append(": ");
                log.append(folder);
                log.append(" ");
                log.append(LOG_FILE_IN_FOLDER_RU[1]);
                log.append(" ");
                log.append(n);
                log.append(" ");
                log.append(LOG_FILE_IN_FOLDER_RU[2]);
                log.append(" ");
                log.append(format);
            }
            case ENG -> {
                log.append(LOG_FILE_IN_FOLDER_ENG[0]);
                log.append(" ");
                log.append(n);
                log.append(" ");
                log.append(format);
                log.append(" ");
                log.append(LOG_FILE_IN_FOLDER_ENG[1]);
                log.append(": ");
                log.append(folder);
            }
            default -> log.append(LOG_DEFAULT);
        }
        return log.toString();
    }

    public static String logFileNotRead(String e) {
        StringBuilder log = new StringBuilder(getLocalDateTime());
        log.append(" | ");
        log.append(e);
        return log.toString();
    }

    public static String logFileRead(String file) {
        StringBuilder log = new StringBuilder(getLocalDateTime());
        log.append(" | ");
        log.append(switch (Settings.getLANGUAGE()) {
            case RU -> LOG_FILE_READ_RU;
            case ENG -> LOG_FILE_READ_ENG;
            default -> LOG_DEFAULT;
        });
        log.append(file);
        return log.toString();
    }



}
