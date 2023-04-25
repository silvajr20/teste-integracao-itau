package com.api.aditamento.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Setter
@Getter
@Builder
public class ApiJuros {
    private BigDecimal taxaDeJuros;
}
