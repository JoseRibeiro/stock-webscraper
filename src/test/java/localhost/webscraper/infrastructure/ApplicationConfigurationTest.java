package localhost.webscraper.infrastructure;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ApplicationConfigurationTest {

    @Test
    void should_return_default_URI_as_investsite() {
        final ApplicationConfiguration configuration = new ApplicationConfiguration();
        assertThat(configuration.getSiteUri(), is("https://www.investsite.com.br"));
    }
}