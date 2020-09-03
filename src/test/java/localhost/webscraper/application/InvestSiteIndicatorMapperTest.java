package localhost.webscraper.application;

import localhost.webscraper.domain.Stock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;

class InvestSiteIndicatorMapperTest {

    final static Map<String, String> indicators = new HashMap<>();
            
    private final InvestSiteIndicatorMapper investSiteIndicatorMapper = new InvestSiteIndicatorMapper();

    private Stock stock;

    @BeforeAll
    static void beforeAll() {

        /* Initializes with simple put to reproduce nullable key and value behavior. */
        indicators.put(null, null);
        indicators.put("Retorno s/ Capital Investido", "17,68%");
        indicators.put("Situação Registro", "ATIVO");
        indicators.put("Preço/VPA", "1,85");
        indicators.put("Empresa", "TAESA");
        indicators.put("Giro do Ativo", "0,20");
        indicators.put("Ativo Total", "R$ 12,35 B");
        indicators.put("Razão Social", "TRANSMISSORA ALIANÇA DE ENERGIA ELÉTRICA S.A.");
        indicators.put("Receita Líquida", "R$ 690,42 M");
        indicators.put("EBITDA", "R$ 1,74 B");
        indicators.put("Margem Bruta", "66,77%");
        indicators.put("Último Fechamento", "R$ 28,49");
        indicators.put("Quant. Ações Totais", "344.498.907");
        indicators.put("Valor Patrimonial da Ação", "R$ 15,40");
        indicators.put("Tipo de Ação", "UNT");
        indicators.put("Dívida Bruta", "R$ 5,90 B");
        indicators.put("Ação", "TAEE11");
        indicators.put("EV/Receita Líquida", "7,24");
        indicators.put("Variação 5 anos (anual)", null);
        indicators.put("Retorno s/ Capital Tangível", "16,78%");
        indicators.put("Situação Emissor", "FASE OPERACIONAL");
        indicators.put("Variação 2020", null);
        indicators.put("Variação 3 anos (anual)", null);
        indicators.put("Margem EBIT", "80,74%");
        indicators.put("EV/EBIT", "8,96");
        indicators.put("Preço/FCF", "12,71");
        indicators.put("Dívida Líquida", "R$ 5,69 B");
        indicators.put("Variação 1 ano", null);
        indicators.put("EBIT", "R$ 579,57 M");
        indicators.put("Preço/NCAV", "-2,57");
        indicators.put("Variação 2 anos (total)", null);
        indicators.put("Maior Preço 52 sem.", "R$ 31,20");
        indicators.put("Preço/FCO", "12,37");
        indicators.put("Disponibilidades", "R$ 209,55 M");
        indicators.put("Preço/Ativo Total", "0,79");
        indicators.put("Retorno s/ Patrimônio Líquido", "24,05%");
        indicators.put("Preço/Capital Giro", "4,49");
        indicators.put("Retorno s/ Ativo", "11,26%");
        indicators.put("Variação 3 anos (total)", null);
        indicators.put("Variação 4 anos (total)", null);
        indicators.put("Volume Financeiro", "R$ 41,82 M");
        indicators.put("Variação 5 anos (total)", null);
        indicators.put("Market Cap", "R$ 9,81 B");
        indicators.put("Margem Líquida", "56,32%");
        indicators.put("Passivo/Patrimônio Líquido", "1,14");
        indicators.put("EV/FCO", "19,54");
        indicators.put("Fator de Cotação", "1 ação");
        indicators.put("Lucro Líquido", "R$ 364,19 M");
        indicators.put("Enterprise Value", "R$ 15,51 B");
        indicators.put("Dívida CP", "R$ 795,54 M");
        indicators.put("Atividade", "Transmissão de Energia Elétrica.");
        indicators.put("Preço/EBIT", "5,67");
        indicators.put("Alavancagem Financeira", "2,14");
        indicators.put("Último Demonstrativo", "ITR - 31/03/2020");
        indicators.put("Variação 2 anos (anual)", null);
        indicators.put("Data Cotação", "10/07/2020");
        indicators.put("Variação 4 anos (anual)", null);
        indicators.put("Menor Preço 52 sem.", "R$ 23,77");
        indicators.put("EV/FCF", "20,08");
        indicators.put("Subsetor", "Energia Elétrica");
        indicators.put("Setor", "Utilidade Pública");
        indicators.put("Segmento de Listagem", "Nível 2 de Governança Corporativa");
        indicators.put("Preço/Receita Líquida", "4,58");
        indicators.put("Resultado Bruto", "R$ 471,09 M");
        indicators.put("Quant. Ações Preferenciais", "0");
        indicators.put("Part. Índices", "IBOVESPA, IBRA, IBRX, IDIV, IEE, IGC, IGCT, ITAG, SMLL, UTIL");
        indicators.put("Segmento", "Energia Elétrica");
        indicators.put("EV/Ativo Total", "1,26");
        indicators.put("Volume Diário Médio (3 meses)", "R$ 71,72 M");
        indicators.put("Dividend Yield", "8,16%");
        indicators.put("Dívida LP", "R$ 5,11 B");
        indicators.put("Depre/Amort", "R$ 13,26 M");
        indicators.put("Lucro/Ação", "R$ 3,50");
        indicators.put("Preço/Lucro", "8,13");
        indicators.put("Quant. Ações Ordinárias", "0");
        indicators.put("EV/EBITDA", "8,89");
        indicators.put("Patrimônio Líquido", "R$ 5,30 B");
    }

