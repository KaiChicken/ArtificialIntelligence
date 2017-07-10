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
public class test {
    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> a = new ArrayList();
        ArrayList<Integer> b = new ArrayList();
        a.add(0);
        a.add(1);
        a.add(2);
        a.add(3);
        b.add(0);
        b.add(1);
        b.add(2);
        b.add(3);
        al.add(a);
        al.add(b);
        for(int i = 0; i < 2; i++){
            
        }
        System.out.println(al.get(0).get(0));
    }
}
