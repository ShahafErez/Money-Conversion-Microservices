package shahaf.currencyconversionservice.controller;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shahaf.currencyconversionservice.dto.CurrencyConversion;
import shahaf.currencyconversionservice.exception.NotFoundException;
import shahaf.currencyconversionservice.proxy.CurrencyExchangeProxy;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    @Autowired
    CurrencyExchangeProxy currencyExchangeProxy;

    private final Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        logger.info("Fetching currency exchange information. from {}, to {}, quantity {}.", from, to, quantity);
        try {
            CurrencyConversion currencyConversion = currencyExchangeProxy.getExchangeValue(from, to);
            currencyConversion.setQuantity(quantity);
            currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
            return currencyConversion;
        } catch (FeignException.NotFound e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
