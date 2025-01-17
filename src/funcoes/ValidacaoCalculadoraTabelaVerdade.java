package funcoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import operacoes.TabelaVerdadeUtil;

public class ValidacaoCalculadoraTabelaVerdade {
	
	private static List<String> listaVariaveis;
	
	private ValidacaoCalculadoraTabelaVerdade() {
	}
	
	/**
	 * 
	 * Realiza valida��o da express�o passada, realizando 
	 * a seguencia de m�todo de valida��o
	 *
	 * @author Comandolli
	 * @param expressao
	 * @return expressao
	 */
	public static String validarExpressao(String expressao) {
		if(validarCaracteresDigitados(expressao) == null || 
		   validarSeparadores(expressao) == null ||
		   validarDuplicidadeCaracter(expressao) == null ||
		   validarOperadorAnd(expressao) == null ||
		   validarOperadorOr(expressao) == null ||
		   validarOperadorNot(expressao) == null ||
		   validarDuplicidadeNomeVariavel(expressao) == null ||
		   validarVariaveisSequencia(expressao) == null) {
			return null;
		}
		return expressao;
	}
	
	/**
	 * 
	 * Verifica se na sequncia a duas vari�veis de calculo 
	 * sem nenhum outro outro caracter no meio 
	 *
	 * @author Leonardo
	 * @param expressao
	 * @return expressao
	 */
	private static String validarVariaveisSequencia(String expressao) {
		Character umCharAnterior = ' ';
		for (var i = 0; i < expressao.length(); i++) {
			if((i != 0) && isVariavelCalculo(umCharAnterior) != null && isVariavelCalculo(expressao.charAt(i)) != null) {
				return null;
			}
			umCharAnterior = expressao.charAt(i);
		}
		return expressao;
	}

	/**
	 * 
	 * Verifica se h� duplicidade de caracteres 
	 * que n�o s�o separadores
	 *
	 * EX: vari�vel A em sequ�ncia na exprss�o ficando - AA
	 *
	 * @author Leonardo
	 * @param expressao
	 * @return expressao
	 */
	public static String validarDuplicidadeCaracter(String expressao) {
		Character umCharAnterior = ' ';
		for (var i = 0; i < expressao.length(); i++) {
			Character umCharNovo = isVariavelCalculo(expressao.charAt(i));
			if(umCharNovo == null) {
				umCharNovo = isOperadorLogico(expressao.charAt(i));
			}
			
			if((i != 0) && umCharNovo != null && umCharAnterior != null && umCharAnterior.equals(umCharNovo)) {
				return null;
			}
			
			if(umCharNovo != null) {
				umCharAnterior = expressao.charAt(i);
			}
			
			if(isSeparador(expressao.charAt(i)) != null) {
				umCharAnterior = null;
			}
		}
		return expressao;
	}
	
	/**
	 * 
	 * Valida duplicidade no nome de vari�veis da express�o
	 *
	 * @author Leonardo
	 * @param expressao
	 * @return expressao
	 */
	public static String validarDuplicidadeNomeVariavel(String expressao) {
		atribuirListaVariaveis(expressao);
		
		for (var i = 0; i < getListaVariaveis().size(); i++) {
			for (var j = 0; j < getListaVariaveis().size(); j++) {
				if(getListaVariaveis().get(i).equals(getListaVariaveis().get(j)) && i != j) {
					return null;
				}
			}
		}
		return expressao;
	}
	
	/**
	 * 
	 * Verifica se o operador l�gico foi colocado entre veri�veis
	 * de c�lculo, ou seja se � um operador v�lido na express�o
	 *
	 * @author Leonardo
	 * @param expressao
	 * @return expressao
	 */
	public static String validarOperadorOr(String expressao) {
		for (var i = 0; i < expressao.length(); i++) {
			Character charOr = isOperadorLogico(expressao.charAt(i));
			
			if((charOr != null && (charOr == 'V' || charOr == 'v')) && 
			   ((i == 0 || i+1 == expressao.length()) || 
			   (isVariavelCalculo(expressao.charAt(i+1)) != null) &&
			   (isOperadorLogico(expressao.charAt(i+1)) != null) &&
			   (isOperadorLogico(expressao.charAt(i+1)) != '~') ||
			   (isVariavelCalculo(expressao.charAt(i-1)) == null))) {
				return null;
			}
		}
		return expressao;
	}
	
