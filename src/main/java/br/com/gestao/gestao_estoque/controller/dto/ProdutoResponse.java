package br.com.gestao.gestao_estoque.controller.dto;

import java.math.BigDecimal;

public record ProdutoResponse(Long id,
                              String nome,
                              String descricao,
                              BigDecimal preco,
                              Integer quantidade,
                              String categoria) {

}
