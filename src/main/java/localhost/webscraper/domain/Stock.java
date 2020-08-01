package localhost.webscraper.domain;

import org.springframework.data.annotation.Id;

public class Stock {

    @Id
    private Long id;

    private String ticker;

    public Stock(String ticker) {
        this.ticker = ticker;
    }

    public Stock() {
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
