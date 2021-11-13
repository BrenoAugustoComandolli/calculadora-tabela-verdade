package operacoes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dados.DadosExpressao;
import dados.DadosExpressaoAtualizado;
import funcoes.ValidacaoCalculadoraTabelaVerdade;

public class TabelaVerdadeUtil {
	
	private static String[][] matrizExibicao;
	private static String[][] matrizExpressoesResp;
	private static Integer tamanhoPosibilidades; 
	private static Integer tamanhoExpressoes; 
	private static Map<String, String> listaExpressoes;
	private static Map<String, String> listaExpressoesInterm = new HashMap<>();
	
	public static final String PREXIFO_EXP = "EXP";
	public static final String PREXIFO_EXPINTERNO = "EXPINT";
	public static final String EXP_FINAL = "RESFIN";
	
	private TabelaVerdadeUtil() {
		throw new IllegalStateException("Classe Utilitária");
	}
	
	/**
	 * 
	 * Realizando a atribuição das variáveis na matriz 
	 *
	 * @author Yuri Martins
	 * @param expressao
	 * @return matrizExibicao
	 */
	public static void realizarAdicicaoValoresCalculo(String expressao) {
		ValidacaoCalculadoraTabelaVerdade.atribuirListaVariaveis(expressao);
		List<String> lVariaveis = ValidacaoCalculadoraTabelaVerdade.getListaVariaveis();
		Integer qtdVariaveis = lVariaveis.size();
		setTamanhoPosibilidades(OperacoesMatematicasUtil.realizarPotenciacao(2, qtdVariaveis));
		setTamanhoExpressoes(recuperarQtdOperadores(expressao)+recuperarAndSemOperadores(expressao)+qtdVariaveis);
		setMatrizExibicao(new String[getTamanhoExpressoes()][getTamanhoPosibilidades()+1]);
		setMatrizExpressoesResp(new String[0][getTamanhoPosibilidades()+1]);
		adicionarValoresPossibilidades(lVariaveis);
	}
	
