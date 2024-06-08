package crs.jcfpapp.utils.reader.fileReader;

import crs.jcfpapp.utils.log.LogCreator;
import crs.jcfpapp.utils.writer.fileWriter.ReportWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface MyFileReader {
    static Optional<ArrayList<String>> getFilesPath(String folderPath, String format){
        ReportWriter rw = new ReportWriter();

        Optional<File[]> filesOptional = Optional.ofNullable(new File(folderPath).listFiles());

        if (filesOptional.isPresent()) {
            ArrayList<String> paths = new ArrayList<>();
            String regex = ".+\\." + format + "$";
            File[] files = filesOptional.get();

            for (File file : files) {
                Matcher matcher = Pattern.compile(regex).matcher(file.getName());

                if (matcher.matches()) {
                    paths.add(folderPath + "\\" + file.getName());
                }
            }
            rw.writeIoLog(LogCreator.logFindFilesInFolder(folderPath, format, paths.size()));
            return Optional.of(paths);

        } else {
            rw.writeIoLog(LogCreator.logFolderNotRead(folderPath));
            return Optional.empty();
        }
    }

    Optional<ArrayList<String>> readFile(String filePath);

    Optional<ArrayList<String>> readFolder(String folderPath);
}
