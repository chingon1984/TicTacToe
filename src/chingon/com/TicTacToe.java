package chingon.com;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    /*Logic!!!
    *checkBoard open
    * checkHorizontal
    * checkDiagonal
    * checkVertical*/


    private char[][] board = new char[11][17];
    private char[][] boardArray;
    private char currentPlayerMark;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();



    public TicTacToe() {
        this.board = initializeBoard();
        this.boardArray = new char[3][3];
        this.currentPlayerMark = 'x';
        checkBoard();
    }

    public char[][] initializeBoard() {
        /*Sets the Dimensions of the board!*/

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 17; j++) {

                if (i == 3 || i == 7) {
                    board[i][j] = '-';
                } else {
                    if (j == 5 || j == 11) {
                        board[i][j] = '|';
                    } else {
                        board[i][j] = ' ';
                    }
                }

            }
        }


        return board;
    }

    public int getNewMove() {
        /*This method gets the next move from the Player and
        calls setNewMark() to manipulate board[][]*/

        /* 0: field is empty
          -1: field is not empty
        * -2: input is invalid
        *  1: input is q/Q
        */


        String choice;

        choice = scanner.next();


        if (choice.length() > 1) {  //Input greater than 1 char...error
            return -2;
        } else {
            if (Character.isDigit(choice.charAt(0))) {  //if Input is a digit
                if (choice.charAt(0) != '0') {   // if between 1 .. 9
                    int choiceDigit = Character.getNumericValue(choice.charAt(0));
                    if (checkField(choiceDigit) == ' ') { //check if field is empty
                        setNewMark(currentPlayerMark, Character.getNumericValue(choice.charAt(0)));
//                        checkBoard();
                        switchCurrentPlayerMark();
                        return 0;
                    }
                    return -1; //if field is not empty
                } else {    // if input is 0
                    return -2;
                }
            } else if (choice.equals("q") || choice.equals("Q")) {   // q or Q for Quit
                return 1;
            }
            return -2;
        }
    }

    private void setNewMark(char currentPlayerMark, int position) {
        /*Set a new mark ('x' or 'o' on a given position.*/

        switch (position) {
            case 1:
                board[9][2] = currentPlayerMark;
                break;
            case 2:
                board[9][8] = currentPlayerMark;
                break;
            case 3:
                board[9][14] = currentPlayerMark;
                break;
            case 4:
                board[5][2] = currentPlayerMark;
                break;
            case 5:
                board[5][8] = currentPlayerMark;
                break;
            case 6:
                board[5][14] = currentPlayerMark;
                break;
            case 7:
                board[1][2] = currentPlayerMark;
                break;
            case 8:
                board[1][8] = currentPlayerMark;
                break;
            case 9:
                board[1][14] = currentPlayerMark;
                break;
        }
    }


    public void printBoard(boolean showNumbers) {
//        printing the tic tac toe game board with numbers
        String sterne = "";
        for (int i = 0; i < 25; i++) {
            sterne += "*";
        }

        if (showNumbers) {
            //         these are the fields to choose from
            board[1][2] = '7';
            board[1][8] = '8';
            board[1][14] = '9';
            board[5][2] = '4';
            board[5][8] = '5';
            board[5][14] = '6';
            board[9][2] = '1';
            board[9][8] = '2';
            board[9][14] = '3';
        }

        System.out.println("\n" + sterne + "\n");
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 17; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

        if (showNumbers) {
            board[1][2] = ' ';
            board[1][8] = ' ';
            board[1][14] = ' ';
            board[5][2] = ' ';
            board[5][8] = ' ';
            board[5][14] = ' ';
            board[9][2] = ' ';
            board[9][8] = ' ';
            board[9][14] = ' ';
        }
    }

    public char checkField(int field) {

        /*Returns the current state of a field ( 'x' or 'o' or empty)
        Player got 'x'
        CPU got 'o'
        If empty ' '*/

        char currentField = ' ';
        switch (field) {
            case 1:
                currentField = board[9][2];
                break;
            case 2:
                currentField = board[9][8];
                break;
            case 3:
                currentField = board[9][14];
                break;
            case 4:
                currentField = board[5][2];
                break;
            case 5:
                currentField = board[5][8];
                break;
            case 6:
                currentField = board[5][14];
                break;
            case 7:
                currentField = board[1][2];
                break;
            case 8:
                currentField = board[1][8];
                break;
            case 9:
                currentField = board[1][14];
                break;
        }

        switch (currentField) {
            case 'x':  //*************Player got 'x'
                return 'x';
            case 'o': //**************CPU got 'o'
                return 'o';
            default:    //************Empty field
                return ' ';
        }
    }

    private void switchCurrentPlayerMark() {
        /*Switch Player Mark from 'o' to 'x' and vice versa*/

        if(currentPlayerMark == 'x') {
            currentPlayerMark = 'o';
        }else {
            currentPlayerMark = 'x';
        }
    }

    private char returnEnemiesPlayerMark() {
        if(currentPlayerMark == 'x') {
            return 'o';
        }
        return 'x';
    }


    public void checkBoard() {
        /*gets whole board settings*/
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (i) {
                    case 0:
                        boardArray[i][j] = checkField(j + 7);
                        break;
                    case 1:
                        boardArray[i][j] = checkField(j + 4);
                        break;
                    case 2:
                        boardArray[i][j] = checkField(j + 1);
                        break;
                }
            }
        }
    }


    public void computerMove() {

        /* 1) Checks if next move leads to a win
        *  2) Checks if next move prevents from Players win
        *  3) Checks if the field in the middle "5" is still empty -> if yes -> place   -> DONE
        *  4) Checks if Player marked a corner -> is counterpart corner still emtpy? -> if yes -> place
        *  5) Checks if any Corner is empty -> if yes -> mark     -> DONE
        *  6) Mark one of the remaining fields (randomly)       -> DONE
        *  */

        boolean moveDone = false;


        // 1 && 2)
        if(!moveDone && twoMarksInARow(true, true)) {
            moveDone = true;
        }
        if(!moveDone && twoMarksInARow(false, true)) {
            moveDone = true;
        }
        //Diagonal
        if(!moveDone && twoMarksInDiagonalRow(true)) {
            moveDone = true;
        }
        if(!moveDone && twoMarksInARow(true, false)) {
            moveDone = true;
        }
        if(!moveDone && twoMarksInARow(false, false)) {
            moveDone = true;
        }
        if(!moveDone && twoMarksInDiagonalRow(false)) {
            moveDone = true;
        }

        // 3)Checks if the field in the middle "5" is still empty -> if yes -> place
        if(!moveDone && boardArray[1][1] == ' ') {
            setNewMark(currentPlayerMark, 5);
            moveDone = true;
        }

        //4) Checks if Player marked a corner -> is counterpart corner still emtpy? -> if yes -> place
        if(!moveDone) {
            if(takeCornerField(true)) {
                moveDone = true;
            }
        }

        // 5) Checks if any Corner is empty -> if yes -> mark
        if(!moveDone) {
            if(takeCornerField(false)) {
                moveDone = true;
            }
        }

        // 6) Mark one of the remaining fields (randomly)
        if(!moveDone) {
            randomMove();
        }
        switchCurrentPlayerMark();
    }

    private boolean takeCornerField(boolean counterCorner) {
        /*If counterCorner = true, the first empty Corner will be marked, whose counterCorner is marked with the Players Mark
        * If counterCorner = false, the first empty Corner will be marked
        * */
        int[] cornerFields = {7, 3, 1, 9};
        if(!counterCorner) {
            for (int i = 0; i < cornerFields.length; i++) {
                if (checkField(cornerFields[i]) == ' ') {
                    setNewMark(currentPlayerMark, cornerFields[i]);
                    return true;
                }
            }
        }else {
            for(int i=0; i < cornerFields.length; i++) {
                if(checkField(cornerFields[i]) == returnEnemiesPlayerMark()) {
                    switch (i) {
                        case 0:
                            if(checkField(3) == ' ') {
                                setNewMark(currentPlayerMark, 3);
                                return true;
                            }
                            break;
                        case 1:
                            if(checkField(7) == ' ') {
                                setNewMark(currentPlayerMark, 7);
                                return true;
                            }
                            break;
                        case 2:
                            if(checkField(9) == ' ') {
                                setNewMark(currentPlayerMark, 9);
                                return true;
                            }
                            break;
                        case 3:
                            if(checkField(1) == ' ') {
                                setNewMark(currentPlayerMark, 1);
                                return true;
                            }
                            break;
                    }
                }
            }
        }
        return false;
    }
    private boolean twoMarksInARow(boolean horizontal, boolean MoveToWin) {
        /*CHecks for possible winning situation if MoveToWin = true
        *CHecks for situation to prevent Lossing if MoveToWin = false
        * If horizontal = true, checks horizontal rows, if horizontal = false -> checks vertical rows*/
        char mark;
        if(MoveToWin) {
            mark = currentPlayerMark;
        }else {
            mark = returnEnemiesPlayerMark();
        }

        for(int i=0; i < 3; i++) {
            int count = 0;
            int position = -1;
            for(int j=0; j<3; j++) {
                if (horizontal) {
                    if (boardArray[i][j] == mark) {
                        count++;
                    } else if (boardArray[i][j] != ' ') {
                        count--;
                        break;
                    } else {
                        position = j;
                    }
                }else {
                    if(boardArray[j][i] == mark) {
                        count++;
                    }else if(boardArray[j][i] != ' ') {
                        count--;
                        break;
                    }else {
                        position = j;
                    }
                }
            }
            if(count == 2) {
                if(horizontal) {
                    setNewMark(currentPlayerMark, transformBoardArrayToPosition(i, position));
                }else {
                    setNewMark(currentPlayerMark, transformBoardArrayToPosition(position, i));
                }
                return true;
            }
        }
        return false;
    }

    private boolean twoMarksInDiagonalRow(boolean moveToWin) {
        char mark;
        if(moveToWin) {
            mark = currentPlayerMark;
        }else {
            mark = returnEnemiesPlayerMark();
        }

        int count = 0;
        int countInv = 0;
        int position = -1;
        int positionInv = -1;
        for(int i=0; i<3; i++) {
            if(boardArray[i][i] == mark) {
                count++;
            }else if(boardArray[i][i] != ' ') {
                count--;
            }else {
                position = i;
            }

            if(boardArray[i][2-i] == mark) {
                countInv++;
            }else if(boardArray[i][2-i] != ' ') {
                countInv--;
            }else {
                positionInv = i;
            }
        }

        if(count == 2) {
            setNewMark(currentPlayerMark, transformBoardArrayToPosition(position,position));
            return  true;
        }
        if(countInv == 2) {
            setNewMark(currentPlayerMark, transformBoardArrayToPosition(positionInv, 2-positionInv));
            return true;
        }
        return false;
    }



    private void randomMove() {
        int freeFields = 9 - checkIfFull();
        int index = random.nextInt(freeFields);
        int count = 0;


        for(int i=0;i<boardArray.length;i++) {
            for(int j=0;j<boardArray[0].length;j++) {
                if(boardArray[i][j] == ' ') {
                    if(count == index) {
                        setNewMark(currentPlayerMark, transformBoardArrayToPosition(i,j));
                        return;
                    }
                    count++;
                }
            }
        }
    }

    private int transformBoardArrayToPosition(int i, int j) {
        switch (i) {
            case 0:
                return j + 7;
            case 1:
                return j + 4;
            default:
                return j + 1;
        }
    }


    public boolean checkThreeInARow() {
        /* Checks if somebody won the game*/
        return (checkHorizontals() || checkVerticals() || checkDiagonals());
    }

    public int checkIfFull() {
        /*Checks if the board is full , so the game is over */
        int count = 0;
        for(int i=0;i<boardArray.length;i++){
            for(int j=0;j<boardArray[0].length;j++) {
                if(boardArray[i][j] != ' ') {
                    count++;
                }
            }
        }
        return count;
    }


    private boolean checkHorizontals() {
        /*CHeck if there are the same symbols 3x in a horizontal row*/
        for (int i = 0; i < 3; i++) {
            boolean threeInARow = true;
            for (int j = 0; j < 2; j++) {
                if (boardArray[i][j] != boardArray[i][j + 1] || boardArray[i][j] == ' ') {
                    threeInARow = false;
                }
            }
            if(threeInARow) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticals() {
        /*CHeck if there are the same symbols 3x in a vertical row*/
        for (int i = 0; i < 3; i++) {
            boolean threeInARow = true;
            for (int j = 0; j < 2; j++) {
                if (boardArray[j][i] != boardArray[j + 1][i] || boardArray[j][i] == ' ') {
                    threeInARow = false;
                }
            }
            if(threeInARow) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        /*Check if there are the same symbols 3x in a diagonal row*/
        boolean threeInARow1 = true;
        boolean threeInARow2 = true;

        for (int i = 0; i < 2; i++) {
            if(boardArray[i][i] != boardArray[i+1][i+1] || boardArray[i][i] == ' ') {
                threeInARow1 = false;
            }
            if(boardArray[i][2-i] != boardArray[i+1][1-i] || boardArray[i][2-i] == ' ') {
                threeInARow2 = false;
            }
        }
        return (threeInARow1 || threeInARow2);
    }

    public int checkWhoStarts() {
        /*Promts the Player if he wants to start the game*/
        String choice = "";

        while (true) {
            System.out.println("\nDo you wanna start? (y/n)...\n(q/Q for quit)\n");
            choice = scanner.next();

            switch(choice) {        //Who starts???
                case "y":
                case "Y":
                    return 2;
                case "n":
                case "N":
                    return 1;
                case "q":
                case "Q":
                    return 0;
            }
        }
    }

}