	/**
	 * 
	 * Verifica se o operador l�gico foi colocado entre veri�veis
	 * de c�lculo, ou seja se � um operador v�lido na express�o
	 *
	 * @author Leonardo
	 * @param expressao
	 * @return expressao
	 */
	public static String validarOperadorAnd(String expressao) {
		for (var i = 0; i < expressao.length(); i++) {
			Character charOr = isOperadorLogico(expressao.charAt(i));
			
			if((charOr != null && (charOr == '^')) && 
			   ((i == 0 || i+1 == expressao.length()) || 
			   (isVariavelCalculo(expressao.charAt(i+1)) == null) &&
			   ((isOperadorLogico(expressao.charAt(i+1)) != null) && 
			    (isOperadorLogico(expressao.charAt(i-1)) != null)) &&
			   ((isOperadorLogico(expressao.charAt(i+1)) != '~') ||	   
			   ((isOperadorLogico(expressao.charAt(i+1)) != '(') &&
				(isOperadorLogico(expressao.charAt(i-1)) != ')'))))) {
				return null;
			}
		}
		return expressao;
	}
	
	/**
	 * 
	 * Verifica se a express�o usa o NOT de forma correta
	 * adicionando uma vari�vel a direita do simbolo 
	 *
	 * @author Leonardo
	 * @param expressao
	 * @return expressao
	 */
	public static String validarOperadorNot(String expressao) {
		for (var i = 0; i < expressao.length(); i++) {
			Character charOr = isOperadorLogico(expressao.charAt(i));
			
			if((charOr != null && charOr == '~') && (i+1 == expressao.length() || (isVariavelCalculo(expressao.charAt(i+1)) == null &&
			   (isSeparador(expressao.charAt(i+1)) != null && isSeparador(expressao.charAt(i+1)) != '(')))) {
				return null;
			}
		}
		return expressao;
	}
	
	/**
	 * 
	 * Realiza valida��o de caracteres digitados, para saber se 
	 * podem ou n�o ser utilizados no calculo 
	 *
	 * @author Leonardo
	 * @param expressao
	 * @return expressao
	 */
	public static String validarCaracteresDigitados(String expressao) {
		for (var i = 0; i < expressao.length(); i++) {
			if ((isVariavelCalculo(expressao.charAt(i)) == null) &&
				(isOperadorLogico(expressao.charAt(i)) == null) &&
				(isSeparador(expressao.charAt(i)) == null))  {
				return null;
			}
		}
		return expressao;
	}
	
	/**
	 * 
	 * Verifica se � um separador 
	 *
	 * @author Leonardo
	 * @param character
	 * @return character
	 */
	public static Character isSeparador(Character character) {
		if(character == '(' || character == ')' || 
		   character == '[' || character == ']' ||
		   character == '{' || character == '}'){
			return character;
		}
		return null;
	}
	
	/**
	 * 
	 * Verifica se o character � um operador l�gico
	 *
	 * @author Leonardo
	 * @param character
	 * @return character
	 */
	public static Character isOperadorLogico(Character character) {
		if(character == '^' || character == '~' || 
		   character == 'V' || character == 'v') {
			return character;
		}
		return null;
	}
	
	/**
	 * 
	 * Verifica se o caracter � uma vari�vel de calculo
	 * 
	 * retorna o charater se for TRUE
	 * retorna null se for FALSE
	 *
	 * @author Leonardo
	 * @param character
	 * @return character
	 */
	public static Character isVariavelCalculo(Character character) {
		if(((character >= 'A' && character <= 'Z') ||
		   (character >= 'a' && character <= 'z')) &&
		   (character != 'V' && character != 'v')) {
			return character;
		}
		return null;
	}
	
