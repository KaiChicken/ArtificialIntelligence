/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Tong
 */
public class connectR {
    public static void main (String[] args){
        int numberOfColumn = 7;
        int numberOfRow = 6; 
        int numberOfConnection = 4; 
        boolean isFirst = true; 
        int depth = 0; 
        
        int[] input = {1,2,3,4,5,1,2,3,4,5,};
        int[][] board = new int[numberOfColumn][numberOfRow];
        char[][] field = new char[7][7];
        
        for (int row = 0; row < 7; ++row) {
            System.out.print("| ");
            for (int col = 0; col < 7; ++col)
                System.out.print(field[row][col] + "| ");
            System.out.println();
        }

        // Print bottom line
        for (int col = 0; col < 7; ++col)
            System.out.print("---");
        System.out.println();
        
        
        
    }
    public void simpleGUI(){
        
    }
    
    public void possibleBoard(int[][] board, int depth){
        
    }
    
}
