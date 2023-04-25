package com.api.aditamento.dtos.request;

import com.api.aditamento.domain.AditamentoQuantidadeParcelas;
import com.api.aditamento.domain.Contrato;
import com.api.aditamento.domain.Financeiro;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContratoQuantidadeParcelasRequestDTO {
    private Contrato contrato;
    private Financeiro financeiro;
    private AditamentoQuantidadeParcelas aditamentoQuantidadeParcelas;
}
