package crs.jcfpapp.utils.reader;

import crs.jcfpapp.utils.log.LogCreator;
import crs.jcfpapp.utils.writer.fileWriter.ReportWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public interface MyReader {
    static Optional<ArrayList<String>> getFilesPath(String folderPath, String format){
        ReportWriter rw = new ReportWriter();

        Optional<File[]> filesOptional = Optional.ofNullable(new File(folderPath).listFiles());

        if (filesOptional.isPresent()) {
            //TODO: делать
            File[] files = filesOptional.get();
            for (File file : files) {
                System.out.println("Найден файл: " + file.getName());
            }
        } else {
            rw.writeIoLog(LogCreator.logFolderNotRead(folderPath));
        }

        //System.out.println("->" + folder);
//        for (File el : files){
//            //System.out.println(el.getName());
//        }

        //ArrayList<File> allFiles = new ArrayList<>(Arrays.asList(folder.listFiles()));

//        if (files != null) {
//            for (File file : files) {
//                if (file.isFile()) {
//                    System.out.println("->"+file.getName()+"<-");
//                }
//            }
//        } else {
//            System.out.println("Не удалось получить список файлов.");
//        }
//
//        ArrayList<String> paths = new ArrayList<>();
//        Optional<ArrayList<String>> o1 = Optional.of(paths);
//        Optional<ArrayList<String>> o2 = Optional.empty();
//
//        System.out.println(o1.isPresent());
//
//        System.out.println(o2.isPresent());

//        public static boolean isRegularExpression(String input, String expectedExtension) {
//            String regex = "^(?!.*[/\\\\:*?\"<>|])[^.][^.]*\\." + expectedExtension + "$";
//            return input.matches(regex);
//        }

        return  null;
    }

    ArrayList<String> readFile(String filePath);

    ArrayList<String> readFolder(String folderPath);
}
