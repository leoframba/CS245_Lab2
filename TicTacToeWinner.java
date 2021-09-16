import java.util.Arrays;

/**
 * Lab-2 Tictactoe
 *
 * @author: Leonardo Framba
 */
public class TicTacToeWinner {
    /**
     * Find out the winner of the tictactoe game
     *
     * @param moves the moves of two players in the 2D array
     * @return result of the game: "A", "B", "Pending", "Draw"
     */
    public String ttcWinner(int[][] moves) {
        int[][] board = new int[3][3];
        //Player one = 1 and player two = 2
        int turn = 1;
        for (int i = 0; i < moves.length; i++) {
            board[moves[i][0]][moves[i][1]] = turn;

            //Start checking for winner after turn 4 bc first player has placed 3rd sign
            if (i > 3) {
                if (checkWin(board, moves[i], turn) && turn == 1) return "A";
                if (checkWin(board, moves[i], turn) && turn == 2) return "B";
            }

            //Flip the turn
            if (turn == 1) turn++;
            else turn--;

            //If the board is full and no winner return Draw
            if (i == 8) {
                return "Draw";
            }
        }

        //Exiting the move loop without a winner or draw = pending more moves
        return "Pending";
    }

    private boolean checkWin(int[][] board, int[] lastMove, int turn) {
        //Check Row
        int count = 0;
        for (int i = 0; i < 3 && count != -1; i++) {
            if (board[lastMove[0]][i] == turn) {
                count++;
            } else count = -1;
            if (count == 3) return true;
        }
        //Check Col
        count = 0;
        for (int i = 0; i < 3 && count != -1; i++) {
            if (board[i][lastMove[1]] == turn) {
                count++;
            } else count = -1;
            if (count == 3) return true;
        }

        //check Diag from (0,0) - (2,2)
        count = 0;
        for (int i = 0, j = 0; i < 3 && count != -1; i++, j++) {
            if (board[i][j] == turn) {
                count++;
            } else count = -1;
            if (count == 3) return true;
        }

        //check Diag from (0,2) - (2,0)
        count = 0;
        for (int i = 0, j = 2; i < 3 && count != -1; i++, j--) {
            if (board[i][j] == turn) {
                count++;
            } else count = -1;
            if (count == 3) return true;
        }

        //If current turn didn't win return false;
        return false;
    }

    public static void main(String[] args) {
        //You may modify the main to test examples
        TicTacToeWinner winner = new TicTacToeWinner();
        int[][] moves = {{0, 0}, {2, 0}, {1, 1}, {2, 1}, {2, 2}};
        System.out.println(winner.ttcWinner(moves));

        /*
        -> Expected winner: "A"
        {{0,0},{2,0},{1,1},{2,1},{2,2}}
        -> Expected winner: "B"
        {{0,0},{1,1},{0,1},{0,2},{1,0},{2,0}}
        -> Expected result: "Draw"
        {{0,0},{1,1},{0,2},{0,1},{2,2},{1,2},{2,1},{2,0},{1,0}}
        -> Expected result: "Pending"
        {{1,0},{2,0},{0,1}}, {{1,1},{0,0}}, {{2,1},{0,1},{0,2}}, {{0,0},{0,1},{2,1}}
        */
    }
}
