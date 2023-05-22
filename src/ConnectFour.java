/**
 * Connect Four Class
 * @author 25bauroth
 * @date May 18th
 * Worked with Issie, Bobby, Sarah
 */
public class ConnectFour implements BoardGame {
    private int[][] board;
    private int currentPlayer;
    private Position[] winningPositions;


    /**
     * Prepares the board for a new game.
     */
    @Override
    public void newGame() {
        board = new int[6][7];
        currentPlayer = 1+(int)(Math.random() * 2);
        winningPositions = new Position[4];
    }

    /**
     * Is the game over?
     *
     * @return true if the game is over, false otherwise
     */
    @Override
    public boolean gameOver() {
        boolean go = true;
        for(int i = 0; i < 7; i++){
            if(!columnFull(i)){
                go = false;
            }
        }
        System.out.println(getWinner() != 0 || go);
        return getWinner() != 0 || go;
    }

    /**
     * Who is the winner?
     *
     * @return 0 if there is no winner, 1 if the first player is a winner, 2 if the second player is a winner.
     */
    @Override
    public int getWinner() {
        return horzWin() > 0 ? horzWin() : vertWin() > 0 ? vertWin() : Math.max(diagWin(), 0);
    }

    private int horzWin(){
        int count, player = 0;
        for(int r = 5; r >= 0; r--){
            count = 0;
            for(int c = 0; c < 7; c++){
                if(board[r][c] == 0){
                    count = 0;
                    player = 0;
                }
                if(board[r][c] == 1){
                    if(player == 1){
                        count++;
                    }
                    else{
                        player = 1;
                        count = 1;
                    }
                }
                if(board[r][c] == 2){
                    if(player == 2){
                        count++;
                    }
                    else{
                        player = 2;
                        count = 1;
                    }
                }
                if(count == 4){
                    setWinningPositions(r, c, 1);
                    return player;
                }
            }
        }
        return 0;
    }

    private int vertWin(){
        int count, player = 0;
        for(int c = 0; c < 7; c++){
            count = 0;
            for(int r = 5; r >= 0; r--){
                if(board[r][c] == 0){
                    count = 0;
                    player = 0;
                }
                if(board[r][c] == 1){
                    if(player == 1){
                        count++;
                    }
                    else{
                        player = 1;
                        count = 1;
                    }
                }
                if(board[r][c] == 2){
                    if(player == 2){
                        count++;
                    }
                    else{
                        player = 2;
                        count = 1;
                    }
                }
                if(count == 4){
                    setWinningPositions(r, c, 0);
                    return player;
                }
            }
        }
        return 0;
    }

    private int diagWin(){
        for(int r = 5; r >= 0; r--) {
            for (int c = 0; c < 7; c++) {
                if(r >= 3 && c <= 3){
                    if(board[r][c] == board[r-1][c+1] && board[r][c] == board[r-2][c+2] && board[r][c] == board[r-3][c+3] && board[r][c] != 0) {
                        setWinningPositions(r, c, 5);
                        return board[r][c];
                    }
                }
                if(r >= 3 && c >= 3){
                    if(board[r][c] == board[r-1][c-1] && board[r][c] == board[r-2][c-2] && board[r][c] == board[r-3][c-3] && board[r][c] != 0) {
                        setWinningPositions(r, c, 3);
                        return board[r][c];
                    }
                }
                if(r <= 2 && c <= 3){
                    if(board[r][c] == board[r+1][c+1] && board[r][c] == board[r+2][c+2] && board[r][c] == board[r+3][c+3] && board[r][c] != 0) {
                        setWinningPositions(r, c, 2);
                        return board[r][c];
                    }
                }
                if(r <= 2 && c >= 3){
                    if(board[r][c] == board[r+1][c-1] && board[r][c] == board[r+2][c-2] && board[r][c] == board[r+3][c-3] && board[r][c] != 0) {
                        setWinningPositions(r, c, 4);
                        return board[r][c];
                    }
                }
            }
        }
        return 0;
    }

    private void setWinningPositions(int r, int c, int method){
        //r = starting row c = starting col method = method to calculate next other points
        //methods 0 - vert, 1 - horz, 2 - diag++, 3 - diag--, 4 - diag+-, 5 - diag -+
        switch (method) {
            case 0 -> {
                winningPositions[0] = new Position(c, r);
                winningPositions[1] = new Position(c, r + 1);
                winningPositions[2] = new Position(c, r + 2);
                winningPositions[3] = new Position(c, r + 3);
            }
            case 1 -> {
                winningPositions[0] = new Position(c, r);
                winningPositions[1] = new Position(c - 1, r);
                winningPositions[2] = new Position(c - 2, r);
                winningPositions[3] = new Position(c - 3, r);
            }
            case 2 -> {
                winningPositions[0] = new Position(c, r);
                winningPositions[1] = new Position(c + 1, r + 1);
                winningPositions[2] = new Position(c + 2, r + 2);
                winningPositions[3] = new Position(c + 3, r + 3);
            }
            case 3 -> {
                winningPositions[0] = new Position(c, r);
                winningPositions[1] = new Position(c - 1, r - 1);
                winningPositions[2] = new Position(c - 2, r - 2);
                winningPositions[3] = new Position(c - 3, r - 3);
            }
            case 4 -> {
                winningPositions[0] = new Position(c, r);
                winningPositions[1] = new Position(c - 1, r + 1);
                winningPositions[2] = new Position(c - 2, r + 2);
                winningPositions[3] = new Position(c - 3, r + 3);
            }
            case 5 -> {
                winningPositions[0] = new Position(c, r);
                winningPositions[1] = new Position(c + 1, r - 1);
                winningPositions[2] = new Position(c + 2, r - 2);
                winningPositions[3] = new Position(c + 3, r - 3);
            }
        }
    }

    /**
     * Where are the tokens that determine who the winner is?
     *
     * @return the locations of the pieces that determine the game winner.
     */
    @Override
    public Position[] getWinningPositions() {
        if(winningPositions[0] == null) winningPositions[0] = new Position(-1, -1);
        return winningPositions;
    }

    /**
     * Does the column have room for an additional move?
     *
     * @param column the column number
     * @return false if there is room for another move in the column, true if not.
     */
    @Override
    public boolean columnFull(int column) {
        return board[0][column] != 0; //is the top row full
    }

    /**
     * Change the game to reflect the current player placing a piece in the column.
     *
     * @param column the column number
     */
    @Override
    public void play(int column) {
        if(!columnFull(column)){
           for(int i = 5; i >= 0; i--){
               if(board[i][column] == 0){
                   board[i][column] = currentPlayer;
                   currentPlayer = (currentPlayer == 1) ? 2 : 1; // sets the player to the other player
                   return;
               }
           }
        }

    }

    /**
     * What is the current board configuration?
     *
     * @return for each cell on the board grid:
     * 0 if it is not filled,
     * 1 if it is filled by the first player's piece,
     * 2 if it is filled by the second player's piece.
     */
    @Override
    public int[][] getBoard() {
        return board;
    }

    /**
     * Gets the current player
     * @return current player
     */
    public int getCurrentPlayer(){
        return currentPlayer;
    }
}
