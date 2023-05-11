package br.com.lanchonete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lanchonete.entity.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long>{

    List<ItemVenda> findByVendaId(Long id);

}
