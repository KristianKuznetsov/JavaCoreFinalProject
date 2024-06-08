package crs.jcfpapp.build;

import crs.jcfpapp.services.Parser;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //AppBuilder.build();




        String[] arr = {"59211-39276|4671.39 gjkgfkjgf", "53078-71353|6783.94", "79829-67684|4713.62 fgknjkgbfkjngbf"};
        String[] arr2 = {"59211-39276|00000-00000|4671.39 рпрпрп   |[name1.txt]", "53078-71353|00000-00000|-6783.94|[name2.txt]"};

        ArrayList<String> list = new ArrayList<>(List.of(arr));
        ArrayList<String> list2 = new ArrayList<>(List.of(arr2));

        Parser.dbTxtParser(list);
        System.out.println(Parser.inputTxtParser(list2));
    }
}