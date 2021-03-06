package localhost.webscraper.domain;

import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {

    Stock findByTicker(String ticker);
}
