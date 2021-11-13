package dados;

public class DadosExpressaoAtualizado {

	private String umaExpressaoValue;
	private Integer valorExpInt;
	
	public DadosExpressaoAtualizado(String umaExpressaoValue, Integer valorExpInt) {
		this.umaExpressaoValue = umaExpressaoValue;
		this.valorExpInt = valorExpInt;
	}
	
	public String getUmaExpressaoValue() {
		return umaExpressaoValue;
	}
	public void setUmaExpressaoValue(String umaExpressaoValue) {
		this.umaExpressaoValue = umaExpressaoValue;
	}
	public Integer getValorExpInt() {
		return valorExpInt;
	}
	public void setValorExpInt(Integer valorExpInt) {
		this.valorExpInt = valorExpInt;
	}
	
}
