package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import result.model.Table;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

public class TestTable {

    Table table;
    String filename = "../result/testdata/Acceptanstester/Maraton/acceptanstestM7-Sorterat-Resultat/sortresultat.txt";

    @BeforeEach
    public void setUp() throws IOException {
        table = new Table(new MockFileHandler(filename));
        table.loadFromFile();
    }

    @Test
    public void testGetRowCount() throws IOException {
        assertEquals(5, table.getRowCount());
    }

    @Test
    public void testGetFileName() {
        assertEquals(filename, table.getFileName());
    }

    @Test
    public void testGetColumnCount() {
        assertEquals(6, table.getColumnCount());
    }

    @Test
    public void testGetColumnName() {
        assertEquals("Result Number", table.getColumnName(0));
        assertEquals("Start Number", table.getColumnName(1));
        assertEquals("Name", table.getColumnName(2));
        assertEquals("Total Time", table.getColumnName(3));
        assertEquals("Start Time", table.getColumnName(4));
        assertEquals("Finish Time", table.getColumnName(5));

    }

    @Test
    public void testGetValueAt() throws IOException {
        assertEquals(1, table.getValueAt(0, 0));
        assertEquals(3, table.getValueAt(0, 1));
        assertEquals("Chris Csson", table.getValueAt(0, 2));
        assertEquals("01:03:06", table.getValueAt(0, 3));
        assertEquals("12:02:00", table.getValueAt(0, 4));
        assertEquals("13:05:06", table.getValueAt(0, 5));
    }

}
