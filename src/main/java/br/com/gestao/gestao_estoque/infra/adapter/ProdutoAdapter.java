package br.com.gestao.gestao_estoque.infra.adapter;

import br.com.gestao.gestao_estoque.controller.dto.ProdutoRequest;
import br.com.gestao.gestao_estoque.controller.dto.ProdutoResponse;
import br.com.gestao.gestao_estoque.domain.entities.Produto;

public interface ProdutoAdapter {

    Produto produtoRequestToProduto(ProdutoRequest request);

    ProdutoResponse produtoToProdutoResponse(Produto produto);
}
