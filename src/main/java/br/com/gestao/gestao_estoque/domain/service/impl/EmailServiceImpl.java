package br.com.gestao.gestao_estoque.domain.service.impl;

import br.com.gestao.gestao_estoque.domain.entities.Produto;
import br.com.gestao.gestao_estoque.domain.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void enviarAlertaEstoqueBaixo(Produto produto) {
        var message = new SimpleMailMessage();
        message.setTo("postechchallenge@gmail.com");
        message.setSubject("Alerta de Estoque Baixo");
        message.setText(String.format("O produto '%s' está com estoque baixo (%d unidades).",
                produto.getNome(), produto.getQuantidadeEstoque()));

        mailSender.send(message);
    }
}
