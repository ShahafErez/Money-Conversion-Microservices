package shahaf.currencyexchangeservice.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shahaf.currencyexchangeservice.model.CurrencyExchange;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    Boolean existsByFromAndTo(String from, String to);
    CurrencyExchange findByFromAndTo(String from, String to);
    void deleteByFromAndTo(String from, String to);
}