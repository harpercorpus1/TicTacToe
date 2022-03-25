package cpsc2150.extendedTicTacToe;

/**
 * @invariants:  row < 100 && column < 100 && column >= 0 && row >= 0
 */
public class BoardPosition {
    private int row;
    private int column;

    /**
     *  pre: row < BOARD_LENGTH && column < BOARD_LENGTH && column >= 0 && row >= 0
     *  post: class data members "row" and "column" are assigned to the values passed in by parameters with the same name
     *  this.row = row; this.column = column;
     */
    BoardPosition(int row, int column){
        this.row = row;
        this.column = column;
    }

    /**
     * @pre: BoardPosition constructor has been run
     * @post: row is unchanged
     * @return: data member row
     */
    public int getRow(){
        return this.row;
    }

    /**
     * @pre BoardPosition constructor has been run
     * @post column is unchanged
     * @return data member column
     */
    public int getColumn(){
        return this.column;
    }

    /**
     * @pre pos must have valid values for its row and column
     * @post pos = #pos
     * @return true if(pos.getRow() == pos.getColumn()) otherwise false
     * @param pos a BoardPosition object that will be compared to another object of the same type
     */
    @Override
    public boolean equals(Object pos) {
        BoardPosition posBP;
        // returns a boolean
        if(pos == null) {
            return false;
        }else{
            posBP = (BoardPosition)pos;
        }
        if(this.getRow() == posBP.getRow() && this.getColumn() == posBP.getColumn()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @pre pos must have valid values for both row and column
     * @post a string is created from with values "pos.getRow(), pos.getColumn()"
     * @return return string created in function
     */
    @Override
    public String toString() {
        // returns String version of "<row>, <column>"
        String strRow = Integer.toString(this.getRow());
        String strCol = Integer.toString(this.getColumn());
        return (strRow + ", " + strCol);
    }
}

