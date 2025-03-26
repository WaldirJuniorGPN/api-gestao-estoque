package br.com.gestao.gestao_estoque.domain.event;

public interface DomainEventPublisher {

    void publish(Object event);
}
