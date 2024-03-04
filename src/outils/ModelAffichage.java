package outils;

import javax.swing.table.AbstractTableModel;

public class ModelAffichage extends AbstractTableModel {
    private Object[][] data;
    private String[] titre;

    public ModelAffichage(Object[][] data, String[] titre){
        this.data = data;
        this.titre = titre;

    }

    public int getRowCount() {
        return this.data.length;
    }

    public int getColumnCount() {
        return this.titre.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data[rowIndex][columnIndex];
    }

    public String getColumnName(int col){
        return this.titre[col];
    }

    public void setValueAt(Object value, int row, int col){
        this.data[col][row] = value;
    }
}
