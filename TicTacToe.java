import java.util.Scanner;

public class TicTacToe {
    static final char CROSS = 'X';
    static final char CIRCLE = 'O';

    // method that displays the current board
    public static void displayBoard(char[][] board) {
        System.out.println("+---+---+---+");
        for (int i = 0; i < board.length; i++) {
            System.out.printf("| %c | %c | %c |\n", board[i][0], board[i][1], board[i][2]);
            System.out.println("+---+---+---+");
        }
    }

    // method that inserts a move on to the board
    public static boolean insertMove(int row, int col, char[][] board, char currentMove) {
        if (board[row][col] == ' ') {  // checks if spot is empty
            board[row][col] = currentMove;
            return true;
        } else {
            return false;
        }
    }

    // method checks for winner
    public static boolean checkWinner(char[][] board, char currentMove) {
        // checks rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            // row check
            if (board[i][0] == currentMove && board[i][1] == currentMove && board[i][2] == currentMove) {
                return true;
            }
            // column check
            if (board[0][i] == currentMove && board[1][i] == currentMove && board[2][i] == currentMove) {
                return true;
            }
        }
        // diagonal check
        if (board[0][0] == currentMove && board[1][1] == currentMove && board[2][2] == currentMove) {
            return true;
        }
        if (board[0][2] == currentMove && board[1][1] == currentMove && board[2][0] == currentMove) {
            return true;
        }

        return false;
    }

    // method to check for  tie
    public static boolean checkTie(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // starts board with empty spaces
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
        };

        int row, col;
        int moveCnt = 0;
        char currentMove = CROSS;  // player X starts

        // starts game loop
        while (true) {
            displayBoard(board);

            // prompts current player
            System.out.printf("Player %c, please enter your move (row col): ", currentMove);

            // gets valid row & col inputs
            while (true) {
                row = input.nextInt();
                col = input.nextInt();

                // validates row & col input (0-2)
                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Invalid row/col. Please enter values between 0 and 2.");
                } else if (!insertMove(row, col, board, currentMove)) {
                    System.out.println("That spot is already occupied. Please choose a different spot.");
                } else {
                    break;  // valid move, exits loop
                }
            }

            // checks for winner
            if (checkWinner(board, currentMove)) {
                displayBoard(board);
                System.out.printf("Player %c wins!\n", currentMove);
                break;  // ends game
            }

            // check for tie
            if (checkTie(board)) {
                displayBoard(board);
                System.out.println("It's a tie!");
                break;  // ends game
            }

            // switches player
            currentMove = (currentMove == CROSS) ? CIRCLE : CROSS;

            // increases move count
            moveCnt++;
        }

        input.close();
    }
}
