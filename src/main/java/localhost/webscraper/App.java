package localhost.webscraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class.getName());
    private static Configuration configuration = new Configuration();
    private static Map<String, String> data = new HashMap<>();

    public static void main(String[] args) {
        logger.info("Starting scraping.");

        try (WebClient webClient = new WebClient()) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

            final HtmlPage page = webClient.getPage(
                    configuration.getSiteUri() + "/principais_indicadores.php?cod_negociacao=TAEE11");
            final DomNodeList<DomElement> trs = page.getElementsByTagName("tr");
            trs.forEach(tr -> extractIndicator(tr.getChildElements()));

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        logger.info("Scraping finished.");
    }

    private static void extractIndicator(Iterable<DomElement> childElements) {
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

    public static void setConfiguration(Configuration configuration) {
        App.configuration = configuration;
    }

    public static Map<String, String> getData() {
        return data;
    }
}
