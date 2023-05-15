package com.example.cryptocurrency.models;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    private List<CryptoCurrency> cryptoCurrencyList;
    private String totalCount;

    public Data(List<CryptoCurrency> cryptoCurrencyList, String totalCount) {
        this.cryptoCurrencyList = cryptoCurrencyList;
        this.totalCount = totalCount;
    }

    public List<CryptoCurrency> getCryptoCurrencyList() {
        return cryptoCurrencyList;
    }

    public void setCryptoCurrencyList(List<CryptoCurrency> cryptoCurrencyList) {
        this.cryptoCurrencyList = cryptoCurrencyList;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
}
