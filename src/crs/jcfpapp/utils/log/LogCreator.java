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

    private static final String LOG_DB_NOT_READ_RU = "Не удалось прочитать базу данных";
    private static final String LOG_DB_NOT_READ_ENG = "The database could not be read";

    private static final String LOG_FILE_READ_RU = "Успешно прочитан файл: ";
    private static final String LOG_FILE_READ_ENG = "The file was read successfully: ";

    private static final String LOG_TXT_DB_READ_RU = "Вычитывает текстовую базу данных";
    private static final String LOG_TXT_DB_READ_ENG = "Proofreads the text database";

    private static final String LOG_SQL_DB_READ_RU = "Вычитывает sql базу данных";
    private static final String LOG_SQL_DB_READ_ENG = "Proofreads the sql database";

    private static final String[] LOG_FILE_IN_FOLDER_RU = {"В папке", "найдено", "файлов формата"};
    private static final String[] LOG_FILE_IN_FOLDER_ENG = {"There are", "files found in the folder"};

    public static String getLocalDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public static String logFolderNotRead(String folder) {
        return getLocalDateTime() + " | " +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_FOLDER_NOT_READ_RU;
                    case ENG -> LOG_FOLDER_NOT_READ_ENG;
                    default -> LOG_DEFAULT;
                } +
                ": " + folder;
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
        return getLocalDateTime() + " | " + e;
    }

    public static String logFileRead(String file) {
        return getLocalDateTime() + " | " +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_FILE_READ_RU;
                    case ENG -> LOG_FILE_READ_ENG;
                    default -> LOG_DEFAULT;
                } + file;
    }

    public static String logReadTxtDB() {
        return getLocalDateTime() + " | " +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_TXT_DB_READ_RU;
                    case ENG -> LOG_TXT_DB_READ_ENG;
                    default -> LOG_DEFAULT;
                };
    }

    public static String logReadSqlDB() {
        return getLocalDateTime() + " | " +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_SQL_DB_READ_RU;
                    case ENG -> LOG_SQL_DB_READ_ENG;
                    default -> LOG_DEFAULT;
                };
    }

    public static String logNotReadDB() {
        return getLocalDateTime() + " | " +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_DB_NOT_READ_RU;
                    case ENG -> LOG_DB_NOT_READ_ENG;
                    default -> LOG_DEFAULT;
                };
    }


}
