package com.example.cryptocurrency.models;

import java.io.Serializable;

public class MarketModel implements Serializable {
    private Data data;
    //private Status status;

    public MarketModel(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    /*
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    */
}

