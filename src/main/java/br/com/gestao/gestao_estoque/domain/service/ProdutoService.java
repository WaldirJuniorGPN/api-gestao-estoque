package br.com.gestao.gestao_estoque.domain.service;

import br.com.gestao.gestao_estoque.controller.dto.ProdutoRequest;
import br.com.gestao.gestao_estoque.controller.dto.ProdutoResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ProdutoService {

    ProdutoResponse criar(ProdutoRequest request);

    ProdutoResponse atualizar(Long id, @Valid ProdutoRequest request);

    List<ProdutoResponse> listar(String categoria, Boolean estoqueBaixo);

    void deletar(Long id);

    void diminuirEstoque(Long id, Integer quantidade);
}
