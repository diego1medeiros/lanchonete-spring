package br.com.lanchonete.bean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;

import com.google.gson.Gson;

import br.com.lanchonete.dto.ItemVendaDto;
import br.com.lanchonete.dto.ProdutoDto;
import br.com.lanchonete.dto.VendaDto;
import br.com.lanchonete.enumeradores.TipoPagamnento;
import br.com.lanchonete.service.VendaService;
import br.com.lanchonete.utils.CarregarImagens;
import br.com.lanchonete.utils.ImprimirPedidos;
import br.com.lanchonete.utils.Message;
import br.com.lanchonete.utils.Relatorio;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;

@Getter
@Setter
@Named(value = "vendaBean")
@ViewScoped
public class VendaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private VendaDto venda;
	private VendaService vendaService = new VendaService();

	private List<ItemVendaDto> listaPedidos;

	private List<VendaDto> listaDaVenda;

	private List<ItemVendaDto> itensVenda;

	private ItemVendaDto itemVenda = new ItemVendaDto();

	// lista imformações dos itens da venda vindo do banco de dados
	@PostConstruct
	public void listaDadosDosItensDoCarrinho() {
		venda = new VendaDto();
		venda.setData(new Date());
		venda.setValorTotal(0);
		venda.setQtdeTotal(0);
		itensVenda = new ArrayList<>();
    	listaDaVenda = listaVenda();

	}

	// adicioanr itens da venda no carrinho
	public void adicionarItens(ActionEvent evento) {
		ProdutoDto produto = (ProdutoDto) evento.getComponent().getAttributes().get("produtoSelecionado");
		int posicaoEncontrada = -1;
		for (int posicao = 0; posicao < itensVenda.size() && posicaoEncontrada < 0; posicao++) {
			ItemVendaDto itemVenda = itensVenda.get(posicao);
			if (itemVenda.getProduto().getId().equals(produto.getId())) {
				posicaoEncontrada = posicao;
			}
		}
		if (posicaoEncontrada < 0) {
			ItemVendaDto itemVenda = new ItemVendaDto();
			itemVenda.setProduto(produto);
			itemVenda.setQtde(1);
			itemVenda.setProdutoId(produto.getId());
			itemVenda.setValorTotal(produto.getPreco());
			Message.info(itemVenda.getProduto().getNome() + " adicionado com sucesso!!!", "");
			itensVenda.add(itemVenda);
		} else {
			ItemVendaDto itemVenda = itensVenda.get(posicaoEncontrada);
			itemVenda.setQtde(itemVenda.getQtde() + 1);
			itemVenda.setProdutoId(produto.getId());

			itemVenda.setValorTotal(produto.getPreco() * itemVenda.getQtde());
			Message.info(itemVenda.getProduto().getNome() + " adicionado com sucesso!!!", "");

		}
		calcularTotalDaVenda();
	}

	// excluir item da Venda
	public void excluirItem(ActionEvent evento) {

		ItemVendaDto itemVenda = (ItemVendaDto) evento.getComponent().getAttributes().get("itemSelecionado");
		int posicaoEncontrada = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {
				posicaoEncontrada = posicao;
			}
		}
		if (posicaoEncontrada > -1) {
			itensVenda.remove(posicaoEncontrada);
		}
		Message.info("Item da venda excluido com sucesso!!!", "");
		calcularTotalDaVenda();
	}

	// calcular o valor da venda
	public void calcularTotalDaVenda() {
		venda.setValorTotal(0);
		venda.setQtdeTotal(0);
		for (ItemVendaDto itemVenda : itensVenda) {
			venda.setValorTotal(venda.getValorTotal() + itemVenda.getValorTotal());
			venda.setQtdeTotal(venda.getQtdeTotal() + itemVenda.getQtde());
		}
	}

	// finalizar o pedido da venda
	public void finalizarVenda() {
		try {
			if (venda.getTipoPagamento().getDescricao() == null) {
				Message.warr("O tipo do pagamento e Obrigatorio!!", "");
				return;
			} else if (venda.getCliente().getId() == null) {
				Message.warr("O Cliente e Obrigatorio!!", "");
				return;
			}
			
			venda.setItensVenda(itensVenda);
			venda.setClienteId(venda.getCliente().getId());
			vendaService.cadastrarVendaNoSpring(venda);
			Message.info("Venda concluida com sucesso!!!", "");
			listaDadosDosItensDoCarrinho();
		} catch (Exception e) {

		}
	}

	// carregar as imagens
	public StreamedContent carregarImagens(ItemVendaDto itemVenda) throws FileNotFoundException {
		return CarregarImagens.carregarImagens(itemVenda);
	}

	// enum das forma do pagamento
	public List<String> getTipoPagamento() {
		return Arrays.asList(TipoPagamnento.getDescricaoComboBox());
	}

	// excluir pedido do cliente
	public void excluirPedidoDoCliente(VendaDto venda) {
		vendaService.excluirVendaNoSpring(venda.getId());
		Message.info("O pedido numero " + venda.getId() + " foi excluido com sucesso!!!", "");
		listaDadosDosItensDoCarrinho();

	}

//	// imrimir os pedidos dos clientes
	public void getImprimirPedidos(VendaDto venda) throws JRException, IOException {
		Relatorio<ItemVendaDto> report = new Relatorio<ItemVendaDto>();
		listaPedidos = listaPedidos(venda.getId());
		if (listaPedidos.size() > 0) {
			report.getRelatorio(listaPedidos);
		} else {
	}
	//	ImprimirPedidos.imprimirPedido(listaPedidos);
	}

	public List<ItemVendaDto> listaPedidos(Long id) {
		String json = vendaService.imprimirPedido(id);
		Gson gson = new Gson();
		ItemVendaDto[] lista = gson.fromJson(json, ItemVendaDto[].class);
		List<ItemVendaDto> listaPedidos = Arrays.asList(lista);
		return listaPedidos;
	}

	public List<VendaDto> listaVenda() {
		Gson gson = new Gson();
		String json = vendaService.listaVendas();
		VendaDto[] listaDaVenda = gson.fromJson(json, VendaDto[].class);
		List<VendaDto> listaVenda = Arrays.asList(listaDaVenda);
		return listaVenda;
	}

}
