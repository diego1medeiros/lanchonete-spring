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

import br.com.lanchonete.dto.DadosAtualizarCliente;
import br.com.lanchonete.dto.DadosCadastroCliente;
import br.com.lanchonete.dto.DadosListagemCliente;
import br.com.lanchonete.entity.Cliente;
import br.com.lanchonete.repository.ClienteRepository;

@RestController
@RequestMapping("/lanchonete")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@GetMapping("/listarcliente")
	public ResponseEntity<?> listarClientes() {
		var listarClientes = repository.findAll().stream().map(DadosListagemCliente::new).toList();
		return ResponseEntity.ok(listarClientes);
	}

	@PostMapping("/cadastrarcliente")
	public ResponseEntity<DadosListagemCliente> cadastrarCliente(@RequestBody DadosCadastroCliente dadosCliente,
			UriComponentsBuilder uriComponentsBuilder) {
		Cliente cliente = new Cliente(dadosCliente);
		repository.save(cliente);
		URI uri = uriComponentsBuilder.path("/listarcliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListagemCliente(cliente));
	}

	@DeleteMapping("/excluircliente/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Transactional
	@PutMapping("/atualizarcliente")
	public ResponseEntity<?> atualizar(@RequestBody DadosAtualizarCliente dadosCliente) {
		var cliente = repository.getReferenceById(dadosCliente.id());
		cliente.atualizarCliente(dadosCliente);
		return ResponseEntity.ok(new DadosListagemCliente(cliente));
	}
}
