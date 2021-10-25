package funcoes;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoCalculadoraTabelaVerdade {

	/**
	 * 
	 * Retorna uma lista de expressoes (Separadores) que estão separados dentro da
	 * expresao principal
	 * 
	 * Ex: [()] x {[()]}
	 * 
	 * com isso é adicionado em separado a expressao 1 : [()] da expressao dois
	 * {[()]}
	 *
	 * @author Luiz Miguel
	 * @param comparacaoSeparadores
	 * @return listaExpressoes
	 */
	public static List<String> separarExpressoesSeparadores(String comparacaoSeparadores) {
		Boolean fechou = false;
		Integer inicioExpressao = 0;
		List<String> listaExpressoes = new ArrayList<>();
		for (var i = 0; i < comparacaoSeparadores.length(); i++) {

			if (isSeparadorFechamento(comparacaoSeparadores.charAt(i)) != null) {
				fechou = true;
			}

			if (isSeparadorAbertura(comparacaoSeparadores.charAt(i)) != null && fechou) {
				listaExpressoes.add(comparacaoSeparadores.substring(inicioExpressao, i));
				inicioExpressao = i;
				fechou = false;
			}
		}

		if (Boolean.TRUE.equals(fechou)) {
			listaExpressoes.add(comparacaoSeparadores.substring(inicioExpressao, comparacaoSeparadores.length()));
		}

		return listaExpressoes;
	}

	/**
	 * 
	 * Verifica se o character é de abertura, se for retorna o próprio character se
	 * não retorna null
	 *
	 * @author Luiz Miguel
	 * @param character
	 * @return character
	 */
	public static Character isSeparadorAbertura(Character character) {
		if (character == '{' || character == '[' || character == '(') {
			return character;
		}
		return null;
	}

	/**
	 * 
	 * Verifica se o character é de fechamento, se for retorna o próprio character
	 * se não retorna null
	 *
	 * @author Luiz Miguel
	 * @param character
	 * @return character
	 */
	public static Character isSeparadorFechamento(Character character) {
		if (character == '}' || character == ']' || character == ')') {
			return character;
		}
		return null;
	}

	public static Character validarSeguenciaIncorreta(Character antigoChar, Character novoChar) {
		if ((isVariavelCalculo(antigoChar) != null && isVariavelCalculo(novoChar) != null)
				|| (isSeparador(antigoChar) != null && isSeparador(novoChar) != null)) {
			return null;
		}
		return novoChar;
	}

	/**
	 * 
	 * Adiciona a lista de variáveis da expressão
	 *
	 * @author Luiz Miguel
	 * @param expressao
	 */
	public static void atribuirListaVariaveis(String expressao) {
		List<String> listaVariaveis = new ArrayList<>();
		for (var i = 0; i < expressao.length(); i++) {
			if (isVariavelCalculo(expressao.charAt(i)) != null) {
				listaVariaveis.add(String.valueOf(expressao.charAt(i)));
			}
		}
		setListaVariaveis(listaVariaveis);
	}

	public static List<String> getListaVariaveis() {
		return listaVariaveis;
	}

	public static void setListaVariaveis(List<String> listaVariaveis) {
		ValidacaoCalculadoraTabelaVerdade.listaVariaveis = listaVariaveis;
	}

}
