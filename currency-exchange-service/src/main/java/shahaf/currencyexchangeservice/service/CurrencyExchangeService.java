package shahaf.currencyexchangeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shahaf.currencyexchangeservice.models.CurrencyExchange;
import shahaf.currencyexchangeservice.exception.NotFoundException;
import shahaf.currencyexchangeservice.reposetory.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchange saveExchangeValue(CurrencyExchange currencyExchange) {
        return currencyExchangeRepository.save(currencyExchange);
    }

    public CurrencyExchange getExchangeValueByFromAndTo(String from, String to) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new NotFoundException(String.format("Unable to find data from %s to %s", from, to));
        }
        return currencyExchange;
    }

}
