/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SARSA;
import java.util.*;
/**
 *
 * @author Tong
 */
public class Grid {
    //state: wall = -1, normal grid = 0 ; goal = 1; 
    //direction: up = 0, right = 1; down = 2, left = 3
    int row;
    int column; 
    double topWeight;
    double rightWeight;
    double leftWeight;
    double bottomWeight;
    int direction; 
    int state; 
    int reward;
    double epsilonValue = 0.2; 
    
    public Grid(int numberOfRow, int numberOfColumn){
        this.row = numberOfRow;
        this.column = numberOfColumn;
        this.topWeight = 0.0;
        this.rightWeight = 0.0;
        this.leftWeight = 0.0;
        this.bottomWeight = 0.0;
        this.direction = 0; 
        this.state = 0; 
        this.reward = 0;
    }
    
    //set the weight within the grid
    public void setTopWeight(double value){
        this.topWeight = value; 
    }
    public void setRightWeight(double value){
        this.rightWeight = value; 
    }
    public void setBottmWeight(double value){
        this.bottomWeight = value; 
    }
    public void setLeftWeight(double value){
        this.leftWeight = value; 
    }
    
    //get weight from the grid
    public double getTopWeight(){
        return topWeight; 
    }
    public double getRightWeight(){
        return rightWeight; 
    }
    public double getBottomWeight(){
        return bottomWeight; 
    }
    public double getLeftWeight(){
        return leftWeight; 
    }
    
    // up = 0, right = 1; down = 2, left = 3
    public void setDirection(int i){
        this.direction = i; 
    }
    
    //get direction that have max utility
    public int getDirection(){
        double maxUtility = 0.0;
        int direction = 0; 
        if(topWeight > maxUtility){
            direction = 0;
        }
        if(rightWeight > maxUtility){
            direction = 1;
        }
        if(bottomWeight > maxUtility){
            direction = 2;
        }
        if(rightWeight > maxUtility){
            direction = 3;
        }
        return direction; 
    }
    
    public int epsilonGreedyDirection(){
        double maxUtility; 
        double random = Math.random();
        if(random > epsilonValue){
            return 
        }
        else if(random < epsilonValue){
            int randomDirection = Math.random();
        }
    }
}
