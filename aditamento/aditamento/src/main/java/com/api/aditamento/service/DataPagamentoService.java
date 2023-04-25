package com.api.aditamento.service;


import com.api.aditamento.domain.Contrato;
import com.api.aditamento.domain.Financeiro;
import com.api.aditamento.dtos.request.ContratoDataPagamentoRequestDTO;
import com.api.aditamento.dtos.response.ContratoDataPagamentoResponseDTO;
import com.api.aditamento.exception.AditaDataPagamentoException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
public class DataPagamentoService {

    LocalDate dataDoDia = LocalDate.now();
    private static String MOCK_ADITAMENTO = "ADITAMENTO";
    private static Double MOCK_NOVO_VALOR_PARCELA = 52000.00;
    private static Integer MOCK_NOVA_QUANTIDADE_PARCELAS = 54;
    private static Integer MOCK_NOVO_DIA_PAGAMENTO = 4;

    public ContratoDataPagamentoResponseDTO aditamentoPagamento(ContratoDataPagamentoRequestDTO entrada) throws AditaDataPagamentoException {
        var saida = new ContratoDataPagamentoResponseDTO();
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

    public ContratoDataPagamentoResponseDTO preencheResponse(ContratoDataPagamentoRequestDTO entrada){
        ContratoDataPagamentoResponseDTO saida = new ContratoDataPagamentoResponseDTO();
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
        finAdt.setQuantidadeParcelas(MOCK_NOVA_QUANTIDADE_PARCELAS);
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
