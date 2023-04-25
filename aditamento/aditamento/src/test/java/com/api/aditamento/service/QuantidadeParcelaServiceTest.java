package com.api.aditamento.service;

import com.api.aditamento.domain.AditamentoQuantidadeParcelas;
import com.api.aditamento.domain.Contrato;
import com.api.aditamento.domain.Financeiro;
import com.api.aditamento.dtos.request.ContratoQuantidadeParcelasRequestDTO;
import com.api.aditamento.dtos.response.ContratoQuantidadeParcelasResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuantidadeParcelaServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private QuantidadeParcelaService qtdParcService;

    @Test
    void testAditamentoQtdParcelas() throws Exception{
        var request = new ContratoQuantidadeParcelasRequestDTO();
        var ctt = new Contrato();
        var fin = new Financeiro();
        var adtFin = new AditamentoQuantidadeParcelas();
        adtFin.setNovaQuantidadeParcelas("54");
        fin.setQuantidadeParcelas(54);
        ctt.setAtivo(true);
        ctt.setParcelasEmAtraso(false);
        request.setAditamentoQuantidadeParcelas(adtFin);
        ContratoQuantidadeParcelasRequestDTO dtPgtoAdit = ContratoQuantidadeParcelasRequestDTO.builder().contrato(ctt).financeiro(fin).aditamentoQuantidadeParcelas(adtFin).build();
        dtPgtoAdit.setAditamentoQuantidadeParcelas(adtFin);
        var qtd = Integer.valueOf(adtFin.getNovaQuantidadeParcelas());
        mockMvc.perform(post("/adita/parcela")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dtPgtoAdit)))
                .andExpect(status().isOk());
        ContratoQuantidadeParcelasResponseDTO adtPgto = qtdParcService.aditaQuantidadeParcelas(dtPgtoAdit);
        Assertions.assertEquals(adtPgto.getFinanceiroAditado().getQuantidadeParcelas(),  qtd);
    }

}
