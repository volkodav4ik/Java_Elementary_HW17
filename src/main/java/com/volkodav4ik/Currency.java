package com.volkodav4ik;

import java.util.ArrayList;
import java.util.List;

public class Currency {
    private final String USD = "USD";

    public class ExchangeRate {
        private String currency;
        private double saleRate;
        private double purchaseRate;

        public ExchangeRate() {
        }

        public String getCurrency() {
            return currency;
        }

        public double getSaleRate() {
            return saleRate;
        }

        public double getPurchaseRate() {
            return purchaseRate;
        }

        @Override
        public String toString() {
            return "ExchangeRate{" +
                    "currency='" + currency + '\'' +
                    ", saleRate=" + saleRate +
                    ", purchaseRate=" + purchaseRate +
                    '}';
        }
    }

    private String date;
    private List<ExchangeRate> exchangeRate = new ArrayList<>();

    public Currency() {
    }

    public String getDate() {
        return date;
    }

    public List<ExchangeRate> getExchangeRate() {
        return exchangeRate;
    }

    public double getSaleDollar() {
        double sale = 0;
        for (ExchangeRate exchangeRate : exchangeRate) {
            if (USD.equals(exchangeRate.currency)) {
                sale = exchangeRate.getSaleRate();
            }
        }
        return sale;
    }

    public double getPurchaseDollar() {
        double purchase = 0;
        for (ExchangeRate exchangeRate : exchangeRate) {
            if (USD.equals(exchangeRate.currency)) {
                purchase = exchangeRate.getPurchaseRate();
            }
        }
        return purchase;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "date='" + date + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
