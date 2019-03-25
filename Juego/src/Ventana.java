//import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.lang.Thread.*;


public class Ventana extends JFrame {
		
	public Ventana() {	
		
        setTitle("Programación Concurrente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1005,1030);
        setLocationRelativeTo(null);
        setResizable(false);
        Juego p=new Juego();         
        setVisible(true);	 
		setContentPane(p);

	}
	
	public static void main(String args[]){
        new Ventana();       
    }
}
