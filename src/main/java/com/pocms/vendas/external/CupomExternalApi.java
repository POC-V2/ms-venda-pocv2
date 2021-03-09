package com.pocms.vendas.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Component
public class CupomExternalApi {

    @Value("${uri_svc_cupom}")
    public String uri_svc_cupom;

    private final RestTemplate restTemplate;

    public CupomExternalApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BigDecimal getDescontoByCupom(@NotNull String cupom) {
        Objects.requireNonNull(cupom, "Código do cupom não pode ser nulo");
        String url = this.uri_svc_cupom + cupom;
        return restTemplate.getForObject(url, BigDecimal.class);
    }

}
