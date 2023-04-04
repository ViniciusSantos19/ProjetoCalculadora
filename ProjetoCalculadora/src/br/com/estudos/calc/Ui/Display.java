package br.com.estudos.calc.Ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.estudos.calc.modelo.Memoria;
import br.com.estudos.calc.modelo.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador{
	
	private final JLabel numeros;
	
	public Display() {
		Memoria.getInstancia().adicionarObservador(this);
		numeros = new JLabel(Memoria.getInstancia().getTextoAtual());
		numeros.setForeground(Color.WHITE);
		numeros.setFont(new Font("courier", Font.PLAIN, 30));
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
		setBackground(Color.BLACK);
		add(numeros);
	}

	@Override
	public void valorAlterado(String valor) {
		this.numeros.setText(valor);
		
	}
	
}
