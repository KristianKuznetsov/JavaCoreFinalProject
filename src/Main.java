import crs.jcfpapp.utils.log.LogCreator;
import crs.jcfpapp.utils.reader.MyReader;
import crs.jcfpapp.utils.writer.fileWriter.ReportWriter;
import mytest.meclass.FileReaderExample;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Укажите путь к папке, файлы которой нужно вывести
//        String folderPath = "E:\\JavaCourses\\homework6\\src\\star\\1";
//
//        File folder = new File(folderPath);
//        File[] files = folder.listFiles();
//
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
//
//        int numThreads = Runtime.getRuntime().availableProcessors();
//        System.out.println("-> "  + numThreads);
//
//        int a = 1;
//        a/=2;
//        System.out.println(" = " + a);
//        FileReaderExample.solve();

        System.out.println(MyReader.getFilesPath("D\\f", "txt"));

        System.out.println(LogCreator.getLocalDateTime());
        System.out.println(LogCreator.getLocalDateTime());
        System.out.println(LogCreator.getLocalDateTime());
        System.out.println(LogCreator.getLocalDateTime());
        System.out.println(LogCreator.getLocalDateTime());
        System.out.println(LogCreator.getLocalDateTime());




    }
}