package org.qa.kucoin;

import org.qa.utils.KucoinUrlAndEndPoints;
import org.qa.utils.Specifications;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class StreamApiTests {

    public List<TickerData> getTickers() {
        return given()
                .when()
                .get(KucoinUrlAndEndPoints.GET_ALL_TICKETS)
                .then().log().body()
                .extract().jsonPath().getList("data.ticker", TickerData.class);
    }

    @Test
    public void testCheckCrypto() {
        Specifications.installSpecification(
                Specifications.requestSpecification(KucoinUrlAndEndPoints.BASE_URL)
        );
        List<TickerData> usdTickers = getTickers().stream().
                filter(usdTicker -> usdTicker.getSymbol().endsWith("USDT")).toList();

        assertTrue(usdTickers.stream().allMatch(usdTicker -> usdTicker.getSymbol().endsWith("USDT")));
    }

    @Test
    public void sortHighToLow() {
        Specifications.installSpecification(
                Specifications.requestSpecification(KucoinUrlAndEndPoints.BASE_URL)
        );
        List<TickerData> higToLow = getTickers().stream().
                filter(usdTicker -> usdTicker.getSymbol().endsWith("USDT"))
                .sorted((o1, o2) -> o2.getChangeRate().compareTo(o1.getChangeRate()))
                .toList();
        List<TickerData> top10 = higToLow.stream().limit(10).toList();

        assertNotNull(higToLow);
        assertNotNull(top10);
    }

    @Test
    public void sortLowToHigh() {
        Specifications.installSpecification(
                Specifications.requestSpecification(KucoinUrlAndEndPoints.BASE_URL)
        );
        List<TickerData> lowToHigh = getTickers().stream().
                filter(usdTicker -> usdTicker.getSymbol().endsWith("USDT"))
                .sorted(new TickerComparatorLow())
                .limit(10)
                .toList();

        assertNotNull(lowToHigh);
    }

    @Test
    public void map() {
        Map<String, Float> usd = new HashMap<>();
        List<String> lowerCases = getTickers().stream()
                .map(tickerData -> tickerData.getSymbol().toLowerCase())
                .toList();

        getTickers().forEach(tickerData -> usd.put(tickerData.getSymbol(), Float.parseFloat(tickerData.getChangeRate())));
    }
}