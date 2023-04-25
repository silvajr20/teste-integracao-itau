package com.api.aditamento.dtos.response;

import com.api.aditamento.model.Contrato;
import com.api.aditamento.model.Financeiro;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContratoQuantidadeParcelasResponseDTO {
    private Contrato contrato;
    private Financeiro financeiro;
    private Financeiro financeiroAditado;
}
