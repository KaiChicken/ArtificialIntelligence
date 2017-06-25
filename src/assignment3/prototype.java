/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.*;

/**
 *
 * @author tom_c
 */
public class prototype {
    int numberOfColumn;
    int numberOfRow;
    int numberOfConnect; 
    boolean isAITurn;
    int[][][] board; 
    int move; 
    
    public static void main(String[] args){
        
    }
    
    public void gameStart(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the columne");
        isAITurn = scanner.nextBoolean();
        System.out.println("Enter the number of the columne");
        numberOfColumn = scanner.nextInt();
        System.out.println("Enter the number of the columne");
        numberOfRow = scanner.nextInt();
        System.out.println("Enter the number of the columne");
        numberOfConnect = scanner.nextInt();
        
        
        createBoard(); 
        while (!isGameOver()){
            if(isAITurn){
                
                
                
                while(!isValidMove(move)){
                    
                }
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
                    }
                }
            }
        }
        
        
    }
    
    public boolean isGameOver(){
        return false;
    }
    
    public boolean isValidMove(int move){
        return true;
    }
    
    public void updateBoard(int move){
        
    }
    
    public void createBoard(){
        
    }
    
    public int boardAssessment(){
        return 0; 
    }
    
    
}
