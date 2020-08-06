package localhost.webscraper.infrastructure;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import localhost.webscraper.application.Scraper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvestSiteScraper implements Scraper {

    private static final Logger LOG = LoggerFactory.getLogger(InvestSiteScraper.class);

    private final ApplicationConfiguration configuration;

    public InvestSiteScraper(ApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Map<String, String> scrapIndicators(String ticker) {
        try (WebClient webClient = new WebClient()) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

            final HtmlPage page = webClient.getPage(
                    configuration.getSiteUri() + "/principais_indicadores.php?cod_negociacao=" + ticker);
            final DomNodeList<DomElement> trs = page.getElementsByTagName("tr");
            Map<String, String> data = new HashMap<>();
            trs.forEach(tr -> extractIndicator(tr.getChildElements(), data));

            return data;

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyMap();
        }
    }

    private void extractIndicator(Iterable<DomElement> childElements, Map<String, String> data) {
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
}
