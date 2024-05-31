import crs.jcfpapp.utils.log.LogCreator;
import crs.jcfpapp.utils.reader.MyReader;

import java.util.ArrayList;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {

//
//        int numThreads = Runtime.getRuntime().availableProcessors();
//        System.out.println("-> "  + numThreads);
//
//        int a = 1;
//        a/=2;
//        System.out.println(" = " + a);
//        FileReaderExample.solve();

        Optional<ArrayList<String>> optional = MyReader.getFilesPath("src\\mytest\\infile", "txt");
        //System.out.println("-> " + optional.isPresent());
        //System.out.println(optional.get());
        //System.out.println("= " + MyReader.getFilesPath("src\\mytest\\infile", "pdf"));




    }
}