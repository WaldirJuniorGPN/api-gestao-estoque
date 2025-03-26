package br.com.gestao.gestao_estoque.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequest(

        @NotBlank(message = "Nome não pode estar vazio ou nulo")
        String nome,

        @NotBlank(message = "Descrição não pode estar vazia ou nula")
        String descricao,

        @NotNull(message = "Preço não pode ser nulo")
        @DecimalMin(value = "0", message = "Preço não pode ser um valor negativo")
        BigDecimal preco,

        @NotNull(message = "Quantidade não pode ser nula")
        Integer quantidade,

        @NotBlank(message = "Categoria não pode estar vazia ou nula")
        String categoria
) {
}
