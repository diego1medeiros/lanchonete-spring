package br.com.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lanchonete.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
