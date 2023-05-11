package br.com.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lanchonete.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

}
