package localhost.webscraper;

import localhost.webscraper.application.B3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private final B3Service b3Service;

    public App(B3Service b3Service) {
        this.b3Service = b3Service;
    }

    public void run(String... args) {
        LOG.info("Starting scraping.");
        b3Service.updateStockIndicators();
        LOG.info("Scraping finished.");
    }

    /**
     * Main method to be used by spring-boot-maven-plugin.
     * @param args Unused.
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
