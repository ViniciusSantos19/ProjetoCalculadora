package br.com.estudos.calc.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.estudos.calc.enums.TipoOperacao;

public class Memoria {

	private String textoBuffer = "";
	private TipoOperacao ultimaOperacao = null;
	private boolean subistituir = false;
	private TipoOperacao tipo;
	private static Memoria instancia = new Memoria();
	private String textoAtual = "";
	private final List<MemoriaObservador> observadores = new ArrayList<>(); 
	
	private Memoria() {
		
	}
	
	public void adicionarObservador(MemoriaObservador observador) {
		observadores.add(observador);
	}
	

	public static Memoria getInstancia() {
		return instancia;
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "oi" : textoAtual;
	}

	public static String getTexto() {
		return Memoria.getInstancia().getTextoAtual();
	}
	
	public void processarComando(String texto) {
		tipo = detectarTipoDeComando(texto);
		System.out.println(tipo);
		
		if(tipo == null) {
			return;
		}else if(tipo == TipoOperacao.ZERA) {
			textoAtual = "";
			textoBuffer = "";
			subistituir = false;
			ultimaOperacao = null;
		}else if(tipo == TipoOperacao.NUMERO 
				|| tipo == TipoOperacao.VIRGULA) {
			textoAtual = subistituir ? texto : textoAtual + texto;
			subistituir = false;
		}else if(tipo == TipoOperacao.MAISMENOS && textoAtual.contains("-") && !textoAtual.isEmpty()) {
				textoAtual = textoAtual.substring(1);
		}else if(tipo == TipoOperacao.MAISMENOS && !textoAtual.contains("-") && !textoAtual.isEmpty()) {
			textoAtual = "-"+textoAtual;
		}else {
			subistituir = true;
			textoAtual = obterResultadorOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = tipo;
			
		}
		
		observadores.forEach(o -> o.valorAlterado(textoAtual));
	}

	private String obterResultadorOperacao() {
		if(ultimaOperacao == null || ultimaOperacao == TipoOperacao.IGUAL) {
			return textoAtual;
		}
		
		Double numeroBuffer = 
				Double.parseDouble(textoBuffer.replace(",", "."));
		
		Double numeroAtual = 
				Double.parseDouble(textoAtual.replace(",", "."));
		
		double resultado = 0;
		
		if(ultimaOperacao == TipoOperacao.SOMA) {
			resultado = this.soma(numeroBuffer, numeroAtual);
		}else if(ultimaOperacao == TipoOperacao.SUBTRAIR) {
			resultado = this.subtracao(numeroBuffer, numeroAtual);
		}else if(ultimaOperacao == TipoOperacao.MULTIPLICAR) {
			resultado = this.multiplicacao(numeroBuffer, numeroAtual);
		}else if(ultimaOperacao == TipoOperacao.DIVIDIR) {
			resultado = this.divisao(numeroBuffer, numeroAtual);
		}
		
		String stringResultado = Double.toString(resultado).replace(".", ",");
		boolean inteiro = stringResultado.endsWith(",0");
		return inteiro ? stringResultado.replace(",0", "") : stringResultado;
		
	}

	private TipoOperacao detectarTipoDeComando(String texto) {
		
		if(textoAtual.isEmpty() && texto == "0") {
			return null;
		}
		
		try {
			Integer.parseInt(texto);
			return TipoOperacao.NUMERO; 
		} catch (NumberFormatException e) {
			if("AC".equals(texto)) {
				return TipoOperacao.ZERA;
			}else if("/".equals(texto)) {
				return TipoOperacao.DIVIDIR;
			}else if("*".equals(texto)) {
				return TipoOperacao.MULTIPLICAR;
			}else if("-".equals(texto)) {
				return TipoOperacao.SUBTRAIR;
			}else if("+".equals(texto)) {
				return TipoOperacao.SOMA;
			}else if(",".equals(texto) && !textoAtual.contains(",")) {
				return TipoOperacao.VIRGULA;
			}else if("=".equals(texto)) {
				return TipoOperacao.IGUAL;
			}else if("+-".equals(texto)) {
				return TipoOperacao.MAISMENOS;
			}
		}
		
		return null;
		
	}
	
	private double soma(double numeroBuffer, double numeroAtual) {
		return numeroBuffer + numeroAtual;
	}
	
	private double subtracao(double numeroBuffer, double numeroAtual) {
		return numeroBuffer - numeroAtual;
	}
	
	private double multiplicacao(double numeroBuffer, double numeroAtual) {
		return numeroBuffer * numeroAtual;
	}
	
	private double divisao(double numeroBuffer, double numeroAtual) {
		return numeroBuffer / numeroAtual;
	}
	
}
