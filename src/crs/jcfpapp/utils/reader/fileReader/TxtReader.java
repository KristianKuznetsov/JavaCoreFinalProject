package crs.jcfpapp.utils.reader.fileReader;

import crs.jcfpapp.services.Parser;
import crs.jcfpapp.utils.log.LogCreator;
import crs.jcfpapp.utils.writer.fileWriter.ReportWriter;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
            rw.writeIoLog(LogCreator.logStartReadFolder(o.get().size()));
            int readSuccessfully = 0;
            ArrayList<String> usedFiles = new ArrayList<>();

            for (int i = 0; i < o.get().size(); i++) {
                Optional<ArrayList<String>> temp = readFile(o.get().get(i));
                if (temp.isPresent()) {
                    readSuccessfully++;
                    usedFiles.add(o.get().get(i));
                    String fName = "|[" + Parser.getFileName(o.get().get(i)) + "]";
                    for (int j = 0; j < temp.get().size(); j++) {
                        allData.add(temp.get().get(j) + fName);
                    }
                }
            }

            rw.writeIoLog(LogCreator.logEndReadFolder(o.get().size(), readSuccessfully, o.get().size() - readSuccessfully));
            //TODO: перенос файлов в каталог архив откаментить
            //FileTransfer.transferFiles(usedFiles, rw);
            return Optional.of(allData);
        } else {
            return Optional.empty();
        }
    }


}
