package localhost.webscraper.domain;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public class Stock {

    @Id
    private Long id;

    private String ticker;

    private BigDecimal priceToEarnings;

    private BigDecimal evToEbitda;

    private BigDecimal currentRatio;

    private BigDecimal grossDebtToNetWorth;

    private BigDecimal returnOnEquity;

    private BigDecimal dividendYield;

    private BigDecimal dividendPayout;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPriceToEarnings() {
        return priceToEarnings;
    }

    public void setPriceToEarnings(BigDecimal priceToEarnings) {
        this.priceToEarnings = priceToEarnings;
    }

    public BigDecimal getEvToEbitda() {
        return evToEbitda;
    }

    public void setEvToEbitda(BigDecimal evToEbitda) {
        this.evToEbitda = evToEbitda;
    }

    public BigDecimal getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(BigDecimal currentRatio) {
        this.currentRatio = currentRatio;
    }

    public BigDecimal getGrossDebtToNetWorth() {
        return grossDebtToNetWorth;
    }

    public void setGrossDebtToNetWorth(BigDecimal grossDebtToNetWorth) {
        this.grossDebtToNetWorth = grossDebtToNetWorth;
    }

    public BigDecimal getReturnOnEquity() {
        return returnOnEquity;
    }

    public void setReturnOnEquity(BigDecimal returnOnEquity) {
        this.returnOnEquity = returnOnEquity;
    }

    public BigDecimal getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(BigDecimal dividendYield) {
        this.dividendYield = dividendYield;
    }

    public BigDecimal getDividendPayout() {
        return dividendPayout;
    }

    public void setDividendPayout(BigDecimal dividendPayout) {
        this.dividendPayout = dividendPayout;
    }
}
