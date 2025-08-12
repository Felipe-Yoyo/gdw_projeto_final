package com.gdw.projeto_final.gdw_final.repository;


import com.gdw.projeto_final.gdw_final.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
