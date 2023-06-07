package br.com.lanchonete.service;

import java.time.LocalDateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import br.com.lanchonete.dto.MovimentacaoCaixaDto;

public class CaixaService {
	
	private static final String BASE_URI = "http://localhost:8082";

	public String listarMovimentacaoDoCaixa() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("caixa/lanchonete");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return response;
	}

	public String salvarMovimentacaoDoCaixa(MovimentacaoCaixaDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/caixa/lanchonete");
		String response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(dto, MediaType.APPLICATION_JSON), String.class);
		return response;
	}

	public String removerRegistroDaMovimentacaoDoCaixa(Long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI)
				.path("/caixa/lanchonete/{id}").resolveTemplate("id", id);
		String response = target.request(MediaType.APPLICATION_JSON).delete(String.class);
		System.out.println(response);
		return response;
	}

	public Double valorTotalDoCaixa(LocalDateTime localDateTime) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/caixa/lanchonete/{localDateTime}").resolveTemplate("localDateTime", localDateTime);
		Double response = target.request(MediaType.APPLICATION_JSON).get(Double.class);
		System.out.println(response);
		return response;
}
	
}
