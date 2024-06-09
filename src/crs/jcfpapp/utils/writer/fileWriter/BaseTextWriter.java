package crs.jcfpapp.utils.writer.fileWriter;

import crs.jcfpapp.utils.log.LogCreator;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class BaseTextWriter {
    private static final String BASE_PATH = "src\\crs\\jcfpapp\\configuration\\db\\textDatabases.txt";

    public static boolean write(HashMap<String, Double> data) {
        ReportWriter rw = new ReportWriter();

        try (FileWriter fileWriter = new FileWriter(BASE_PATH)) {
            rw.writeIoLog(LogCreator.logTextDbUpdate());

            for (Map.Entry<String, Double> entry : data.entrySet()) {
                BigDecimal roundVal = BigDecimal.valueOf(entry.getValue()).setScale(2, RoundingMode.HALF_UP);
                fileWriter.write(entry.getKey() + "|" + roundVal + "\n");
            }

            rw.writeIoLog(LogCreator.logUpdateSuccessful());
            return true;
        } catch (IOException e) {
            rw.writeIoLog(LogCreator.logUpdateFailed());
            return false;
        }
    }
}
