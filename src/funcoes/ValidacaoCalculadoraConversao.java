package funcoes;

import java.math.BigDecimal;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import operacoes.ConversaoUtil;
import operacoes.OperacoesMatematicasUtil;

public class ValidacaoCalculadoraConversao {
	
	private static String textoDigitadoValidacao = null; 
	
	private ValidacaoCalculadoraConversao() {
	}

	/**
	 * 
	 * Se estiver selecionado o tipo decimal � executado 
	 * o m�todo atual de adi��o da pontua��o milhar se n�o 
	 * retorna o pr�prio texto
	 *
	 * @author Comandolli
	 * @param textoDigitado
	 * @return textoAuxiliar
	 */
	public static String adicionarPontuacaoMilhar(String textoDigitado, JRadioButton rdbEntradaDecimal) {
		String textoSemZerosIniciais = textoDigitado;  
		var textoAuxiliar = new StringBuilder();
		var zerosInicio = new StringBuilder();
		
		if(rdbEntradaDecimal.isSelected()) {
			if(textoDigitado.startsWith("0")) {
				while (textoSemZerosIniciais.startsWith("0")) {
					zerosInicio.append("0");
					textoSemZerosIniciais = textoSemZerosIniciais.replace("0", "");
				}
			}
			
			textoSemZerosIniciais = textoSemZerosIniciais.replace(".", "");
			
			while (textoSemZerosIniciais.length() >= 4) {
				textoAuxiliar.insert(0, "."+textoSemZerosIniciais.substring(textoSemZerosIniciais.length()-3,textoSemZerosIniciais.length()));
				textoSemZerosIniciais = textoSemZerosIniciais.substring(0,textoSemZerosIniciais.length()-3);
			}
			
			if(textoSemZerosIniciais.length() > 0) {
				textoAuxiliar.insert(0, textoSemZerosIniciais);
			}
			if(!textoAuxiliar.toString().equals("") && textoAuxiliar.toString().charAt(0) == '-' && 
				textoAuxiliar.toString().charAt(1) == '.') {
				textoAuxiliar.replace(1, 2, "");
			}
			return zerosInicio+textoAuxiliar.toString();
		}else {
			return textoSemZerosIniciais;
		}
	}
	
	/**
	 * 
	 * Verifica se o valor atual � diferente de nulo, se for
	 * atribui a vari�vel com o valor atual, assim como captura
	 * o tipo atual desse dado, se n�o � realizado a convers�o 
	 * dos valores e � feito o calculo entre os valores, por fim
	 * esse valor � exibido em tela 
	 * 
	 * @author Comandolli
	 * @param valorAtual
	 * @param tpDigitacao
	 * @throws Exception 
	 */
	public static void verificarAtribuicaoValorAtual(String valorAtual, JTextArea tpDigitacao, JRadioButton rdbBinario, JRadioButton rdbHexadecimal) {
		if(OperacoesMatematicasUtil.getValorAtual() == null) {
			OperacoesMatematicasUtil.setValorAtual(valorAtual);
			ValidacaoCalculadoraConversao.atribuirTipoNumero(rdbBinario, rdbHexadecimal);
		}else if(OperacoesMatematicasUtil.getOperacao() != null){
			String valorSemPonto = ValidacaoCalculadoraConversao.realizarConversao(rdbBinario, rdbHexadecimal, valorAtual);
			OperacoesMatematicasUtil.calcular(new BigDecimal(valorSemPonto));
			tpDigitacao.setText(OperacoesMatematicasUtil.getValorAtual());
		}
	}
	
	/**
	 * 
	 * Verifica qual tipo de n�mero est� selecionado para
	 * atribuir a vari�vel de tipo atual, j� que a mesma 
	 * ser� utilizada para o calculo
	 *
	 * @author Comandolli
	 * @param rdbDecimal
	 * @param rdbHexadecimal
	 */
	public static void atribuirTipoNumero(JRadioButton rdbBinario, JRadioButton rdbHexadecimal) {
		if (rdbBinario.isSelected()) {
			OperacoesMatematicasUtil.setTipoAtual(OperacoesMatematicasUtil.BINARIO);
		} else if (rdbHexadecimal.isSelected()) {
			OperacoesMatematicasUtil.setTipoAtual(OperacoesMatematicasUtil.HEXADECIMAL);
		} else {
			OperacoesMatematicasUtil.setTipoAtual(OperacoesMatematicasUtil.DECIMAL);
		}
	}

