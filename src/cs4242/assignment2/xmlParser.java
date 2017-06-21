/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4242.assignment2;
import java.io.*;
import java.util.*;
/**
 *
 * @author Tong
 */
public class xmlParser {
    Scanner scanner = null;
    
    public Tree parse() throws FileNotFoundException{
        Tree root = new Tree();
        try {
            scanner = new Scanner(new File("input2.txt"));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return createTree(root);
    }
    
    public Tree createTree(Tree newTree) {
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            System.out.println(line);
            if(line.equals("")) continue;
            int isClosingTag = findString(line, "</",1);
            if(isClosingTag >= 0){
                continue; 
            }
            
            String behavior = getBehavior(line);
            if (!behavior.equals("")){
                setBehavior(newTree, behavior);
            }else {
                int first = findString(line, '<', 1);
                int second = findString(line, '>', 1);
                newTree.value = line.substring(first + 1, second); 
            }
            
        }
        scanner.close();
        return newTree;
    }
    
    //
    public void setBehavior(Tree tree, String behavior){
        Tree child = new Tree();
        child.setData(behavior);
        
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String childResponse = getResponse(line);
            String childBehavior = getBehavior(line);
            if(!childBehavior.equals("")){
                setBehavior(child, childBehavior);
            }
            else if(!childResponse.equals("")){
                setResponse(child, childResponse);
            }else{
                break;
            }
            
        }
        tree.addChild(child);
    }
    
    public void setResponse(Tree tree, String response){
        Tree child = new Tree();
        child.setData(response);
        tree.addChild(child);
    }
    
    public int findString(String line, char ch, int l){
        int pos = line.indexOf(ch);
        while(--l > 0 && pos != -1){
            pos = line.indexOf(ch, pos + 1);
            
        }
        return pos;
    }
    
    public int findString(String line, String string, int l){
        int pos = line.indexOf(string);
        while(--l > 0 && pos != -1){
            pos = line.indexOf(string, pos + 1);
        }
        return pos;
    }
    
    public String getBehavior (String string){
        String behavior="";
        int first = findString(string, '"', 1);
        int second = findString(string, '"', 2);
        if(first >= 0){
            behavior = string.substring(first + 1, second);
        }
        return behavior ;
    }
    
    public String getResponse(String string){
        String response = "";
        int first = findString(string, '"', 3);
        int second = findString(string, '"', 4);
        if(first >= 0){
            response = string.substring(first+1, second);
        }
        return response;
    }
    
    public void printTree(Tree header, int tab){
        for(int i = tab; i > 0; i--){
            System.out.print("\t");
        }
        if(header.totalNumberOfChildren() > 0){
            System.out.println("behavior = " + header.getData());
        }else{
            System.out.println("response = " + header.getData());
        }
        for(int i = 0; i < header.totalNumberOfChildren(); i++){
            printTree(header.getChild(i), tab + 1);
        }
    }
}
