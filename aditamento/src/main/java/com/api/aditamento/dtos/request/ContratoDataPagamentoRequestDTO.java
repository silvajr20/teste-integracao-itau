package com.api.aditamento.dtos.request;

import com.api.aditamento.model.AditamentoDataPagamento;
import com.api.aditamento.model.Contrato;
import com.api.aditamento.model.Financeiro;
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
