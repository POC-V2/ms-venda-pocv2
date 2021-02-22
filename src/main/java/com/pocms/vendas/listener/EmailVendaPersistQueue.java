package com.pocms.vendas.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pocms.vendas.converter.VendaConverter;
import com.pocms.vendas.event.EmailVendaPersistEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmailVendaPersistQueue implements ApplicationListener<EmailVendaPersistEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailVendaPersistQueue.class);

    private final ObjectMapper objectMapper;

    private final RabbitTemplate rabbitTemplate;

    public EmailVendaPersistQueue(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void onApplicationEvent(EmailVendaPersistEvent event) {
        try {
            var emailVenda = VendaConverter.toEmailVendaDto(event.getVenda());
            var json = objectMapper.writeValueAsString(emailVenda);
            rabbitTemplate.convertAndSend("queue.email.venda.persist", json);
        } catch (JsonProcessingException e) {
            LOGGER.error("Não foi possivel converter o objeto EmailVendaPersistDto para JSON", e);
        }
    }

}
