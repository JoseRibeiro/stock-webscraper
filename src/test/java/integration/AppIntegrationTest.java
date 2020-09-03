package integration;

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
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringJUnitConfig(classes = App.class)
@Sql(scripts = {"classpath:cleanup-database.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AppIntegrationTest {

    private static final String TAEE11 = "TAEE11";

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private App app;

    @Autowired
    private ApplicationConfiguration configuration;

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
    void run() {
        app.run();
        final Stock taesa = stockRepository.findByTicker(TAEE11);
        assertThat(taesa, is(notNullValue()));
        assertThat(taesa.getPriceToEarnings(), is(new BigDecimal("8.13")));
    }

    @Test
    void runOverExistingStock() {
        // TODO improve test to assure that the stock was actually updated between executions of run.
        /* Run app to create stocks. */
        app.run();

        /* Second run to update the stocks */
        app.run();

        final Stock taesa = stockRepository.findByTicker(TAEE11);
        assertThat(taesa, is(notNullValue()));
    }
}