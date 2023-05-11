package br.com.lanchonete.enumeradores;

public enum FuncaoFuncionario {

	GERENTE("Gerente"),
	MOTOBOY("Motoboy"),
	ATENDENTE("Atendente");

	private FuncaoFuncionario (String descricao) {
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
		String[] listaRetorno = new String[3];
		int i = 0;
		for (FuncaoFuncionario funcaoFuncionario : FuncaoFuncionario.values()) {
			listaRetorno[i] = funcaoFuncionario.getDescricao();
			i++;
		}
		return listaRetorno;
	}

}