package operacoes;

import java.util.ArrayList;
import java.util.List;

public class ConversaoUtil {

	private ConversaoUtil() {
	}

	/**
	 * 
	 * Conversão de DECIMAL para BINÁRIO
	 *
	 * @author Luiz Miguel
	 * @param decimal
	 * @return
	 */
	public static String converterDecimalParaBinario(int decimal) {
		var valorNegativo = false;
		if (String.valueOf(decimal).charAt(0) == '-') {
			decimal = Integer.parseInt(String.valueOf(decimal).substring(1));
			valorNegativo = true;
		}

		List<Integer> listaNumeros = new ArrayList<>();
		while (decimal > 0) {
			listaNumeros.add(decimal % 2);
			decimal = decimal / 2;
		}

		var texto = new StringBuilder();
		for (Integer umItem : listaNumeros) {
			texto.insert(0, umItem.toString());
		}

		if (valorNegativo) {
			return "-" + texto.toString();
		}
		return String.valueOf(texto.toString());
	}

	/**
	 * 
	 * Conversão de BINÁRIO para DECIMAL
	 *
	 * @author Luiz Miguel
	 * @param binario
	 * @return
	 */
	public static String converterBinarioParaDecimal(String binario) {
		var valorNegativo = false;
		if (binario.charAt(0) == '-') {
			binario = String.valueOf(binario).substring(1);
			valorNegativo = true;
		}

		var soma = 0;
		var contador = 0;
		for (int i = binario.length() - 1; i >= 0; i--) {
			soma = (int) (soma + Math.pow(2, contador) * Character.getNumericValue(binario.charAt(i)));
			contador++;
		}

		if (valorNegativo) {
			return "-" + soma;
		}
		return String.valueOf(soma);
	}
	
	/**
	 * 
	 * Conversão de HEXADECIMAL para DECIMAL
	 *
	 * @author Leonardo
	 * @param sNumeroHexa
	 * @return
	 * @throws Exception
	 */
	public static String converterHexaParaDecimal(String sNumeroHexa) {
		var valorNegativo = false;
		if(sNumeroHexa.charAt(0) == '-') {
			sNumeroHexa = sNumeroHexa.substring(1);
			valorNegativo = true;
		}
		
		sNumeroHexa = sNumeroHexa.replace("x", "0");
		
		var iBaseCalculo = 1;
		var iVariavelTemporaria = 0;
		
		for (int lIdx = sNumeroHexa.length() - 1; lIdx >= 0; lIdx--){
			
			switch (sNumeroHexa.toUpperCase().charAt(lIdx)){
				case '0': iVariavelTemporaria += 0  * iBaseCalculo; break;
				case '1': iVariavelTemporaria += 1  * iBaseCalculo; break;
				case '2': iVariavelTemporaria += 2  * iBaseCalculo; break;
				case '3': iVariavelTemporaria += 3  * iBaseCalculo; break;
				case '4': iVariavelTemporaria += 4  * iBaseCalculo; break;
				case '5': iVariavelTemporaria += 5  * iBaseCalculo; break;
				case '6': iVariavelTemporaria += 6  * iBaseCalculo; break;
				case '7': iVariavelTemporaria += 7  * iBaseCalculo; break;
				case '8': iVariavelTemporaria += 8  * iBaseCalculo; break;
				case '9': iVariavelTemporaria += 9  * iBaseCalculo; break;
				case 'A': iVariavelTemporaria += 10 * iBaseCalculo; break;
				case 'B': iVariavelTemporaria += 11 * iBaseCalculo; break;
				case 'C': iVariavelTemporaria += 12 * iBaseCalculo; break;
				case 'D': iVariavelTemporaria += 13 * iBaseCalculo; break;
				case 'E': iVariavelTemporaria += 14 * iBaseCalculo; break;
				case 'F': iVariavelTemporaria += 15 * iBaseCalculo; break;
				default: iVariavelTemporaria = 0;
			}
			
			iBaseCalculo *= 16;
		}
		
		if(valorNegativo) {
			return "-"+iVariavelTemporaria;
		}
		return String.valueOf(iVariavelTemporaria);
	}
	
	/**
	 * 
	 * Conversão de DECIMAL para HEXADECIMAL 
	 *
	 * @author Leonardo
	 * @param iEntrada
	 * @return
	 */
	public static String converterDecimalParaHexa(int iEntrada){
		var valorNegativo = false;
		if(String.valueOf(iEntrada).charAt(0) == '-') {
			iEntrada = Integer.parseInt(String.valueOf(iEntrada).substring(1));
			valorNegativo = true;
		}
		
		var sTextoSaida = new StringBuilder();
		var sHexChars = "0123456789ABCDEF";  
		
		var iVariavelTemporaria = 0;
		
		while (iEntrada > 16){
			iVariavelTemporaria = iEntrada % 16;
			sTextoSaida.insert(0,sHexChars.charAt(iVariavelTemporaria));
			iEntrada = iEntrada / 16;
		}
		
		if(iEntrada > 0) {
			sTextoSaida.insert(0,sHexChars.charAt(iEntrada));
		}
		
		if(valorNegativo) {
			return "-"+sTextoSaida.toString();
		}
		return sTextoSaida.toString();
	}

}

