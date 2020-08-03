package localhost.webscraper.infrastructure;

import localhost.webscraper.application.Scraper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class InvestSiteScraper implements Scraper {

    @Override
    public Map<String, String> scrapIndicators(String ticker) {
        return Collections.emptyMap();
    }
}
