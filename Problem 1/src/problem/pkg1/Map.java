package problem.pkg1;

import javax.swing.JLabel;

public class Map {
    int y;
    int x;
    int RowCount;
    int ColumnCount;
    int IntegerMatrix[][];
    Cell CellMatrix[][];
    JLabel VisualMatrix[][];
    boolean Display;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getRowCount() {
        return RowCount;
    }

    public void setRowCount(int RowCount) {
        this.RowCount = RowCount;
    }

    public int getColumnCount() {
        return ColumnCount;
    }

    public void setColumnCount(int ColumnCount) {
        this.ColumnCount = ColumnCount;
    }

    public int[][] getIntegerMatrix() {
        return IntegerMatrix;
    }

    public void setIntegerMatrix(int[][] IntegerMatrix) {
        this.IntegerMatrix = IntegerMatrix;
    }

    public Cell[][] getCellMatrix() {
        return CellMatrix;
    }

    public void setCellMatrix(Cell[][] CellMatrix) {
        this.CellMatrix = CellMatrix;
    }

    public JLabel[][] getVisualMatrix() {
        return VisualMatrix;
    }

    public void setVisualMatrix(JLabel[][] VisualMatrix) {
        this.VisualMatrix = VisualMatrix;
    }

    public boolean isDisplay() {
        return Display;
    }

    public void setDisplay(boolean Display) {
        this.Display = Display;
    }
}
