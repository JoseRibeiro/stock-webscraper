package localhost.webscraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import localhost.webscraper.domain.Stock;
import localhost.webscraper.domain.StockRepository;
import localhost.webscraper.infrastructure.ApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ApplicationConfiguration configuration;

    private static final Logger logger = LoggerFactory.getLogger(App.class.getName());
    private Map<String, String> data = new HashMap<>();

    public void run(String... args) {
        logger.info("Starting scraping.");

        try (WebClient webClient = new WebClient()) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

            final HtmlPage page = webClient.getPage(
                    configuration.getSiteUri() + "/principais_indicadores.php?cod_negociacao=TAEE11");
            final DomNodeList<DomElement> trs = page.getElementsByTagName("tr");
            trs.forEach(tr -> extractIndicator(tr.getChildElements()));

            saveStock();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        logger.info("Scraping finished.");
    }

    private void saveStock() {
        final String taee11 = "TAEE11";
        Stock stock = Optional.ofNullable(stockRepository.findByTicker(taee11)).orElse(new Stock(taee11));
        stockRepository.save(stock);
    }

    private void extractIndicator(Iterable<DomElement> childElements) {
        String key = null, value = null;
        for (DomElement td : childElements) {
            if (td.getAttribute("class").equalsIgnoreCase("esquerda")) {
                key = td.getTextContent();
            } else if (td.getAttribute("class").equalsIgnoreCase("direita")) {
                value = td.getTextContent();
            }
        }
        data.put(key, value);
    }

    public Map<String, String> getData() {
        return data;
    }
}
