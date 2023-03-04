package register.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestRegisterTable {

    RegisterTable table;
    String filename = "../result/testdata/maraton1/start.txt";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));
        table = new RegisterTable(filename);
    }

    @AfterEach
    public void tearDown() throws IOException {
        System.setOut(standardOut);
        /** Replace the contents of start.txt with the contents of start_original.txt */
        List<String> start = stringListFromFile("../result/testdata/maraton1/start_original.txt");
        Files.write(Paths.get(filename), start, StandardCharsets.UTF_8);
    }

    @Test
    public void testGetRowCount() throws IOException {
        assertEquals(0, table.getRowCount());
        RegisterModel registerModel = new RegisterModel(null);
        registerModel.setStartNum(0);
        table.registerToTable(registerModel);
        assertEquals(1, table.getRowCount());
    }

    @Test
    public void testGetFileName() {
        assertEquals(filename, table.getFileName());
    }

    @Test
    public void testGetColumnCount() {
        assertEquals(2, table.getColumnCount());
    }

    @Test
    public void testGetColumnName() {
        assertEquals("Start Number", table.getColumnName(0));
        assertEquals("Time", table.getColumnName(1));
    }

    @Test
    public void testGetValueAt() throws IOException {
        RegisterModel registerModel = new RegisterModel("1");
        registerModel.setStartNum(1);
        table.registerToTable(registerModel);
        assertEquals(1, table.getValueAt(0, 0));
        assertEquals("1", table.getValueAt(0, 1));
        assertEquals("Out of bounds", table.getValueAt(0, 2));
    }

    @Test
    public void testSetValueAt() throws IOException {
        RegisterModel registerModel = new RegisterModel("1");
        registerModel.setStartNum(1);
        table.registerToTable(registerModel);
        assertEquals(1, table.getValueAt(0, 0));
        assertEquals("1", table.getValueAt(0, 1));
        table.setValueAt("2", 0, 0);
        assertEquals(2, table.getValueAt(0, 0));
        table.setValueAt("hej", 0, 0);
        assertEquals("Invalid input", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testDeleteSelectedRow() throws IOException {
        RegisterModel registerModel = new RegisterModel("1");
        registerModel.setStartNum(1);
        table.registerToTable(registerModel);
        assertEquals(1, table.getRowCount());
        table.deleteRow(0);
        assertEquals(0, table.getRowCount());
    }

    @Test
    public void testIsCellEditable() {
        assertEquals(true, table.isCellEditable(0, 0));
        assertEquals(false, table.isCellEditable(0, 1));
    }

    @Test
    public void testEditStartNumber() {

    }

    @Test
    public void testGenerateOutput() throws NumberFormatException, IOException {
        table.loadFromFile();
        List<String> out = table.generateOutput();
        List<String> expected = stringListFromFile("../result/testdata/maraton1/start.txt");
        assertEquals(expected, out);

    }

    static List<String> stringListFromFile(String path) throws IOException {
        return Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
    }
}