	/**
	 * 
	 * Adicionando valores de possibilidades
	 *
	 * @author Comandolli
	 * @param lVariaveis
	 */
	private static void adicionarValoresPossibilidades(List<String> lVariaveis) {
		Integer divisorZerosUns = getTamanhoPosibilidades();
		for (var i = 0; i < lVariaveis.size(); i++) {
			divisorZerosUns /= 2;
			Integer contador = divisorZerosUns;
			var isLigado = true;
			for (var j = 0; j < getTamanhoPosibilidades()+1; j++) {

				contador = verificaAtribuiValorProbabilidade(j, i, contador, isLigado, lVariaveis);
				
				if(contador == 0) {
					contador = divisorZerosUns;
					if(isLigado) {
						isLigado = false;
					}else {
						isLigado = true;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * Verifica se o valor será 0 ou 1 na posição do vetor 
	 * passado, além disso o contador é atualizado a 
	 * cada atribuição
	 *
	 * @author Comandolli
	 * @param j
	 * @param i
	 * @param contador
	 * @param isLigado
	 * @param lVariaveis
	 * @return contador
	 */
	private static int verificaAtribuiValorProbabilidade(int j, int i, int contador, boolean isLigado, List<String> lVariaveis) {
		if(j == 0) {
			getMatrizExibicao()[i][j] = String.valueOf(lVariaveis.get(i));
		}else if (isLigado)  {
			getMatrizExibicao()[i][j] = "1";
			contador--;
		}else {
			getMatrizExibicao()[i][j] = "0";
			contador--;
		}
		return contador;
	}
	
	/**
	 * 
	 * Recuperar as expressões para realizar as operações, 
	 * que estão entre separadores
	 *
	 * @author Yuri Martins
	 * @param expressao
	 */
	public static void dividirExpressoesSeparadores(String expressao) {
		Integer inicioExpressao = null;
		Integer fimExpressao = null;
		Map<String, String> listaExpressoes = new HashMap<>();
		Integer qtdExpressoes = 0;
		
		while(expressao.contains("(") || expressao.contains("[") || expressao.contains("{")) {
			inicioExpressao = retornarUltimaAbertura(expressao, inicioExpressao);
			fimExpressao = retornarFechamentoPosUltAbertura(expressao, inicioExpressao, fimExpressao);
			
			if(inicioExpressao != null && fimExpressao != null) {
				qtdExpressoes++;
				String identificador = PREXIFO_EXP+qtdExpressoes.toString();
				listaExpressoes.put(identificador, expressao.substring(inicioExpressao, fimExpressao+1));
				String expressaoNova = expressao.substring(0, inicioExpressao)+identificador+expressao.substring(fimExpressao+1);
				expressao = expressaoNova;
			}
			
			inicioExpressao = null;
			fimExpressao = null;
		}
		listaExpressoes.put(EXP_FINAL, expressao);
		setListaExpressoes(listaExpressoes);
	}
	
	/**
	 * 
	 * Retorna o último separador de abertura da expressão
	 *
	 * @author Comandolli
	 * @param expressao
	 * @param inicioExpressao
	 * @return inicioExpressao
	 */
	private static Integer retornarUltimaAbertura(String expressao, Integer inicioExpressao) {
		for (var i = 0; i < expressao.length(); i++) {
			if(ValidacaoCalculadoraTabelaVerdade.isSeparadorAbertura(expressao.charAt(i)) != null) {
				inicioExpressao = i;
			}
		}
		return inicioExpressao;
	}
	
	/**
	 * 
	 * Retorna o primeiro separador de fechamento 
	 * pós último separador de abertura
	 *
	 * @author Comandolli
	 * @param expressao
	 * @param inicioExpressao
	 * @param fimExpressao
	 * @return fimExpressao
	 */
	private static Integer retornarFechamentoPosUltAbertura(String expressao, Integer inicioExpressao, Integer fimExpressao) {
		if(inicioExpressao != null) {
			for (var i = inicioExpressao; i < expressao.length(); i++) {
				if(ValidacaoCalculadoraTabelaVerdade.isSeparadorFechamento(expressao.charAt(i)) != null) {
					fimExpressao = i;
					break;
				}
			}
		}
		return fimExpressao;
	}
	
	/**
	 * 
	 * Recuperar quantidade de operadores 
	 *
	 * @author Yuri Martins
	 * @param expressao
	 * @return qtdOperadores
	 */
	private static Integer recuperarQtdOperadores(String expressao) {
		var qtdOperadores = 0;
		for (var i = 0; i < expressao.length(); i++) {
			if(ValidacaoCalculadoraTabelaVerdade.isOperadorLogico(expressao.charAt(i)) != null) {
				qtdOperadores++;
			}
		}
		return qtdOperadores;
	}
	
	/**
	 * 
	 * Recuperar quantidade de operações AND sem operadores
	 *
	 * @author Comandolli
	 * @param expressao
	 * @return qtdCasos
	 */
	private static Integer recuperarAndSemOperadores(String expressao) {
		var qtdCasos = 0;
		for (var i = 0; i < expressao.length(); i++) {
			if(ValidacaoCalculadoraTabelaVerdade.isVariavelCalculo(expressao.charAt(i)) != null &&
			   i < expressao.length()-1 &&
			   ValidacaoCalculadoraTabelaVerdade.isSeparadorAbertura(expressao.charAt(i+1)) != null) {
				qtdCasos++;
			}
		}
		return qtdCasos;
	}
	
	/**
	 * 
	 * Verifica se o caracter achado é o último da expressão
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param umChar
	 * @param indice
	 * @return isUltimo
	 */
	private static Boolean isUltimoOperadorDaExpressao(String umaExpressaoValue, Integer indice, Boolean expSemSinal) {
		for (var i = 0; i < umaExpressaoValue.length(); i++) {
			Character outroChar = umaExpressaoValue.charAt(i);
			if(ValidacaoCalculadoraTabelaVerdade.isOperadorLogico(outroChar) != null && i != indice) {
				return false;
			}
		}
		Boolean isUltimo = Boolean.FALSE;
		if(expSemSinal != null) {
			isUltimo = Boolean.TRUE.equals(expSemSinal) ? Boolean.TRUE : !ValidacaoCalculadoraTabelaVerdade.isExpressaoAndSemSinal(umaExpressaoValue);
		}
		return isUltimo;
	}
	
	/**
	 * 
	 * Realiza calculos da tabela verdade, 
	 * expressão por expressão
	 *
	 * @author Comandolli
	 */
	public static void realizarOperacaoesResultado() {
		Map<String, String> listaExpressoes = getListaExpressoes();
		
		for (Map.Entry<String, String> umaExpressao : listaExpressoes.entrySet()) {
			resolverExpressaoIndiv(umaExpressao.getValue(), umaExpressao.getKey());
		}
	}
	
	/**
	 * 
	 * Realiza todos as operaçoes da expressão 
	 * (AND/OR/NOT)
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param umaExpressaoKey
	 * @return valorExpInt
	 */
	private static Integer resolverExpressaoIndiv(String umaExpressaoValue, String umaExpressaoKey) {
		var valorExpInt = 0;
		var dadosExpressaoAtualizado = new DadosExpressaoAtualizado(umaExpressaoValue, valorExpInt);
		
		dadosExpressaoAtualizado = verificarExpressaoNot(dadosExpressaoAtualizado.getUmaExpressaoValue(), 
				                                         umaExpressaoKey, dadosExpressaoAtualizado.getValorExpInt());
		
		dadosExpressaoAtualizado = verificarExpressaoAnd(dadosExpressaoAtualizado.getUmaExpressaoValue(), 
				                                  umaExpressaoKey, dadosExpressaoAtualizado.getValorExpInt());
		
		verificarExpressaoOr(dadosExpressaoAtualizado.getUmaExpressaoValue(), 
				             umaExpressaoKey, dadosExpressaoAtualizado.getValorExpInt());
		
		return valorExpInt;
	}
	
	/**
	 * 
	 * Verifica se tem operador NOT na espressão 
	 * e realiza a atribuição dos valores 
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param umaExpressaoKey
	 * @param valorExpInt
	 * @return dadosExpressaoAtualizado
	 */
	private static DadosExpressaoAtualizado verificarExpressaoNot(String umaExpressaoValue, String umaExpressaoKey, Integer valorExpInt){
		Boolean ultimo;
		while(umaExpressaoValue.contains("~")) {
			for (var i = 0; i < umaExpressaoValue.length(); i++) {
				Character umChar = umaExpressaoValue.charAt(i);
				if(ValidacaoCalculadoraTabelaVerdade.isOperadorLogico(umChar) != null && umChar == '~') {
					valorExpInt++;
					ultimo = isUltimoOperadorDaExpressao(umaExpressaoValue, i, true);
					umaExpressaoValue = verificarAtribuirValoresNot(umaExpressaoValue, umaExpressaoKey, valorExpInt, umChar, i, ultimo);
					break;
				}
			}
		}
		return new DadosExpressaoAtualizado(umaExpressaoValue, valorExpInt);
	}
	
	/**
	 * 
	 * Verifica se tem operador AND na espressão 
	 * e realiza a atribuição dos valores 
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param umaExpressaoKey
	 * @param valorExpInt
	 * @return dadosExpressaoAtualizado
	 */
	private static DadosExpressaoAtualizado verificarExpressaoAnd(String umaExpressaoValue, String umaExpressaoKey, Integer valorExpInt){
		Boolean ultimo;
		Boolean expSemSinal;
		while(umaExpressaoValue.contains("^") || 
			  Boolean.TRUE.equals(ValidacaoCalculadoraTabelaVerdade.isExpressaoAndSemSinal(umaExpressaoValue))) {
			
			for (var i = 0; i < umaExpressaoValue.length(); i++) {
				Character umChar = umaExpressaoValue.charAt(i);
				if(ValidacaoCalculadoraTabelaVerdade.isOperacaoAnd(umaExpressaoValue, umChar, i) != null) {
					valorExpInt++;
					expSemSinal = ValidacaoCalculadoraTabelaVerdade.isVariavelCalculo(umChar) != null && 
                                  i < umaExpressaoValue.length()-1 &&
                                  ValidacaoCalculadoraTabelaVerdade.isProximoExpressao(umaExpressaoValue, i).equals(umChar);
					ultimo = isUltimoOperadorDaExpressao(umaExpressaoValue, i, expSemSinal);
					
					umaExpressaoValue = verificarAtribuirValoresAnd(umaExpressaoValue, umaExpressaoKey, valorExpInt, umChar, i, ultimo, expSemSinal);
					break;
				}
			}
		}
		return new DadosExpressaoAtualizado(umaExpressaoValue, valorExpInt);
	}
	
	/**
	 * 
	 * Verifica se tem operador OR na espressão 
	 * e realiza a atribuição dos valores 
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param umaExpressaoKey
	 * @param valorExpInt
	 */
	private static void verificarExpressaoOr(String umaExpressaoValue, String umaExpressaoKey, Integer valorExpInt){
		Boolean ultimo;
		while(umaExpressaoValue.contains("v") || umaExpressaoValue.contains("V")) {
			for (var i = 0; i < umaExpressaoValue.length(); i++) {
				Character umChar = umaExpressaoValue.charAt(i);
				if(ValidacaoCalculadoraTabelaVerdade.isOperadorLogico(umChar) != null && (umChar == 'v' || umChar == 'V')) {
					valorExpInt++;
					ultimo = isUltimoOperadorDaExpressao(umaExpressaoValue, i, null);
					umaExpressaoValue = verificarAtribuirValoresOr(umaExpressaoValue, umaExpressaoKey, valorExpInt, umChar, i, ultimo);
					break;
				}
			}
		}
	}
	
	/**
	 * 
	 * Atribui os valores para a matriz de respostas da expressões 
	 * Essa matriz contém todas as operações realizadas durante 
	 * o cálculo, assim será adicionada a matriz de exibição
	 * 
	 * Se for a última operação da expressão o nome atribuido a coluna será
	 * da expressão inteira, pois é o resultado final, se não 
	 * tem o nome de coluna intermediária
	 *
	 * @author Comandolli
	 * @param colunaValores
	 * @param valorExpInt
	 */
	private static String atribuirProximaExpressao(String umaExpressaoValue, String[] colunaValores, String umaExpressaoKey, 
			                                       Integer valorExpInt, Boolean ultimo, DadosExpressao dadosExpressao){
		var chaveExpressao = "";
		for (var l = 0; l < getMatrizExpressoesResp().length; l++) {
			for (var c = 0; c < getTamanhoPosibilidades()+1; c++) {
				if(c == 0 && getMatrizExpressoesResp()[l][c] == null) {
					if(Boolean.TRUE.equals(ultimo)) {
						chaveExpressao = umaExpressaoKey;
						getMatrizExpressoesResp()[l][c] = chaveExpressao;
					}else {
						chaveExpressao = PREXIFO_EXPINTERNO+valorExpInt;
						getMatrizExpressoesResp()[l][c] = chaveExpressao;
					}
				}else if(getMatrizExpressoesResp()[l][c] == null) {
					getMatrizExpressoesResp()[l][c] = colunaValores[c-1];
				}
			}
		}
		umaExpressaoValue = substituiExpNome(umaExpressaoValue, dadosExpressao.getIndice(), 
											 dadosExpressao.getTamanhoExpSustituicaoAntes(), 
											 dadosExpressao.getTamanhoExpSustituicaoPos(),
											 dadosExpressao.getExpSemSinal(),
											 chaveExpressao); 
		return umaExpressaoValue;
	}
	
	/**
	 * 
	 * Troca o valor da expressão pela chave 
	 * de descrição da expressão
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param indice
	 * @param tamanhoExpSustituicaoAntes
	 * @param tamanhoExpSustituicaoPos
	 * @param chaveExpressao
	 * @return umaExpressaoValue
	 */
	private static String substituiExpNome(String umaExpressaoValue, Integer indice, Integer tamanhoExpSustituicaoAntes, 
			                               Integer tamanhoExpSustituicaoPos, Boolean expSemSinal, String chaveExpressao){
		
		if(tamanhoExpSustituicaoAntes == null) {
			tamanhoExpSustituicaoAntes = 0;
		}
		
		adicionarExpressaoIntermediaria(umaExpressaoValue, indice, tamanhoExpSustituicaoAntes, 
                						tamanhoExpSustituicaoPos, chaveExpressao, expSemSinal);
		
		String novaExpressao;
		if(Boolean.TRUE.equals(expSemSinal)) {
			novaExpressao = umaExpressaoValue.substring(0, indice)+
					        chaveExpressao+
					        umaExpressaoValue.substring(indice+tamanhoExpSustituicaoPos+1);
		}else{
	        novaExpressao = umaExpressaoValue.substring(0, indice-tamanhoExpSustituicaoAntes) + 
					chaveExpressao +
					umaExpressaoValue.substring(indice+tamanhoExpSustituicaoPos+1);
		}
		
		novaExpressao = novaExpressao.replace("(", "");
		novaExpressao = novaExpressao.replace(")", "");
		novaExpressao = novaExpressao.replace("[", "");
		novaExpressao = novaExpressao.replace("]", "");
		novaExpressao = novaExpressao.replace("{", "");
		novaExpressao = novaExpressao.replace("}", "");
		
		return novaExpressao;
	}
	
	/**
	 * 
	 * Adiciona o valor da expressão intermediária
	 * na lista de expressões
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param indice
	 * @param tamanhoExpSustituicaoAntes
	 * @param tamanhoExpSustituicaoPos
	 * @param chaveExpressao
	 */
	private static void adicionarExpressaoIntermediaria(String umaExpressaoValue, Integer indice, Integer tamanhoExpSustituicaoAntes, 
                                                        Integer tamanhoExpSustituicaoPos, String chaveExpressao, Boolean expSemSinal) {
		String expressao;
		if(Boolean.TRUE.equals(expSemSinal)) {
		    expressao = umaExpressaoValue.substring(indice, indice+tamanhoExpSustituicaoPos+1);
		}else{
			expressao = umaExpressaoValue.substring(indice-tamanhoExpSustituicaoAntes, indice+tamanhoExpSustituicaoPos+1);
		}
		
		getListaExpressoesInterm().put(chaveExpressao, expressao);
	}
	
	/**
	 * 
	 * verifica se é uma expressão ou variável que será feito 
	 * a operação NOT, depois realiza a operação em si
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param umaExpressaoKey
	 * @param valorExpInt
	 * @param umChar
	 * @param indice
	 */
	private static String verificarAtribuirValoresNot(String umaExpressaoValue, String umaExpressaoKey, Integer valorExpInt, 
													Character umChar, Integer indice, Boolean ultimo){
		aumentarUmaLinhaMatrizResp();
		String[] colunaValores = null;
		Integer tamanhoExpSustituicao = 0;
		
		if(ValidacaoCalculadoraTabelaVerdade.isProximoExpressao(umaExpressaoValue, indice).equals(umChar)) {
			String nomeExpressao = retornarNomeExpressaoProximo(umaExpressaoValue, indice);
			tamanhoExpSustituicao += nomeExpressao.length();
			colunaValores = recuperarColunaExp(nomeExpressao);
		}else {
			var nomeVariavel = String.valueOf(umaExpressaoValue.charAt(indice+1));
			tamanhoExpSustituicao += nomeVariavel.length();
			colunaValores = recuperarColunaVariavel(nomeVariavel);
		}
		
		String[] colunaNot = realizarOperacaoNot(colunaValores);
		var dadosExpressao = new DadosExpressao(indice, null, tamanhoExpSustituicao, null);
		return atribuirProximaExpressao(umaExpressaoValue, colunaNot, umaExpressaoKey, valorExpInt, ultimo, dadosExpressao);
	}
	
	/**
	 * 
	 * verifica se é uma expressão ou variável que será feito 
	 * a operação AND, isso para valor do lado esquerdo e direito,
	 * depois realiza a operação em si
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param umaExpressaoKey
	 * @param valorExpInt
	 * @param umChar
	 * @param indice
	 * @param ultimo
	 */
	private static String verificarAtribuirValoresAnd(String umaExpressaoValue, String umaExpressaoKey, Integer valorExpInt, 
			                                          Character umChar, Integer indice, Boolean ultimo, Boolean expSemSinal){
		aumentarUmaLinhaMatrizResp();
		String[] colunaValoresApos = null;
		String[] colunaValoresAntes = null;
		Integer tamanhoExpSustituicaoAntes = 0;
		Integer tamanhoExpSustituicaoPos = 0;
		
		if(Boolean.TRUE.equals(expSemSinal)) {
			indice = indice+1;
			umChar = umaExpressaoValue.charAt(indice);
		}
		
		if(ValidacaoCalculadoraTabelaVerdade.isAntesExpressao(umaExpressaoValue, indice).equals(umChar)) {
			String nomeExpressao = retornarNomeExpressaoAnterior(umaExpressaoValue, indice);
			tamanhoExpSustituicaoAntes += nomeExpressao.length();
			colunaValoresAntes = recuperarColunaExp(nomeExpressao);
		}else {
			var nomeVariavel = String.valueOf(umaExpressaoValue.charAt(indice-1));
			tamanhoExpSustituicaoAntes += nomeVariavel.length();
			colunaValoresAntes = recuperarColunaVariavel(nomeVariavel);
		}
		
		if(Boolean.TRUE.equals(expSemSinal)) {
			indice = indice-1;
			umChar = umaExpressaoValue.charAt(indice);
		}
		
		if(ValidacaoCalculadoraTabelaVerdade.isProximoExpressao(umaExpressaoValue, indice).equals(umChar)) {
			String nomeExpressao = retornarNomeExpressaoProximo(umaExpressaoValue, indice);
			tamanhoExpSustituicaoPos += nomeExpressao.length();
			colunaValoresApos = recuperarColunaExp(nomeExpressao);
		}else {
			var nomeVariavel = String.valueOf(umaExpressaoValue.charAt(indice+1));
			tamanhoExpSustituicaoPos += nomeVariavel.length();
			colunaValoresApos = recuperarColunaVariavel(nomeVariavel);
		}
		
		String[] colunaAnd = realizarOperacaoAnd(colunaValoresAntes, colunaValoresApos);
		var dadosExpressao = new DadosExpressao(indice, tamanhoExpSustituicaoAntes, tamanhoExpSustituicaoPos, expSemSinal);
		return atribuirProximaExpressao(umaExpressaoValue, colunaAnd, umaExpressaoKey, valorExpInt, ultimo, dadosExpressao);
	}
	
	/**
	 * 
	 * verifica se é uma expressão ou variável que será feito 
	 * a operação OR, isso para valor do lado esquerdo e direito,
	 * depois realiza a operação em si
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param umaExpressaoKey
	 * @param valorExpInt
	 * @param umChar
	 * @param indice
	 * @param ultimo
	 */
	private static String verificarAtribuirValoresOr(String umaExpressaoValue, String umaExpressaoKey, Integer valorExpInt,
			                                       Character umChar, Integer indice, Boolean ultimo){
		aumentarUmaLinhaMatrizResp();
		String[] colunaValoresApos = null;
		String[] colunaValoresAntes = null;
		Integer tamanhoExpSustituicaoAntes = 0;
		Integer tamanhoExpSustituicaoPos = 0;
		
		if(ValidacaoCalculadoraTabelaVerdade.isAntesExpressao(umaExpressaoValue, indice).equals(umChar)) {
			String nomeExpressao = retornarNomeExpressaoAnterior(umaExpressaoValue, indice);
			tamanhoExpSustituicaoAntes += nomeExpressao.length();
			colunaValoresAntes = recuperarColunaExp(nomeExpressao);
		}else {
			var nomeVariavel = String.valueOf(umaExpressaoValue.charAt(indice-1));
			tamanhoExpSustituicaoAntes += nomeVariavel.length();
			colunaValoresAntes = recuperarColunaVariavel(nomeVariavel);
		}
		
		if(ValidacaoCalculadoraTabelaVerdade.isProximoExpressao(umaExpressaoValue, indice).equals(umChar)) {
			String nomeExpressao = retornarNomeExpressaoProximo(umaExpressaoValue, indice);
			tamanhoExpSustituicaoPos += nomeExpressao.length();
			colunaValoresApos = recuperarColunaExp(nomeExpressao);
		}else {
			var nomeVariavel = String.valueOf(umaExpressaoValue.charAt(indice+1));
			tamanhoExpSustituicaoPos += nomeVariavel.length();
			colunaValoresApos = recuperarColunaVariavel(nomeVariavel);
		}
		
		String[] colunaOr = realizarOperacaoOr(colunaValoresAntes, colunaValoresApos);
		var dadosExpressao = new DadosExpressao(indice, tamanhoExpSustituicaoAntes, tamanhoExpSustituicaoPos, null);
		return atribuirProximaExpressao(umaExpressaoValue, colunaOr, umaExpressaoKey, valorExpInt, ultimo, dadosExpressao);
	}
	
	/**
	 * 
	 * Recuperar o valor da coluna da expressão
	 *
	 * @author Comandolli
	 * @param nomeExpressao
	 * @return colunaValVariavel
	 */
	private static String[] recuperarColunaExp(String nomeExpressao) {
		String[] colunaValVariavel = null;
		colunaValVariavel = new String[getTamanhoPosibilidades()];
		Integer linhaCerta = null;
		for (var l = 0; l < getMatrizExpressoesResp().length; l++) {
			for (var c = 0; c < getMatrizExpressoesResp()[0].length; c++) {
				if((c == 0) && getMatrizExpressoesResp()[l][c] != null && getMatrizExpressoesResp()[l][c].equals(nomeExpressao)){
					linhaCerta = l;
				}else if(linhaCerta != null && linhaCerta == l) {
					colunaValVariavel[c-1] = getMatrizExpressoesResp()[l][c];
				}
			}
		}
		return colunaValVariavel;
	}
	
	/**
	 * 
	 * Recuperar o valor da coluna da variável
	 *
	 * @author Comandolli
	 * @param nomeVariavel
	 * @return colunaValVariavel
	 */
	private static String[] recuperarColunaVariavel(String nomeVariavel) {
		String[] colunaValVariavel = null;
		colunaValVariavel = new String[getTamanhoPosibilidades()];
		Integer linhaCerta = null;
		for (var l = 0; l < getMatrizExibicao().length; l++) {
			for (var c = 0; c < getMatrizExibicao()[0].length; c++) {
				if((c == 0) && getMatrizExibicao()[l][c] != null && getMatrizExibicao()[l][c].equals(nomeVariavel)){
					linhaCerta = l;
				}else if(linhaCerta != null && linhaCerta == l) {
					colunaValVariavel[c-1] = getMatrizExibicao()[l][c];
				}
			}
		}
		return colunaValVariavel;
	}
	
	/**
	 * 
	 * Recupera o nome da expressão para retorno do valor na matriz 
	 *
	 * Nome da expressão - próximo
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param indice
	 * @return nomeExpressao
	 */
	private static String retornarNomeExpressaoProximo(String umaExpressaoValue, Integer indice) {
		var nomeExpressao = new StringBuilder();
		var completou = false;
		for (var i = indice+1; i < umaExpressaoValue.length(); i++) {
			Character umChar = umaExpressaoValue.charAt(i);
			if(umChar == 'E' || umChar == 'X' || umChar == 'P' || umChar == 'I' ||
			   umChar == 'N' || umChar == 'T' || (umChar >= '1' && umChar <= '9')) {
				nomeExpressao.append(umChar);
				completou = true;
			}else if(completou) {
				break;
			}
		}
		return nomeExpressao.toString();
	}
	
	/**
	 * 
	 * Recupera o nome da expressão para retorno do valor na matriz 
	 *
	 * Nome da expressão - anterior
	 *
	 * @author Comandolli
	 * @param umaExpressaoValue
	 * @param indice
	 * @return nomeExpressao
	 */
	private static String retornarNomeExpressaoAnterior(String umaExpressaoValue, Integer indice) {
		var nomeExpressao = new StringBuilder();
		for (var i = indice-1; i >= 0; i--) {
			Character umChar = umaExpressaoValue.charAt(i);
			if(umChar == 'E' || umChar == 'X' || umChar == 'P' || umChar == 'I' ||
			   umChar == 'N' || umChar == 'T' || (umChar >= '1' && umChar <= '9')) {
				nomeExpressao.insert(0, umChar);
			}
		}
		return nomeExpressao.toString();
	}
	
	/**
	 * 
	 * Adiciona uma linha a matriz de resposta
	 *
	 * @author Comandolli
	 */
	private static void aumentarUmaLinhaMatrizResp() {
		String[][] matrizTemp = null;
		
		matrizTemp = new String[getMatrizExpressoesResp().length+1][getTamanhoPosibilidades()+1];
		for (var i = 0; i < getMatrizExpressoesResp().length; i++) {
	        System.arraycopy(getMatrizExpressoesResp()[i], 0, matrizTemp[i], 0, getMatrizExpressoesResp()[i].length);
	    }
		
		setMatrizExpressoesResp(matrizTemp);
	}
	
	/**
	 * 
	 * Realiza operações NOT,
	 * invertendo o valor do vetor
	 *
	 * @author Comandolli
	 * @param colunaValores
	 * @return colunaValores
	 */
	private static String[] realizarOperacaoNot(String[] colunaValores) {
		for (var i = 0; i < colunaValores.length; i++) {
			String umValor = colunaValores[i];
			if(umValor.equals("0")) {
				colunaValores[i] = "1";
			}else {
				colunaValores[i] = "0";
			}
		}
		return colunaValores;
	}
	
	/**
	 * 
	 * Realiza operações AND, comparando se os 
	 * vetores tem 1 na mesma posição, se tiver 
	 * então é atribuido 1 a resposta, são não 0
	 *
	 * @author Comandolli
	 * @param umColuna
	 * @param outraColuna
	 * @return
	 */
	private static String[] realizarOperacaoAnd(String[] umColuna, String[] outraColuna) {
		String[] resposta = null;
		resposta = new String[umColuna.length];
		
		for (var i = 0; i < umColuna.length; i++) {
			String umValor = umColuna[i];
			String outroValor = outraColuna[i];
			if(umValor.equals("1") && outroValor.equals("1")) {
				resposta[i] = "1";
			}else {
				resposta[i] = "0";
			}
		}
		return resposta;
	}
	
	/**
	 * 
	 * Realiza operações OR, comparando se os 
	 * vetores tem, um dos dois 1 na posição, se tiver 
	 * então é atribuido 1 a resposta, são não 0
	 *
	 * @author Comandolli
	 * @param expressao
	 */
	private static String[] realizarOperacaoOr(String[] umColuna, String[] outraColuna) {
		String[] resposta = null;
		resposta = new String[umColuna.length];
		
		for (var i = 0; i < umColuna.length; i++) {
			String umValor = umColuna[i];
			String outroValor = outraColuna[i];
			if(umValor.equals("1") || outroValor.equals("1")) {
				resposta[i] = "1";
			}else {
				resposta[i] = "0";
			}
		}
		return resposta;
	}
	
	/**
	 * 
	 * Montagem de matriz de exibição para resultados.
	 * Com expressões intermediárias e final 
	 *
	 * @author Comandolli
	 */
	public static void montarMatrizExibicaoResultado() {
		List<String> lVariaveis = ValidacaoCalculadoraTabelaVerdade.getListaVariaveis();
		Integer qtdVariaveis = lVariaveis.size();
		
		for (var l = 0; l < getMatrizExpressoesResp().length; l++) {
			for (var c = 0; c < getMatrizExpressoesResp()[0].length; c++) {
				if(getMatrizExibicao()[l+qtdVariaveis][c] == null) {
					if(c == 0) {
						String chaveExp = getMatrizExpressoesResp()[l][c]; 
						String nomeExp = getListaExpressoesInterm().get(chaveExp);
						getMatrizExibicao()[l+qtdVariaveis][c] = substituirExpChavePorValor(nomeExp);
					}else {
						getMatrizExibicao()[l+qtdVariaveis][c] = getMatrizExpressoesResp()[l][c]; 
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * Substituir expressão de chave 
	 * pelo seu valor correspondente 
	 *
	 * @author Comandolli
	 * @param nomeExp
	 * @return nomeExp
	 */
	private static String substituirExpChavePorValor(String nomeExp) {
		while(nomeExp.indexOf("EXP") != -1) {
			for (var i = 0; i < nomeExp.length(); i++) {
				Character umChar = nomeExp.charAt(i);
				
				if(Boolean.TRUE.equals(isExpressaoInterna(nomeExp, umChar, i)) ||
				   Boolean.TRUE.equals(isExpressaoExterna(nomeExp, umChar, i))) {
					
					String chaveCompleta = retornarNomeExpressaoProximo(nomeExp, i-1);
					String nomeSubst;
					
					if(Boolean.TRUE.equals(isExpressaoInterna(nomeExp, umChar, i))){
						nomeSubst = getListaExpressoesInterm().get(chaveCompleta);
					}else {
						nomeSubst = getListaExpressoes().get(chaveCompleta);
					}
					
					nomeExp = nomeExp.substring(0, i)+nomeSubst+nomeExp.substring(i+chaveCompleta.length());
					break;
				}
			}
		}
		return nomeExp;
	}
	
	/**
	 * 
	 * Verifica se a expressão é interna 
	 * para visualização
	 *
	 * @author Comandolli
	 * @param nomeExp
	 * @param umChar
	 * @param indice
	 * @return isExpressaoInterna 
	 */
	private static Boolean isExpressaoInterna(String nomeExp, Character umChar, Integer indice) {
		return (umChar.equals('E') && indice < nomeExp.length()-1 && 
		        nomeExp.charAt(indice+1) == 'X' && indice < nomeExp.length()-3 && 
		        nomeExp.charAt(indice+3) == 'I');
	}
	
	/**
	 * 
	 * Verifica se a expressão é extrena 
	 * para visualização
	 *
	 * @author Comandolli
	 * @param nomeExp
	 * @param umChar
	 * @param indice
	 * @return isExpressaoInterna 
	 */
	private static Boolean isExpressaoExterna(String nomeExp, Character umChar, Integer indice) {
		return umChar.equals('E') && indice < nomeExp.length()-1 && nomeExp.charAt(indice+1) == 'X';
	}

	/**
	 * 
	 * Realiza exibição da matriz na tabela,
	 * recebendo a matriz preenchida e invertando
	 * para adição na propriedade model
	 *
	 * @author Comandolli
	 * @param matrizExibicao
	 * @param tExibicao
	 */
	public static void realizaExibicaoMatriz(String[][] matrizExibicao, JTable tExibicao){
		String[] cabecalho = null;
		cabecalho = new String[getTamanhoExpressoes()];
		
		String[][] conteudoTemp = null;
		conteudoTemp = new String[getTamanhoExpressoes()][getTamanhoPosibilidades()];
		
		String[][] conteudo = null;
		conteudo = new String[getTamanhoPosibilidades()][getTamanhoExpressoes()];
		
		for (var l = 0; l < getTamanhoExpressoes(); l++) {
			for (var c = 0; c < getTamanhoPosibilidades()+1; c++) {
				if(c == 0) {
					cabecalho[l] = matrizExibicao[l][c];
				}else {
					conteudoTemp[l][c-1] = matrizExibicao[l][c];
				}
			}
		}
		
		for (var l = 0; l < conteudoTemp.length; l++) {
			for (var c = 0; c < conteudoTemp[0].length; c++) {
				conteudo[c][l] = conteudoTemp[l][c];
			}
		}
		var modelo = new DefaultTableModel(conteudo,cabecalho);
		tExibicao.setModel(modelo);
	}

	public static String[][] getMatrizExibicao() {
		return matrizExibicao;
	}

	public static void setMatrizExibicao(String[][] matrizExibicao) {
		TabelaVerdadeUtil.matrizExibicao = matrizExibicao;
	}

	public static Integer getTamanhoPosibilidades() {
		return tamanhoPosibilidades;
	}

	public static void setTamanhoPosibilidades(Integer tamanhoPosibilidades) {
		TabelaVerdadeUtil.tamanhoPosibilidades = tamanhoPosibilidades;
	}

	public static Integer getTamanhoExpressoes() {
		return tamanhoExpressoes;
	}

	public static void setTamanhoExpressoes(Integer tamanhoExpressoes) {
		TabelaVerdadeUtil.tamanhoExpressoes = tamanhoExpressoes;
	}

	public static Map<String, String> getListaExpressoes() {
		return listaExpressoes;
	}

	public static void setListaExpressoes(Map<String, String> listaExpressoes) {
		TabelaVerdadeUtil.listaExpressoes = listaExpressoes;
	}

	public static String[][] getMatrizExpressoesResp() {
		return matrizExpressoesResp;
	}

	public static void setMatrizExpressoesResp(String[][] listaExpressoesResp) {
		TabelaVerdadeUtil.matrizExpressoesResp = listaExpressoesResp;
	}

	public static Map<String, String> getListaExpressoesInterm() {
		return listaExpressoesInterm;
	}

	public static void setListaExpressoesInterm(Map<String, String> listaExpressoesInterm) {
		TabelaVerdadeUtil.listaExpressoesInterm = listaExpressoesInterm;
	}
	
}
