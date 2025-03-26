package br.com.gestao.gestao_estoque.domain.service.impl;

import br.com.gestao.gestao_estoque.controller.dto.ProdutoRequest;
import br.com.gestao.gestao_estoque.controller.dto.ProdutoResponse;
import br.com.gestao.gestao_estoque.domain.entities.Produto;
import br.com.gestao.gestao_estoque.domain.event.DomainEventPublisher;
import br.com.gestao.gestao_estoque.domain.service.ProdutoService;
import br.com.gestao.gestao_estoque.infra.adapter.ProdutoAdapter;
import br.com.gestao.gestao_estoque.infra.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoAdapter adapter;
    private final DomainEventPublisher eventPublisher;

    @Override
    public ProdutoResponse criar(ProdutoRequest request) {
        var produto = adapter.produtoRequestToProduto(request);
        repository.save(produto);

        return adapter.produtoToProdutoResponse(produto);
    }

    @Override
    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        var produto = buscarNoBanco(id);
        atualizarProduto(produto, request);

        return adapter.produtoToProdutoResponse(produto);
    }

    @Override
    public List<ProdutoResponse> listar(String categoria, Boolean estoqueBaixo) {
        var responseList = repository.findByFilters(categoria, estoqueBaixo);

        return responseList.stream()
                .map(adapter::produtoToProdutoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void diminuirEstoque(Long id, Integer quantidade) {
        var produto = buscarNoBanco(id);
        produto.diminuirEstoque(quantidade, eventPublisher);
    }

    private Produto buscarNoBanco(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Produto com ID: %d não foi encontrado", id)));
    }

    private void atualizarProduto(Produto produto, ProdutoRequest request) {
        produto.atualizarNome(request.nome())
                .atualizarCategoria(request.categoria())
                .atualizarDescricao(request.descricao())
                .atualizarPreco(request.preco())
                .atualizarQuantidade(request.quantidade());
    }
}
