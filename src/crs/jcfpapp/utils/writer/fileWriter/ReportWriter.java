package crs.jcfpapp.utils.writer.fileWriter;

import crs.jcfpapp.utils.writer.MyWriter;

import java.io.FileWriter;
import java.io.IOException;

public class ReportWriter implements MyWriter<String> {
    private static final String TRANSACTIONS_LOG_FILE_PATH = "src\\crs\\report\\transactionsStatus.txt";
    private static final String INPUT_OUTPUT_LOG_FILE_PATH = "src\\crs\\report\\ioStatus.txt";

    public ReportWriter() {
    }

    public boolean writeTransLog(String data) {
        return write(TRANSACTIONS_LOG_FILE_PATH, data);
    }

    public boolean writeIoLog(String data) {
        return write(INPUT_OUTPUT_LOG_FILE_PATH, data);
    }

    @Override
    public boolean write(String way, String data) {
        try (FileWriter fileWriter = new FileWriter(way, true)) {
            fileWriter.write(data + "\n");
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
