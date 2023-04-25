package com.api.aditamento.dtos.request;

import com.api.aditamento.model.AditamentoQuantidadeParcelas;
import com.api.aditamento.model.Contrato;
import com.api.aditamento.model.Financeiro;
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
