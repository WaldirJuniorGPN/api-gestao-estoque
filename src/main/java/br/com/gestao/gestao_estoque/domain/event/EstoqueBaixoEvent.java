package br.com.gestao.gestao_estoque.domain.event;

import br.com.gestao.gestao_estoque.domain.entities.Produto;
import lombok.Getter;

public class EstoqueBaixoEvent {

    @Getter
    private final Produto produto;

    public EstoqueBaixoEvent(Produto produto) {
        this.produto = produto;
    }
}
