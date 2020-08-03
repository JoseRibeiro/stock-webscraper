package localhost.webscraper.application;

import java.util.Map;

public interface Scraper {
    Map<String, String> scrapIndicators(String ticker);
}
