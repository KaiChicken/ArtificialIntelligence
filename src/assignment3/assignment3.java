/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.*;

/**
 *
 * @author Tong
 */
public class assignment3 {
    int numberOfColumn;
    int numberOfRow;
    int numberOfConnect; 
    boolean isAITurn;
    int[][][] board; 
    int move; 
    int opponentSymbol = 1;
    int AISymbol = -1; 
    int score = 0; 
    int[][][] testBoard;
    int testScore; 
    int AIMove; 
    int opponentMaxScore = 0; 
    int AIMaxScore = 0;
    int opponentMaxMove = 0;
    int AIMaxMove = 0; 
    
    public static void main(String[] args){
        assignment3 a3 = new assignment3();
        a3.gameStart();
    }
    
    public void gameStart(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you going first? (true or false) ");
        isAITurn = !scanner.nextBoolean();
        System.out.println("Enter the number of the row");
        numberOfRow = scanner.nextInt();
        System.out.println("Enter the number of the columne");
        numberOfColumn = scanner.nextInt();
        System.out.println("Enter the number of the connect");
        numberOfConnect = scanner.nextInt();
        
        System.out.println(isAITurn);
        System.out.println(numberOfColumn);
        System.out.println(numberOfRow);
        System.out.println(numberOfConnect);
        
        //createBoard(numberOfRow, numberOfColumn); 
        
        board = new int[numberOfRow][numberOfColumn][2];
        int[][][] board2 = board;
        System.out.println(board.length);
        System.out.println(board[0].length);
        printBoard(board);
        testBoard = new int[numberOfRow][numberOfColumn][2];
        while (!isGameOver(board, numberOfConnect)){
            if(isAITurn){
                System.out.println("AI is thinking...");
                
                for(int i = 0; i < numberOfColumn; i++){
                    //testBoard = board
                    for(int j = 0; j < numberOfRow; j++){
                        for(int k = 0; k < numberOfColumn; k++){
                            for(int l = 0; l < 2; l++){
                                testBoard[j][k][l] = board[j][k][l];
                            }
                        }
                    }
                    
                    testBoard = updateBoard(testBoard, i, -1);
                    //printBoard(testBoard);
                    System.out.println("i" + i);
                    for(int j = 0; j < numberOfColumn; j++){
                        testBoard = updateBoard(testBoard, j, 1);
                        printBoard(testBoard);
                        if(opponentMaxScore <= boardAssessment(testBoard, numberOfConnect, 1)){
                            opponentMaxScore = boardAssessment(testBoard, numberOfConnect, 1);
                            opponentMaxMove = j; 
                        }
                    }
                    if(AIMaxScore < opponentMaxScore){
                        AIMaxScore = opponentMaxScore;
                        AIMaxMove = i; 
                        move = i; 
                        System.out.println("move");
                    }
                }
                System.out.println("middle" + move);
                board = updateBoard(board, move, -1);
                printBoard(board);
                isAITurn = false; 
                /*if (isValidMove(board, move)){
                    board = updateBoard(board, move, -1);
                    printBoard(board);
                    //printMoveSequence(board);
                    isAITurn = true; 
                }
                else {
                    while(!isValidMove(board, move)){
                        System.out.println("Invalid input. Please enter the move again. ");
                        move = scanner.nextInt();
                        if (isValidMove(board, move)){
                            board = updateBoard(board, move, -1);
                            printBoard(board);
                            //printMoveSequence(board);
                            isAITurn = true; 
                        }
                    }*/
                
            }
            else {
                System.out.println("Your turn now. Enter the move: ");
                move = scanner.nextInt();
                if (isValidMove(board, move)){
                    board = updateBoard(board, move, 1);
                    printBoard(board);
                    score = boardAssessment(board, 4, 1);
                    System.out.println("score" + score);
                    score = 0; 
                    //printMoveSequence(board);
                    isAITurn = true; 
                }
                else {
                    while(!isValidMove(board, move)){
                        System.out.println("Invalid input. Please enter the move again. ");
                        move = scanner.nextInt();
                        if (isValidMove(board, move)){
                            board = updateBoard(board, move, 1);
                            printBoard(board);
                            //printMoveSequence(board);
                            isAITurn = true; 
                        }
                    }
                }
            }
        }
        if (!isAITurn)
            System.out.println("Game Over. Winner is AI");
        else
            System.out.println("Congratulation. You won the game");
    }
    
