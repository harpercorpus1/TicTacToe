package cpsc2150.extendedTicTacToe;

/**
 * invariants crawler != NULL,
 */
public abstract class AbsGameBoard implements IGameBoard {

    /**
     * @pre this is of type IGameBoard, this != NULL
     * @return string created of game board is returned
     * @post: method will create a game board completely as a string, with players represented on it with "x" and "o"
     * this == #this
     */
    @Override
    public String toString() {
        BoardPosition crawler = new BoardPosition(0, 0);
        String retString = "   ";
        for (int i = 0; i < getNumColumns(); i++) {
            if (i < 10) {
                retString += " " + i + "|";
                continue;
            }
            retString += (i + "|");
        }
        retString += "\n";
        for (int k = 0; k < getNumRows(); k++) {
            if (k < 10) {
                retString += " " + k + "|";
            } else {
                retString += (k + "|");
            }
            for (int j = 0; j < getNumColumns(); j++) {
                crawler = new BoardPosition(k, j);
                retString += whatsAtPos(crawler) + " |";
            }
            retString += "\n";
        }
        return retString;
    }
}
