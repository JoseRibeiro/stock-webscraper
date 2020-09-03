package localhost.webscraper.application;

import localhost.webscraper.domain.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
public class InvestSiteIndicatorMapper implements IndicatorMapper {

    private static final Logger LOG = LoggerFactory.getLogger(InvestSiteIndicatorMapper.class);

    private static final String MARKET_CAP = "Market Cap";

    private static final String PRICE_TO_EARNINGS = "Preço/Lucro";

    private static final String EV_TO_EBITDA = "EV/EBITDA";

    private static final String CURRENT_RATIO = "Liquidez Corrente";

    private static final String GROSS_DEBT_TO_NET_WORTH = "Dív. Bruta / PL";

    private static final String ROE = "Retorno s/ Patrimônio Líquido";

    private static final String DIVIDEND_YIELD = "Dividend Yield";

    private static final String DIVIDEND_PAYOUT = "Dividend Payout";

    @Override
    public void mapIndicatorsToAttributes(Map<String, String> indicators, Stock stock) {
        LOG.info("Ticker: {}", stock.getTicker());

        stock.setMarketCap(getBigDecimal(indicators, MARKET_CAP));
        stock.setPriceToEarnings(getBigDecimal(indicators, PRICE_TO_EARNINGS));
        stock.setEvToEbitda(getBigDecimal(indicators, EV_TO_EBITDA));
        stock.setCurrentRatio(getBigDecimal(indicators, CURRENT_RATIO));
        stock.setGrossDebtToNetWorth(getBigDecimal(indicators, GROSS_DEBT_TO_NET_WORTH));
        stock.setReturnOnEquity(getBigDecimal(indicators, ROE));
        stock.setDividendYield(getBigDecimal(indicators, DIVIDEND_YIELD));
        stock.setDividendPayout(getBigDecimal(indicators, DIVIDEND_PAYOUT));
    }

    private BigDecimal getBigDecimal(Map<String, String> indicators, String indicator) {
        LOG.info("Indicator: {}", indicator);

        // TODO this method definitely needs a refactor.
        final String sanitizedIndicator = Optional.ofNullable(indicators.get(indicator)).orElse("")
                .replace("R$ ", "")
                .replace("%", "")
                .replaceAll("\\.", "")
                .replace(",", ".");

        if (sanitizedIndicator.isBlank()) {
            return null;
        }

        if (sanitizedIndicator.contains(" B")) {
            final String indicatorWithoutScale = sanitizedIndicator.replace(" B", "");
            return new BigDecimal(indicatorWithoutScale).multiply(new BigDecimal(1000000000));
        } else if (sanitizedIndicator.contains(" M")) {
            final String indicatorWithoutScale = sanitizedIndicator.replace(" M", "");
            return new BigDecimal(indicatorWithoutScale).multiply(new BigDecimal(1000000));
        }

        return new BigDecimal(sanitizedIndicator);
    }
}
