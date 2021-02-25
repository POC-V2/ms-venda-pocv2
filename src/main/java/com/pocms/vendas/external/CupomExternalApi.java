package com.pocms.vendas.external;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Component
public class CupomExternalApi {

    private final RestTemplate restTemplate;

    public CupomExternalApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BigDecimal getDescontoByCupom(@NotNull String cupom) {
        Objects.requireNonNull(cupom, "Código do cupom não pode ser nulo");
        String url = "http://ms-cupom.production.svc.cluster.local:30337/" + cupom;
        return restTemplate.getForObject(url, BigDecimal.class);
    }

}
