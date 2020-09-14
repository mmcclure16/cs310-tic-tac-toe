package edu.jsu.mcis;

import java.util.Arrays;

public class TicTacToeModel {

    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */

    /* ENUM TYPE DEFINITIONS */

    /* Mark (represents X, O, or an empty square */

    public enum Mark {

        X("X"),
        O("O"),
        EMPTY("-");

        private String message;

        private Mark(String msg) {
            message = msg;
        }

        @Override
        public String toString() {
            return message;
        }

    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */

    public enum Result {

        X("X"),
        O("O"),
        TIE("TIE"),
        NONE("NONE");

        private String message;

        private Result(String msg) {
            message = msg;
        }

        @Override
        public String toString() {
            return message;
        }

    };

    /* CONSTRUCTOR */

    public TicTacToeModel() {

        this(TicTacToe.DEFAULT_WIDTH);

    }

    /* CONSTRUCTOR */

    public TicTacToeModel(int width) {

        /* Initialize width; X goes first */

        this.width = width;
        xTurn = true;

        /* Create board (width x width) as a 2D Mark array */

        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */

        // INSERT YOUR CODE HERE
        for (int i = 0; i < width; i++)
            for (int j = 0; j < width; j++)
                board[i][j] = Mark.EMPTY;

    }

    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */

        // INSERT YOUR CODE HERE
            if (isValidSquare(row, col) && !isSquareMarked(row, col)) {
                xTurn = xTurn;
                board[row][col] = xTurn ? Mark.X : Mark.O;
                xTurn = !xTurn;
                return true;
        }
        return false;

    }

    private boolean isValidSquare(int row, int col) {

        /* Return TRUE if the specified location is within the bounds of the board */

        // INSERT YOUR CODE HERE
        return row >= 0 && row < width && col >= 0 && col < width;

    }

    private boolean isSquareMarked(int row, int col) {

        /* Return TRUE if the square at specified location is marked */

        // INSERT YOUR CODE HERE
        return !getMark(row, col).equals(Mark.EMPTY);

    }

    public Mark getMark(int row, int col) {

        /* Return the mark from the square at the specified location */

        // INSERT YOUR CODE HERE
        if (isValidSquare(row, col))
            return board[row][col];
        return null;

    }

    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */

        // INSERT YOUR CODE HERE
        if(isGameover()){
            if(isMarkWin(Mark.X)){
                return Result.X;
            } else if(isMarkWin(Mark.O)){
                return Result.O;
            }else{
                return Result.TIE;
            }
        }else{
            return Result.NONE;
        }

    }

    private boolean isMarkWin(Mark mark) {

        /* Check the squares of the board to see if the specified mark is the
           winner */

        // INSERT YOUR CODE HERE
        for (int i = 0; i < width; i++) {
            int row = 0, col = 0, ldiag = 0, rdiag = 0;
            for (int j = 0; j < width; j++) {
                if (getMark(j, i).equals(mark)) row++;
                if (getMark(i, j).equals(mark)) col++;
                if (i == 0 && getMark(j, j).equals(mark)) ldiag++;
                if (i == width - 1 && getMark(i - j, j).equals(mark)) rdiag++;
            }
            if (Arrays.asList(row, col, ldiag, rdiag).contains(width))
                return true;
        }
        return false;

    }

    private boolean isTie() {

        /* Check the squares of the board to see if the game is a tie */

        // INSERT YOUR CODE HERE

        return getResult().equals(Result.TIE);

    }

    public boolean isGameover() {

        /* Return TRUE if the game is over */

        if (isMarkWin(Mark.X) || isMarkWin(Mark.O)) return true;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < width; j++)
                if (getMark(j, i).equals(Mark.EMPTY))
                    return false;
        return true;

    }

    public boolean isXTurn() {

        /* Getter for xTurn */

        return xTurn;

    }

    public int getWidth() {

        /* Getter for width */

        return width;

    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder("  ");

        /* Output the board contents as a string (see examples) */

        // INSERT YOUR CODE HERE
        output.append("012\n");
        for(int i = 0; i < width; i++){
            output.append(i).append(" ");
            for(int j = 0; j < width; j++){
                Mark mark = board[i][j];
                output.append(mark.message);
                }
            output.append("\n");
            }
        return output.toString();
    }

}
