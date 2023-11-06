package br.com.lanchonete.enumeradores;

import java.util.Locale;
import java.util.ResourceBundle;

public enum TipoPagamnento{

	 DINHEIRO("DINHEIRO"),
	    CARTAO_DE_DEBITO_VISA("CARTAO_DE_DEBITO_VISA"),
	    CARTAO_DE_DEBITO_MASTERCARD("CARTAO_DE_DEBITO_MASTERCARD"),
	    CARTAO_DE_DEBITO_ELO("CARTAO_DE_DEBITO_ELO"),
	    CARTAO_DE_CREDITO_VISA("CARTAO_DE_CREDITO_VISA"),
	    CARTAO_DE_CREDITO_MASTERCARD("CARTAO_DE_CREDITO_MASTERCARD"),
	    CARTAO_DE_CREDITO_ELO("CARTAO_DE_CREDITO_ELO");
	
	
	 public String getDescricao() {
	        Locale ptBrLocale = new Locale("pt", "BR");
	        ResourceBundle bundle = ResourceBundle.getBundle("TipoPagamento", ptBrLocale);
	        return bundle.getString(descricao);
	    }

	private TipoPagamnento(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

//	public String getDescricao() {
//		return descricao;
//	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static String[] getDescricaoComboBox() {
		String[] listaRetorno = new String[7];
		int i = 0;
        Locale.setDefault(new Locale("pt", "BR"));

		for (TipoPagamnento tipoPagamnento : TipoPagamnento.values()) {
			listaRetorno[i] = tipoPagamnento.getDescricao();
			i++;
		}
		return listaRetorno;
	}

}