	/**
	 * 
	 * Realiza a convers�o dos valores (Um armazenado e outro valor em tela)
	 * 
	 * Primeiro � tirado as v�rgulas, depois � pego os valores armazenados, e os valores em
	 * tela, para em seguida fazer a valida��o de qual convers�o deve ser feita 
	 * 
	 * DECIMAL n�o � convertido, se for de outro tipo � transformado em DECIMAL
	 * para opera��o futura e convers�o final de exibi��o
	 *
	 * @author Comandolli
	 * @param rdbBinario
	 * @param rdbHexadecimal
	 * @param valorTela
	 * @return valorTela
	 * @throws Exception
	 */
	public static String realizarConversao(JRadioButton rdbBinario, JRadioButton rdbHexadecimal, String valorTela) {
		valorTela = valorTela.replace(".", "");
		String tipoArmazenado = OperacoesMatematicasUtil.getTipoAtual();
		String valorArmazenado = OperacoesMatematicasUtil.getValorAtual();
		atribuirTipoNumero(rdbBinario, rdbHexadecimal);
		
		if(!OperacoesMatematicasUtil.getTipoAtual().equals(OperacoesMatematicasUtil.DECIMAL)) {
			if(OperacoesMatematicasUtil.getTipoAtual().equals(OperacoesMatematicasUtil.HEXADECIMAL)) {
				valorTela = String.valueOf(ConversaoUtil.converterHexaParaDecimal(valorTela));
			}else {
				valorTela = String.valueOf(ConversaoUtil.converterBinarioParaDecimal(valorTela));
			}
		}
		
		if(!tipoArmazenado.equals(OperacoesMatematicasUtil.DECIMAL)) {
			if(tipoArmazenado.equals(OperacoesMatematicasUtil.HEXADECIMAL)) {
				OperacoesMatematicasUtil.setValorAtual(String.valueOf(ConversaoUtil.converterHexaParaDecimal(valorArmazenado)));
			}else {
				OperacoesMatematicasUtil.setValorAtual(String.valueOf(ConversaoUtil.converterBinarioParaDecimal(valorArmazenado)));
			}
		}
		return valorTela;
	}
	
	/**
	 * 
	 * Convers�o final para o tipo especificado
	 *
	 * @author Comandolli
	 * @param valorFinal
	 * @param rdbBinario
	 * @param rdbHexadecimal
	 * @return valorFinal
	 */
	public static String convercaoValorFinal(String valorFinal, JRadioButton rdbBinario, JRadioButton rdbHexadecimal) {
		atribuirTipoNumero(rdbBinario, rdbHexadecimal);
	
		if(OperacoesMatematicasUtil.getTipoAtual().equals(OperacoesMatematicasUtil.BINARIO)) {
			valorFinal = ConversaoUtil.converterDecimalParaBinario(Integer.parseInt(valorFinal));
		}else if(OperacoesMatematicasUtil.getTipoAtual().equals(OperacoesMatematicasUtil.HEXADECIMAL)){
			valorFinal = ConversaoUtil.converterDecimalParaHexa(Integer.parseInt(valorFinal));
		}
		
		return valorFinal; 
	}
	
	/**
	 * 
	 * Adiciona sele��o ao radioButton do n�mero final do calculo
	 *
	 * @author Comandolli
	 * @param rdbEntradaBinario
	 * @param rdbEntradaHexadecimal
	 * @param rdbSaidaBinario
	 * @param rdbSaidaHexadecimal
	 * @param rdbSaidaDecimal
	 */
	public static void alterarTipoNumeroFinal(JRadioButton rdbSaidaBinario, JRadioButton rdbSaidaHexadecimal, 
											  JRadioButton rdbEntradaBinario, JRadioButton rdbEntradaHexadecimal, JRadioButton rdbEntradaDecimal) {
		if(rdbSaidaBinario.isSelected()) {
			rdbEntradaDecimal.setSelected(false);
			rdbEntradaHexadecimal.setSelected(false);
			rdbEntradaBinario.setSelected(true);
		}else if(rdbSaidaHexadecimal.isSelected()) {
			rdbEntradaDecimal.setSelected(false);
			rdbEntradaHexadecimal.setSelected(true);
			rdbEntradaBinario.setSelected(false);
		}else {
			rdbEntradaDecimal.setSelected(true);
			rdbEntradaHexadecimal.setSelected(false);
			rdbEntradaBinario.setSelected(false);
		}
	}

