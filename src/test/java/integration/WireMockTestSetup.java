package integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import localhost.webscraper.infrastructure.ApplicationConfiguration;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockTestSetup {

    private static WireMockServer WIRE_MOCK_SERVER;

    static void startWireMockServer() {
        WIRE_MOCK_SERVER = new WireMockServer();
        WIRE_MOCK_SERVER.start();
    }

    static void stopWireMockServer() {
        WIRE_MOCK_SERVER.stop();
    }

    static void setUpWiremock(ApplicationConfiguration configuration, String ticker) throws IOException {
        configuration.setSiteUri("http://localhost:8080");

        stubFor(any(anyUrl())
                .atPriority(10)
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html")
                        .withStatus(404)));

        stubFor(get(urlEqualTo("/principais_indicadores.php?cod_negociacao=" + ticker))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html")
                        .withBody(AppIntegrationTest.class.getResourceAsStream("/InvestSite-" + ticker + ".html").readAllBytes())));
    }
}
