package br.com.gestao.gestao_estoque.infra.repository;

import br.com.gestao.gestao_estoque.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE (:categoria IS NULL OR p.categoria = :categoria) AND (:estoqueBaixo IS NULL OR :estoqueBaixo = false OR p.quantidade < 10)")
    List<Produto> findByFilters(@Param("categoria") String categoria, @Param("estoqueBaixo") Boolean estoqueBaixo);

    Optional<Produto> findById(Long id);
}
