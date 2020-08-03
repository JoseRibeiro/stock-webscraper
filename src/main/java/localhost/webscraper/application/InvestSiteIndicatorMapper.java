package localhost.webscraper.application;

import localhost.webscraper.domain.Stock;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
public class InvestSiteIndicatorMapper implements IndicatorMapper {

    private static final String PRICE_TO_EARNINGS = "P/L";

    @Override
    public void mapIndicatorsToAttributes(Map<String, String> indicators, Stock stock) {
        final String sanitizedIndicator = Optional.ofNullable(indicators.get(PRICE_TO_EARNINGS)).orElse("0").replaceAll("\\.", "").replace(",", ".");
        final BigDecimal priceToEarnings = new BigDecimal(sanitizedIndicator);
        stock.setPriceToEarnings(priceToEarnings);
    }
}
