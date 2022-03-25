package cpsc2150.extendedTicTacToe;

import java.util.Arrays;

/**
 *  invariants board != NULL, 0 < rows < 100, 0 < column < 100, 3 < win < 25
 *
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    int rows, column, win;

    static char[][] board;

    /**
     * pre:
     * post: creates game board as a 2D array board[BOARD_LENGTH][BOARD_LENGTH], initializes all locations within
     * post: the 2D array to a space character " ";
     */
    public GameBoard(int rows, int column, int win) {
        this.rows = rows;
        this.column = column;
        this.win = win;
        board = new char[rows][column];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(board[i], ' ');
        }
    }

    public int getNumRows(){
        return rows;
    }

    public int getNumColumns(){ return column; }

    public int getNumToWin(){ return win; }

    /**
     * @pre checkSpace(marker) != false
     * @post char player is placed at location marker
     * @param marker Position on the board that will be assigned the char player
     * @param player character of the player which will be placed at the position
     */
    public void placeMarker(BoardPosition marker, char player) {
        board[marker.getRow()][marker.getColumn()] = player;
    }

    /**
     * @return return true if all positions in array are full, otherwise false
     * @pre board != NULL
     * @post board = #board
     */
    public boolean checkForDraw() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.column; j++) {
                if ((board[i][j] == ' ')) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return if method finds what was explained in the post-condition, return true, otherwise false
     * @pre player == 'x' || player == 'o' && lastPos != NULL
     * @post method tests to see if there is a collection of the char player in a horizontal arrangement of length at
     * least 5
     * @param lastPos position of the location at which the last player character was placed
     * @param player character at the position lastPos
     */
    public boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        int row, col, high, low;
        row = lastPos.getRow();
        col = lastPos.getColumn();
        while (board[row][col] == player) {
            if (col == (this.column - 1)) {
                col = this.column;
                break;
            }
            col += 1;
        }
        high = col - 1;
        col = lastPos.getColumn();
        while (board[row][col] == player) {
            if (col == 0) {
                col = -1;
                break;
            }
            col -= 1;
        }
        low = col + 1;

        return high - low + 1 >= win;


    }

    /**
     * @return if method finds what was explained in the post-condition, return true, otherwise false
     * @pre player == 'x' || player == 'o' && lastPos != NULL
     * @post method tests to see if there is a collection of the char player in a horizontal arrangement of length at
     * least 5
     * @param lastPos position of the location at which the last player character was placed
     * @param player character at the position lastPos
     */
    public boolean checkVerticalWin(BoardPosition lastPos, char player) {
        int row, col, high, low;
        row = lastPos.getRow();
        col = lastPos.getColumn();
        while (board[row][col] == player) {
            if (row == this.rows - 1) {
                row = this.rows;
                break;
            }
            row += 1;
        }
        high = row - 1;
        row = lastPos.getRow();
        while (board[row][col] == player) {
            if (row == 0) {
                row = -1;
                break;
            }
            row -= 1;
        }
        low = row + 1;

        return high - low + 1 >= win;

    }

    /**
     * @return if method finds what was explained in the post-condition, return true, otherwise false
     * @pre player == 'x' || player == 'o' && lastPos != NULL
     * @post method tests to see if there is a collection of the char player in a diagonal arrangement of length at
     * least 5
     * @param lastPos position of the location at which the last player character was placed
     * @param player the character at the position lastPos
     */
    public boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int row, col, high, low;
        row = lastPos.getRow();
        col = lastPos.getColumn();
        while (board[row][col] == player) {
            if (row == (this.rows - 1) || col == (this.column - 1)) {
                col++;
                break;
            }
            row += 1;
            col += 1;
        }
        high = col - 1;
        row = lastPos.getRow();
        col = lastPos.getColumn();
        while (board[row][col] == player) {
            if (row == 0 || col == 0) {
                col--;
                break;
            }
            row -= 1;
            col -= 1;
        }
        low = col + 1;

        if((high - low + 1) >=win){
            return true;
        }

        row = lastPos.getRow();
        col = lastPos.getColumn();
        while (board[row][col] == player) {
            if (row == (this.rows - 1) || col == 0) {
                row++;
                break;
            }
            row += 1;
            col -= 1;
        }
        high = row - 1;
        row = lastPos.getRow();
        col = lastPos.getColumn();
        while (board[row][col] == player) {
            if (row == 0 || col == (this.column - 1)) {
                row--;
                break;
            }
            row -= 1;
            col += 1;
        }
        low = row + 1;

        return (high - low + 1) >= win;

    }

    /**
     * @pre BoardPosition pos is valid and has valid data members of row and column, pos.getRow() < max_number of rows && pos.getRow() >= 0
     * pos.getColumn() < max_number of columns && pos.getColumn() >= 0
     * @post return char at position "pos"
     * @param pos Object of type BoardPosition that is the location of what will be returned
     * @return char of player that is at location pos
     */
    public char whatsAtPos(BoardPosition pos) {
        return board[pos.getRow()][pos.getColumn()];
    }

}


