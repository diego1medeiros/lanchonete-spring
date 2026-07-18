package br.com.lanchonete.controller;


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

import br.com.lanchonete.dto.Clientedto.DadosAtualizarCliente;
import br.com.lanchonete.dto.Clientedto.DadosCadastroCliente;
import br.com.lanchonete.dto.Clientedto.DadosListagemCliente;
import br.com.lanchonete.service.ClienteService;
//teste

@RestController
@RequestMapping("/cliente/lanchonete")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<?> listarClientes() {
		var listarClientes = clienteService.listarDados();
		return ResponseEntity.ok(listarClientes);
	}

	@PostMapping
	public ResponseEntity<DadosListagemCliente> cadastrarCliente(@RequestBody DadosCadastroCliente dadosCliente,
			UriComponentsBuilder uriComponentsBuilder) {
		DadosListagemCliente clienteCadastrado = clienteService.cadastrarDados(dadosCliente);
		var uri = uriComponentsBuilder.path("/clientes/{id}").buildAndExpand(clienteCadastrado.id()).toUri();
		return ResponseEntity.created(uri).body(clienteCadastrado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		clienteService.excluirDados(id);
		return ResponseEntity.noContent().build();
	}

	@Transactional
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody DadosAtualizarCliente dadosCliente) {
		return ResponseEntity.ok(clienteService.atualizarDados(dadosCliente));
	}
}
