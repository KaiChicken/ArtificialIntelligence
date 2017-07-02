/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handwritingRecognition;

import java.io.*;
import java.util.*;

/**
 *
 * @author tom_c
 */
public class handwritingRecognition {
    int numberOfLine;
    int[][] trainData;
    int[][] testData;
    ArrayList<String> data = new ArrayList<String>();
    private final String trainFilename = "optdigits_train.txt";
    private final String testFilename = "optdigits_test.txt";
    
    public static void main(String[] args){
        handwritingRecognition hr = new handwritingRecognition();
        hr.readDataFile(hr.trainFilename);
        hr.printData();
    }
    public void printData(){
        for(int i = 0; i < trainData.length; i++){
            for(int j = 0; j < trainData[0].length; j++){
                System.out.print(trainData[i][j]);
            }
            System.out.println();
        }
    }
    public void readTrainFile(){
    }
    public void readDataFile(String filename){
        String line; 
        int countLine = 0; 
        int rowCounter = 0;
        int columnCounter = 0;
        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null){
                countLine++;
                data.add(line);
            }
            trainData = new int[countLine][65];
            
            
            for(String a: data){
                for(String b:a.split(",")){
                    trainData[rowCounter][columnCounter] = Integer.parseInt(b); 
                    //System.out.print(Integer.parseInt(b)+", ");
                    columnCounter++;
                }
                columnCounter = 0;
                //System.out.println();
                rowCounter++;
            }
            rowCounter = 0; 
            
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");                
        }
        catch(IOException e){
            
        }
        countLine = 0;
        
    }
    
}
