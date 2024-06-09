package crs.jcfpapp.utils.log;

import crs.jcfpapp.configuration.Settings;
import crs.jcfpapp.services.Record;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

    private static final String[] LOG_START_READ_FOLDER_RU = {"Начата вычитка ", " файлов"};
    private static final String[] LOG_START_READ_FOLDER_ENG = {"The proofreading of ", " files has begun"};

    private static final String[] LOG_END_READ_FOLDER_RU = {"Завершена вычитка ", " файлов, успешно ", ", провалено "};
    private static final String[] LOG_END_READ_FOLDER_ENG = {"Proofreading of ", " files is completed, ", " is successful, ", " is failed"};

    private static final String[] LOG_START_FILES_TRANSFER_RU = {"Из папки input начинаем перенос ", " файлов в папку archive"};
    private static final String[] LOG_START_FILES_TRANSFER_END = {"From the input folder, we begin transferring ", " files to the archive folder"};

    private static final String[] LOG_FILE_TRANSFER_RU = {"Файл ", " перемещен в "};
    private static final String[] LOG_FILE_TRANSFER_END = {"File ", " has been moved to "};

    private static final String LOG_FILE_NOT_TRANSFER_RU = "Ошибка при перемещении файла ";
    private static final String LOG_FILE_NOT_TRANSFER_END = "Error when moving the file ";

    private static final String[] LOG_END_FILES_TRANSFER_RU = {"Закончен перенос ", " файлов из папки input в папку archive, успешно ", ", провалено "};
    private static final String[] LOG_END_FILES_TRANSFER_END = {"The transfer of ", " files from the input folder to the archive folder is completed, ", " is successful, ", " is failed"};

    private static final String LOG_COMPLETED_RU = "успешно отработан";
    private static final String LOG_COMPLETED_ENG = "successfully completed";

    private static final String LOG_PROCESSING_ERROR_RU = "ошибка во время обработки";
    private static final String LOG_PROCESSING_ERROR_ENG = "error during processing";

    private static final String LOG_INCORRECT_AMOUNT_RU = "неверная сумма перевода";
    private static final String LOG_INCORRECT_AMOUNT_ENG = "incorrect transfer amount";

    private static final String LOG_INSUFFICIENT_FUNDS_RU = "недостаточно средств";
    private static final String LOG_INSUFFICIENT_FUNDS_ENG = "insufficient funds";

    private static final String LOG_UNKNOWN_ACCOUNT_RU = "неизвестный счёт отправитель";
    private static final String LOG_UNKNOWN_ACCOUNT_ENG = "unknown sender account";

    private static final String LOG_TRANSLATION_FROM_RU = "перевод с";
    private static final String LOG_TRANSLATION_FROM_ENG = "translation from";

    private static final String LOG_TRANSLATION_TO_RU = "на";
    private static final String LOG_TRANSLATION_TO_ENG = "to";

    private static final String LOG_TEXT_DB_UPDATE_RU = "Обновляем текстовую базу данных";
    private static final String LOG_TEXT_DB_UPDATE_ENG = "Updating the text database";

    private static final String LOG_SQL_DB_UPDATE_RU = "Обновляем sql базу данных";
    private static final String LOG_SQL_DB_UPDATE_ENG = "Updating the sql database";

    private static final String LOG_DB_UPDATE_SUCCESSFUL_RU = "Обновление базы успешно";
    private static final String LOG_DB_UPDATE_SUCCESSFUL_ENG = "Database update is successful";

    private static final String LOG_DB_UPDATE_FAILED_RU = "Обновление базы провалено";
    private static final String LOG_DB_UPDATE_FAILED_ENG = "Database update failed";

    public static String logTextDbUpdate(){
        return getLocalDateTime() + " | " +
                languageSelection(LOG_TEXT_DB_UPDATE_RU, LOG_TEXT_DB_UPDATE_ENG);
    }

    public static String logSqlDbUpdate(){
        return getLocalDateTime() + " | " +
                languageSelection(LOG_SQL_DB_UPDATE_RU, LOG_SQL_DB_UPDATE_ENG);
    }

    public static String logUpdateSuccessful(){
        return getLocalDateTime() + " | " +
                languageSelection(LOG_DB_UPDATE_SUCCESSFUL_RU, LOG_DB_UPDATE_SUCCESSFUL_ENG);
    }

    public static String logUpdateFailed(){
        return getLocalDateTime() + " | " +
                languageSelection(LOG_DB_UPDATE_FAILED_RU, LOG_DB_UPDATE_FAILED_ENG);
    }

    public static String logProcessingError(Record record, ArrayList<Boolean> status){
        StringBuilder builder = new StringBuilder(bodyOfTheTranslationReport(record));
        builder.append(languageSelection(LOG_PROCESSING_ERROR_RU, LOG_PROCESSING_ERROR_ENG));

        if(status.get(0)){
            builder.append(" | ");
            builder.append(languageSelection(LOG_INCORRECT_AMOUNT_RU, LOG_INCORRECT_AMOUNT_ENG));
        }

        if(status.get(1)){
            builder.append(" | ");
            builder.append(languageSelection(LOG_INSUFFICIENT_FUNDS_RU, LOG_INSUFFICIENT_FUNDS_ENG));
        }

        if(status.get(2)){
            builder.append(" | ");
            builder.append(languageSelection(LOG_UNKNOWN_ACCOUNT_RU, LOG_UNKNOWN_ACCOUNT_ENG));
        }

        return builder.toString();
    }

    public static String logSuccessfullyTransfer(Record record){
        return bodyOfTheTranslationReport(record) + languageSelection(LOG_COMPLETED_RU, LOG_COMPLETED_ENG);
    }

    public static String getLocalDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public static String languageSelection(String ru, String eng) {
        return switch (Settings.getLANGUAGE()) {
            case RU -> ru;
            case ENG -> eng;
            default -> LOG_DEFAULT;
        };
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

    public static String logStartReadFolder(int n) {
        return getLocalDateTime() + " | " +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_START_READ_FOLDER_RU[0];
                    case ENG -> LOG_START_READ_FOLDER_ENG[0];
                    default -> LOG_DEFAULT;
                } + n +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_START_READ_FOLDER_RU[1];
                    case ENG -> LOG_START_READ_FOLDER_ENG[1];
                    default -> LOG_DEFAULT;
                };
    }

    public static String logEndReadFolder(int n, int successful, int failed) {
        return getLocalDateTime() + " | " +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_END_READ_FOLDER_RU[0];
                    case ENG -> LOG_END_READ_FOLDER_ENG[0];
                    default -> LOG_DEFAULT;
                } + n +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_END_READ_FOLDER_RU[1];
                    case ENG -> LOG_END_READ_FOLDER_ENG[1];
                    default -> LOG_DEFAULT;
                } + successful +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_END_READ_FOLDER_RU[2];
                    case ENG -> LOG_END_READ_FOLDER_ENG[2];
                    default -> LOG_DEFAULT;
                } + failed +
                switch (Settings.getLANGUAGE()) {
                    case RU -> "";
                    case ENG -> LOG_END_READ_FOLDER_ENG[3];
                    default -> LOG_DEFAULT;
                };
    }

    public static String logStartFilesTransfer(int n) {
        return getLocalDateTime() + " | " +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_START_FILES_TRANSFER_RU[0];
                    case ENG -> LOG_START_FILES_TRANSFER_END[0];
                    default -> LOG_DEFAULT;
                } + n +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_START_FILES_TRANSFER_RU[1];
                    case ENG -> LOG_START_FILES_TRANSFER_END[1];
                    default -> LOG_DEFAULT;
                };
    }

    public static String logEndFilesTransfer(int n, int s, int f) {
        return getLocalDateTime() + " | " +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_END_FILES_TRANSFER_RU[0];
                    case ENG -> LOG_END_FILES_TRANSFER_END[0];
                    default -> LOG_DEFAULT;
                } + n +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_END_FILES_TRANSFER_RU[1];
                    case ENG -> LOG_END_FILES_TRANSFER_END[1];
                    default -> LOG_DEFAULT;
                } + s +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_END_FILES_TRANSFER_RU[2];
                    case ENG -> LOG_END_FILES_TRANSFER_END[2];
                    default -> LOG_DEFAULT;
                } + f +
                switch (Settings.getLANGUAGE()) {
                    case RU -> "";
                    case ENG -> LOG_END_FILES_TRANSFER_END[3];
                    default -> LOG_DEFAULT;
                };
    }

    public static String logFileTransfer(String start, String end) {
        return getLocalDateTime() + " | " +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_FILE_TRANSFER_RU[0];
                    case ENG -> LOG_FILE_TRANSFER_END[0];
                    default -> LOG_DEFAULT;
                } + start +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_FILE_TRANSFER_RU[1];
                    case ENG -> LOG_FILE_TRANSFER_END[1];
                    default -> LOG_DEFAULT;
                } + end;
    }

    public static String logFileNotTransfer(String file) {
        return getLocalDateTime() + " | " +
                switch (Settings.getLANGUAGE()) {
                    case RU -> LOG_FILE_NOT_TRANSFER_RU;
                    case ENG -> LOG_FILE_NOT_TRANSFER_END;
                    default -> LOG_DEFAULT;
                } + file;
    }

    public static String bodyOfTheTranslationReport(Record r){
        StringBuilder builder = new StringBuilder();
        builder.append(getLocalDateTime());
        builder.append(" | ");
        builder.append(r.getFile());
        builder.append(" | ");
        builder.append(languageSelection(LOG_TRANSLATION_FROM_RU, LOG_TRANSLATION_FROM_ENG));
        builder.append(" ");
        builder.append(r.getInAccount());
        builder.append(" ");
        builder.append(languageSelection(LOG_TRANSLATION_TO_RU, LOG_TRANSLATION_TO_ENG));
        builder.append(" ");
        builder.append(r.getOutAccount());
        builder.append(" ");
        builder.append(r.getAmount());
        builder.append(" | ");
        return builder.toString();
    }

}
