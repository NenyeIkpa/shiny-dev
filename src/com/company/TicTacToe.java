package com.company;

public class TicTacToe {

        public static void gameBoard(char[][] board) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    System.out.print(board[r][c]);
                }
                System.out.println();
            }

        }

        public static char hasWon(char[][] board) {
            //for the rows
            for(int r = 0; r < 3; r++) {
                if (board[r][0] == board[r][1] && board[r][1] == board[r][2] && board[r][0] != '_') {
                    return board[r][0];
                }
            }
            //for the columns
            for(int c = 0; c < 3; c++) {
                if (board[0][c] == board[1][c] && board[1][c] == board[2][c] && board[0][c] != '_') {
                    return board[0][c];
                }
            }

            //for the diagonals
            if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] !=  '_') return board[0][0];

            if(board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '_') return board[2][0];

            //Nobody has won
            return '_';
        }
            //check if board is full
        public static Boolean isATie(char[][] board) {
            for(int r = 0; r < 3; r++) {
                for(int c = 0; c < 3; c++) {
                    if(board[r][c] == '_') {
                        return false;
                    }
                }
            }
            return true;
        }

}

