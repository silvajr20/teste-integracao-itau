package com.api.aditamento.service;

import com.api.aditamento.model.AditamentoDataPagamento;
import com.api.aditamento.model.Contrato;
import com.api.aditamento.model.Financeiro;
import com.api.aditamento.dtos.request.ContratoDataPagamentoRequestDTO;
import com.api.aditamento.dtos.response.ContratoDataPagamentoResponseDTO;
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
public class DataPagamentoServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DataPagamentoService dataPgtoService;

    @Test
    void testAditamentoDataPagamento() throws Exception{
        var request = new ContratoDataPagamentoRequestDTO();
        var ctt = new Contrato();
        var fin = new Financeiro();
        var adtFin = new AditamentoDataPagamento();
        adtFin.setNovaDataPagamento(4);
        ctt.setAtivo(true);
        ctt.setParcelasEmAtraso(false);
        request.setAditamentoDataPagamento(adtFin);
        ContratoDataPagamentoRequestDTO dtPgtoAdit = ContratoDataPagamentoRequestDTO.builder().contrato(ctt).financeiro(fin).aditamentoDataPagamento(adtFin).build();
        dtPgtoAdit.setAditamentoDataPagamento(adtFin);
        mockMvc.perform(post("/adita/pagamento")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dtPgtoAdit)))
                .andExpect(status().isOk());

        ContratoDataPagamentoResponseDTO adtPgto = dataPgtoService.aditamentoPagamento(dtPgtoAdit);
        Assertions.assertEquals(adtPgto.getFinanceiroAditado().getDiaPagamento(),  dtPgtoAdit.getAditamentoDataPagamento().getNovaDataPagamento());
    }

}
