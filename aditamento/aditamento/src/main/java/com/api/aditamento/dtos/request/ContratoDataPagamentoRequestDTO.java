package com.api.aditamento.dtos.request;

import com.api.aditamento.domain.AditamentoDataPagamento;
import com.api.aditamento.domain.Contrato;
import com.api.aditamento.domain.Financeiro;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratoDataPagamentoRequestDTO {
    private Contrato contrato;
    private Financeiro financeiro;
    private AditamentoDataPagamento aditamentoDataPagamento;
}
