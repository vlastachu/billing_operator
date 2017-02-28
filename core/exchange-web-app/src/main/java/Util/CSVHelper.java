package Util;

import model.Account;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CSVHelper {
    public static class WrongFileFormatException extends RuntimeException {
        public WrongFileFormatException(String message) {
            super(message);
        }
    }
    private static List<List<String>> getCellsRowsFromFile(String csvFile) throws IOException {
        final BufferedReader br = new BufferedReader(new FileReader(csvFile));
        final String cvsSplitBy = ",";
        final List<List<String>> rows = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            List<String> cells = Arrays.asList(line.split(cvsSplitBy));
            if (cells.size() > 0 && !(cells.size() == 1 && cells.get(0).isEmpty()))
                rows.add(cells);
        }
        return rows;
    }

    public static List<Account> getAccountsFromFile(String csvFile) throws IOException, WrongFileFormatException {
        final List<Account> accounts = new ArrayList<>();
        final List<List<String>> rows = getCellsRowsFromFile(csvFile);
        final int accountFieldsCount = 2;
        int recordCounter = 0;
        for (List<String> cells: rows) {
            recordCounter++;
            if (cells.size() == accountFieldsCount) {
                String phoneNumber = cells.get(0).replace(" ", "");
                try {
                    int money = Integer.parseInt(cells.get(1).replace(" ", ""));
                    accounts.add(new Account(phoneNumber, money));
                } catch (NumberFormatException e) {
                    String message = "Record #" + recordCounter + ": Can't parse integer for money field";
                    throw new WrongFileFormatException(message);
                }
            } else {
                String message = "Record #" + recordCounter + ": " + accountFieldsCount + " cells expected, but " + cells.size() + " given";
                throw new WrongFileFormatException(message);
            }
        }
        return accounts;
    }

    public static void saveAccountsToFile(String csvFile, List<Account> accounts) {

    }
}
