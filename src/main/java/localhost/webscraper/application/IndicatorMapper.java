package localhost.webscraper.application;

import localhost.webscraper.domain.Stock;

import java.util.Map;

public interface IndicatorMapper {
    void mapIndicatorsToAttributes(Map<String, String> indicators, Stock stock);
}
