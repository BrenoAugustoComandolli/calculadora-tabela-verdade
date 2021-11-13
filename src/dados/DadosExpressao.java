package dados;

public class DadosExpressao {
	
	private Integer indice;
	private Integer tamanhoExpSustituicaoAntes;
	private Integer tamanhoExpSustituicaoPos;
	private Boolean expSemSinal;
	
	public DadosExpressao(Integer indice, Integer tamanhoExpSustituicaoAntes, Integer tamanhoExpSustituicaoPos, Boolean expSemSinal) {
		this.indice = indice;
		this.tamanhoExpSustituicaoAntes = tamanhoExpSustituicaoAntes;
		this.tamanhoExpSustituicaoPos = tamanhoExpSustituicaoPos;
		this.expSemSinal = expSemSinal;
	}
	
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public Integer getTamanhoExpSustituicaoAntes() {
		return tamanhoExpSustituicaoAntes;
	}
	public void setTamanhoExpSustituicaoAntes(Integer tamanhoExpSustituicaoAntes) {
		this.tamanhoExpSustituicaoAntes = tamanhoExpSustituicaoAntes;
	}
	public Integer getTamanhoExpSustituicaoPos() {
		return tamanhoExpSustituicaoPos;
	}
	public void setTamanhoExpSustituicaoPos(Integer tamanhoExpSustituicaoPos) {
		this.tamanhoExpSustituicaoPos = tamanhoExpSustituicaoPos;
	}

	public Boolean getExpSemSinal() {
		return expSemSinal;
	}

	public void setExpSemSinal(Boolean expSemSinal) {
		this.expSemSinal = expSemSinal;
	}
	
}
