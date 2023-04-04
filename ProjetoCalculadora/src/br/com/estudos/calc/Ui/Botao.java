package br.com.estudos.calc.Ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton{

	public Botao(String texto, Color color) {
		
		setOpaque(true);
		setFont(new Font("courier", Font.PLAIN, 30));
		setForeground(Color.WHITE);
		setBackground(color);
		setText(texto);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
	
}
