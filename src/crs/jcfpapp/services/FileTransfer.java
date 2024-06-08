package crs.jcfpapp.services;

import crs.jcfpapp.utils.log.LogCreator;
import crs.jcfpapp.utils.writer.fileWriter.ReportWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class FileTransfer {
    private static final String NEW_PATH = "src\\crs\\resources\\archive";

    public static void transferFiles(ArrayList<String> usedFiles, ReportWriter rw) {
        int transferSuccessfully = usedFiles.size();
        rw.writeIoLog(LogCreator.logStartFilesTransfer(transferSuccessfully));
        for (String filePath : usedFiles) {
            try {
                Path source = Paths.get(filePath);
                Path destination = Paths.get(NEW_PATH, source.getFileName().toString());

                Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                rw.writeIoLog(LogCreator.logFileTransfer(source.getFileName().toString(), NEW_PATH));
            } catch (IOException e) {
                rw.writeIoLog(LogCreator.logFileNotTransfer(filePath));
                transferSuccessfully--;
            }
        }
        rw.writeIoLog(LogCreator.logEndFilesTransfer(usedFiles.size(), transferSuccessfully, usedFiles.size() - transferSuccessfully));
    }
}