package br.com.estudos.calc.Ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Calculadora extends JFrame{

	private static final long serialVersionUID = 1L;

	public Calculadora() {
		
		organizaLayout();
		
		setResizable(false);
		setVisible(true);
		setTitle("Calculadora");
		setSize(232, 322);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	private void organizaLayout() {
		setLayout(new BorderLayout());
		
		Display display = new Display();
		display.setPreferredSize(new Dimension(233,60));
		add(display, BorderLayout.NORTH);
		
		Teclado teclado = new Teclado();
		add(teclado, BorderLayout.CENTER);
		
		
	}

	public static void main(String[] args) {
		new Calculadora();
	}
	
}