	/**
	 * 
	 * Realiza todas as valida��es para os separadores 
	 *
	 * @author Leonardo
	 * @param expressao
	 * @return expressao
	 */
	public static String validarSeparadores(String expressao) {
		var comparacaoSeparadores = new StringBuilder();
		for (var i = 0; i < expressao.length(); i++) {
			Character charVerificado = isSeparador(expressao.charAt(i));
			if(charVerificado != null) {
				comparacaoSeparadores.append(charVerificado);
			}
		}
		
		if(verificaQuatidadeSeparadores(comparacaoSeparadores) == null ||
		   verificaTodosSeperadoresExpressaoIgualdade(comparacaoSeparadores) == null ||
		   verificarPrecedenciaSeparadores(comparacaoSeparadores) == null) {
			return null;
		}
		
		return expressao;
	}
	
	/**
	 * 
	 * Realizar valida��o de separadores - precedencia
	 *
	 * @author Leonardo
	 * @param comparacaoSeparadores
	 * @return comparacaoSeparadores.toString()
	 */
	public static String verificarPrecedenciaSeparadores(StringBuilder comparacaoSeparadores) {
		List<String> listaExpressoes = separarExpressoesSeparadores(comparacaoSeparadores.toString());
		for (var i = 0; i < listaExpressoes.size(); i++) {
			if(verificarPresedeciaExpIndividual(listaExpressoes.get(i)) == null) {
				return null;
			}
		}
		return comparacaoSeparadores.toString();
	}
	
	/**
	 * 
	 * Recupera as express�es individualmente para valida��o das mesmas
	 * 
	 * Se retornar null � porque alguma das exprees�es s�o inv�lidas
	 * Se n�o, se retornar comparacaoSeparadores
	 *
	 * @author Leonardo
	 * @param comparacaoSeparadores
	 * @return comparacaoSeparadores.toString()
	 */
	public static String verificaTodosSeperadoresExpressaoIgualdade(StringBuilder comparacaoSeparadores) {
		List<String> listaExpressoes = separarExpressoesSeparadores(comparacaoSeparadores.toString());
		for (var i = 0; i < listaExpressoes.size(); i++) {
			if(verificarFechamentoSeparadoresIguais(listaExpressoes.get(i)) == null) {
				return null;
			}
		}
		return comparacaoSeparadores.toString();
	}
	
	/**
	 * 
	 * Verifica se a quantidade separadores � igual 
	 * 
	 * Ex: comparacaoSeparadores = {[()]}
	 * 
	 * qtdParentesesAbertos tem que ser igual qtdParentesesFechados, pois
	 * se abri um separador tenho que fechar o mesmo 
	 *
	 * @author Leonardo
	 * @param comparacaoSeparadores
	 * @return comparacaoSeparadores.toString() 
	 */
	public static String verificaQuatidadeSeparadores(StringBuilder comparacaoSeparadores) {
		var qtdParentesesAbertos = 0;
		var qtdParentesesFechados = 0;
		var qtdColchetesAbertos = 0;
		var qtdColchetesFechados = 0;
		var qtdChavesAbertos = 0;
		var qtdChavesFechados = 0;
		
		for (var i = 0; i < comparacaoSeparadores.toString().length(); i++) {
			
			switch (comparacaoSeparadores.toString().charAt(i)) {
			
			case '(': { qtdParentesesAbertos++; break; }
			case ')': { qtdParentesesFechados++; break; }
			case '[': { qtdColchetesAbertos++; break; }
			case ']': { qtdColchetesFechados++; break; }
			case '{': { qtdChavesAbertos++; break; }
			case '}': { qtdChavesFechados++; break; }
			
			default:
				throw new IllegalArgumentException();
			}
		}
		
		if((qtdParentesesAbertos != qtdParentesesFechados && qtdParentesesAbertos > 0) ||
		   (qtdColchetesAbertos != qtdColchetesFechados && qtdColchetesAbertos > 0) ||
		   (qtdChavesAbertos != qtdChavesFechados && qtdChavesAbertos > 0)) {
			return null;
		}
		return comparacaoSeparadores.toString();
	}
	
