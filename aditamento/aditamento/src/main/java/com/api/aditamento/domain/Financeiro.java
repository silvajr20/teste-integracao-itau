package com.api.aditamento.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Financeiro {
    private String dataCalculo;
    private String tipoCalculo;
    private Double valorTotal;
    private Integer quantidadeParcelas;
    private Double valorParcelas;
    private Integer diaPagamento;
    private Double percentualTaxaJuros;
}