    //check if the game is over
    public boolean isGameOver(int[][][] board, int connect){
        boolean isOver; 
        int numberOfRow = board.length;
        int numberOfColumn = board[0].length;
        int numberOfConnect = connect;
        int columnCount = 0;
        int rowCount = 0;
        int diagonalCount = 0; 
        int backDiagonalCount = 0; 
        //check row win
        for(int i = 0; i < numberOfRow; i++){
            rowCount = 1; 
            for(int j = 1; j < numberOfColumn; j++){
                if(board[i][j][0] != 0 && board[i][j][0] == board[i][j-1][0]){
                    rowCount++;
                }else {
                    rowCount = 1;
                }
                if(rowCount >= numberOfConnect){
                    return true;
                }
            }
        }
        
        //check column win
        for(int i = 0; i < numberOfColumn; i++){
            columnCount = 0; 
            for(int j = 1; j < numberOfRow; j++){
                if(board[j][i][0] != 0 && board[j][i][0] == board[j-1][i][0]){
                    columnCount++;
                }else {
                    columnCount = 1;
                }
                
                if(columnCount >= numberOfConnect){
                    return true;
                }
            }
        }
        
        //check diagonal win
        for (int column = 0; column < numberOfColumn; ++column) {
            int count = 0;
            // Traverse diagonal that starts at [0][column], we start with the first row,
            // because we will compare elements with the previous one, similar to how
            // we did this for rows and columns
            for (int row = 1; row < numberOfRow; ++row) {
                // Coordinates an the diagonal change as [row + i][column + i], 
                // so we stop when column can get outside of the range
                if (column - row < 0) break;
                if (board[row][column-row][0] != 0 &&
                    board[row - 1][column - row + 1][0] == board[row][column-row][0])
                    ++count;
                else
                    count = 1;
                if (count >= numberOfConnect) return true;
            }
        }

        // There are diagonals, that starts on left of each row, let's check them
        for (int row = 0; row < numberOfRow; ++row) {
            int count = 0;
            // Traverse diagonal that starts at [row][0], we start with the first column,
            // because we will compare elements with the previous one, similar to how
            // we did this for rows and columns
            for (int column = 5; column >= numberOfColumn; --column) {
                // Coordinates an the diagonal change as [row + i][column + i], 
                // so we stop when column can get outside of the range
                if (column - row < 0) break;
                if (board[column - row][column][0] != ' ' &&
                    board[column - row - 1][column + 1][0] == board[column - row][column][0])
                    ++count;
                else
                    count = 1;
                if (count >= numberOfConnect) return true;
            }
        }
        return false;
    }
    
    //check if the move is a valid move
    public boolean isValidMove(int[][][] board, int move){
        int countEmptySpace = 0;
        for(int i = 0; i < board.length; i++){
            if (move < board[0].length){
                if(board[i][move][0] == 0){
                    countEmptySpace++;
                }
            }
        }
        if (countEmptySpace > 0 && move < board[0].length){
            return true;
        }
        else {
            return false;
        }
    }
    
    //print the board
    public void printBoard(int[][][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                System.out.format("%3d%1s", board[i][j][0], ", ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    //print the move sequence of a board
    public void printMoveSequence(int[][][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j][1]+", ");
            }
            System.out.println();
        }
    }
    
    //insert the move and update the board
    public int[][][] updateBoard(int[][][] board2, int move, int identity){
        int moveCount = 0; 
        for(int i = board2.length-1; i >= 0; i--){
            for(int j = 0; j < board2[0].length; j++){
                if(board2[i][j][0] != 0){
                    moveCount++;
                }
            }
            if(board2[i][move][0] == 0){
                board2[i][move][0] += identity; 
                board2[i][move][1] = moveCount;
                break;
            }
        }
        return board2;
    }
    
