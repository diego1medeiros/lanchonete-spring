package br.com.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lanchonete.entity.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Long>{

}
