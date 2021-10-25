package operacoes;

import java.math.BigDecimal;

public class OperacoesMatematicasUtil {
	
	public static final String SOMA = "SOMA";
	public static final String SUBTRACAO = "SUBTRACAO";
	public static final String MULTIPLICACAO = "MULTIPLICACAO";
	
	public static final String BINARIO = "BINARIO";
	public static final String DECIMAL = "DECIMAL";
	public static final String HEXADECIMAL = "HEXADECIMAL";

	private static String valorAtual = null;  
	private static String tipoAtual = null;
	private static String operacaoAtual = "";
	
	/**
	 * 
	 * Realiza a soma dos números decimais 
	 *
	 * @author Mayara
	 * @param numeroDigitado
	 */
	private static void somar(BigDecimal numeroDigitado) {
		var valor = new BigDecimal(valorAtual);
		valorAtual = String.valueOf(valor.add(numeroDigitado));
	}
	
	/**
	 * 
	 * Realiza a subtração dos números decimais
	 *
	 * @author Mayara
	 * @param numeroDigitado
	 */
	private static void subtracao(BigDecimal numeroDigitado) {
		var valor = new BigDecimal(valorAtual);
		valorAtual = String.valueOf(valor.subtract(numeroDigitado));
	}

	/**
	 * 
	 * Realiza a multiplicação dos números decimais
	 *
	 * @author Mayara
	 * @param numeroDigitado
	 */
	private static void multiplicacao(BigDecimal numeroDigitado) {
		var valor = new BigDecimal(valorAtual);
		valorAtual = String.valueOf(valor.multiply(numeroDigitado));
	}

	/**
	 * 
	 * Realiza a verificação de qual operação será feita 
	 * 
	 * SOMA
	 * SUBTRAÇAO
	 * MULTIPLIÇAO
	 *
	 * @author Mayara
	 * @param numeroDigitado
	 */
	public static void calcular(BigDecimal numeroDigitado) {
		switch (OperacoesMatematicasUtil.operacaoAtual) {
		
		case OperacoesMatematicasUtil.SOMA: {
			OperacoesMatematicasUtil.somar(numeroDigitado);
			break;
		}
		case OperacoesMatematicasUtil.SUBTRACAO: {
			OperacoesMatematicasUtil.subtracao(numeroDigitado);
			break;
		}
		case OperacoesMatematicasUtil.MULTIPLICACAO: {
			OperacoesMatematicasUtil.multiplicacao(numeroDigitado);
			break;
		}
		default:
			throw new IllegalArgumentException("Opera��o inv�lida: " + OperacoesMatematicasUtil.operacaoAtual);
		}
	}
	
	/**
	 * 
	 * Realiza potenciação 
	 *
	 * @author Mayara
	 * @param base
	 * @param potencia
	 * @return total
	 */
	public static Integer realizarPotenciacao(Integer base, Integer potencia) {
		if (potencia == 0 || potencia == 1) {
			return base;
		}
		
		int total = base;
		for (var i = 1; i < potencia; i++) {
			total *= base;
		}
		return total;
	}

	public static String getValorAtual() {
		return valorAtual;
	}

	public static void setValorAtual(String valorAtual) {
		OperacoesMatematicasUtil.valorAtual = valorAtual;
	}

	public static String getOperacao() {
		return operacaoAtual;
	}

	public static void setOperacao(String operacao) {
		OperacoesMatematicasUtil.operacaoAtual = operacao;
	}
	
	public static String getTipoAtual() {
		return tipoAtual;
	}

	public static void setTipoAtual(String tipoAtual) {
		OperacoesMatematicasUtil.tipoAtual = tipoAtual;
	}

	private OperacoesMatematicasUtil() {
		throw new IllegalStateException("Classe Utilitária");
	}

}
