package br.com.gestao.gestao_estoque.infra.adapter.impl;

import br.com.gestao.gestao_estoque.controller.dto.ProdutoRequest;
import br.com.gestao.gestao_estoque.controller.dto.ProdutoResponse;
import br.com.gestao.gestao_estoque.domain.entities.Produto;
import br.com.gestao.gestao_estoque.infra.adapter.ProdutoAdapter;
import org.springframework.stereotype.Component;

@Component
public class ProdutoAdapterImpl implements ProdutoAdapter {

    @Override
    public Produto produtoRequestToProduto(ProdutoRequest request) {
        return new Produto(
                request.nome(),
                request.descricao(),
                request.preco(),
                request.quantidade(),
                request.categoria()
        );
    }

    @Override
    public ProdutoResponse produtoToProdutoResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidadeEstoque(),
                produto.getCategoria()
        );
    }
}
