package br.com.lanchonete.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lanchonete.dto.vendadto.DadosCadastroVenda;
import br.com.lanchonete.entity.ItemVenda;
import br.com.lanchonete.entity.Produto;
import br.com.lanchonete.entity.Venda;
import br.com.lanchonete.repository.ItemVendaRepository;
import br.com.lanchonete.repository.ProdutoRepository;
import br.com.lanchonete.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ItemVendaRepository itemVendarepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public void cadastrarVenda(DadosCadastroVenda dadosVenda) {
		Venda venda = new Venda(dadosVenda);
		vendaRepository.save(venda);
		List<ItemVenda> itensVenda = dadosVenda.itensVenda();
		for (ItemVenda itemVenda : itensVenda) {
			itemVenda.setVenda(venda);
			itemVendarepository.save(itemVenda);
			Produto produto = itemVenda.getProduto();
			int quantidade = produto.getEstoque() - itemVenda.getQtde();
			if (quantidade >= 0) {
				produto.setEstoque(quantidade);
				produtoRepository.save(produto);
			}
		}
	}

	public void removerVenda(Long id) {
		vendaRepository.deleteById(id);
		Venda venda = vendaRepository.getReferenceById(id);
		List<ItemVenda> ItensVenda = itemVendarepository.findByVendaId(id);
		for (ItemVenda itemVenda : ItensVenda) {
			itemVenda.setVenda(venda);
			itemVendarepository.delete(itemVenda);
		}
	}
}
