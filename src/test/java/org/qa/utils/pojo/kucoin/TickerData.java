package org.qa.utils.pojo.kucoin;

public class TickerData {

    private String symbol;
    private String symbolName;
    private String buy;
    private String bestBidSize;
    private String sell;
    private String bestAskSize;
    private String changeRate;
    private String changePrice;
    private String high;
    private String low;
    private String vol;
    private String volValue;
    private String last;
    private String averagePrice;
    private String takerFeeRate;
    private String makerFeeRate;
    private String takerCoefficient;
    private String makerCoefficient;

    public TickerData() {
    }

    public TickerData(
            String symbol, String symbolName, String buy, String bestBidSize,
            String sell, String bestAskSize, String changeRate, String changePrice,
            String high, String low, String vol, String volValue,
            String last, String averagePrice, String takerFeeRate,
            String makerFeeRate, String takerCoefficient, String makerCoefficient) {
        this.symbol = symbol;
        this.symbolName = symbolName;
        this.buy = buy;
        this.bestBidSize = bestBidSize;
        this.sell = sell;
        this.bestAskSize = bestAskSize;
        this.changeRate = changeRate;
        this.changePrice = changePrice;
        this.high = high;
        this.low = low;
        this.vol = vol;
        this.volValue = volValue;
        this.last = last;
        this.averagePrice = averagePrice;
        this.takerFeeRate = takerFeeRate;
        this.makerFeeRate = makerFeeRate;
        this.takerCoefficient = takerCoefficient;
        this.makerCoefficient = makerCoefficient;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public String getBuy() {
        return buy;
    }

    public String getBestBidSize() {
        return bestBidSize;
    }

    public String getSell() {
        return sell;
    }

    public String getBestAskSize() {
        return bestAskSize;
    }

    public String getChangeRate() {
        return changeRate;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getVol() {
        return vol;
    }

    public String getVolValue() {
        return volValue;
    }

    public String getLast() {
        return last;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public String getTakerFeeRate() {
        return takerFeeRate;
    }

    public String getMakerFeeRate() {
        return makerFeeRate;
    }

    public String getTakerCoefficient() {
        return takerCoefficient;
    }

    public String getMakerCoefficient() {
        return makerCoefficient;
    }
}
