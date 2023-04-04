package br.com.estudos.calc.Ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.estudos.calc.modelo.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener{

	private final Color cinzaEscuro = Color.DARK_GRAY;
	private final Color laranja = Color.ORANGE;
	private final Color cinzaClaro = Color.LIGHT_GRAY;
	
	public Teclado() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		setLayout(layout);
		
		//linha 0
		c.gridwidth = 2;
		adicionarBotao("AC", cinzaEscuro ,c ,0,0);
		c.gridwidth = 1;
		adicionarBotao("+-", cinzaEscuro, c, 2, 0);
		adicionarBotao("/", laranja ,c ,3,0);
		
		//linha 1
		adicionarBotao("7", cinzaClaro, c, 0, 1);
		adicionarBotao("8", cinzaClaro, c, 1, 1);
		adicionarBotao("9", cinzaClaro, c, 2, 1);
		adicionarBotao("*", laranja, c, 3, 1);
		
		//linha 2
		adicionarBotao("4", cinzaClaro, c, 0, 2);
		adicionarBotao("5", cinzaClaro, c, 1, 2);
		adicionarBotao("6", cinzaClaro, c, 2, 2);
		adicionarBotao("-", laranja, c, 3, 2);
		
		//linha 3
		adicionarBotao("1", cinzaClaro, c, 0, 3);
		adicionarBotao("2", cinzaClaro, c, 1, 3);
		adicionarBotao("3", cinzaClaro, c, 2, 3);
		adicionarBotao("+", laranja, c, 3, 3);
		
		//linha 4
		c.gridwidth = 2;
		adicionarBotao("0", cinzaClaro, c, 0, 4);
		c.gridwidth = 1;
		adicionarBotao(",", cinzaClaro, c, 2, 4);
		adicionarBotao("=", laranja, c, 3, 4);
	
	}

	private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
		
		Botao botao = new Botao(texto, cor);
		c.gridx = x;
		c.gridy = y;	
		botao.addActionListener(this);
		add(botao, c);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() instanceof JButton) {
			JButton jButton = (JButton)e.getSource();
			Memoria.getInstancia().processarComando(jButton.getText());
		}
		
	}
	
}
