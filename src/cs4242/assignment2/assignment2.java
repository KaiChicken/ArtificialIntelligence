/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4242.assignment2;
import java.io.FileNotFoundException;
import java.util.*;
/**
 *
 * @author Tong
 */
public class assignment2 {
    public static void main(String[] args) throws FileNotFoundException{
        assignment2 a2 = new assignment2();
    }
    Tree tree; 
    int dfsSteps;
    int bfsSteps;
    Queue<Tree> bfsQ = new LinkedList<Tree>();
    
    assignment2() throws FileNotFoundException{
        xmlParser parser = new xmlParser();
        System.out.println("Reading xml file and loading Tree");
        tree = parser.parse();
        System.out.println();
        parser.printTree(tree, 0);
    }
    
    public void start(){
        xmlParser parser = new xmlParser();
        
        
    }
    public Tree searchWithDFS(Tree tree, String string){
        dfsSteps++; 
        if (string.equals(tree.getData())){
            return tree;
        }else{
            for(int i = 0; i < tree.totalNumberOfChildren(); i++){
                Tree tree2 = searchWithDFS(tree.getChild(i), string);
                if(tree2 != null){
                    return tree2;
                }
            }
        }
        return null;
    }
    
    public Tree searchWithBFS(String response){
        bfsSteps++;
        if (bfsQ.peek().getData().equals(response)){
            return bfsQ.peek();
        }
        while(!bfsQ.isEmpty()){
            for(int i = 0; i < bfsQ.peek().totalNumberOfChildren(); i++){
                bfsSteps++;
                if(bfsQ.peek().getChild(i).getData().equals(response)){
                    return bfsQ.peek().getChild(i);
                }
                
            }
            bfsQ.poll();
        }
        return null; 
    }
    
}
