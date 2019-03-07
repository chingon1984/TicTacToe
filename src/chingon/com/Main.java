package chingon.com;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        /*TODO:
        * Build Point 1 and 2 of computerMove()
        */

        TicTacToe game = new TicTacToe();
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        String choice;


            startGame(game);

            while(!quit) {
            System.out.println("\nAgain? (y/n)");
            choice = scanner.nextLine();
            if(choice.equals("n")) {
                quit = true;
            }else if(choice.equals("y")) {
                startGame(game);
            }
        }
    }

    private static void startGame(TicTacToe game) {
//        Game Processing
        System.out.println("Welcome to TicTacToe Game. Let's play a game");

        switch (game.checkWhoStarts()) {
            case 0:
                System.out.println("Bye Bye");
                return;
            case 1:
                game.printBoard(true);
                while(true) {
                    if(computerInput(game)) {
                        return;
                    }
                    game.printBoard(false);
                    if(playerInput(game)) {
                        return;
                    }
                }
            case 2:
                game.printBoard(true);
                while(true) {
                    if(playerInput(game)) {
                        return;
                    }
                    if(computerInput(game)) {
                        return;
                    }
                    game.printBoard(false);
                }
        }
    }

    private static boolean computerInput(TicTacToe game) {
        /*Calls computerMove and checks if games has and end*/
        game.checkBoard();
        game.computerMove();
        switch (winnerCheck(game)) {
            case 1:
                System.out.println("\n***You lost the game! HAHAHA  :DDD***");
                return true;
            case 0:
                System.out.println("\n***Draw!!! Nobody won***");
                return true;
        }
        return false;
    }

    private static boolean playerInput(TicTacToe game) {
//        Gets Player input and checks if game has an end
        game.checkBoard();

        boolean quit = false;
        System.out.println("\n\nChoose one number of the field (q=Quit)\n");
        while(!quit) {
            switch (game.getNewMove()) {
                case -2:
                    System.out.println("\n*****Enter valid value between 1..9!*****");
                    break;
                case -1:
                    System.out.println("\n*****Field is already marked!*****");
                    break;
                case 1:
                    System.out.println("\n*****So, you want to leave...*****");
                    return true;
                case 0:
                    quit = true;
            }
        }

        switch (winnerCheck(game)) {
            case 1:
                System.out.println("\n***You won the game! Congratulations!!!***");
                return true;
            case 0:
                System.out.println("\n***Draw!!! Nobody won***");
                return true;
        }
        return false;
    }

    private static int winnerCheck(TicTacToe game) {
        game.checkBoard();

        //Check if there's a winner or the game has finished with a due
        if(game.checkThreeInARow()) {
            //Winner
            game.printBoard(false);
            return 1;
        }else if(game.checkIfFull() == 9) {
            // Draw
            game.printBoard(false);
            return 0;
        }
        return -1;
    }
}

