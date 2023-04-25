package com.api.aditamento.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {
    private String idContrato;
    private String ultimoDigitoContrato;
    private String numerocpfcnpjCliente;
    private String dataContratacao;
    private String ativo;
    private String parcelasEmAtraso;
}
