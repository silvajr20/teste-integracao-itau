package com.api.aditamento.dtos.response;

import com.api.aditamento.domain.Contrato;
import com.api.aditamento.domain.Financeiro;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContratoDataPagamentoResponseDTO {
    private Contrato contrato;
    private Financeiro financeiro;
    private Financeiro financeiroAditado;
}
