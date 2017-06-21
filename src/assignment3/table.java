/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Tong
 */
public class table extends JFrame{
    JTable table;
    public table(int numberOfColumn, int numberOfRow){
        setLayout(new FlowLayout());
        
        String[] columnNames = new String[numberOfColumn];
        for(int i = 0; i < numberOfColumn; i++){
            columnNames[i] = String.valueOf(i);
        }
        Object[][] data = new Object[numberOfColumn][numberOfRow+1];
        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,500));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setGridColor(Color.BLACK);
        add(scrollPane);
        
        
    };
    
    public static void main(String[] args){
        table gui = new table(20,20);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(600,600);
        gui.setVisible(true);
        gui.setTitle("abc");
    }
    
}
