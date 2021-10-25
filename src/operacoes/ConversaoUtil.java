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

}
