package crs.jcfpapp.utils.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogCreator {
    private static final String LOG_DEFAULT = "DEFAULT MESSAGE";
    private static final String LOG_FOLDER_NOT_READ_RU = "Не удалось прочитать содержимое папки";
    private static final String LOG_FOLDER_NOT_READ_ENG = "The contents of the folder could not be read";

    private static String Language = "RU";

    public static void setRuLanguage() {
        Language = "RU";
    }

    public static void setEngLanguage() {
        Language = "ENG";
    }

    public static String getLocalDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public static String logFolderNotRead(String folder){
        StringBuilder log = new StringBuilder(getLocalDateTime());
        log.append(" | ");
        log.append(switch (Language) {
            case "RU" -> LOG_FOLDER_NOT_READ_RU;
            case "ENG" -> LOG_FOLDER_NOT_READ_ENG;
            default -> LOG_DEFAULT;
        });
        log.append(": ");
        log.append(folder);
        return log.toString();
    }

}
