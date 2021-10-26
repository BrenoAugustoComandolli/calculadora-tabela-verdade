package operacoes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import funcoes.ValidacaoCalculadoraTabelaVerdade;

public class TabelaVerdadeUtil {
	
	private static String[][] matrizExibicao;
	private static Integer tamanhoPosibilidades; 
	private static Integer tamanhoExpressoes; 
	
	private TabelaVerdadeUtil() {
		throw new IllegalStateException("Classe Utilitária");
	}
	
	/**
	 * 
	 * Realizado a atribuição das variáveis na matriz 
	 *
	 * @author Yuri Martins
	 * @param expressao
	 * @return matrizExibicao
	 */
	public static String[][] realizarAdicicaoVariaveis(String expressao) {
		ValidacaoCalculadoraTabelaVerdade.atribuirListaVariaveis(expressao);
		List<String> lVariaveis = ValidacaoCalculadoraTabelaVerdade.getListaVariaveis();
		Integer qtdVariaveis = lVariaveis.size();
		setTamanhoPosibilidades(OperacoesMatematicasUtil.realizarPotenciacao(2, qtdVariaveis));
		setTamanhoExpressoes(capturaQtdOperadores(expressao)+qtdVariaveis);
		setMatrizExibicao(new String[tamanhoExpressoes][tamanhoPosibilidades+1]);
		
		adicionarValoresPossibilidades(lVariaveis);
		
		return matrizExibicao; 
	}
	
