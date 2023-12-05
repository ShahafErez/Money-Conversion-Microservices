package shahaf.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shahaf.currencyexchangeservice.dto.CurrencyExchange;
import shahaf.currencyexchangeservice.dto.EnvironmentInfo;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    Environment environment;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to) {
        return new CurrencyExchange(
                1L, from, to, BigDecimal.valueOf(50),
                new EnvironmentInfo(environment.getProperty("local.server.port"))
        );
    }

}
