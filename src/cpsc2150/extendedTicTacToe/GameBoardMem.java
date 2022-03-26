package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * invariants 3 < max_row < 100, 3 < max_col < 100, 3 < win_condition < 25,  1 < CharArray.size() <= 10
 *
 * Correspondences rows = max_row
 *                  columns = max_col
 *                  win = win_condition
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard{

    int max_row, max_col, win_condition;
    List<Character> CharArray;
    Map<Character, List<BoardPosition>> BoardPosMap;

    public GameBoardMem(int rows, int columns, int win){
        max_row = rows;
        max_col = columns;
        win_condition = win;
        BoardPosMap = new HashMap<>();
        CharArray = new ArrayList<Character>();
    }

    /**
     * @pre this is of type GameBoardMem, this != NULL
     * @post this == #this, max_row == #max_row
     * @return data member max_row which is equal to the number of rows in the Board
     */
    public int getNumRows(){ return max_row; }

    /**
     * @pre this is of type GameBoardMem, this != NULL
     * @post this == #this, max_col == #max_col
     * @return data member max_col which is equal to the number of columns in the Board
     */
    public int getNumColumns(){ return max_col; }

    /**
     * @pre this is of type GameBoard, this != NULL
     * @post this == #this, win_condition == #win_condition
     * @return data member win_condition which is equal to the number of equal elements in a row to win the game
     */
    public int getNumToWin(){ return win_condition; }

    /**
     * @pre BoardPosition pos is valid and has valid data members of row and column, pos.getRow() < max_number of rows && pos.getRow() >= 0
     * pos.getColumn() < max_number of columns && pos.getColumn() >= 0
     * @post return char at position "pos"
     * @param pos Object of type BoardPosition that is the location of what will be returned
     * @return char of player that is at location pos
     */
    public char whatsAtPos(BoardPosition pos){
        if(BoardPosMap.isEmpty()){
            return ' ';
        }
        for(int i = 0; i < BoardPosMap.size(); i++){
            if(BoardPosMap.get(CharArray.get(i)).contains(pos)){
                return CharArray.get(i);
            }
        }
        return ' ';
    }

    /**
     * @return  returns true if player list contains position
     * @pre player is char && pos != NULL
     * @post return true if player map list == pos, otherwise returns false
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        if(BoardPosMap.containsKey(player)){
            return BoardPosMap.get(player).contains(pos);
        }else{
            return false;
        }
    }

    /**
     * @pre checkSpace(pos) != false
     * @post char player is placed at location marker
     * @param pos Position on the board that will be added to the player position list
     * @param player character of the player placing marker
     */
    public void placeMarker(BoardPosition pos, char player){
        if(BoardPosMap.containsKey(player)){
            BoardPosMap.get(player).add(pos);
        }else{
            List<BoardPosition> value = new ArrayList<>();
            value.add(pos);
            BoardPosMap.put(player, value);
            CharArray.add(player);
        }
    }
}
