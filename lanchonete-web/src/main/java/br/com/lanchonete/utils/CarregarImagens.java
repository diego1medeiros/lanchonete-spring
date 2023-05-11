package br.com.lanchonete.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.lanchonete.dto.ItemVendaDto;
import br.com.lanchonete.dto.ProdutoDto;

public class CarregarImagens {

	public static StreamedContent carregarImagens(ProdutoDto produto) throws FileNotFoundException {
		InputStream stream = new FileInputStream(produto.getCaminhoImagem());
		StreamedContent imagem = DefaultStreamedContent.builder().stream(() -> stream).build();
		return imagem;
	}

	public static StreamedContent carregarImagens(ItemVendaDto itemVenda) throws FileNotFoundException {
		InputStream stream = new FileInputStream(itemVenda.getProduto().getCaminhoImagem());
		StreamedContent	imagem = DefaultStreamedContent.builder().stream(() -> stream).build();
	return imagem;
}
}