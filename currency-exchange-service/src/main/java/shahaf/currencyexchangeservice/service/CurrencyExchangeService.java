package shahaf.currencyexchangeservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shahaf.currencyexchangeservice.exception.NotFoundException;
import shahaf.currencyexchangeservice.model.CurrencyExchange;
import shahaf.currencyexchangeservice.reposetory.CurrencyExchangeRepository;

@Transactional
@Service
public class CurrencyExchangeService {

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeService.class);

    public CurrencyExchange saveExchangeValue(CurrencyExchange currencyExchange) {
        String from = currencyExchange.getFrom();
        String to = currencyExchange.getTo();
        logger.info("Adding currency exchange to DB. from {}, to {}", from, to);
        if (Boolean.TRUE.equals(currencyExchangeRepository.existsByFromAndTo(from, to))) {
            logger.info("Overriding currency exchange, deleting values from {}, to {} from DB.", from, to);
            currencyExchangeRepository.deleteByFromAndTo(from, to);
        }
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
