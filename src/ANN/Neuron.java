/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;
import java.util.*;

/**
 *
 * @author tom_c
 */
public class Neuron {
    private static final int bias = 1; 
    private static final double alpha = 0.1;
    
    private ArrayList<Integer> input;
    private ArrayList<Double> weights;
    private double biasWeight;
    private double output;
    
    public Neuron(){
        this.input = new ArrayList<>();
        this.weights = new ArrayList<>();
        this.biasWeight = Math.random();
        this.output = 0.0;
    }
    
    public void setInput(ArrayList<Integer> input){
        this.input = new ArrayList<>(input);
        if(this.input.size() == 0 ){
            generateWeights();
        }
    }
    
    public void generateWeights(){
        for(int i = 0; i < input.size(); i++){
            weights.add(Math.random()/100);
        }
    }
    
    public void calculateOutput(){
        double outputSum = 0;
        for(int i = 0; i < this.input.size(); i++){
            outputSum = this.input.get(i) * this.weights.get(i);
        }
        outputSum += bias * biasWeight;
        this.output = sigmoidFunction(outputSum);
    }
    
    public double sigmoidFunction(double input){
        return (1/(1+Math.exp(input)));
    }
    public double sigmoidFunction(int input){
        return (1/(1+Math.exp((double)input)));
    }
    
    public void adjustWeight()
    
}
