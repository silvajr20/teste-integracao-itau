package com.api.aditamento.model;

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
    private Boolean ativo;
    private Boolean parcelasEmAtraso;
}
