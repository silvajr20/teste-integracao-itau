package com.api.aditamento.controller;

import com.api.aditamento.domain.AditamentoDataPagamento;
import com.api.aditamento.domain.AditamentoQuantidadeParcelas;
import com.api.aditamento.domain.Contrato;
import com.api.aditamento.domain.Financeiro;
import com.api.aditamento.dtos.request.ContratoDataPagamentoRequestDTO;
import com.api.aditamento.dtos.request.ContratoQuantidadeParcelasRequestDTO;
import com.api.aditamento.dtos.response.ContratoDataPagamentoResponseDTO;
import com.api.aditamento.dtos.response.ContratoQuantidadeParcelasResponseDTO;
import com.api.aditamento.exception.AditaDataPagamentoException;
import com.api.aditamento.exception.AditaQuantidadeParcelasException;
import com.api.aditamento.service.DataPagamentoService;
import com.api.aditamento.service.QuantidadeParcelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OperacoesController {

    @Autowired
    private DataPagamentoService dataPagamentoService;
    @Autowired
    private QuantidadeParcelaService quantidadeParcelaService;

    @ExceptionHandler(AditaDataPagamentoException.class)
    @RequestMapping(value =  "/adita/pagamento", method = RequestMethod.POST)
    public ResponseEntity<ContratoDataPagamentoResponseDTO> pagamento(@RequestBody ContratoDataPagamentoRequestDTO cttPgto){
        var request = preencheRequestDtPgto(cttPgto);
        return ResponseEntity.ok().body(dataPagamentoService.aditamentoPagamento(request));
    }

    @ExceptionHandler(AditaQuantidadeParcelasException.class)
    @RequestMapping(value =  "/adita/parcela", method = RequestMethod.POST)
    public ResponseEntity<ContratoQuantidadeParcelasResponseDTO> parcela(@RequestBody ContratoQuantidadeParcelasRequestDTO qtdParc){
        var request = preencheRequestQtdParc(qtdParc);
        return ResponseEntity.ok().body(quantidadeParcelaService.aditaQuantidadeParcelas(request));
    }

    //PREENCHIMENTO DOS VALORES MAPEADOS NO REQUEST DO ENDPOINT ADITAMENTO DA DATA DE PAGAMENTO

    public ContratoDataPagamentoRequestDTO preencheRequestDtPgto(ContratoDataPagamentoRequestDTO entrada){
        var retornoRquest = new ContratoDataPagamentoRequestDTO();
        var ctt = new Contrato();
        var fin = new Financeiro();
        var adt = new AditamentoDataPagamento();
        //        contrato
        ctt.setIdContrato(entrada.getContrato().getIdContrato());
        ctt.setUltimoDigitoContrato(entrada.getContrato().getUltimoDigitoContrato());
        ctt.setNumerocpfcnpjCliente(entrada.getContrato().getNumerocpfcnpjCliente());
        ctt.setDataContratacao(entrada.getContrato().getDataContratacao());
        ctt.setAtivo(entrada.getContrato().getAtivo());
        ctt.setParcelasEmAtraso(entrada.getContrato().getParcelasEmAtraso());
//        financeiro
        fin.setDataCalculo(entrada.getFinanceiro().getDataCalculo());
        fin.setTipoCalculo(entrada.getFinanceiro().getTipoCalculo());
        fin.setValorTotal(entrada.getFinanceiro().getValorTotal());
        fin.setQuantidadeParcelas(entrada.getFinanceiro().getQuantidadeParcelas());
        fin.setValorParcelas(entrada.getFinanceiro().getValorParcelas());
        fin.setDiaPagamento(entrada.getFinanceiro().getDiaPagamento());
        fin.setPercentualTaxaJuros(entrada.getFinanceiro().getPercentualTaxaJuros());
//        aditamento da data de pagamento
        adt.setNovaDataPagamento(entrada.getAditamentoDataPagamento().getNovaDataPagamento());
//      atribuicao dos valores de retorno
        retornoRquest.setContrato(ctt);
        retornoRquest.setFinanceiro(fin);
        retornoRquest.setAditamentoDataPagamento(adt);
        return retornoRquest;
    }

    // PREENCHIMENTO DOS VALORES DO REQUEST MAPEADOS NO ENDPOINT ADITAMENTO DA QUANTIDADE DE PARCELAS

    public ContratoQuantidadeParcelasRequestDTO preencheRequestQtdParc(ContratoQuantidadeParcelasRequestDTO entrada){
        var retornoRquest = new ContratoQuantidadeParcelasRequestDTO();
        var ctt = new Contrato();
        var fin = new Financeiro();
        var adt = new AditamentoQuantidadeParcelas();
        //        contrato
        ctt.setIdContrato(entrada.getContrato().getIdContrato());
        ctt.setUltimoDigitoContrato(entrada.getContrato().getUltimoDigitoContrato());
        ctt.setNumerocpfcnpjCliente(entrada.getContrato().getNumerocpfcnpjCliente());
        ctt.setDataContratacao(entrada.getContrato().getDataContratacao());
        ctt.setAtivo(entrada.getContrato().getAtivo());
        ctt.setParcelasEmAtraso(entrada.getContrato().getParcelasEmAtraso());
//        financeiro
        fin.setDataCalculo(entrada.getFinanceiro().getDataCalculo());
        fin.setTipoCalculo(entrada.getFinanceiro().getTipoCalculo());
        fin.setValorTotal(entrada.getFinanceiro().getValorTotal());
        fin.setQuantidadeParcelas(entrada.getFinanceiro().getQuantidadeParcelas());
        fin.setValorParcelas(entrada.getFinanceiro().getValorParcelas());
        fin.setDiaPagamento(entrada.getFinanceiro().getDiaPagamento());
        fin.setPercentualTaxaJuros(entrada.getFinanceiro().getPercentualTaxaJuros());
//        aditamento da quantidade de parcelas
        adt.setNovaQuantidadeParcelas(entrada.getAditamentoQuantidadeParcelas().getNovaQuantidadeParcelas());
//      atribuicao dos valores de retorno
        retornoRquest.setContrato(ctt);
        retornoRquest.setFinanceiro(fin);
        retornoRquest.setAditamentoQuantidadeParcelas(adt);
        return retornoRquest;
    }


}
