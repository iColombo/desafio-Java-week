package com.linuxtips.challengejavaweek.repository;

import com.linuxtips.challengejavaweek.model.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	boolean existsByNome(String nome);
	
	@Query("SELECT p FROM Produto p ORDER BY p.nome")
	List<Produto> findAll();

}