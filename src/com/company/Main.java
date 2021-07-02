package com.company;//package com.company;

import java.util.Scanner;

import static com.company.TicTacToe.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //get players names
        System.out.println("Player one, what's your name: ");
        String playerOne = scanner.nextLine();
        System.out.println("Player two, what's your name");
        String playerTwo = scanner.nextLine();

//        call method to print the ticktacktoe game board

        char[][] board = new char[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = '_';
            }
        }

        // keep track of play turn
        Boolean isPlayerOne = true;

        //
        Boolean gameEnded = false;
        while (!gameEnded) {
            gameBoard(board);
            //symbol in play
            char symbol = ' ';
            if (isPlayerOne) {
                symbol = 'X';
            } else {
                symbol = 'O';
            }
            //control turn's in play
            if (isPlayerOne) {
                System.out.println(playerOne + "'s turn(X):");
            } else {
                System.out.println(playerTwo + "'s turn(O):");
            }

            //get a move from players

            int row = 0;
            int col = 0;
            while (true) {
                System.out.println("Make a move(enter row and column of choice: 0, 1 or 2");
                row = scanner.nextInt();
                col = scanner.nextInt();

                //check input from players
                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Out of bounds");
                } else if (board[row][col] != '_') {
                    System.out.println("Already in play");
                } else {
                    //valid
                    break;
                }

            }
            // setting the position on the board to player's symbol
            board[row][col] = symbol;


            //check winner
            if (hasWon(board) == 'X') {
                System.out.println(playerOne + " has won!");
                gameEnded = true;
            } else if (hasWon(board) == 'O') {
                System.out.println(playerTwo + " has won!");
                gameEnded = true;
            } else { if (isATie(board)) {
                    System.out.println("It's a draw!");
                    gameEnded = true;
                } else {
                    // game continues, toggles turns
                    isPlayerOne = !isPlayerOne;
                }
            }
        }
    }
}