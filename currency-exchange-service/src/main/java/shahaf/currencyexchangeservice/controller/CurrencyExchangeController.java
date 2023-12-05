package shahaf.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import shahaf.currencyexchangeservice.models.CurrencyExchange;
import shahaf.currencyexchangeservice.service.CurrencyExchangeService;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    CurrencyExchangeService currencyExchangeService;
    @Autowired
    Environment environment;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyExchangeService.getExchangeValueByFromAndTo(from, to);
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }

    @PostMapping
    public CurrencyExchange saveExchangeValue(@RequestBody CurrencyExchange currencyExchange) {
        CurrencyExchange savedCurrencyExchange = currencyExchangeService.saveExchangeValue(currencyExchange);
        savedCurrencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return savedCurrencyExchange;
    }
}
