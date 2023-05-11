package br.com.lanchonete.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import br.com.lanchonete.dto.FornecedorDto;

public class FornecedorService {

	private static final String BASE_URI = "http://localhost:8082";

	public String listaFornecedor() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/lanchonete/listarfornecedor");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return response;
	}

	public String cadastrarFornecedorNoSpring(FornecedorDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/lanchonete/cadastrarfornecedor");
		String response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(dto, MediaType.APPLICATION_JSON), String.class);
		return response;
	}

	public String excluirFornecedorNoSpring(Long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI)
				.path("/lanchonete/excluirfornecedor/{id}").resolveTemplate("id", id);
		String response = target.request(MediaType.APPLICATION_JSON).delete(String.class);
		return response;
	}

	public String atualizarrFornecedorNoSpring(FornecedorDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/lanchonete/atualizarfornecedor");
		String response = target.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(dto, MediaType.APPLICATION_JSON), String.class);
		return response;
	}
}
