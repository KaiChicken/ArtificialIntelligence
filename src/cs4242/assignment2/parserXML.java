/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4242.assignment2;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Tong
 */
public class parserXML {
    public static void main(String [] args) throws FileNotFoundException{
        parserXML p = new parserXML();
        p.parse();
    }
    Scanner scanner = null;
    public Tree parse() throws FileNotFoundException{
        Tree root = new Tree();
        try {
            scanner = new Scanner(new File("input2.txt"));
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public parserXML(){
        
    }
}
