package localhost.webscraper;

public class Configuration {
    private String siteUri = "https://www.investsite.com.br";

    public Configuration(String siteUri) {
        this.siteUri = siteUri;
    }

    public Configuration() {
    }

    public String getSiteUri() {
        return siteUri;
    }

    public void setSiteUri(String siteUri) {
        this.siteUri = siteUri;
    }
}