package com.api.aditamento.service;

import com.api.aditamento.domain.Contrato;
import com.api.aditamento.domain.Financeiro;
import com.api.aditamento.dtos.request.ContratoQuantidadeParcelasRequestDTO;
import com.api.aditamento.dtos.response.ContratoQuantidadeParcelasResponseDTO;
import com.api.aditamento.exception.AditaDataPagamentoException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class QuantidadeParcelaService {

    LocalDate dataDoDia = LocalDate.now();
    private static String MOCK_ADITAMENTO = "ADITAMENTO";
    private static Double MOCK_NOVO_VALOR_PARCELA = 52000.00;
    private static Integer MOCK_NOVA_QUANTIDADE_PARCELAS = 54;
    private static Integer MOCK_NOVO_DIA_PAGAMENTO = 4;

    public ContratoQuantidadeParcelasResponseDTO aditaQuantidadeParcelas(ContratoQuantidadeParcelasRequestDTO entrada){

        var saida = new ContratoQuantidadeParcelasResponseDTO();
        try {
            saida = preencheResponse(entrada);
        } catch (AditaDataPagamentoException e){
            throw new AditaDataPagamentoException(e.getMessage());
        }
        if(saida.getContrato().getAtivo().equals(true)){
            return saida;
        }else{
            return null;
        }
    }

    public ContratoQuantidadeParcelasResponseDTO preencheResponse(ContratoQuantidadeParcelasRequestDTO entrada){
        ContratoQuantidadeParcelasResponseDTO saida = new ContratoQuantidadeParcelasResponseDTO();
        var ctt = new Contrato();
        var fin = new Financeiro();
        var finAdt = new Financeiro();
        var strcttquatrodigitos = StringUtils.left(entrada.getContrato().getIdContrato(), 4);
        var strcttultimodigito = StringUtils.right(entrada.getContrato().getIdContrato(), 1);

        ctt.setIdContrato(strcttquatrodigitos);
        ctt.setUltimoDigitoContrato(strcttultimodigito);
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
        //        financeiro aditamento
        finAdt.setDataCalculo(dataDoDia.toString());
        finAdt.setTipoCalculo(MOCK_ADITAMENTO);
        finAdt.setValorTotal(MOCK_NOVO_VALOR_PARCELA);
//        valida se a quantidade de parcelas é inferior a quantidade já existente
        if(fin.getQuantidadeParcelas() < MOCK_NOVA_QUANTIDADE_PARCELAS){
            Integer qtd_mock = Integer.valueOf(MOCK_NOVA_QUANTIDADE_PARCELAS);
            finAdt.setQuantidadeParcelas(qtd_mock);
        }else{
            Integer qtd = Integer.valueOf(entrada.getAditamentoQuantidadeParcelas().getNovaQuantidadeParcelas());
            finAdt.setQuantidadeParcelas(Integer.valueOf(qtd));
        }
//        recalcula o valor das parcelas
        finAdt.setValorParcelas(recalculaValor(MOCK_NOVO_VALOR_PARCELA, MOCK_NOVA_QUANTIDADE_PARCELAS));
        finAdt.setDiaPagamento(MOCK_NOVO_DIA_PAGAMENTO);
        finAdt.setPercentualTaxaJuros(entrada.getFinanceiro().getPercentualTaxaJuros());

//      atribuicao dos valores de retorno
        saida.setContrato(ctt);
        saida.setFinanceiro(fin);
        saida.setFinanceiroAditado(finAdt);
        return saida;
    }

    public Double recalculaValor(Double valorTotal, Integer qtdParcelas){
        return valorTotal / qtdParcelas;
    }

}
