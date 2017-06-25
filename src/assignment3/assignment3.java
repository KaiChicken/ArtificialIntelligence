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
    
    public static void main(String[] args){
        assignment3 a3 = new assignment3();
        a3.gameStart();
    }
    
    public void gameStart(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you going first? (true or false) ");
        isAITurn = !scanner.nextBoolean();
        System.out.println("Enter the number of the columne");
        numberOfColumn = scanner.nextInt();
        System.out.println("Enter the number of the row");
        numberOfRow = scanner.nextInt();
        System.out.println("Enter the number of the connect");
        numberOfConnect = scanner.nextInt();
        
        System.out.println(isAITurn);
        System.out.println(numberOfColumn);
        System.out.println(numberOfRow);
        System.out.println(numberOfConnect);
        
        createBoard(numberOfRow, numberOfColumn); 
        while (!isGameOver()){
            if(isAITurn){
                
                
                
                while(!isValidMove(move)){
                    
                }
                isAITurn = false; 
            }
            else {
                System.out.println("Enter the move: ");
                move = scanner.nextInt();
                if (isValidMove(move)){
                    updateBoard(move);
                }
                while(!isValidMove(move)){
                    System.out.println("Invalid input. Please enter the move again. ");
                    move = scanner.nextInt();
                    if (isValidMove(move)){
                        updateBoard(move); 
                        isAITurn = true; 
                    }
                }
            }
        }
        if (!isAITurn)
            System.out.println("Game Over. Winner is AI");
        else
            System.out.println("Congratulation. You won the game");
    }
    
    public boolean isGameOver(){
        int columnCount = 0;
        int rowCount = 0; 
        int diagonalCount = 0; 
        for(int i = 0; i < numberOfRow; i++){
            rowCount = 0; 
            for(int j = 1; j < numberOfColumn; j++){
                if(board[i][j][0] != 0 && board[i][j][0] == board[i][j-1][0]){
                    rowCount++;
                }else {
                    rowCount = 1;
                }
                
                if(rowCount > numberOfConnect){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isValidMove(int move){
        return true;
    }
    
    public void updateBoard(int move){
        
    }
    
    public int[][][] createBoard(int numberOfRow, int numberOfColumn){
        board = new int[numberOfColumn][numberOfRow][2];
        return board; 
    }
    
    public int boardAssessment(int[][][] board){
        return 0; 
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
                if (maxScore < boardAssessment(maxBoard)){
                    maxScore = boardAssessment(maxBoard);
                }
            }
        }
        if (depth > 0){
            for(int i = 0; i < numberOfColumn; i++){
                for(int j = 0; j < numberOfColumn; j++){
                    recursive(board, depth);
                }
            }
        depth -= 1;
        }
        return null; 
    }
    
    public void possibleAIMove(){
        for (int i = 0; i < numberOfColumn; i++){
        }
    }
    
    public void possibbleOpponentMove(){
        
    }
}
