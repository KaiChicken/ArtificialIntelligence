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
public class board {
    public int numberOfColumn = 0;
    public int numberOfRow = 0; 
    public board(){
        
    }
    public board(int[][] data){
        numberOfColumn = data.length;
        numberOfRow = data[0].length;
    }
    public void setBoard(int[][] array){
        
    }
}
