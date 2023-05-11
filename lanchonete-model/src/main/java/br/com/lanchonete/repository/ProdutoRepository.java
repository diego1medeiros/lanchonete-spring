package br.com.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lanchonete.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

}
