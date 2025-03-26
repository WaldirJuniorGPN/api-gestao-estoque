package br.com.gestao.gestao_estoque.domain.event;

import br.com.gestao.gestao_estoque.domain.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EstoqueBaixoListener {

    private final EmailService emailService;

    @Async
    @EventListener
    public void aoDetectarEstoqueBaixo(EstoqueBaixoEvent event) {
        emailService.enviarAlertaEstoqueBaixo(event.getProduto());
    }
}
