package integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import localhost.webscraper.App;
import localhost.webscraper.domain.Stock;
import localhost.webscraper.domain.StockRepository;
import localhost.webscraper.infrastructure.ApplicationConfiguration;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringJUnitConfig(classes = App.class)
@Sql(scripts = {"classpath:cleanup-database.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AppTest {

    private static final String TAEE11 = "TAEE11";
    public static WireMockServer WIRE_MOCK_SERVER;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private App app;

    @Autowired
    private ApplicationConfiguration configuration;

    @BeforeAll
    static void beforeAll() {
        WIRE_MOCK_SERVER = new WireMockServer();
        WIRE_MOCK_SERVER.start();
    }

    @AfterAll
    static void afterAll() {
        WIRE_MOCK_SERVER.stop();
    }

    @BeforeEach
    void setUp() throws IOException {
        configuration.setSiteUri("http://localhost:8080");

        stubFor(any(anyUrl())
                .atPriority(10)
                .willReturn(aResponse()
                        .withStatus(404)));

        stubFor(get(urlEqualTo("/principais_indicadores.php?cod_negociacao=" + TAEE11))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html")
                        .withBody(AppTest.class.getResourceAsStream("/InvestSite-" + TAEE11 + ".html").readAllBytes())));
    }

    @AfterEach
    void tearDown() {
        WireMock.reset();
    }

    @Test
    void run() {
        app.run();
        Map<String, String> data = app.getData();
        assertThat(data, hasEntry("EV/EBITDA", "8,89"));
        final Stock taesa = stockRepository.findByTicker(TAEE11);
        assertThat(taesa, is(notNullValue()));
    }

    @Test
    void runOverExistingStock() {
        app.run();
        app.run();
        final Stock taesa = stockRepository.findByTicker(TAEE11);
        assertThat(taesa, is(notNullValue()));
    }
}