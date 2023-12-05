package shahaf.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shahaf.currencyexchangeservice.models.CurrencyExchange;
import shahaf.currencyexchangeservice.service.CurrencyExchangeService;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    CurrencyExchangeService currencyExchangeService;

    @PostMapping
    public CurrencyExchange saveExchangeValue(@RequestBody CurrencyExchange currencyExchange) {
        return currencyExchangeService.saveExchangeValue(currencyExchange);
    }

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {
        return currencyExchangeService.getExchangeValueByFromAndTo(from, to);
    }
}
