package crs.developerclasses;

import crs.jcfpapp.utils.writer.fileWriter.ReportWriter;

import java.util.Random;

public class RecordGenerator {
    private static final String[] account = {
            "79829-67684", "53078-71353", "61459-89692", "64078-69194",
            "87355-61848", "26974-60636", "83747-94941", "90753-74028",
            "66191-33055", "36161-79705", "41701-27231", "69715-24063",
            "69660-34386", "97904-33887", "46963-37465", "75629-59927",
            "62094-37266", "93084-58163", "40698-57851", "30011-83007",
            "68845-33820", "65345-47386", "38710-32857", "95673-16927",
            "36339-20632", "74676-18628", "28148-14181", "65090-66033",
            "91920-86978", "83961-85985", "92446-53693", "10475-10082",
            "86224-11181", "82163-57912", "46976-96878", "58414-76494",
            "79971-17767", "88359-57226", "42650-54164", "59211-39276"
    };

    public static String generateAccountNumber(Random random) {
        int firstPart = random.nextInt(90000) + 10000;
        int secondPart = random.nextInt(90000) + 10000;
        return String.format("%05d-%05d", firstPart, secondPart);
    }

    public static String generateTransferAmount(Random random) {
        int integerPart = random.nextInt(-10000, 10000);
        int fractionalPart = random.nextInt(100);

        return integerPart + "." + ((fractionalPart < 10) ? "0" + fractionalPart : fractionalPart);
    }


    public static String getInFileRecord(Random random) {
        return account[random.nextInt(account.length)] +
                "|" +
                account[random.nextInt(account.length)] +
                "|" +
                generateTransferAmount(random);
    }

    public static double convertToDouble(String el) {
        return Double.parseDouble(el);
    }

    public static void generateInFile(String path, int n, Random random) {
        ReportWriter rw = new ReportWriter();
        for (int i = 0; i < n; i++) {
            rw.write(path, getInFileRecord(random), true);
        }
    }

    public static void generateStartFile(Random random) {
        ReportWriter rw = new ReportWriter();
        final String path = "src\\crs\\jcfpapp\\configuration\\db\\textDatabases.txt";

        for (int i = 0; i < account.length; i++) {
            if(random.nextInt(100) < 70 ) {
                StringBuilder builder = new StringBuilder();
                builder.append(account[i]);
                builder.append("|");
                builder.append(Math.abs(convertToDouble(generateTransferAmount(random))));
                rw.write(path, builder.toString(), true);
            }
        }
    }

    public static void generateInputFolder(int n, int startId) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            String path = "src\\crs\\resources\\input\\inFile" + startId + ".txt";
            startId++;
            RecordGenerator.generateInFile(path, random.nextInt(25), random);
        }
    }
}