    @BeforeEach
    void setUp() {
        stock = new Stock("ABCD01");
    }

    @Test
    void shouldMapIndicatorMarketCapBillionScale() {
        investSiteIndicatorMapper.mapIndicatorsToAttributes(indicators, stock);

        assertThat(stock.getMarketCap(), comparesEqualTo(new BigDecimal("9810000000")));
    }

    @Test
    void shouldMapIndicatorMarketCapMillionScale() {
        indicators.put("Market Cap", "R$ 210 M");

        investSiteIndicatorMapper.mapIndicatorsToAttributes(indicators, stock);

        assertThat(stock.getMarketCap(), comparesEqualTo(new BigDecimal("210000000")));
    }

    @Test
    void shouldMapIndicatorPriceToEarnings() {
        investSiteIndicatorMapper.mapIndicatorsToAttributes(indicators, stock);

        assertThat(stock.getPriceToEarnings(), is(new BigDecimal("8.13")));
    }

    @Test
    void shouldMapIndicatorEvToEbitda() {
        investSiteIndicatorMapper.mapIndicatorsToAttributes(indicators, stock);

        assertThat(stock.getEvToEbitda(), is(new BigDecimal("8.89")));
    }

    @Test
    void shouldMapIndicatorCurrentRatio() {
        investSiteIndicatorMapper.mapIndicatorsToAttributes(indicators, stock);

        assertThat(stock.getCurrentRatio(), is(new BigDecimal("0")));
    }

    @Test
    void shouldMapIndicatorGrossDebtToNetWorth() {
        investSiteIndicatorMapper.mapIndicatorsToAttributes(indicators, stock);

        assertThat(stock.getGrossDebtToNetWorth(), is(new BigDecimal("0")));
    }

    @Test
    void shouldMapIndicatorReturnOnEquity() {
        investSiteIndicatorMapper.mapIndicatorsToAttributes(indicators, stock);

        assertThat(stock.getReturnOnEquity(), is(new BigDecimal("24.05")));
    }

    @Test
    void shouldMapIndicatorDividendYield() {
        investSiteIndicatorMapper.mapIndicatorsToAttributes(indicators, stock);

        assertThat(stock.getDividendYield(), is(new BigDecimal("8.16")));
    }

    @Test
    void shouldMapIndicatorDividendPayout() {
        investSiteIndicatorMapper.mapIndicatorsToAttributes(indicators, stock);

        assertThat(stock.getDividendPayout(), is(new BigDecimal("0")));
    }
}