package br.com.lanchonete.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import br.com.lanchonete.dto.ProdutoDto;

public class ProdutoService {

	private static final String BASE_URI = "http://gateway:8082";

	public String listarProduto() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/produto/lanchonete");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		//System.out.println(response);

		return response;
	}

	public String cadastrarProdutoNoSpring(ProdutoDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/produto/lanchonete");
		String response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(dto, MediaType.APPLICATION_JSON), String.class);
		return response;
	}

	public String excluirProdutoNoSpring(Long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI)
				.path("/produto/lanchonete/{id}").resolveTemplate("id", id);
		String response = target.request(MediaType.APPLICATION_JSON).delete(String.class);
		return response;
	}

	public String atualizarProdutoNoSpring(ProdutoDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/produto/lanchonete");
		String response = target.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(dto, MediaType.APPLICATION_JSON), String.class);
		return response;
	}
}