	/**
	 * 
	 * Adicionando valores de possibilidades
	 *
	 * @author Comandolli
	 * @param lVariaveis
	 */
	public static void adicionarValoresPossibilidades(List<String> lVariaveis) {
		Integer divisorZerosUns = getTamanhoPosibilidades();
		for (var i = 0; i < lVariaveis.size(); i++) {
			divisorZerosUns /= 2;
			Integer contador = divisorZerosUns;
			var isLigado = true;
			for (var j = 0; j < getTamanhoPosibilidades()+1; j++) {

				if(j == 0) {
					getMatrizExibicao()[i][j] = String.valueOf(lVariaveis.get(i));
				}else if (isLigado)  {
					getMatrizExibicao()[i][j] = "1";
					contador--;
				}else {
					getMatrizExibicao()[i][j] = "0";
					contador--;
				}
				
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
	 * Recuperar as expressoes para realizar as operações 
	 *
	 * @author Yuri Martins
	 * @param expressao
	 * @return listaExpressoes
	 */
	public static List<String> pegarExpressaos(String expressao) {
		Boolean fechou = false;
		Integer inicioExpressao = 0;
		List<String> listaExpressoes = new ArrayList<>();
		for (var i = 0; i < expressao.length(); i++) {
			
			if(ValidacaoCalculadoraTabelaVerdade.isSeparadorFechamento(expressao.charAt(i)) != null){
				fechou = true;
			}
			
			if(ValidacaoCalculadoraTabelaVerdade.isSeparadorAbertura(expressao.charAt(i)) != null && fechou) {
				listaExpressoes.add(expressao.substring(inicioExpressao, i));
				inicioExpressao = i;
				fechou = false;
			}
		}
		return listaExpressoes;
	}
	
	/**
	 * 
	 * Recupera quantidade de operadores 
	 *
	 * @author Yuri Martins
	 * @param expressao
	 * @return qtdOperadores
	 */
	public static Integer capturaQtdOperadores(String expressao) {
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
	 * Realiza operações de AND
	 *
	 * @author Yuri Martind
	 * @param expressao
	 */
	public static void realizarOperacaoAnd(String expressao) {
		for (var iIdxExpressao = 0; iIdxExpressao < expressao.length(); ++iIdxExpressao){
			if (ValidacaoCalculadoraTabelaVerdade.isOperadorLogico(expressao.charAt(iIdxExpressao)) == null)
				continue;
			
			if (expressao.charAt(iIdxExpressao) != '^')
				continue;
			
			ValidacaoCalculadoraTabelaVerdade.atribuirListaVariaveis(expressao);
			List<String> lVariaveis = ValidacaoCalculadoraTabelaVerdade.getListaVariaveis();
			Integer qtdVariaveis = lVariaveis.size();
			Integer tamanhoPosibilidades = OperacoesMatematicasUtil.realizarPotenciacao(2, qtdVariaveis);
			Integer tamanhoExpressoes = capturaQtdOperadores(expressao)+qtdVariaveis+1;
			
			String[] aLinhaUm = null;
			String[] aLinhaDois = null;
			
			var iColunaVazia = 0;
			
			var bPreencheuTudo = false;
			
			for (var lIdxColuna = 0; lIdxColuna <= tamanhoExpressoes; ++lIdxColuna){
				for(var lIdxLinha = 0; lIdxLinha < tamanhoPosibilidades + 1; ++lIdxLinha){
					if (lIdxLinha == 0){
						if (getMatrizExibicao()[lIdxColuna][lIdxLinha] == null){
							iColunaVazia = lIdxColuna;
							bPreencheuTudo = true;
							break;
						}
						
						if (getMatrizExibicao()[lIdxColuna][lIdxLinha].equals(String.valueOf(expressao.charAt(iIdxExpressao - 1)))){
							aLinhaUm = getMatrizExibicao()[lIdxColuna];
							break;
						}
						
						if (getMatrizExibicao()[lIdxColuna][lIdxLinha].equals(String.valueOf(expressao.charAt(iIdxExpressao + 1)))){
							aLinhaDois = getMatrizExibicao()[lIdxColuna];
							break;
						}
					}
				}
				
				if (bPreencheuTudo)
					break;
			}
			
			
			String[] aLinhaPreenchida = new String[aLinhaUm.length];
			
			aLinhaPreenchida[0] = String.valueOf(expressao.charAt(iIdxExpressao - 1)) + 
								  String.valueOf(expressao.charAt(iIdxExpressao)) + 
								  String.valueOf(expressao.charAt(iIdxExpressao + 1));
				
			for (var iColunaUm = 1; iColunaUm <= aLinhaUm.length - 1; ++iColunaUm){
				aLinhaPreenchida[iColunaUm] = (aLinhaUm[iColunaUm] == "1" && aLinhaDois[iColunaUm] == "1") ? "1" : "0";
			}
				
			getMatrizExibicao()[iColunaVazia] = aLinhaPreenchida;
		}
	}
	
	/**
	 * 
	 * Realiza operações de OR
	 *
	 * @author Yuri Martins
	 * @param expressao
	 */
	public static void realizarOperacaoOr(String expressao) {
		for (int iIdxExpressao = 0; iIdxExpressao < expressao.length(); ++iIdxExpressao) {
			if (ValidacaoCalculadoraTabelaVerdade.isOperadorLogico(expressao.charAt(iIdxExpressao)) == null)
				continue;
			
			if (expressao.charAt(iIdxExpressao) != 'v' && expressao.charAt(iIdxExpressao) != 'V')
				continue;
			
			ValidacaoCalculadoraTabelaVerdade.atribuirListaVariaveis(expressao);
			List<String> lVariaveis = ValidacaoCalculadoraTabelaVerdade.getListaVariaveis();
			Integer qtdVariaveis = lVariaveis.size();
			Integer tamanhoPosibilidades = OperacoesMatematicasUtil.realizarPotenciacao(2, qtdVariaveis);
			Integer tamanhoExpressoes = capturaQtdOperadores(expressao)+qtdVariaveis+1;
			
			String[] aLinhaUm = null;
			String[] aLinhaDois = null;
			
			int iColunaVazia = 0;
			
			boolean bPreencheuTudo = false;
			
			for (int lIdxColuna = 0; lIdxColuna <= tamanhoExpressoes; ++lIdxColuna){
				
				for(int lIdxLinha = 0; lIdxLinha < tamanhoPosibilidades + 1; ++lIdxLinha){
					
					if (lIdxLinha == 0){
						
						if (matrizExibicao[lIdxColuna][lIdxLinha] == null){
							iColunaVazia = lIdxColuna;
							bPreencheuTudo = true;
							break;
						}
						
						if (matrizExibicao[lIdxColuna][lIdxLinha].equals(String.valueOf(expressao.charAt(iIdxExpressao - 1)))){
							aLinhaUm = matrizExibicao[lIdxColuna];
							break;
						}
						
						if (matrizExibicao[lIdxColuna][lIdxLinha].equals(String.valueOf(expressao.charAt(iIdxExpressao + 1)))){
							aLinhaDois = matrizExibicao[lIdxColuna];
							break;
						}
					}
				}
				
				if (bPreencheuTudo)
					break;
			}
			
			String[] aLinhaPreenchida = new String[aLinhaUm.length];
			
			aLinhaPreenchida[0] = String.valueOf(expressao.charAt(iIdxExpressao - 1)) + 
								  String.valueOf(expressao.charAt(iIdxExpressao)) + 
								  String.valueOf(expressao.charAt(iIdxExpressao + 1));
				
			for (var iColunaUm = 1; iColunaUm <= aLinhaUm.length - 1; ++iColunaUm){
				aLinhaPreenchida[iColunaUm] = (aLinhaUm[iColunaUm] == "1" || aLinhaDois[iColunaUm] == "1") ? "1" : "0";
			}
				
			matrizExibicao[iColunaVazia] = aLinhaPreenchida;
		}
	}
	
	/**
	 * 
	 * Realiza operações de NOT
	 *
	 * @author Yuri Martins
	 * @param expressao
	 */
	public static void realizarOperacaoNot(String expressao) {
		for (int iIdxExpressao = 0; iIdxExpressao < expressao.length(); ++iIdxExpressao){
			if (ValidacaoCalculadoraTabelaVerdade.isOperadorLogico(expressao.charAt(iIdxExpressao)) == null)
				continue;
			
			if (expressao.charAt(iIdxExpressao) != '~')
				continue;
			
			ValidacaoCalculadoraTabelaVerdade.atribuirListaVariaveis(expressao);
			List<String> lVariaveis = ValidacaoCalculadoraTabelaVerdade.getListaVariaveis();
			Integer qtdVariaveis = lVariaveis.size();
			Integer tamanhoPosibilidades = OperacoesMatematicasUtil.realizarPotenciacao(2, qtdVariaveis);
			Integer tamanhoExpressoes = capturaQtdOperadores(expressao)+qtdVariaveis+1;
			
			var bEncontrouColuna = false;
			var bPreencheuInformacao = false;
			
			int lColuna = 0;
			
			for (int lIdxColuna = 0; lIdxColuna <= tamanhoExpressoes; ++lIdxColuna){
				for(int lIdxLinha = 0; lIdxLinha < tamanhoPosibilidades + 1; ++lIdxLinha){
					if (lIdxLinha == 0){
						if (matrizExibicao[lIdxColuna][lIdxLinha] == null)
							break;
						
						if (matrizExibicao[lIdxColuna][lIdxLinha].equals(String.valueOf(expressao.charAt(iIdxExpressao + 1)))){
							bEncontrouColuna = true;
							continue;
						}
					}
					
					if (!bEncontrouColuna)
						break;
					
					if (!bPreencheuInformacao){
						lColuna = lIdxColuna;
						bPreencheuInformacao = true;
						break;
					}
				}
				
				if (getMatrizExibicao()[lIdxColuna][0] != null)
					continue;
				
				if (!bEncontrouColuna)
					break;
				
				for(int lIdxLinha = 0; lIdxLinha < tamanhoPosibilidades + 1; ++lIdxLinha){
					if (lIdxLinha == 0){
						getMatrizExibicao()[lIdxColuna][lIdxLinha] = String.valueOf(expressao.charAt(iIdxExpressao)) + 
																String.valueOf(expressao.charAt(iIdxExpressao + 1));
						continue;
					}
					getMatrizExibicao()[lIdxColuna][lIdxLinha] = getMatrizExibicao()[lColuna][lIdxLinha] == "1" ? "0" : "1";
				}
				break;
			}
		}
	}
	
	/**
	 * 
	 * Realiza exibição da matriz na griedview
	 *
	 * @author Comandolli
	 * @param matrizExibicao
	 * @param tExibicao
	 */
	public static void realizaExibicaoMatriz(String[][] matrizExibicao, JTable tExibicao){
		String cabecalho[] = new String[getTamanhoExpressoes()];
		String conteudo[][] = new String[getTamanhoExpressoes()][getTamanhoPosibilidades()];
		
		for (var i = 0; i < getTamanhoExpressoes(); i++) {
			for (var j = 0; j < getTamanhoPosibilidades()+1; j++) {
				if(j == 0) {
					cabecalho[i] = matrizExibicao[i][j];
				}else {
					conteudo[i][j-1]= matrizExibicao[i][j];
				}
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
	
}
