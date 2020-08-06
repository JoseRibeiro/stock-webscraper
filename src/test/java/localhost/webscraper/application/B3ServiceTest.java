package localhost.webscraper.application;

import localhost.webscraper.domain.B3Tickers;
import localhost.webscraper.domain.Stock;
import localhost.webscraper.domain.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class B3ServiceTest {

    @Mock
    private Scraper scraper;

    @Mock
    private StockRepository stockRepository;

    @Captor
    private ArgumentCaptor<Stock> stockCaptor;

    private B3Service service;

    @BeforeEach
    void setUp() {
        service = new B3Service(scraper, stockRepository, new InvestSiteIndicatorMapper());
    }

    @Test
    void shouldScrapSiteForIndicators() {
        service.updateStockIndicators();
        verify(scraper).scrapIndicators(B3Tickers.TAEE11.name());
    }

    @Test
    void shouldCreateStockWithTheIndicators() {
        Map<String, String> indicators = Map.of("Preço/Lucro", "9,9");
        when(scraper.scrapIndicators(B3Tickers.TAEE11.name())).thenReturn(indicators);

        service.updateStockIndicators();

        verify(stockRepository).save(stockCaptor.capture());
        final Stock capturedStock = stockCaptor.getValue();
        assertThat(capturedStock, is(notNullValue()));
        assertThat(capturedStock.getPriceToEarnings(), is(new BigDecimal("9.9")));
    }
}