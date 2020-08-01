package integration;

import localhost.webscraper.App;
import localhost.webscraper.domain.Stock;
import localhost.webscraper.domain.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;

@SpringJUnitConfig(classes = App.class)
public class StockRepositoryIntegrationTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    void findAllStocks() {
        final Iterable<Stock> all = stockRepository.findAll();
        assertThat(all, is(emptyIterable()));
    }
}
