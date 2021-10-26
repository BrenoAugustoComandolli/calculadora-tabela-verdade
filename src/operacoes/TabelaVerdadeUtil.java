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
		Integer tamanhoPosibilidades = OperacoesMatematicasUtil.realizarPotenciacao(2, qtdVariaveis);
		Integer tamanhoExpressoes = capturaQtdOperadores(expressao)+qtdVariaveis+1;
		tamanhoPosibilidades = OperacoesMatematicasUtil.realizarPotenciacao(2, qtdVariaveis);
		tamanhoExpressoes = capturaQtdOperadores(expressao)+qtdVariaveis+1;
		var matrizExibicao = new String[tamanhoExpressoes][tamanhoPosibilidades+1];
		
		Integer divisorZerosUns = tamanhoPosibilidades;
		for (var i = 0; i < lVariaveis.size(); i++) {
			divisorZerosUns /= 2;
			Integer contador = divisorZerosUns;
			var isLigado = true;
			for (var j = 0; j < tamanhoPosibilidades+1; j++) {

				if(j == 0) {
					matrizExibicao[i][j] = String.valueOf(lVariaveis.get(i));
				}else if (isLigado)  {
					matrizExibicao[i][j] = "1";
					contador--;
				}else {
					matrizExibicao[i][j] = "0";
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
		return matrizExibicao; 
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
	
	public static void realizarOperacaoAnd(String expressao) 
	{
		for (int iIdxExpressao = 0; iIdxExpressao < expressao.length(); ++iIdxExpressao)
		{
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
			
			int iColunaVazia = 0;
			
			boolean bPreencheuTudo = false;
			
			for (int lIdxColuna = 0; lIdxColuna <= tamanhoExpressoes; ++lIdxColuna)
			{
				for(int lIdxLinha = 0; lIdxLinha < tamanhoPosibilidades + 1; ++lIdxLinha)
				{
					if (lIdxLinha == 0)
					{
						if (matrizExibicao[lIdxColuna][lIdxLinha] == null)
						{
							iColunaVazia = lIdxColuna;
							bPreencheuTudo = true;
							break;
						}
						
						if (matrizExibicao[lIdxColuna][lIdxLinha].equals(String.valueOf(expressao.charAt(iIdxExpressao - 1))))
						{
							aLinhaUm = matrizExibicao[lIdxColuna];
							break;
						}
						
						if (matrizExibicao[lIdxColuna][lIdxLinha].equals(String.valueOf(expressao.charAt(iIdxExpressao + 1))))
						{
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
				
			for (int iColunaUm = 1; iColunaUm <= aLinhaUm.length - 1; ++iColunaUm)
			{
				aLinhaPreenchida[iColunaUm] = (aLinhaUm[iColunaUm] == "1" && aLinhaDois[iColunaUm] == "1") ? "1" : "0";
			}
				
			matrizExibicao[iColunaVazia] = aLinhaPreenchida;
		}
	}
	
	public static void realizarOperacaoOr(String expressao) 
	{
		for (int iIdxExpressao = 0; iIdxExpressao < expressao.length(); ++iIdxExpressao)
		{
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
			
			for (int lIdxColuna = 0; lIdxColuna <= tamanhoExpressoes; ++lIdxColuna)
			{
				for(int lIdxLinha = 0; lIdxLinha < tamanhoPosibilidades + 1; ++lIdxLinha)
				{
					if (lIdxLinha == 0)
					{
						if (matrizExibicao[lIdxColuna][lIdxLinha] == null)
						{
							iColunaVazia = lIdxColuna;
							bPreencheuTudo = true;
							break;
						}
						
						if (matrizExibicao[lIdxColuna][lIdxLinha].equals(String.valueOf(expressao.charAt(iIdxExpressao - 1))))
						{
							aLinhaUm = matrizExibicao[lIdxColuna];
							break;
						}
						
						if (matrizExibicao[lIdxColuna][lIdxLinha].equals(String.valueOf(expressao.charAt(iIdxExpressao + 1))))
						{
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
				
			for (int iColunaUm = 1; iColunaUm <= aLinhaUm.length - 1; ++iColunaUm)
			{
				aLinhaPreenchida[iColunaUm] = (aLinhaUm[iColunaUm] == "1" || aLinhaDois[iColunaUm] == "1") ? "1" : "0";
			}
				
			matrizExibicao[iColunaVazia] = aLinhaPreenchida;
		}
	}
	
	public static void realizarOperacaoNot(String expressao) 
	{
		for (int iIdxExpressao = 0; iIdxExpressao < expressao.length(); ++iIdxExpressao)
		{
			if (ValidacaoCalculadoraTabelaVerdade.isOperadorLogico(expressao.charAt(iIdxExpressao)) == null)
				continue;
			
			if (expressao.charAt(iIdxExpressao) != '~')
				continue;
			
			ValidacaoCalculadoraTabelaVerdade.atribuirListaVariaveis(expressao);
			List<String> lVariaveis = ValidacaoCalculadoraTabelaVerdade.getListaVariaveis();
			Integer qtdVariaveis = lVariaveis.size();
			Integer tamanhoPosibilidades = OperacoesMatematicasUtil.realizarPotenciacao(2, qtdVariaveis);
			Integer tamanhoExpressoes = capturaQtdOperadores(expressao)+qtdVariaveis+1;
			
			boolean bEncontrouColuna = false;
			boolean bPreencheuInformacao = false;
			
			int lColuna = 0;
			
			for (int lIdxColuna = 0; lIdxColuna <= tamanhoExpressoes; ++lIdxColuna)
			{
				for(int lIdxLinha = 0; lIdxLinha < tamanhoPosibilidades + 1; ++lIdxLinha)
				{
					if (lIdxLinha == 0)
					{
						if (matrizExibicao[lIdxColuna][lIdxLinha] == null)
							break;
						
						if (matrizExibicao[lIdxColuna][lIdxLinha].equals(String.valueOf(expressao.charAt(iIdxExpressao + 1))))
						{
							bEncontrouColuna = true;
							continue;
						}
					}
					
					if (!bEncontrouColuna)
						break;
					
					if (!bPreencheuInformacao)
					{
						lColuna = lIdxColuna;
						bPreencheuInformacao = true;
						break;
					}
				}
				
				if (matrizExibicao[lIdxColuna][0] != null)
					continue;
				
				if (!bEncontrouColuna)
					break;
				
				for(int lIdxLinha = 0; lIdxLinha < tamanhoPosibilidades + 1; ++lIdxLinha)
				{
					if (lIdxLinha == 0)
					{
						matrizExibicao[lIdxColuna][lIdxLinha] = String.valueOf(expressao.charAt(iIdxExpressao)) + 
																String.valueOf(expressao.charAt(iIdxExpressao + 1));
						continue;
					}
					matrizExibicao[lIdxColuna][lIdxLinha] = matrizExibicao[lColuna][lIdxLinha] == "1" ? "0" : "1";
				}
				
				break;
			}
		}
	}
	
	public static void realizaExibicaoMatriz(String[][] matrizExibicao, JTable tExibicao){
	
		String cabecalho[] = new String [tamanhoExpressoes];
		String conteudo[][] = new String[tamanhoExpressoes][tamanhoPosibilidades];
		
		for (var i = 0; i < matrizExibicao.length; i++) {
			for (var j = 0; j < matrizExibicao.length; j++) {
				if(j == 0) {
					cabecalho[i] = matrizExibicao[i][j];
				}else {
					conteudo[i][j]= matrizExibicao[i][j];
				}
			}
		}
		DefaultTableModel modelo = new DefaultTableModel(conteudo,cabecalho);
		tExibicao.setModel(modelo);
	}

	public static String[][] getMatrizExibicao() {
		return matrizExibicao;
	}

	public static void setMatrizExibicao(String[][] matrizExibicao) {
		TabelaVerdadeUtil.matrizExibicao = matrizExibicao;
	}
	
}
