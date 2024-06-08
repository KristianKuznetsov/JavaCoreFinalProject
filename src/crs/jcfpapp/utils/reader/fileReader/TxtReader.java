package crs.jcfpapp.utils.reader.fileReader;

import crs.jcfpapp.configuration.Settings;
import crs.jcfpapp.utils.log.LogCreator;
import crs.jcfpapp.utils.writer.fileWriter.ReportWriter;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class TxtReader implements MyFileReader {
    private static final String FORMAT = "txt";

    @Override
    public Optional<ArrayList<String>> readFile(String filePath) {
        ArrayList<String> list = new ArrayList<>();
        ReportWriter rw = new ReportWriter();
        try (FileReader reader = new FileReader(filePath)) {
            Scanner scan = new Scanner(reader);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                line.trim();
                if (!line.equals("")) {
                    list.add(line);
                }
            }

            rw.writeIoLog(LogCreator.logFileRead(filePath));
            return Optional.of(list);
        } catch (IOException e) {
            rw.writeIoLog(LogCreator.logFileNotRead(e.toString()));
            return Optional.empty();
        }
    }

    @Override
    public Optional<ArrayList<String>> readFolder(String folderPath) {
        Optional<ArrayList<String>> o = MyFileReader.getFilesPath(folderPath, FORMAT);
        ArrayList<String> allData = new ArrayList<>();
        ReportWriter rw = new ReportWriter();

        if (o.isPresent()) {

            int readSuccessfully = 0;




            //TODO: ffff



            return Optional.of(allData);
        } else {
            return Optional.empty();
        }

    }


}
