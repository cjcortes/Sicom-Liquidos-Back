package com.sicom.ms.infrastructure.sicomapi.products;

import com.sicom.ms.domain.model.products.ProductOPSimple;
import com.sicom.ms.domain.model.products.ProductsOPSimpleGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class ProductsOPSimpleGatewayAdapter implements ProductsOPSimpleGateway {
    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<ProductOPSimple> getProductsOPSimpleBySicomCodeUseCase(String sicomCode) {

        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.get()
                .uri("WEBSERVICE/liquidos/ops/Productos?codigoSicom="+sicomCode+"")
                .retrieve()
                .bodyToFlux(ProductOPSimpleDTO.class).map(p -> ProductOPSimple.builder()
                        .key(p.key)
                        .productName(p.nombreProducto)
                        .productType(p.tipoProducto)
                        .productCode(p.codigoProducto)
                        .storedCapacity(p.capacidadAlmacenada)
                        .numberDispensers(p.numeroSurtidores)
                        .numberHoses(p.numeroMangueras)
                        .tankNumber(p.numeroTanque)
                        .productQuota(p.cupoProducto)
                        .nominalTotalCapacity(p.capacidadTotalNominal)
                        .totalOperatingCapacity(p.capacidadTotalOperativa)
                        .sicomAgentCode(p.codigoSicomAgente)
                        .build())
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 404 ? Flux.empty() : Mono.error(ex));
    }
}
