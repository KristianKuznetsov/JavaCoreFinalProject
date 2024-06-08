package crs.jcfpapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static HashMap<String, Double> dbTxtParser(ArrayList<String> list) {
        HashMap<String, Double> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches("(\\d{5}-\\d{5})\\|(\\d+\\.\\d{2}).*")) {
                String[] result = new String[2];
                result[0] = list.get(i).replaceAll("(\\d{5}-\\d{5})\\|(\\d+\\.\\d{2}).*", "$1");
                result[1] = list.get(i).replaceAll("(\\d{5}-\\d{5})\\|(\\d+\\.\\d{2}).*", "$2");
                map.put(result[0], Double.parseDouble(result[1]));
            }
        }

        System.out.println(map);
        return map;
    }

    public static String getFileName(String line) {
        Matcher matcher = Pattern.compile("([a-zA-Zа-яА-Я0-9]+\\.txt)$").matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "no";
        }
    }

    public static ArrayList<Record> inputTxtParser(ArrayList<String> arr) {
        ArrayList<Record> res = new ArrayList<>();
        String patDate = ".*(\\d{5}-\\d{5})\\|(\\d{5}-\\d{5})\\|(-?\\d+\\.\\d{2}).*";
        String patFName = "\\|\\[([a-zA-Zа-яА-Я0-9]+\\.txt)\\]$";

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).matches(patDate)) {
                String[] result = new String[4];
                result[0] = arr.get(i).replaceAll(patDate, "$1");
                result[1] = arr.get(i).replaceAll(patDate, "$2");
                result[3] = arr.get(i).replaceAll(patDate, "$3");

                Matcher matcher = Pattern.compile(patFName).matcher(arr.get(i));
                if (matcher.find()) {
                    result[2] = matcher.group(1);
                } else {
                    result[2] = "no";
                }
                res.add(new Record(result[0], result[1], result[2], Double.parseDouble(result[3])));
            }
        }
        return res;
    }


}
