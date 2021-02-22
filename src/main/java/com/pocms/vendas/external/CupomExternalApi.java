package com.pocms.vendas.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Component
public class CupomExternalApi {

    @Autowired private RestTemplate restTemplate;

    public BigDecimal getDescontoByCupom(@NotNull String cupom) {
        Objects.requireNonNull(cupom, "Código do cupom não pode ser nulo");
        String url = "http://cupons/" + cupom;
        return restTemplate.getForObject(url, BigDecimal.class);
    }

}
