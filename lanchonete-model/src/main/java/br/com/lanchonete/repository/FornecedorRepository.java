package br.com.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lanchonete.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {

}
