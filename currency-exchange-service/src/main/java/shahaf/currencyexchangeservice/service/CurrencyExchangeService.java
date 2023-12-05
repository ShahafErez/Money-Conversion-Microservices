package shahaf.currencyexchangeservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shahaf.currencyexchangeservice.exception.NotFoundException;
import shahaf.currencyexchangeservice.models.CurrencyExchange;
import shahaf.currencyexchangeservice.reposetory.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeService.class);

    public CurrencyExchange saveExchangeValue(CurrencyExchange currencyExchange) {
        logger.info("Adding currency exchange to DB. from {}, to {}", currencyExchange.getFrom(), currencyExchange.getTo());
        return currencyExchangeRepository.save(currencyExchange);
    }

    public CurrencyExchange getExchangeValueByFromAndTo(String from, String to) {
        logger.info("Getting currency exchange from DB. from {}, to {}", from, to);
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new NotFoundException(String.format("Unable to find data from %s to %s", from, to));
        }
        return currencyExchange;
    }

}
