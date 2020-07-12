package localhost.webscraper;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    public static WireMockServer WIRE_MOCK_SERVER;

    @BeforeAll
    static void beforeAll() {
        WIRE_MOCK_SERVER = new WireMockServer();
        WIRE_MOCK_SERVER.start();

        App.setConfiguration(new Configuration("http://localhost:8080"));
    }

    @AfterAll
    static void afterAll() {
        WIRE_MOCK_SERVER.stop();
    }

    @BeforeEach
    void setUp() throws IOException {
        stubFor(any(anyUrl())
                .atPriority(10)
                .willReturn(aResponse()
                        .withStatus(404)));

        stubFor(get(urlEqualTo("/principais_indicadores.php?cod_negociacao=TAEE11"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html")
                        .withBody(AppTest.class.getResourceAsStream("/InvestSite-TAEE11.html").readAllBytes())));
    }

    @AfterEach
    void tearDown() {
        WireMock.reset();
    }

    @Test
    void main() {
        App.main(null);
        Map<String, String> data = App.getData();
        assertThat(data, hasEntry("EV/EBITDA", "8,89"));
    }
}