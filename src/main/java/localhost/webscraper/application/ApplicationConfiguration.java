package localhost.webscraper.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories
public class ApplicationConfiguration extends AbstractJdbcConfiguration {

    private String siteUri = "https://www.investsite.com.br";

    public ApplicationConfiguration(String siteUri) {
        this.siteUri = siteUri;
    }

    public ApplicationConfiguration() {
    }

    public String getSiteUri() {
        return siteUri;
    }

    public void setSiteUri(String siteUri) {
        this.siteUri = siteUri;
    }
}