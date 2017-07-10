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
    int numberOfLayer = 4;
    double[] inputLayer = new double[64];
    double[] layer1 = new double[32];
    double[] layer2 = new double[16];
    double learningRate = 0.1; 
    double[] outputLayer = new double[10];
    double[] outputLayerWeightedSum = new double[10];
    double initialLayerSum = 0.0;
    double layer1Sum = 0.0;
    double layer2Sum = 0.0;
    double outputLayerSum = 0.0; 
    double[] layer1Delta = new double[32];
    double[] layer2Delta = new double[16];
    double[] outputLayerDelta = new double[10];
    double deltaj = 0.0;
    double deltai = 0.0;
    
    
    double[][] weight = new double[65][32];
    double[][] weight2 = new double[33][16];
    double[][] weight3 = new double[17][10];
     
    public static void main(String[] args){
        handwritingRecognition hr = new handwritingRecognition();
        hr.readDataFile(hr.trainFilename);
        //hr.printData(hr.trainData);
        
        //initial weight value 
        for(int i = 0; i < 64; i++){
            for(int j = 0; j < 32; j++){
                hr.weight[i][j] = (double)0.001;
                //System.out.print(hr.weight[i][j]+", ");
            }
            //System.out.println();
        }
        //hr.weight[65][32] = 1;
        
        for(int i = 0; i < 32; i++){
            for(int j = 0; j < 16; j++){
                hr.weight2[i][j] = (double)0.001;
                //System.out.print(hr.weight2[i][j]+", ");
            }
            //System.out.println();
        }
        //hr.weight2[33][16] = 1;
        
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 10; j++){
                hr.weight3[i][j] = (double)0.001;
                //System.out.print(hr.weight3[i][j]+", ");
            }
            //System.out.println();
        }
        
        
        //enter input layer value
        for(int i = 0; i < hr.inputLayer.length; i++){
            hr.inputLayer[i] = (double)((double)hr.trainData[0][i]/16);
            //hr.inputLayer[i] = 1.0;
            System.out.print(hr.inputLayer[i]+",, ");
        }
        System.out.println();
        
        double nodeSum = 0;
        //calculate the layer1 value
        System.out.println("layer1");
        for(int k = 0; k < hr.layer1.length; k++){
            for(int i = 0; i < hr.inputLayer.length; i++){
                nodeSum += hr.inputLayer[i] * hr.weight[i][k];
                //System.out.println(hr.trainData[0][i] + "   " + hr.weight[i][k] + "    " + nodeSum);
            }
            hr.layer1[k] = nodeSum*(1/(1+Math.exp(nodeSum*-1)));
            //hr.layer1[k] = nodeSum;
            nodeSum = 0;
            System.out.print(hr.layer1[k]+", ");
        }
        System.out.println();
        
        //calculate the layer2 value
        System.out.println("layer2");
        //weight2
        for(int k = 0; k < hr.layer2.length; k++){
            for(int i = 0; i < hr.layer1.length; i++){
                nodeSum += hr.layer1[i] * hr.weight2[i][k];
                //System.out.println(hr.trainData[0][i] + "   " + hr.weight[i][k] + "    " + nodeSum);
            }
            hr.layer2[k] = nodeSum*(1/(1+Math.exp(nodeSum*-1)));
            //hr.layer2[k] = nodeSum;
            nodeSum = 0;
            System.out.print(hr.layer2[k]+", ");
        }
        System.out.println();
        
        //calculate the output layer value
        System.out.println("output layer");
        //weight3
        for(int k = 0; k < hr.outputLayer.length; k++){
            for(int i = 0; i < hr.layer2.length; i++){
                nodeSum += hr.layer2[i] * hr.weight3[i][k];
                //System.out.println(hr.trainData[0][i] + "   " + hr.weight[i][k] + "    " + nodeSum);
            }
            hr.outputLayer[k] = nodeSum*(1/(1+Math.exp(nodeSum*-1)));
            //hr.outputLayer[k] = nodeSum;
            nodeSum = 0;
            System.out.print(hr.outputLayer[k]+", ");
        }
        System.out.println();
        
        //calculate output layer percentage
        double outputSum = 0; 
        double[] outputPercentage = new double[hr.outputLayer.length];
        System.out.println("output percentage: ");
        for(int i = 0; i < hr.outputLayer.length; i++){
            outputSum += hr.outputLayer[i];
        }
        System.out.println(outputSum);
        for(int i = 0; i < hr.outputLayer.length; i++){
            outputPercentage[i] = (double)hr.outputLayer[i]/outputSum;
            System.out.print(outputPercentage[i]+",");
        }
        System.out.println();
        
    }
    
    
    //print text file
    public void printData(int[][] data){
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[0].length; j++){
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
    }
    
    
    public void initialWeight(){
        //initial weight value 
        for(int i = 0; i < 65; i++){
            for(int j = 0; j < 32; j++){
                weight[i][j] = (double)0.001;
            }
        }
        
        for(int i = 0; i < 33; i++){
            for(int j = 0; j < 16; j++){
                weight2[i][j] = (double)0.001;
            }
        }
        
        for(int i = 0; i < 17; i++){
            for(int j = 0; j < 10; j++){
                weight3[i][j] = (double)0.001;
            }
        }
    }
    
    public void feedForward(double[] input){
        //enter input layer 
        for(int i = 0; i < input.length-1; i++){
            inputLayer[i] = input[i];
        }
        inputLayer[inputLayer.length-1] = 1;
        
        double nodeSum = 0;
        //calculate the layer1 value
        for(int k = 0; k < layer1.length-1; k++){
            for(int i = 0; i < inputLayer.length; i++){
                nodeSum += inputLayer[i] * weight[i][k];
            }
            layer1[k] = sigmoidFunction(nodeSum);
            nodeSum = 0;
        }
        layer1[layer1.length-1] = 1;
        
        //calculate the layer2 value
        System.out.println("layer2");
        //weight2
        for(int k = 0; k < layer2.length-1; k++){
            for(int i = 0; i < layer1.length; i++){
                nodeSum += layer1[i] * weight2[i][k];
            }
            outputLayerWeightedSum[k] = nodeSum;
            layer1[k] = sigmoidFunction(nodeSum);
            nodeSum = 0;
        }
        layer2[layer2.length-1] = 1;
        
        //calculate the output layer value
        for(int k = 0; k < outputLayer.length-1; k++){
            for(int i = 0; i < layer2.length; i++){
                nodeSum += layer2[i] * weight3[i][k];
            }
            outputLayer[k] = sigmoidFunction(nodeSum);
            nodeSum = 0;
        }
        
        
        for(int i = 0; i < layer1.length; i++){
            layer1Sum += layer1[i];
        }
        
        for(int i = 0; i < layer2.length; i++){
            layer2Sum += layer1[i];
        }
        
        for(int i = 0; i < outputLayer.length; i++){
            outputLayerSum += outputLayer[i];
        }
        //calculate output layer percentage
        /*double outputSum = 0; 
        double[] outputPercentage = new double[outputLayer.length];
        System.out.println("output percentage: ");
        for(int i = 0; i < outputLayer.length; i++){
            outputSum += outputLayer[i];
        }
        System.out.println(outputSum);
        for(int i = 0; i < outputLayer.length; i++){
            outputPercentage[i] = (double)outputLayer[i]/outputSum;
            System.out.print(outputPercentage[i]+",");
        }
        System.out.println();*/
        
    }
    
    public void backPropagation(){
        for(int i = 0; i < outputLayer.length; i++){
            deltaj = sigmoidFunctionDerivative(outputLayerWeightedSum[i]) * (outputLayerSum-outputLayer[i]);
            for(int j = 0; j < layer2.length; j++){
                weight3[j][i] = weight3[j][i] + learningRate * layer2[j] * deltaj;
            }
        }
        
        for(int i = 0; i < layer2.length; i++){
            deltai = sigmoidFunctionDerivative(layer2[i]) * (outputLayerSum-outputLayer[i]);
            for(int j = 0; j < layer1.length; j++){
                weight2[j][i] = weight2[j][i] + learningRate * layer1[j] * weight2[j][i] * deltai;
            }
        }
        
        for(int i = 0; i < outputLayer.length; i++){
            for(int j = 0; j < layer2.length; j++){
                weight2[j][i] = weight2[j][i] + learningRate * layer2[j] * weight2[j][i] * sigmoidFunctionDerivative(outputLayerWeightedSum[i]) * (outputLayerSum-outputLayer[i]);
            }
        }
    }
    
    //sigmoid function
    public double sigmoidFunction(double neuron){
        return (1/(1+Math.exp(neuron*-1)));
    }
    
    //derivative of sigmoid function
    public double sigmoidFunctionDerivative(double x){
        return Math.exp(-x)/(Math.pow((1+Math.exp(-x)),2));
    }
    
    
    public void readTrainFile(){
    }
    //read text file
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
    
    
    //training
    public void training(double[] example, double[][] weight, double[][] weight2, double[][] weight3 ){
        
        
    }
    
}
