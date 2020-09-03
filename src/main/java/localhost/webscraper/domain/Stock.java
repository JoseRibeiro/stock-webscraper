package localhost.webscraper.domain;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public class Stock {

    @Id
    private Long id;

    /**
     * Ticker.
     */
    private String ticker;

    /**
     * Valor de mercado.
     */
    private BigDecimal marketCap;

    /**
     * Preço/Lucro.
     */
    private BigDecimal priceToEarnings;

    /**
     * EV/EBITDA
     */
    private BigDecimal evToEbitda;

    /**
     * Liquidez corrente.
     */
    private BigDecimal currentRatio;

    /**
     * Dívida bruta/Patrimônio Líquido.
     */
    private BigDecimal grossDebtToNetWorth;

    /**
     * ROE.
     */
    private BigDecimal returnOnEquity;

    /**
     * Dividend yield.
     */
    private BigDecimal dividendYield;

    /**
     * Dividend payout.
     */
    private BigDecimal dividendPayout;

    public Stock(String ticker) {
        this.ticker = ticker;
    }

    public Stock() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
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

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", marketCap=" + marketCap +
                ", priceToEarnings=" + priceToEarnings +
                ", evToEbitda=" + evToEbitda +
                ", currentRatio=" + currentRatio +
                ", grossDebtToNetWorth=" + grossDebtToNetWorth +
                ", returnOnEquity=" + returnOnEquity +
                ", dividendYield=" + dividendYield +
                ", dividendPayout=" + dividendPayout +
                '}';
    }
}
