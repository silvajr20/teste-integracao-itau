package com.api.aditamento.feign;

import com.api.aditamento.domain.ApiJuros;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "TaxaDeJurosApi", url = "https://itautestes.juros.com/")
public interface FeignChamadaJuros {
    @RequestMapping(method = RequestMethod.GET, value = "/juros")
    ApiJuros getPosts(@RequestParam int dias);
}
