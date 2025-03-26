package br.com.gestao.gestao_estoque.domain.service;

import br.com.gestao.gestao_estoque.domain.entities.Produto;

public interface EmailService {

    void enviarAlertaEstoqueBaixo(Produto produto);
}
