package register.view;

import javax.swing.JPanel;

import register.view.tabels.TableView;

public class TablesPanel extends JPanel {
  /**
   * Creates a new tables panel
   *
   * @author Oliver Levay
   * @author Simon Persson
   * @param tables the tables to add
   */
  public TablesPanel(TableView... tables) {
    for (TableView table : tables) {
      this.add(table);
    }
  }
}
