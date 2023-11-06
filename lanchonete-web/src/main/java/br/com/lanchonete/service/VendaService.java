package br.com.lanchonete.service;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import br.com.lanchonete.dto.VendaDto;

public class VendaService {

	private static final String BASE_URI = "http://gateway:8082";

	public String listaVendas() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/venda/lanchonete");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(response);
		return response;
	}

	public String cadastrarVendaNoSpring(VendaDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/venda/lanchonete");
		String response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(dto, MediaType.APPLICATION_JSON), String.class);
		return response;
	}

	public String excluirVendaNoSpring(Long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI)
				.path("/venda/lanchonete/{id}").resolveTemplate("id", id);
		String response = target.request(MediaType.APPLICATION_JSON).delete(String.class);
		return response;
	}
	
	public String imprimirPedido(Long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI)
				.path("/venda/lanchonete/{id}").resolveTemplate("id", id);
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return response;
	}
}
