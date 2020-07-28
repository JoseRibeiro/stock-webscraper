package integration;

import localhost.webscraper.App;
import localhost.webscraper.domain.Stock;
import localhost.webscraper.domain.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = App.class)
public class StockRepositoryIntegrationTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    void saveStock() {
        final Iterable<Stock> all = stockRepository.findAll();
        assertThat(all, is(emptyIterable()));
    }
}
