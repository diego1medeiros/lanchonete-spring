package br.com.lanchonete.enumeradores;

public enum TipoPagamnento{

	DINHEIRO(" Dinheiro"), 
	CARTÃO_DE_DEBITO_VISA("Cartão de Debito Visa"), 
	CARTÃO_DE_DEBITO_MASTERCARD("Cartão de Debito MasterCard"), 
	CARTÃO_DE_DEBITO_ELO("Cartão de Debito Elo"), 
	CARTÃO_DE_CREDITO_VISA("Cartão de Credito Visa"), 
	CARTÃO_DE_CREDITO_MASTERCARD("Cartão de Ctredito MasterCard"),
	CARTÃO_DE_CREDITO_ELO("Cartão de Credito Elo");

	private TipoPagamnento(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static String[] getDescricaoComboBox() {
		String[] listaRetorno = new String[7];
		int i = 0;
		for (TipoPagamnento tipoPagamnento : TipoPagamnento.values()) {
			listaRetorno[i] = tipoPagamnento.getDescricao();
			i++;
		}
		return listaRetorno;
	}

}
