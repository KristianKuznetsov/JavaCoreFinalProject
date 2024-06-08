package crs.jcfpapp.build;

import crs.jcfpapp.configuration.Settings;
import crs.jcfpapp.services.Parser;
import crs.jcfpapp.services.Record;
import crs.jcfpapp.utils.reader.fileReader.TxtReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class AppBuilder {
    private static final String INPUT_FOLDER = "src\\crs\\resources\\input";
    public static void build(){

        TxtReader reader = new TxtReader();

        Optional<HashMap<String, Double>> dbOptional = Settings.start();

        Optional<ArrayList<String>> folderStringRecords = reader.readFolder(INPUT_FOLDER);
        if(folderStringRecords.isPresent()){
            ArrayList<Record> operations = Parser.inputTxtParser(folderStringRecords.get());
            System.out.println(operations);
        }






    }
}
