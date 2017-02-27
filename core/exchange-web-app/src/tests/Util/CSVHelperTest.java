package Util;

import model.Account;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CSVHelperTest {
    public static final String csvResourceFolder = "./src/main/resources/test_csv/";

    @Test(expected = FileNotFoundException.class)
    public void notExistingFile() throws IOException {
        CSVHelper.getAccountsFromFile(csvResourceFolder + "no_such_file");
    }

    @Test
    public void notANumber() throws IOException {
        try {
            CSVHelper.getAccountsFromFile(csvResourceFolder + "not_a_number.csv");
            fail("Expected an WrongFileFormatException to be thrown");
        } catch (CSVHelper.WrongFileFormatException e) {
            assertThat(e.getMessage(), is("Record #1: Can't parse integer for money field"));
        }
    }

    @Test
    public void redundantCell() throws IOException {
        try {
            CSVHelper.getAccountsFromFile(csvResourceFolder + "redundant_cell.csv");
            fail("Expected an WrongFileFormatException to be thrown");
        } catch (CSVHelper.WrongFileFormatException e) {
            assertThat(e.getMessage(), is("Record #1: 2 cells expected, but 3 given"));
        }
    }

    @Test
    public void emptyFile() throws IOException {
        List<Account> accounts = CSVHelper.getAccountsFromFile(csvResourceFolder + "empty.csv");
        assertEquals(0, accounts.size());
    }

    @Test
    public void singleLine() throws IOException {
        List<Account> accounts = CSVHelper.getAccountsFromFile(csvResourceFolder + "single_record.csv");
        assertEquals(1, accounts.size());
        assertEquals(new Account("999999999", 1000), accounts.get(0));
    }

    @Test
    public void multipleLine() throws IOException {
        List<Account> accounts = CSVHelper.getAccountsFromFile(csvResourceFolder + "multiple_line.csv");
        assertEquals(3, accounts.size());
        assertEquals(new Account("999999999", 1000), accounts.get(0));
        assertEquals(new Account("888888888", 2000), accounts.get(1));
        assertEquals(new Account("777777777", 3000), accounts.get(2));
    }

    @Test
    public void emptyLine() throws IOException {
        List<Account> accounts = CSVHelper.getAccountsFromFile(csvResourceFolder + "empty_line.csv");
        assertEquals(2, accounts.size());
        assertEquals(new Account("12", 23), accounts.get(0));
        assertEquals(new Account("23", 34), accounts.get(1));
    }

}