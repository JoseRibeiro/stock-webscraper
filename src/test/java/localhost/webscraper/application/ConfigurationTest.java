package localhost.webscraper;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ConfigurationTest {

    @Test
    void should_return_default_URI_as_investsite() {
        final Configuration configuration = new Configuration();
        assertThat(configuration.getSiteUri(), is("https://www.investsite.com.br"));
    }
}