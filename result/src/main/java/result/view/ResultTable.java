package result.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;
import java.util.Arrays;

import result.model.Table;

public class ResultTable extends JPanel {

  int[] columnWidths = { 150, 150, 150, 150, 150, 150 };

  /**
   * Creates a new ResultTable with the given table model.
   * 
   * @param resultTableModel the table model to be displayed in the table
   * @author Oliver Levay
   * @author Sharmarke Osman
   */
  public ResultTable(Table resultTableModel) {
    super();
    JTable resultTable = new JTable(resultTableModel);
    resultTable.setFont(new Font("Arial", Font.PLAIN, 20));
    resultTable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 20));
    resultTable.setRowHeight(40);
    resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    resultTable.getColumnModel().getColumn(0).setMinWidth(columnWidths[0]);
    resultTable.getColumnModel().getColumn(1).setMinWidth(columnWidths[1]);
    resultTable.getColumnModel().getColumn(2).setMinWidth(columnWidths[2]);
    resultTable.getColumnModel().getColumn(3).setMinWidth(columnWidths[3]);
    resultTable.getColumnModel().getColumn(4).setMinWidth(columnWidths[4]);
    resultTable.getColumnModel().getColumn(5).setMinWidth(columnWidths[5]);

    JScrollPane resultScrollPane = new JScrollPane(resultTable);
    resultScrollPane.setPreferredSize(new Dimension(getWidth() + 20, 500));

    this.add(resultScrollPane);
  }

  /**
   * Returns the width of the table.
   * 
   * @return the width of the table
   * @author Oliver Levay
   * @author Sharmarke Osman
   */
  public int getWidth() {
    return Arrays.stream(columnWidths).sum();
  }

}