	/**
	 * 
	 * Verifica se a sequ�ncia de separados � igual, por exemplo:
	 * 
	 * comparacaoSeparadores = {[( )]}
	 *
	 * Ser� verificado se esse valor de comparacaoSeparadores
	 * segue a ordem de abertura e fechamento "{}", "[]", "()"
	 * 
	 * retorna null se for inv�lida a sequ�ncia, se n�o retorna 
	 * o pr�prio texto passado
	 *
	 * @author Leonardo
	 * @param comparacaoSeparadores
	 * @return comparacaoSeparadores
	 */
	public static String verificarFechamentoSeparadoresIguais(String comparacaoSeparadores) {
		var lSeparadoresAbertura = new StringBuilder();
		for (var i = 0; i < comparacaoSeparadores.length(); i++) {
			Character separadorAbertura = isSeparadorAbertura(comparacaoSeparadores.charAt(i));
			if(separadorAbertura != null){
				lSeparadoresAbertura.append(separadorAbertura); 
			}
		}
		
		for (var i = 0; i < comparacaoSeparadores.length(); i++) {
			Character separadorFechamento = isSeparadorFechamento(comparacaoSeparadores.charAt(i));  
			if(separadorFechamento != null) {
				if((lSeparadoresAbertura.toString().charAt(lSeparadoresAbertura.length()-1) == '(' && separadorFechamento == ')') ||
				   (lSeparadoresAbertura.toString().charAt(lSeparadoresAbertura.length()-1) == '[' && separadorFechamento == ']') ||
				   (lSeparadoresAbertura.toString().charAt(lSeparadoresAbertura.length()-1) == '{' && separadorFechamento == '}')) {
					lSeparadoresAbertura.replace(lSeparadoresAbertura.length()-1, lSeparadoresAbertura.length(), "");
				}else {
					return null;
				}
			}
		}
		return comparacaoSeparadores;
	}
	
	/**
	 * 
	 * Valida toda a sequencia de separadores da expressao passada
	 * 
	 * Se for v�lido retorna a pr�pria String, se n�o retorna null
	 *
	 * @author Leonardo
	 * @param expressao
	 * @return comparacaoSeparadores
	 */
	public static String verificarPresedeciaExpIndividual(String expressao) {
		for (var i = 0; i < expressao.length(); i++) {
			Character umChar = isSeparadorAbertura(expressao.charAt(i));

			if(umChar != null) {
				for (var j = i; j < expressao.length(); j++) {
					Character outroChar = isSeparadorAbertura(expressao.charAt(j));
				
					if((j > i) && outroChar != null && isPrecedenciaValida(umChar, outroChar) == null) {
						return null;
					}
				}
			}			
		}
		return expressao;
	}
	
	/**
	 * 
	 * Verifica se a precendecia do char est� correta 
	 * 
	 * Se estiver retorna o char original se n�o 
	 * retorna null
	 *
	 * @author Leonardo
	 * @param umChar
	 * @param outroChar
	 * @return umChar
	 */
	public static Character isPrecedenciaValida(Character umChar, Character outroChar) {
		if((umChar == '{' && umChar.equals(outroChar)) ||
		   (umChar == '[' && (outroChar == '{' || umChar.equals(outroChar))) ||
		   (umChar == '(' && (outroChar == '{' || outroChar == '['))){
			return null;
		}
		return umChar;
	}
	
	/**
	 * 
	 * Retorna uma lista de expressoes (Separadores) que est�o separados dentro da
	 * expresao principal
	 * 
	 * Ex: [()] x {[()]}
	 * 
	 * com isso � adicionado em separado a expressao 1 : [()] da expressao dois
	 * {[()]}
	 *
	 * @author Luiz Miguel
	 * @param comparacaoSeparadores
	 * @return listaExpressoes
	 */
	public static List<String> separarExpressoesSeparadores(String comparacaoSeparadores) {
		TabelaVerdadeUtil.dividirExpressoesSeparadores(comparacaoSeparadores);
		List<String> listaExpressoes = new ArrayList<>();
		Map<String, String> listaExpressoesTemp = new HashMap<>();
		listaExpressoesTemp.putAll(TabelaVerdadeUtil.getListaExpressoes());
		listaExpressoesTemp.remove(TabelaVerdadeUtil.EXP_FINAL);
		
		for (Map.Entry<String, String> umItemCompacao : listaExpressoesTemp.entrySet()) {
			for (Map.Entry<String, String> umItem : listaExpressoesTemp.entrySet()) {
				if(umItem.getValue().contains(umItemCompacao.getKey())) {
					Integer inicioSubstituicao = umItem.getValue().indexOf(umItemCompacao.getKey());
					Integer finalSubstituicao = inicioSubstituicao+(umItemCompacao.getKey()).length();
					
					String identificadorTexto = listaExpressoesTemp.get(umItemCompacao.getKey());
					
					listaExpressoesTemp.replace(umItem.getKey(), 
					umItem.getValue().substring(0,inicioSubstituicao)+
					identificadorTexto+
					umItem.getValue().substring(finalSubstituicao,umItem.getValue().length()));
				}
			}
		}
		
		listaExpressoes.addAll(listaExpressoesTemp.values());
		return listaExpressoes;
	}

