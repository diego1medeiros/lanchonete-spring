package br.com.lanchonete.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.lanchonete.dto.produtodto.DadosCadastroProduto;
import br.com.lanchonete.dto.produtodto.DadosListagemProdutos;
import br.com.lanchonete.entity.Produto;
import br.com.lanchonete.repository.ProdutoRepository;

@RestController
@RequestMapping("/lanchonete")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;

	@GetMapping("/listarproduto")
	public ResponseEntity<?> listarProdutos() {
		var listarProdutos = repository.findAll().stream().map(DadosListagemProdutos::new).toList();
		return ResponseEntity.ok(listarProdutos);
	}
	
	@PostMapping("/cadastrarproduto")
	public ResponseEntity<?> cadastrarProduto(@RequestBody DadosCadastroProduto dadosProduto,
			UriComponentsBuilder uriComponentsBuilder) {
		Produto produto = new Produto(dadosProduto);
		repository.save(produto);
		URI uri = uriComponentsBuilder.path("/listarproduto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListagemProdutos(produto));
	}
	
	@Transactional
	@PutMapping("/atualizarproduto")
	public ResponseEntity<?> atualizarProduto(@RequestBody  DadosCadastroProduto dadosProduto){
		var produto = repository.getReferenceById(dadosProduto.id());
		produto.atualizarProduto(dadosProduto);
		return ResponseEntity.ok(new DadosListagemProdutos(produto));
	}
	
	@DeleteMapping("/excluirproduto/{id}")
	public ResponseEntity<?> excluirProduto(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
