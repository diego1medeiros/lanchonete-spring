package br.com.lanchonete.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.lanchonete.dto.vendadto.DadosCadastroVenda;
import br.com.lanchonete.dto.vendadto.DadosListagemVenda;
import br.com.lanchonete.entity.ItemVenda;
import br.com.lanchonete.entity.Venda;
import br.com.lanchonete.repository.ItemVendaRepository;
import br.com.lanchonete.repository.VendaRepository;
import br.com.lanchonete.service.VendaService;

@RestController
@RequestMapping("/venda/lanchonete")
public class VendaController {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ItemVendaRepository itemVendarepository;
	
	@Autowired
	private VendaService vendaService;

	@PostMapping
	public ResponseEntity<?> cadastrarFornecedor(@RequestBody DadosCadastroVenda dadosVenda,
			UriComponentsBuilder uriComponentsBuilder) {
		vendaService.cadastrarVenda(dadosVenda);
		Venda venda = new Venda(dadosVenda);
		URI uri = uriComponentsBuilder.path("/listarvenda/{id}").buildAndExpand(venda.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListagemVenda(venda));
	}

	@GetMapping
	public ResponseEntity<?> listarVendas() {
		var listarVendas = vendaRepository.findAll().stream().map(DadosListagemVenda::new).toList();
		return ResponseEntity.ok(listarVendas);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		vendaService.removerVenda(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> imprimirPedido(@PathVariable Long id) {
		List<ItemVenda> ItensVenda = itemVendarepository.findByVendaId(id);
		return ResponseEntity.ok(ItensVenda);
	}
	
	
	
}
