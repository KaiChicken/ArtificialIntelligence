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
    int justACounter = 0;
    int numberOfLine;
    int[][] trainData;
    int[][] testData;
    double learningRate = 0.1; 
    ArrayList<String> data = new ArrayList<String>();
    private final String trainFilename = "optdigits_train.txt";
    private final String testFilename = "optdigits_test.txt";
    int numberOfLayer = 4;
    int expectedOutput = 0;
    int actualOutput = 0;
    
    double[] inputLayer = new double[65];
    double[] layer1 = new double[33];
    double[] layer2 = new double[17];
    double[] outputLayer = new double[10];
    
    double[] testInputLayer = new double[65];
    double[] testLayer1 = new double[33];
    double[] testLayer2 = new double[17];
    double[] testOutputLayer = new double[10];
    
    double[] inputLayerWeightedSum = new double[65];
    double[] layer1WeightedSum = new double[33];
    double[] layer2WeightedSum = new double[17];
    double[] outputLayerWeightedSum = new double[10];
    
    double initialLayerSum = 0.0;
    double layer1Sum = 0.0;
    double layer2Sum = 0.0;
    double outputLayerSum = 0.0; 
    
    double[] inputLayerDelta = new double[65];
    double[] layer1Delta = new double[33];
    double[] layer2Delta = new double[17];
    double[] outputLayerDelta = new double[10];
    
    double deltaj = 0.0;
    double deltai = 0.0;
    
    double[][] weight = new double[65][32];
    double[][] weight2 = new double[33][16];
    double[][] weight3 = new double[17][10];

     
    public static void main(String[] args){
        handwritingRecognition hr = new handwritingRecognition();
        hr.readDataFile(hr.trainFilename);
        hr.readTestFile(hr.testFilename);
        //hr.printData(hr.trainData);
        //hr.printData(hr.testData);
        /*for(int i = 0; i < hr.testData.length; i++){
            for(int j = 0; j < hr.testData[0].length; j++){
                System.out.print(hr.testData[i][j]);
            }
            System.out.println();
        }*/
        
        hr.initialWeight();
        
        System.out.println("weight3");
        for(int i = 0; i < hr.weight3.length; i++){
            for(int j = 0; j < hr.weight3[0].length; j++){
                System.out.print(hr.weight3[i][j] + ", ");
            }
            System.out.println();
        }
        //train data
        for(int i = 0; i < 1; i++){
            //if((int)hr.trainData[i][64] == 1){
                double[] in = new double[hr.trainData[i].length];
                //System.out.println("try " + i);
                for(int j = 0; j < hr.trainData[i].length; j++){
                    in[j] = hr.trainData[i][j];
                }
                hr.feedForward(in);
                hr.backPropagation((int)in[64]);
                hr.outputLayerSum =0.0;
                hr.layer1Sum = 0.0;
                hr.layer2Sum = 0.0;
            //}
        }
        
        //test
        /*
        int correctCounter = 0; 
        int numberOfTest = 0; 
        for(int i = 0; i < hr.testData.length; i++){
            double[] test = new double[hr.testData[i].length];
            for(int j = 0; j < hr.testData[i].length; j++){
                test[j] = hr.testData[i][j];
            }
            hr.test(test);
            hr.expectedOutput = (int)test[64];
            //System.out.println("expected "+hr.expectedOutput + " vs actual " + hr.actualOutput);
            if(hr.expectedOutput == hr.actualOutput){
               correctCounter++;
            }
            numberOfTest++;
            outputLayerSum = 0.0;
        }
        
        System.out.println("number of input tested: " + numberOfTest);
        System.out.println("number of correct answer: " + correctCounter);
        System.out.println("correct answer rate " + (double)(correctCounter)/(double)(numberOfTest));
        correctCounter =0;
        numberOfTest = 0;
        */
        
        
        System.out.println("weight3");
        for(int i = 0; i < hr.weight3.length; i++){
            for(int j = 0; j < hr.weight3[0].length; j++){
                System.out.print(hr.weight3[i][j] + ", ");
            }
            System.out.println();
        }
        
        System.out.println("weight2");
        for(int i = 0; i < hr.weight2.length; i++){
            for(int j = 0; j < hr.weight2[0].length; j++){
                System.out.print(hr.weight2[i][j] + ", ");
            }
            System.out.println();
        }
        
        System.out.println("weight");
        for(int i = 0; i < hr.weight.length; i++){
            for(int j = 0; j < hr.weight[0].length; j++){
                System.out.print(hr.weight[i][j] + ", ");
            }
            System.out.println();
        }
        
        
        double layerSum = 0.0;
        /*
        System.out.println("\ninput layer");
        for(int i = 0; i < hr.inputLayer.length; i++){
            System.out.print(hr.inputLayer[i]+", ");
            layerSum += hr.inputLayer[i];
        }
        System.out.println("\n"+layerSum);
        layerSum = 0.0;
        
        System.out.println("\n\nlayer1");
        for(int i = 0; i < hr.layer1.length; i++){
            System.out.print(hr.layer1[i]+", ");
            layerSum += hr.layer1[i];
        }
        System.out.println("\n"+layerSum);
        layerSum = 0.0;
        
        System.out.println("\n\nlayer2");
        for(int i = 0; i < hr.layer2.length; i++){
            System.out.print(hr.layer2[i]+", ");
            layerSum += hr.layer2[i];
        }
        System.out.println("\n"+layerSum);
        layerSum = 0.0;
        */
        /*
        System.out.println("\n\noutputLayer");
        for(int i = 0; i < hr.outputLayer.length; i++){
            System.out.print(hr.outputLayerWeightedSum[i]+", ");
            layerSum += hr.outputLayerWeightedSum[i];
        }
        System.out.println("\n"+layerSum);
        layerSum = 0.0;
        
        System.out.println("\n\noutput layer weighted");
        for(int i = 0; i < hr.outputLayer.length; i++){
            //System.out.print(hr.sigmoidFunctionDerivative(hr.outputLayerWeightedSum[i])+", ");
            System.out.print(hr.outputLayerWeightedSum[i]+", ");
        }
        */
        System.out.println("\n\noutput layer");
        for(int i = 0; i < hr.outputLayer.length; i++){
            System.out.print(hr.outputLayer[i]+", ");
        }
        
        System.out.println("\n\noutput delta");
        for(int i = 0; i < hr.outputLayerDelta.length; i++){
            System.out.print(hr.outputLayerDelta[i]+", ");
        }
    }
    
    //test 
    public void test(double[] input){
        //enter input layer 
        for(int i = 0; i < input.length-1; i++){
            testInputLayer[i] = input[i]/16;
        }
        testInputLayer[testInputLayer.length-1] = 1;
        
        double nodeSum = 0;
        //calculate the layer1 value
        for(int k = 0; k < testLayer1.length-1; k++){
            for(int i = 0; i < testInputLayer.length; i++){
                nodeSum += testInputLayer[i] * weight[i][k];
            }
            testLayer1[k] = sigmoidFunction(nodeSum);
            nodeSum = 0;
        }
        testLayer1[testLayer1.length-1] = 1;
        
        //calculate the layer2 value
        //System.out.println("layer2");
        //weight2
        for(int k = 0; k < testLayer2.length-1; k++){
            for(int i = 0; i < testLayer1.length; i++){
                nodeSum += testLayer1[i] * weight2[i][k];
            }
            testLayer2[k] = sigmoidFunction(nodeSum);
            nodeSum = 0;
        }
        testLayer2[testLayer2.length-1] = 1;
        
        //calculate the output layer value
        for(int k = 0; k < testOutputLayer.length-1; k++){
            for(int i = 0; i < testLayer2.length; i++){
                nodeSum += testLayer2[i] * weight3[i][k];
            }
            outputLayerWeightedSum[k] = nodeSum;
            testOutputLayer[k] = sigmoidFunction(nodeSum);
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
        
        double maxPercentage =0.0;
        //calculate output layer percentage
        double outputSum = 0; 
        double[] outputPercentage = new double[testOutputLayer.length];
        //System.out.println("output percentage: ");
        for(int i = 0; i < testOutputLayer.length; i++){
            outputSum += testOutputLayer[i];
        }
        //System.out.println(outputSum);
        for(int i = 0; i < testOutputLayer.length; i++){
            outputPercentage[i] = (double)testOutputLayer[i]/outputSum;
            //System.out.print(outputPercentage[i]+",");
        }
        for(int i = 0; i < testOutputLayer.length; i++){
            if(outputPercentage[i] > maxPercentage){
                maxPercentage = outputPercentage[i];
                this.actualOutput = i;
            }
            //System.out.println("output p "+i+" "+outputPercentage[i]);
        }
        outputSum = 0;
        //System.out.println("actual" + actualOutput);
        //System.out.println();
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
                weight[i][j] = (double)0.01;
                //weight[i][j] = (double)Math.pow(Math.random(), 5);
            }
        }
        
        for(int i = 0; i < 33; i++){
            for(int j = 0; j < 16; j++){
                weight2[i][j] = (double)0.01;
            }
        }
        
        for(int i = 0; i < 17; i++){
            for(int j = 0; j < 10; j++){
                weight3[i][j] = (double)0.01;
            }
        }
    }
    
    public void feedForward(double[] input){
        //enter input layer 
        for(int i = 0; i < input.length-1; i++){
            inputLayer[i] = input[i]/16;
            inputLayerWeightedSum[i] = input[i]/16;
        }
        inputLayer[inputLayer.length-1] = 1;
        inputLayerWeightedSum[inputLayerWeightedSum.length-1] = 1;
        
        double nodeSum = 0;
        //calculate the layer1 value
        for(int k = 0; k < layer1.length-1; k++){
            for(int i = 0; i < inputLayer.length; i++){
                nodeSum += inputLayer[i] * weight[i][k];
            }
            layer1WeightedSum[k] = nodeSum; 
            layer1[k] = sigmoidFunction(nodeSum);
            nodeSum = 0;
        }
        layer1[layer1.length-1] = 1;
        layer1WeightedSum[layer1WeightedSum.length-1] = 1;
        
        //calculate the layer2 value
        //System.out.println("layer2");
        //weight2
        for(int k = 0; k < layer2.length-1; k++){
            for(int i = 0; i < layer1.length; i++){
                nodeSum += layer1[i] * weight2[i][k];
            }
            layer2WeightedSum[k] = nodeSum; 
            layer2[k] = sigmoidFunction(nodeSum);
            nodeSum = 0;
        }
        layer2[layer2.length-1] = 1;
        layer2WeightedSum[layer2WeightedSum.length-1] = 1;
        
        //calculate the output layer value
        for(int k = 0; k < outputLayer.length; k++){
            for(int i = 0; i < layer2.length; i++){
                nodeSum += layer2[i] * weight3[i][k];
            }
            outputLayerWeightedSum[k] = nodeSum;
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
    
    public void backPropagation(int expectedOutput){
        /*for(int i = 0; i < outputLayer.length; i++){
            deltaj = sigmoidFunctionDerivative(outputLayerWeightedSum[i]) * (outputLayerSum-outputLayer[i]);
            for(int j = 0; j < layer2.length; j++){
                weight3[j][i] = weight3[j][i] + learningRate * layer2[j] * deltaj;
            }
        }*/
        for(int i = 0; i < outputLayer.length; i++){
            if (i == expectedOutput){
                outputLayerDelta[i] = sigmoidFunctionDerivative(outputLayerWeightedSum[i]) * (1-outputLayer[i]);
                //System.out.println("what what" + sigmoidFunctionDerivative(outputLayerWeightedSum[i]));
            }else{
                outputLayerDelta[i] = sigmoidFunctionDerivative(outputLayerWeightedSum[i]) * (0-outputLayer[i]);
            }
        }

        for(int i = 0; i < layer2.length; i++){
            double weight3Sum = 0.0; 
            for(int j = 0; j < weight3[0].length; j++){
                weight3Sum += weight3[i][j] * outputLayerDelta[j];
            }
            layer2Delta[i] = sigmoidFunctionDerivative(layer2WeightedSum[i]) * weight3Sum;
            weight3Sum = 0.0;
        }
        
        for(int i = 0; i < layer1.length; i++){
            double weight2Sum = 0.0; 
            for(int j = 0; j < weight2[0].length; j++){
                weight2Sum += weight2[i][j] * layer2Delta[j];
            }
            layer1Delta[i] = sigmoidFunctionDerivative(layer1WeightedSum[i]) * weight2Sum;
            weight2Sum = 0.0;
        }
        
        for(int i = 0; i < inputLayer.length; i++){
            double weight1Sum = 0.0; 
            for(int j = 0; j < weight[0].length; j++){
                weight1Sum += weight[i][j] * layer1Delta[j];
            }
            inputLayerDelta[i] = sigmoidFunctionDerivative(inputLayerWeightedSum[i]) * weight1Sum;
            weight1Sum = 0.0;
        }
        
        //System.out.println("weight3 " + weight3.length + " layer2 " + layer2.length);
        for(int i = 0; i < weight3.length; i++){
            for(int j = 0; j < weight3[0].length; j++){
                weight3[i][j] = weight3[i][j] + learningRate * layer2[i] * outputLayerDelta[j];
            }
        }
        
        for(int i = 0; i < weight2.length; i++){
            for(int j = 0; j < weight2[0].length; j++){
                weight2[i][j] = weight2[i][j] + learningRate * layer1[i] * layer2Delta[j];
            }
        }
        
        for(int i = 0; i < weight.length; i++){
            for(int j = 0; j < weight[0].length; j++){
                weight[i][j] = weight[i][j] + learningRate * inputLayer[i] * layer1Delta[j];
            }
        }
    }
    
    //sigmoid function
    public double sigmoidFunction(double neuron){
        return (1/(1+Math.exp(neuron*-1)));
    }
    
    //derivative of sigmoid function
    public double sigmoidFunctionDerivative(double x){
        return Math.exp(x*(double)-1)/(Math.pow((1+Math.exp(x*(double)-1)),2));
    }
    
    //read test data file
    public void readTestFile(String filename){
        String line; 
        int countLine = 0; 
        int rowCounter = 0;
        int columnCounter = 0;
        data.clear();
        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null){
                countLine++;
                data.add(line);
            }
            testData = new int[countLine][65];
            
            for(String a: data){
                for(String b:a.split(",")){
                    testData[rowCounter][columnCounter] = Integer.parseInt(b); 
                    //System.out.print(Integer.parseInt(b)+", ");
                    columnCounter++;
                }
                columnCounter = 0;
                //System.out.println();
                rowCounter++;
            }
            rowCounter = 0; 
            columnCounter = 0;
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");                
        }
        catch(IOException e){
            
        }
        countLine = 0;
    }
    
    
    //read text file
    public void readDataFile(String filename){
        String line; 
        int countLine = 0; 
        int rowCounter = 0;
        int columnCounter = 0;
        data.clear();
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
