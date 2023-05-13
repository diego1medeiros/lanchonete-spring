package br.com.lanchonete.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.google.gson.Gson;

import br.com.lanchonete.dto.FuncionarioDto;
import br.com.lanchonete.dto.MovimentacaoCaixaDto;
import br.com.lanchonete.service.CaixaService;
import br.com.lanchonete.service.FuncionarioService;
import br.com.lanchonete.service.VendaService;
import br.com.lanchonete.utils.FormatarData;
import br.com.lanchonete.utils.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "caixaBean")
@ViewScoped
public class CaixaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ScheduleModel caixas;

	private MovimentacaoCaixaDto movimentacaoCaixa = new MovimentacaoCaixaDto();

	private List<MovimentacaoCaixaDto> movimentacaoCaixas;

	private FuncionarioService funcionarioService = new FuncionarioService();

	private CaixaService caixaService = new CaixaService();

	private VendaService vendaService = new VendaService();

	@PostConstruct
	public void listar() {
		caixas = new DefaultScheduleModel();
		movimentacaoCaixa = new MovimentacaoCaixaDto();
		movimentacaoCaixas = listarMovimentacaoCaixa();
		caixas = listarDadosDoCaixaFechedo();
	}

	public List<MovimentacaoCaixaDto> listarMovimentacaoCaixa() {
		String json = caixaService.listarMovimentacaoDoCaixa();
		Gson gson = new Gson();
		MovimentacaoCaixaDto[] lista = gson.fromJson(json, MovimentacaoCaixaDto[].class);
		List<MovimentacaoCaixaDto> movimentacao = Arrays.asList(lista);
		return movimentacao;
	}

	public void criarNovoCaixa(SelectEvent<LocalDateTime> selectEvent) {
		LocalDateTime localDateTime = selectEvent.getObject();
		Double valorTotal = caixaService.valorTotalDoCaixa(localDateTime);
		if (valorTotal == null) {
			movimentacaoCaixa = new MovimentacaoCaixaDto();
			movimentacaoCaixa.setData(FormatarData.converterLocalDateParaDate(localDateTime));
			Message.warr("Não foi aberto nenhum caixa nesse dia!!!", "");
		} else {
			movimentacaoCaixa = new MovimentacaoCaixaDto();
			movimentacaoCaixa.setData(FormatarData.converterLocalDateParaDate(localDateTime));
			movimentacaoCaixa.setValorTotal(valorTotal);
			Message.warr("Caixa está aberto!!!", "");
		}
	}

	public void selecionadoCaixa(SelectEvent<?> selectEvent) {
		ScheduleEvent<?> evento = (ScheduleEvent<?>) selectEvent.getObject();
		for (MovimentacaoCaixaDto caixa : movimentacaoCaixas) {
			Long id = Long.valueOf(evento.getId());
			if (caixa.getId().equals(id) || caixa.getValorCaixa() != 0) {
				movimentacaoCaixa = caixa;
				Message.warr("Caixa está Fechado!!!", "");
				break;
			}
		}
	}

	public void salvarMovimentacaoDoCaixa() {
		if (movimentacaoCaixa.getId() == null) {
			if (movimentacaoCaixa.getFuncionario().getId() == null) {
				Message.warr("O funcionario e obrigatorio!!", "");
				return;
			}
			if (movimentacaoCaixa.getValorCaixa() == 0) {
				Message.warr("O Valor do caixa e obrigatorio!!", "");
				return;
			}
			caixaService.salvarMovimentacaoDoCaixa(movimentacaoCaixa);
			Message.info("O registro do caixa salvo com sucesso!!!", "");
			listar();

		} else {
			Message.warr("Caixa estar Fechado!!!", "");
		}
	}

	public List<SelectItem> getListaNomeFuncioanrio() {
		List<SelectItem> lista = new ArrayList<>();
		String json = funcionarioService.listarFuncionario();
		Gson gson = new Gson();
		FuncionarioDto[] listaFuncionarios = gson.fromJson(json, FuncionarioDto[].class);
		for (FuncionarioDto funcionario : listaFuncionarios) {
			lista.add(new SelectItem(funcionario.getId(), funcionario.getNome()));
		}
		return lista;
	}

	public MovimentacaoCaixaDto removerRegistroDaMOvimentacaoDoCaixa(MovimentacaoCaixaDto movimentacaoCaixa) {
		caixaService.removerRegistroDaMovimentacaoDoCaixa(movimentacaoCaixa.getId());
		listar();
		Message.info("O registro do Caixa foi excluido com sucesso!!!!", "");
		return movimentacaoCaixa;
	}

	public String getformatarData(Date data) {
		return FormatarData.formatarData(data);
	}
	public ScheduleModel listarDadosDoCaixaFechedo() {
		ScheduleModel caixas = new DefaultScheduleModel();
		List<MovimentacaoCaixaDto> movimentacaoCaixas = listarMovimentacaoCaixa();
		for (MovimentacaoCaixaDto movimentacaoCaixa : movimentacaoCaixas) {
			/// DefaultScheduleEvent<?> evento = new DefaultScheduleEvent<>();
			LocalDateTime localDateTime = FormatarData.converterDateParaLocalDate(movimentacaoCaixa.getData().toInstant());
			// Instant instant = movimentacaoCaixa.getData().toInstant();
			// LocalDateTime localDateTime = LocalDateTime.ofInstant(instant,
			// ZoneId.systemDefault());
			// evento.setStartDate(localDateTime);
			// evento.setId(movimentacaoCaixa.getId().toString());
			// evento.setDescription(movimentacaoCaixa.getObservacao());
			// evento.setTitle("Caixa está Fechado");
			// evento.setAllDay(true);
			// evento.setEditable(true);
			caixas.addEvent(
					evento(localDateTime, movimentacaoCaixa.getId().toString(), movimentacaoCaixa.getObservacao()));
		}
		return caixas;

	}

	public DefaultScheduleEvent<?> evento(LocalDateTime localDateTime, String id, String observacao) {
		DefaultScheduleEvent<?> evento = new DefaultScheduleEvent<>();
		evento.setStartDate(localDateTime);
		evento.setId(id);
		evento.setDescription(observacao);
		evento.setTitle("Caixa está Fechado");
		evento.setAllDay(true);
		evento.setEditable(true);
		return evento;
	}
}
