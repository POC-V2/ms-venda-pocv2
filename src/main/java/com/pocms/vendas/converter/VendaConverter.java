package com.pocms.vendas.converter;

import com.pocms.vendas.dto.EmailVendaDto;
import com.pocms.vendas.dto.EmailVendaItemDto;
import com.pocms.vendas.model.Venda;

import java.util.stream.Collectors;

public class VendaConverter {

    public static EmailVendaDto toEmailVendaDto(Venda venda) {
        var itens = venda.getItens().stream()
                .map(it -> new EmailVendaItemDto(
                        it.getProduto().getDescricao(),
                        it.getQuantidade(),
                        it.getValor(),
                        it.getValorTotal()
                ))
                .collect(Collectors.toSet());

        return new EmailVendaDto(
                venda.getId(),
                venda.getEmail(),
                venda.getValor(),
                venda.getDesconto(),
                itens
        );
    }

}