    public int[][][] updateTestBoard(int[][][] board2, int move, int identity){

        return null;
    }
    
    /*public int[][][] createBoard(int numberOfRow, int numberOfColumn){
        board = new int[numberOfColumn][numberOfRow][2];
        return board; 
    }*/
    
    
    public int boardAssessment(int[][][] board, int numberOfConnect, int identity){
        int numberOfRow = board.length;
        int numberOfColumn = board[0].length;
        int rowCount = 0;
        int columnCount = 0; 
        int diagonalCount = 0; 
        int backDiagonalCount = 0; 
        int countSpace = 0;
        int countMove = 0; 
        int countGoodMove = 0;
        int score = 0; 
        
        //score for rows
        for(int i = 0; i < numberOfRow; i++){
            for(int j = 0; j < numberOfColumn; j++){
                if(board[i][j][0] == 1) {
                    for(int k = 0; k < numberOfConnect; k++){
                        if((i - numberOfConnect) >= 0){
                            if (board[i-k][j][0] == 1){
                                countMove++;
                                countGoodMove++;
                                //System.out.println("count Move");
                            }
                            if (board[i-k][j][0] == 0){
                                countSpace++;
                                countGoodMove++;
                                //System.out.println("count space");
                            }
                            //System.out.println("what");
                        }
                        //System.out.println("countcount" + numberOfRow + "   " + (i));
                    }
                    if ((countMove + countSpace) >= numberOfConnect){
                        if (countMove == numberOfConnect){
                            score += 9999999;
                        }
                        if (countMove == numberOfConnect-1){
                            score += 10;
                        }
                        if (countMove == numberOfConnect-2){
                            score += 4;
                        }
                        if (countMove == numberOfConnect-3){
                            score += 2;
                        }
                    }
                }
                countMove = 0;
                countSpace = 0;
                countGoodMove = 0;
            }
        }
        countMove = 0;
        countSpace = 0;
        countGoodMove = 0;
        
        //score for columns
        for(int i = 0; i < numberOfRow; i++){
            for(int j = 0; j < numberOfColumn; j++){
                if(board[i][j][0] == 1) {
                    for(int k = 0; k < numberOfConnect; k++){
                        if((j + numberOfConnect) <= numberOfColumn){
                            if (board[i][j+k][0] == 1){
                                countMove++;
                                countGoodMove++;
                                //System.out.println("count Move");
                            }
                            if (board[i][j+k][0] == 0){
                                countSpace++;
                                countGoodMove++;
                                //System.out.println("count space");
                            }
                            //System.out.println("what");
                        }
                        //System.out.println("countcount" + numberOfRow + "   " + (i));
                    }
                    if ((countMove + countSpace) >= numberOfConnect){
                        if (countMove == numberOfConnect){
                            score += 9999999;
                        }
                        if (countMove == numberOfConnect-1){
                            score += 10;
                        }
                        if (countMove == numberOfConnect-2){
                            score += 4;
                        }
                        if (countMove == numberOfConnect-3){
                            score += 2;
                        }
                    }
                }
                countMove = 0;
                countSpace = 0;
                countGoodMove = 0;
            }
        }
        return score;
    }
    
    public int[][][] inputBoard(int[][][] board, int move){
        return null; 
    }
    
    public int[][][] recursive(int[][][] board, int depth){
        int[][][] maxBoard = null;
        int maxScore = 0; 
        if (depth == 0){
            for(int i = 0; i < numberOfColumn; i++){
                maxBoard = inputBoard(board, i);
                
            }
        }
        if (depth > 0){
            for(int i = 0; i < numberOfColumn; i++){
                for(int j = 0; j < numberOfColumn; j++){
                    recursive(maxBoard, depth-1);
                }
            }
        }
        return recursive(maxBoard, depth-1);
    }
    
    public void possibleAIMove(){
        for (int i = 0; i < numberOfColumn; i++){
        }
    }
    
    public void possibbleOpponentMove(){
        
    }
    
    public void firstLayer(int[][][] board, int numberOfColumn){
        
    }
}
