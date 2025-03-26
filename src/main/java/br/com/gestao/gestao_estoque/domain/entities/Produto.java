package br.com.gestao.gestao_estoque.domain.entities;

import br.com.gestao.gestao_estoque.domain.event.DomainEventPublisher;
import br.com.gestao.gestao_estoque.domain.event.EstoqueBaixoEvent;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Entity(name = "Produto")
@Table(name = "produtos")
@EqualsAndHashCode
@Getter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidadeEstoque;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    protected Produto() {
    }

    public Produto(String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    public Produto atualizarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalStateException("Nome não pdoe ser nulo ou vazio");
        }
        this.nome = nome;
        return this;
    }

    public Produto atualizarPreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Preço não pode ser nulo ou um valor negativo");
        }
        this.preco = preco;
        return this;
    }

    public Produto atualizarDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalStateException("Descrição não pode ser nula ou vazia");
        }
        this.descricao = descricao;
        return this;
    }

    public Produto atualizarQuantidade(Integer quantidade) {
        if (quantidade == null) {
            throw new IllegalStateException("Quantidade não pode estar nula");
        }
        this.quantidadeEstoque = quantidade;
        return this;
    }

    public Produto atualizarCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalStateException("Categoria não pode estar nula ou vaiza");
        }
        this.categoria = categoria;
        return this;
    }

    public void diminuirEstoque(Integer quantidade, DomainEventPublisher publisher) {
        if (quantidade < 0) {
            throw new IllegalStateException("A quantidade a subtrair deve ser maior que zero");
        }
        this.quantidadeEstoque -= quantidade;

        if (this.quantidadeEstoque < 5) {
            publisher.publish(new EstoqueBaixoEvent(this));
        }
    }

    public void aumentarEstoque(Integer quantidade) {
        if (quantidade < 0) {
            throw new IllegalStateException("A quantidade a somar deve ser maior que zero");
        }
        this.quantidadeEstoque += quantidade;
    }
}
