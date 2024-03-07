package outils;

import javax.swing.table.AbstractTableModel;

/**
 * TabbleModel pour la vue affichage
 */
public class ModelAffichage extends AbstractTableModel {
    private Object[][] data;
    private String[] titre;

    /**
     *
     * @param data  Object[]
     * @param titre String[]
     */
    public ModelAffichage(Object[][] data, String[] titre){
        this.data = data;
        this.titre = titre;

    }

    /**
     *
     * @return nombre de lignes
     */
    public int getRowCount() {
        return this.data.length;
    }

    /**
     *
     * @return nombre de colonnes
     */
    public int getColumnCount() {
        return this.titre.length;
    }

    /**
     *
     * @param rowIndex        the row whose value is to be queried
     * @param columnIndex     the column whose value is to be queried
     * @return
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data[rowIndex][columnIndex];
    }

    /**
     *
     * @param col  the column being queried
     * @return
     */
    public String getColumnName(int col){
        return this.titre[col];
    }

    /**
     *
     * @param value   value to assign to cell
     * @param row   row of cell
     * @param col  column of cell
     */
    public void setValueAt(Object value, int row, int col){
        this.data[col][row] = value;
    }
}