	/**
	 * 
	 * Verifica se o character � de abertura, se for retorna o pr�prio character se
	 * n�o retorna null
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
	 * Verifica se o character � de fechamento, se for retorna o pr�prio character
	 * se n�o retorna null
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
	 * Adiciona a lista de vari�veis da express�o
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
	
	/**
	 * 
	 * Verifica se os pr�ximos caracteres s�o 'EX'
	 * 
	 * se for retorna o pr�prio caracter anterior
	 * se n�o retorna o pr�ximo valor, que ser� uma
	 * vari�vel
	 *
	 * @author Comandolli
	 * @param umaExpressao
	 * @param indice
	 * @return character
	 */ 
	public static Character isProximoExpressao(String umaExpressao, Integer indice) {
		if(umaExpressao.charAt(indice+1) == 'E' && 
		   indice+2 < umaExpressao.length() && 
		   umaExpressao.charAt(indice+2) == 'X'){
			return umaExpressao.charAt(indice);
		}
		return umaExpressao.charAt(indice+1);
	}
	
	/**
	 * 
	 * Verifica se os caracteres anteriores s�o n�meros
	 *
	 * se for retorna o caracter passado como par�metro
	 * se n�o retorna o caracter anterior, que ser� uma
	 * vari�vel
	 *
	 * @author Comandolli
	 * @param umaExpressao
	 * @param indice
	 * @return character
	 */
	public static Character isAntesExpressao(String umaExpressao, Integer indice) {
		if(umaExpressao.charAt(indice-1) >= '1' && 
		   umaExpressao.charAt(indice-1) <= '9'){
			return umaExpressao.charAt(indice);
		}
		return umaExpressao.charAt(indice-1);
	}
	
	/**
	 * 
	 * Verifica se a express�o cont�m um AND sem o sinal '^'
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @return isExpressaoAndSemSinal
	 */
	public static Boolean isExpressaoAndSemSinal(String umaExpressaoValue) {
		for (var i = 0; i < umaExpressaoValue.length(); i++) {
			Character umChar = umaExpressaoValue.charAt(i);
			if(isVariavelCalculo(umChar) != null && 
			   i < umaExpressaoValue.length()-1 &&
			   ValidacaoCalculadoraTabelaVerdade.isProximoExpressao(umaExpressaoValue, i).equals(umChar)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * Verifica se a express�o � uma express�o AND, 
	 * a partir do indice informado
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param umChar
	 * @param indice
	 * @return umChar
	 */
	public static Character isOperacaoAnd(String umaExpressaoValue, Character umChar, Integer indice) {
		if((ValidacaoCalculadoraTabelaVerdade.isOperadorLogico(umChar) != null && umChar == '^') || 
		   (ValidacaoCalculadoraTabelaVerdade.isVariavelCalculo(umChar) != null &&
		    indice < umaExpressaoValue.length()-1 &&
		    ValidacaoCalculadoraTabelaVerdade.isProximoExpressao(umaExpressaoValue, indice).equals(umChar))) {
			return umChar;
		}
		return null;
	}

	public static List<String> getListaVariaveis() {
		return listaVariaveis;
	}

	public static void setListaVariaveis(List<String> listaVariaveis) {
		ValidacaoCalculadoraTabelaVerdade.listaVariaveis = listaVariaveis;
	}

}
