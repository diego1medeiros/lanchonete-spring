package br.com.lanchonete.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

import br.com.lanchonete.dto.movimentacaocaixa.DadosListagemMovimentacao;
import br.com.lanchonete.dto.movimentacaocaixa.DadosMovimentacaoCaixa;
import br.com.lanchonete.entity.Caixa;
import br.com.lanchonete.repository.CaixaRepository;
import br.com.lanchonete.repository.VendaRepository;

@RestController
@RequestMapping("/caixa/lanchonete")
public class CaixaController {

	@Autowired
	private CaixaRepository caixaRepository;

	@Autowired
	private VendaRepository vendaRepository;

	@GetMapping
	public ResponseEntity<?> listarMovimentacaoCaixa() {
		var listarMovimentacaoCaixa = caixaRepository.findAll().stream().map(DadosListagemMovimentacao::new).toList();
		return ResponseEntity.ok(listarMovimentacaoCaixa);
	}

	@PostMapping
	public ResponseEntity<?> cadastrarCliente(@RequestBody DadosMovimentacaoCaixa dadosMovimentacaoCaixa,
			UriComponentsBuilder uriComponentsBuilder) {
		Caixa caixa = new Caixa(dadosMovimentacaoCaixa);
		caixaRepository.save(caixa);
		URI uri = uriComponentsBuilder.path("/listarcaixa/{id}").buildAndExpand(caixa.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListagemMovimentacao(caixa));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		caixaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{localDateTime}")
	public Double valorTotalDoCaixa(@PathVariable LocalDateTime localDateTime) {
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		Double valorTotal = vendaRepository.sumValorTotalByData(date);
		return valorTotal;
	}

}
