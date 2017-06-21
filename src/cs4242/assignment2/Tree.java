/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4242.assignment2;
import java.util.*;
/**
 *
 * @author Tong
 */
public class Tree {
    public String value = "";
    private List<Tree> children = new ArrayList<Tree>();
    
    public void setData(String value){
        this.children = new ArrayList<>();
        this.value = value;
    }
    
    public void addChild(Tree child){
        children.add(child);
    }
    
    public String getData(){
        return value;
    }
    
    public List<Tree> getChildren(){
        return children;
    }
    
    public Tree getChild(int i){
        return children.get(i);
    }
    
    public int totalNumberOfChildren(){
        return children.size();
    }
    
    public Boolean isEmpty(){
        return null;
    }
    
}
