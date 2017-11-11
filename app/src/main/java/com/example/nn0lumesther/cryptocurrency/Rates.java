package com.example.nn0lumesther.cryptocurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Rates {

    ArrayList<CashRate> ratesArrayList = new ArrayList<>(); //contains all 20 monies and their btc & eth rates

    Rates() {}

    //Method to populate the ratesArrayList
    void add(String currency, Double btcRate, Double ethRate) {
        CashRate cashRate = new CashRate(currency, btcRate, ethRate);
        ratesArrayList.add(cashRate);
    }

    //This method sorts the ratesArrayList alphabetically OR by the currencies' values in BTC
    //...since BTC and ETH maintain a constant rate across all currencies
    void orderList(final int mode) {
        Collections.sort(ratesArrayList, new Comparator<CashRate>() {
            @Override
            public int compare(CashRate lhs, CashRate rhs) {
                if(mode == Constants.ORDER_ALPHABETICAL)return lhs.getCurrency().compareTo(rhs.getCurrency());
                else return Double.compare(lhs.getBtcRate(), rhs.getBtcRate());
            }
        });
    }

    //Custom class carries name of currency and its BTC and ETH rates and methods to return these properties
    class CashRate {
        private String currency;
        private double btcRate, ethRate;

        CashRate(String currency, double btcRate, double ethRate) {
            this.currency = currency;
            this.btcRate = btcRate;
            this.ethRate = ethRate;
        }

        String getCurrency() {
            return currency;
        }

        double getBtcRate() {
            return btcRate;
        }

        double getEthRate() {
            return ethRate;
        }
    }

}