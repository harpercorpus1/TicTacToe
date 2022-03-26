package cpsc2150.extendedTicTacToe;

/**
 * Defines: board - Z x Z - Game Board,
 *          rows - number of rows in the Game Board
 *          columns - number of columns in the Game Board
 *          win - number of equal elements placed in a row necessary to win the game
 *
 * Constraints: 0 < getNumRows() < max_num_rows,
 *              0 < getNumColumns() < 100,
 *              0 < getNumToWin() < 25
 *
 * Initialization Ensures: Game board has a board, of size getNumRows() x getNumColumns(), and it will never be empty
 */
public interface IGameBoard {

    int max_num_rows = 100, max_num_col = 100, max_players = 25;

    //primary

    char whatsAtPos(BoardPosition pos);
    void placeMarker(BoardPosition marker, char player);

    /**
     * @pre this is of type GameBoard, this != NULL
     * @post this == #this, BOARD_LENGTH == #BOARD_LENGTH
     * @return data member BOARD_LENGTH which is equal to the number of columns in the GameBoard
     */
    int getNumRows();

    /**
     * @pre this is of type GameBoard, this != NULL
     * @post this == #this, BOARD_LENGTH == #BOARD_LENGTH
     * @return data member BOARD_LENGTH which is equal to the number of columns in the GameBoard
     */
    int getNumColumns();

    /**
     * @pre this is of type GameBoard, this != NULL
     * @post this == #this, WIN_CONDITION == #WIN_CONDITION
     * @return data member WIN_CONDITION which is equal to the number of characters in a row to win
     */
    int getNumToWin();

    //secondary

    /**
     * @return if(checkVerticalWin () || checkHorizontalWin() || checkDiagonalWin()) return true, otherwise return false
     * @pre:  pos != NULL
     * @post: board = #board, lastPos = #lastPos
     * @param lastPos position of the location at which the last player character was placed
     */
    default boolean checkForWinner(BoardPosition lastPos){
        if(checkHorizontalWin(lastPos, whatsAtPos(lastPos))){
            return true;
        }
        if(checkVerticalWin(lastPos, whatsAtPos(lastPos))){
            return true;
        }
        return checkDiagonalWin(lastPos, whatsAtPos(lastPos));
    }

    /**
     * @return true if(whatsAtPos(pos) == player, otherwise false
     * @pre player == 'x' || player == 'o' && pos != NULL
     * @post return true if(board[pos.getRow(), pos.getColumn()] == player, otherwise returns false
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player){
        return whatsAtPos(pos) == player;
    }

    /**
     * // function will return true is the position "pos" is available, false otherwise,
     * // if "pos" is outside bounds of 8 x 8 Board, then false
     *
     * @pre: pos != NULL
     * @post: if(pos.whatsAtPos () == ' ' && pos.getColumn() <= BOARD_LENGTH && pos.getRow() <= BOARD_LENGTH
     * && pos.getRow() >= 0, && pos.getColumn() >= 0) return true, otherwise return false;
     * @param pos object of BoardPosition references the BoardPosition on the board that will be checked
     * @return true if space is valid and unfilled, otherwise false
     */
    default boolean checkSpace(BoardPosition pos) {
        return whatsAtPos(pos) == ' ';
    }


    // secondary that are overridden for efficiency


    /**
     * @return return true if all positions in array are full, otherwise false
     * @pre board != NULL
     * @post board = #board
     */
    default boolean checkForDraw() {
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                BoardPosition crawler = new BoardPosition(i,j);
                if ((whatsAtPos(crawler) == ' ')) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return if method finds what was explained in the post-condition, return true, otherwise false
     * @pre: player == 'x' || player == 'o' && lastPos != NULL
     * @post: method tests to see if there is a collection of the char player in a horizontal arrangement of length at
     * least 5
     * @param lastPos position of the location at which the last player character was placed
     * @param player character at the position lastPos
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        int row, col, high, low;
        row = lastPos.getRow();
        col = lastPos.getColumn();
        BoardPosition crawler = new BoardPosition(row,col);
        while (whatsAtPos(crawler) == player) {
            if (col == (getNumColumns() - 1)){
                col = getNumColumns();
                break;
            }
            col += 1;
            crawler = new BoardPosition(row, col);
        }
        high = col - 1;
        col = lastPos.getColumn();
        crawler = new BoardPosition(row,col);
        while (whatsAtPos(crawler) == player) {
            if (col == 0) {
                col = -1;
                break;
            }
            col -= 1;
            crawler = new BoardPosition(row, col);
        }
        low = col + 1;
        return (high - low + 1) >= getNumToWin();
    }

    /**
     * @return if method finds what was explained in the post-condition, return true, otherwise false
     * @pre: player == 'x' || player == 'o' && lastPos != NULL
     * @post: method tests to see if there is a collection of the char player in a horizontal arrangement of length at
     * least 5
     * @param lastPos position of the location at which the last player character was placed
     * @param player character at the position lastPos
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player) {
        int row, col, high, low;
        row = lastPos.getRow();
        col = lastPos.getColumn();
        BoardPosition crawler = new BoardPosition(row, col);
        while (whatsAtPos(crawler) == player) {
            if (row == getNumRows() - 1) {
                row = getNumRows();
                break;
            }
            row += 1;
            crawler = new BoardPosition(row, col);
        }
        high = row - 1;
        row = lastPos.getRow();
        crawler = new BoardPosition(row, col);
        while (whatsAtPos(crawler) == player) {
            if (row == 0) {
                row = -1;
                break;
            }
            row -= 1;
            crawler = new BoardPosition(row, col);
        }
        low = row + 1;

        return high - low + 1 >= getNumToWin();
    }

    /**
     * @return method tests to see if there is a collection of the char player in a diagonal arrangement of length at
     * least 5 in the diagonal direction
     * @pre: player == 'x' || player == 'o' && lastPos != NULL
     * @post: lastPos == #lastPos, char == #char
     * @param lastPos position of the location at which the last player character was placed
     * @param player the character at the position lastPos
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int row, col, high, low;
        row = lastPos.getRow();
        col = lastPos.getColumn();
        BoardPosition crawler = new BoardPosition(row,col);
        while (whatsAtPos(crawler) == player) {
            if (row == (getNumRows() - 1) || col == (getNumColumns() - 1)) {
                col++;
                break;
            }
            row += 1;
            col += 1;
            crawler = new BoardPosition(row, col);
        }
        high = col - 1;
        row = lastPos.getRow();
        col = lastPos.getColumn();
        crawler = new BoardPosition(row, col);
        while (whatsAtPos(crawler) == player) {
            if (row == 0 || col == 0) {
                col--;
                break;
            }
            row -= 1;
            col -= 1;
            crawler = new BoardPosition(row, col);
        }
        low = col + 1;

        if(high - low + 1 >= getNumToWin()){
            return true;
        }

        row = lastPos.getRow();
        col = lastPos.getColumn();
        crawler = new BoardPosition(row, col);
        while (whatsAtPos(crawler) == player) {
            if (row == (getNumRows() - 1) || col == 0){
                row++;
                break;
            }
            row += 1;
            col -= 1;
            crawler = new BoardPosition(row, col);
        }
        high = row - 1;
        row = lastPos.getRow();
        col = lastPos.getColumn();
        crawler = new BoardPosition(row, col);
        while (whatsAtPos(crawler) == player) {
            if (row == 0 || col == (getNumColumns() - 1)){
                row--;
                break;
            }
            row -= 1;
            col += 1;
            crawler = new BoardPosition(row, col);
        }
        low = row + 1;

        return (high - low + 1) >= getNumToWin();
    }

}
