package localhost.webscraper.application;

import localhost.webscraper.domain.B3Tickers;
import localhost.webscraper.domain.Stock;
import localhost.webscraper.domain.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class B3Service {

    private static final Logger LOG = LoggerFactory.getLogger(B3Service.class);

    private Scraper scraper;
    private StockRepository stockRepository;
    private IndicatorMapper indicatorMapper;

    public B3Service(Scraper scraper, StockRepository stockRepository, IndicatorMapper indicatorMapper) {
        this.scraper = scraper;
        this.stockRepository = stockRepository;
        this.indicatorMapper = indicatorMapper;
    }

    public void updateStockIndicators() {
        Stream.of(B3Tickers.values()).forEach(this::updateStockIndicator);
    }

    private void updateStockIndicator(B3Tickers ticker) {
        final Map<String, String> indicators = scraper.scrapIndicators(ticker.name());
        final Stock stock = Optional.ofNullable(stockRepository.findByTicker(ticker.name())).orElse(new Stock(ticker.name()));
        indicatorMapper.mapIndicatorsToAttributes(indicators, stock);

        LOG.info("Saving: {}", stock);
        stockRepository.save(stock);
    }
}
