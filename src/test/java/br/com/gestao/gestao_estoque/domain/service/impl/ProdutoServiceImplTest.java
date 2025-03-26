package br.com.gestao.gestao_estoque.domain.service.impl;

import br.com.gestao.gestao_estoque.controller.dto.ProdutoRequest;
import br.com.gestao.gestao_estoque.controller.dto.ProdutoResponse;
import br.com.gestao.gestao_estoque.domain.entities.Produto;
import br.com.gestao.gestao_estoque.infra.adapter.ProdutoAdapter;
import br.com.gestao.gestao_estoque.infra.repository.ProdutoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoServiceImplTest {

    private AutoCloseable closeable;
    private Produto produto;
    private ProdutoRequest request;
    private ProdutoResponse response;

    @Mock
    private ProdutoRepository repository;

    @Mock
    private ProdutoAdapter adapter;

    @InjectMocks
    private ProdutoServiceImpl service;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.request = new ProdutoRequest("Nome", "Descrição", new BigDecimal("10.99"), 10, "Eletrônico");
        this.produto = new Produto(request.nome(), request.descricao(), request.preco(), request.quantidade(), request.categoria());
        this.response = new ProdutoResponse(null, request.nome(), request.descricao(), request.preco(), request.quantidade(), request.categoria());
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    void deveCriarProdutoComSucesso() {

        var expectativa = this.response;

        when(adapter.produtoRequestToProduto(any(ProdutoRequest.class))).thenReturn(produto);
        when(repository.save(any(Produto.class))).thenReturn(produto);
        when(adapter.produtoToProdutoResponse(any(Produto.class))).thenReturn(expectativa);

        var resultado = service.criar(request);

        assertEquals(expectativa, resultado);
        verify(adapter, times(1)).produtoRequestToProduto(any(ProdutoRequest.class));
        verify(repository, times(1)).save(any(Produto.class));
        verify(adapter, times(1)).produtoToProdutoResponse(any(Produto.class));
    }

    @Test
    void deveAtualizarComSucesso() {

        when(repository.findById(1L)).thenReturn(Optional.ofNullable(produto));
        when(adapter.produtoToProdutoResponse(any(Produto.class))).thenReturn(response);

        var expectativa = this.response;
        var resultado = service.atualizar(1L, request);

        assertEquals(expectativa, resultado);
        verify(repository, times(1)).findById(1L);
        verify(adapter, times(1)).produtoToProdutoResponse(any(Produto.class));
    }

    @Test
    void deveListarProdutosSemFiltroComSucesso() {

        var produtoList = List.of(produto);

        when(repository.findByFilters(null, null)).thenReturn(produtoList);
        when(adapter.produtoToProdutoResponse(any(Produto.class))).thenReturn(response);

        var expectativa = List.of(response);
        var resultado = service.listar(null, null);

        assertEquals(expectativa, resultado);
        verify(repository, times(1)).findByFilters(null, null);
        verify(adapter, times(1)).produtoToProdutoResponse(any(Produto.class));
    }

}