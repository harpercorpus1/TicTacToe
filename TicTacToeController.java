package cpsc2150.extendedTicTacToe;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    private int numPlayers;
    private boolean gameOver = false;
    private int playerNumTracker = 0;
    private char[] playerTokens = {'X', 'O', 'T', 'R', 'Z', 'H', 'J', 'W', 'A', 'M'};

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;

    public static final int MAX_PLAYERS = 10;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;

        // Some code is needed here.
        numPlayers = np;
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        // Your code goes here
        if(gameOver){
            gameOver = false;
            playerNumTracker = 0;
            newGame();
        }
        BoardPosition lastPos = new BoardPosition(row, col);
        if(curGame.checkSpace(lastPos)){
            curGame.placeMarker(lastPos, playerTokens[playerNumTracker]);
            screen.setMarker(row, col, playerTokens[playerNumTracker]);
            if(playerNumTracker == (numPlayers-1)){
                playerNumTracker = 0;
            }else{
                playerNumTracker++;
            }
        }
        if(curGame.checkForDraw()){
            screen.setMessage("It's a Draw!!\nClick any Button to Start a new Game");
            gameOver = true;
        }
        if(curGame.checkForWinner(lastPos)){
            if(playerNumTracker == 0){
                screen.setMessage("Player " + playerTokens[numPlayers-1] + " Wins!\nClick any Button to Start a new Game");
            }else{
                screen.setMessage("Player " + playerTokens[playerNumTracker - 1] + " Wins!\nClick any Button to Start a new Game");
            }
            gameOver = true;
        }
        if(!gameOver) {
            screen.setMessage("It is " + playerTokens[playerNumTracker] + "'s Turn");
        }
    }


    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}