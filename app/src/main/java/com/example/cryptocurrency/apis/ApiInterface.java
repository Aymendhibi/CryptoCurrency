package com.example.cryptocurrency.apis;

import com.example.cryptocurrency.models.MarketModel;

import retrofit2.http.GET;

public interface ApiInterface {

    @GET("data-api/v3/cryptocurrency/listing?start=1&limit=500")
    retrofit2.Call<MarketModel> getMarketData();
}
