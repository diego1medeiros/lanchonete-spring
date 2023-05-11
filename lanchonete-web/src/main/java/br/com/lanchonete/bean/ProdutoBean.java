package br.com.lanchonete.bean;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import com.google.gson.Gson;
import br.com.lanchonete.dto.FornecedorDto;
import br.com.lanchonete.dto.ProdutoDto;
import br.com.lanchonete.service.FornecedorService;
import br.com.lanchonete.service.ProdutoService;
import br.com.lanchonete.utils.CarregarImagens;
import br.com.lanchonete.utils.Message;
import br.com.lanchonete.utils.UploadImagem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "produtobean")
@RequestScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ProdutoDto[] listaProdutos;

	@Inject
	private ProdutoDto produto;

	private UploadedFile file;

	@Inject
	private ProdutoService produtoService;
	
	@Inject
	private FornecedorService fornecedorService;

	// cadastrar e atualizar produtos no banco de dados
	public String cadastrarProduto() {

		try {
			if (produto.getId() == null) {
				UploadImagem.uploadImagem(file);
				produto.setCaminhoImagem("C:/Uploads/" + file.getFileName());
				produtoService.cadastrarProdutoNoSpring(produto);
				Message.info("Cadastro", "Produto cadastrado com sucesso!!");

			} else if (produto.getCaminhoImagem() == null) {
				Message.warr("A imagem e obrigatoria!!", "");

			} else {
				Path origem = Paths.get(produto.getCaminhoImagem());
				Path destino = Paths.get("C:/Uploads/" + file.getFileName());
				Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
				produtoService.atualizarProdutoNoSpring(produto);
				Message.info("Atualização", "Produto atualizado com sucesso!!");
			}
		} catch (Exception e) {

		}
		listaDadosDosProdutos();
		limpar();
		return null;
	}

	// caminho da imagem recuperado do banco de dados
	public StreamedContent carregarImagens(ProdutoDto produto) throws FileNotFoundException {
		return CarregarImagens.carregarImagens(produto);
	}

	// lista imformações dos produtos vido do banco de dados
	@PostConstruct
	public void listaDadosDosProdutos() {
		String json = produtoService.listarProduto();
		Gson gson = new Gson();
		listaProdutos = gson.fromJson(json, ProdutoDto[].class);
	}

	// excluir produtos do banco de dados
	public void excluirProduto(ProdutoDto produto) {
		try {
			Path arquivo = Paths.get(produto.getCaminhoImagem());
			Files.deleteIfExists(arquivo);
			produtoService.excluirProdutoNoSpring(produto.getId());
			listaDadosDosProdutos();
			limpar();
			Message.info("Excluir", "Produto excluido com sucesso!!");
		} catch (Exception e) {
		}
	}

	// lista fornecedores no combobox

	public List<SelectItem> getLista() {
		List<SelectItem> lista = new ArrayList<>();
	        String json = fornecedorService.listaFornecedor();
		Gson gson = new Gson();
		FornecedorDto[] listaFornecedores = gson.fromJson(json, FornecedorDto[].class);
		for (FornecedorDto fornecedor : listaFornecedores) {
			lista.add(new SelectItem(fornecedor.getId(), fornecedor.getRazaoSocial()));
		}
		return lista;
	}

	// lista dados da tela
	public void limpar() {
		produto = new ProdutoDto();

	}
}