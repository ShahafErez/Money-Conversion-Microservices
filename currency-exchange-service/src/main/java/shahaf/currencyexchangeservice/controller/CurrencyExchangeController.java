package shahaf.currencyexchangeservice.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import shahaf.currencyexchangeservice.model.CurrencyExchange;
import shahaf.currencyexchangeservice.service.CurrencyExchangeService;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    CurrencyExchangeService currencyExchangeService;
    @Autowired
    Environment environment;

    @GetMapping("/from/{from}/to/{to}")
    @Retry(name = "default")
    public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyExchangeService.getExchangeValueByFromAndTo(from, to);
        return addEnvironmentInfo(currencyExchange);
    }

    @PostMapping
    public CurrencyExchange saveExchangeValue(@RequestBody CurrencyExchange currencyExchange) {
        CurrencyExchange savedCurrencyExchange = currencyExchangeService.saveExchangeValue(currencyExchange);
        return addEnvironmentInfo(savedCurrencyExchange);
    }

    private CurrencyExchange addEnvironmentInfo(CurrencyExchange currencyExchange){
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        currencyExchange.setHost(environment.getProperty("HOSTNAME"));
        currencyExchange.setVersion("v11");
        return currencyExchange;
    }
}
