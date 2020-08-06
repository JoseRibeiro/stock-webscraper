package integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import localhost.webscraper.App;
import localhost.webscraper.infrastructure.ApplicationConfiguration;
import localhost.webscraper.infrastructure.InvestSiteScraper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringJUnitConfig(classes = App.class)
class InvestSiteScraperIntegrationTest {

    private final String TAEE11 = "TAEE11";

    @Autowired
    private ApplicationConfiguration configuration;

    @Autowired
    private InvestSiteScraper investSiteScraper;

    @BeforeAll
    static void beforeAll() {
        WireMockTestSetup.startWireMockServer();
    }

    @AfterAll
    static void afterAll() {
        WireMockTestSetup.stopWireMockServer();
    }

    @BeforeEach
    void setUp() throws IOException {
        WireMockTestSetup.setUpWiremock(configuration, TAEE11);
    }

    @AfterEach
    void tearDown() {
        WireMock.reset();
    }

    @Test
    void shouldScrapPriceToEarningsIndicator() {
        final Map<String, String> indicators = investSiteScraper.scrapIndicators(TAEE11);
        assertThat(indicators, hasEntry("Preço/Lucro", "8,13"));
    }

    @Test
    void shouldScrapEvToEbitdaIndicator() {
        final Map<String, String> indicators = investSiteScraper.scrapIndicators(TAEE11);
        assertThat(indicators, hasEntry("EV/EBITDA", "8,89"));
    }

    @Test
    void shouldReturnEmptyMapOnError() {

        /* Override site uri to force IOException (MalformedURLException). */
        configuration.setSiteUri("MalformadURL");

        final Map<String, String> indicators = investSiteScraper.scrapIndicators(null);

        assertThat(indicators, is(anEmptyMap()));
    }
}