	/**
	 * 
	 * Converte o valor do visor para o valor correspondente a troca 
	 * do tipo nas opera��es 
	 *
	 * @author Comandolli
	 * @param rdbEntradaBinario
	 * @param rdbEntradaHexadecimal
	 * @param rdbEntradaDecimal
	 * @param tipoConversao
	 * @param valorVisor
	 * @return valorConvertido
	 */
	public static String realizaConversaoTrocaTipo(JRadioButton rdbEntradaBinario, JRadioButton rdbEntradaHexadecimal, 
			                                     JRadioButton rdbEntradaDecimal, Integer tipoConversao, String valorVisor) {
		var valorConvertido = new StringBuilder();
		if(rdbEntradaBinario.isSelected() && tipoConversao != 1) {
			if(tipoConversao == 2) {
				Integer valorDecimal = Integer.parseInt(ConversaoUtil.converterBinarioParaDecimal(valorVisor));
				valorConvertido.append(ConversaoUtil.converterDecimalParaHexa(valorDecimal));
			}else {
				valorConvertido.append(ConversaoUtil.converterBinarioParaDecimal(valorVisor));
			}
		}else if(rdbEntradaHexadecimal.isSelected() && tipoConversao != 2) {
			if(tipoConversao == 1) {
				Integer valorDecimal = Integer.parseInt(ConversaoUtil.converterHexaParaDecimal(valorVisor));
				valorConvertido.append(ConversaoUtil.converterDecimalParaBinario(valorDecimal));
			}else {
				valorConvertido.append(String.valueOf(ConversaoUtil.converterHexaParaDecimal(valorVisor)));
			}
		}else if(rdbEntradaDecimal.isSelected() && tipoConversao != 3){
			if(tipoConversao == 1) {
				valorConvertido.append(ConversaoUtil.converterDecimalParaBinario(Integer.parseInt(valorVisor)));
			}else {
				valorConvertido.append(ConversaoUtil.converterDecimalParaHexa(Integer.parseInt(valorVisor)));
			}
		}
		return valorConvertido.toString();
	}
	
	/**
	 * 
	 * Verifica se o texto � um HEXADECIMAL e trata caso n�o for 
	 *
	 * @author Comandolli
	 * @param tpDigitacao
	 */
	public static void isNumeroHexadecimalTratando(JTextArea tpDigitacao) {
		for (char umCarecter : tpDigitacao.getText().toCharArray()) {
			if (!(umCarecter >= 'A' && umCarecter <= 'F') && !(umCarecter >= 'a' && umCarecter <= 'f') &&
					!(umCarecter >= '0' && umCarecter <= '9') && (umCarecter != '-')) {
				tpDigitacao.setText("");
				break;
			}
	    }
	}
	
	/**
	 * 
	 * Verifica se o texto � um DECIMAL e trata caso n�o for 
	 *
	 * @author Comandolli
	 * @param tpDigitacao
	 */
	public static void isNumeroDecimalTratando(JTextArea tpDigitacao) {
		for (char umCarecter : tpDigitacao.getText().toCharArray()) {
			if (!(umCarecter >= '0' && umCarecter <= '9') && (umCarecter != '-')) {
				tpDigitacao.setText("");
				break;
			}
	    }
	}
	
	/**
	 * 
	 * Verifica se o texto � um BINARIO e trata caso n�o for 
	 *
	 * @author Comandolli
	 * @param tpDigitacao
	 */
	public static void isNumeroBinarioTratando(JTextArea tpDigitacao) {
		for (char umCarecter : tpDigitacao.getText().toCharArray()) {
			if (umCarecter != '0' && umCarecter != '1' && umCarecter != '-') {
				tpDigitacao.setText("");
				break; 
			}
	    }
	}
	
	/**
	 * 
	 * Retira �ltimo caracter inv�lido para o tipo digitado 
	 *
	 * @author Comandolli
	 * @param tpDigitacao
	 * @param rdbEntradaBinario
	 */
	public static void tirarSimboloNaoBinario(JTextArea tpDigitacao, JRadioButton rdbEntradaBinario) {
		if(rdbEntradaBinario.isSelected()) {
			tpDigitacao.setText(tpDigitacao.getText().substring(0, tpDigitacao.getText().length()-1));
		}
	}

	/**
	 * 
	 * Limparar valores e opera��es da calculadora
	 *
	 * @author Comandolli
	 * @param tpDigitacao
	 */
	public static void limparCalculadora(JTextArea tpDigitacao) {
		OperacoesMatematicasUtil.setOperacao(null); 
		OperacoesMatematicasUtil.setTipoAtual(null);
		OperacoesMatematicasUtil.setValorAtual(null);
		tpDigitacao.setText("");
		ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(null);
	}
	
	public static String getTextoDigitadoValidacao() {
		return textoDigitadoValidacao;
	}
	
	public static void setTextoDigitadoValidacao(String textoDigitadoValidacao) {
		ValidacaoCalculadoraConversao.textoDigitadoValidacao = textoDigitadoValidacao;
	}
	
}
