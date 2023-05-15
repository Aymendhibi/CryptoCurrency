package com.example.cryptocurrency.models;


import java.io.Serializable;

public class Quote implements Serializable {
        private final double dominance;
        private final double fullyDilluttedMarketCap;
        private final String lastUpdated;
        private final double marketCap;
        private final double marketCapByTotalSupply;
        private final String name;
        private final double percentChange1h;
        private   double percentChange24h;
        private final double percentChange30d;
        private final double percentChange60d;
        private final double percentChange7d;
        private final double percentChange90d;
        private final double price;
        private final double turnover;
        private final double tvl;
        private final double volume24h;
        private final double ytdPriceChangePercentage;

        public Quote(double dominance, double fullyDilluttedMarketCap, String lastUpdated, double marketCap,
                     double marketCapByTotalSupply, String name, double percentChange1h, double percentChange24h,
                     double percentChange30d, double percentChange60d, double percentChange7d, double percentChange90d,
                     double price, double turnover, double tvl, double volume24h, double ytdPriceChangePercentage) {
            this.dominance = dominance;
            this.fullyDilluttedMarketCap = fullyDilluttedMarketCap;
            this.lastUpdated = lastUpdated;
            this.marketCap = marketCap;
            this.marketCapByTotalSupply = marketCapByTotalSupply;
            this.name = name;
            this.percentChange1h = percentChange1h;
            this.percentChange24h = percentChange24h;
            this.percentChange30d = percentChange30d;
            this.percentChange60d = percentChange60d;
            this.percentChange7d = percentChange7d;
            this.percentChange90d = percentChange90d;
            this.price = price;
            this.turnover = turnover;
            this.tvl = tvl;
            this.volume24h = volume24h;
            this.ytdPriceChangePercentage = ytdPriceChangePercentage;
        }

        public double getDominance() {
            return dominance;
        }

        public double getFullyDilluttedMarketCap() {
            return fullyDilluttedMarketCap;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public double getMarketCap() {
            return marketCap;
        }

        public double getMarketCapByTotalSupply() {
            return marketCapByTotalSupply;
        }

        public String getName() {
            return name;
        }

        public double getPercentChange1h() {
            return percentChange1h;
        }

        public double getPercentChange24h() {
            return percentChange24h;
        }

        public double getPercentChange30d() {
            return percentChange30d;
        }

        public double getPercentChange60d() {
            return percentChange60d;
        }

        public double getPercentChange7d() {
            return percentChange7d;
        }

        public double getPercentChange90d() {
            return percentChange90d;
        }

        public double getPrice() {
            return price;
        }

        public double getTurnover() {
            return turnover;
        }

        public double getTvl() {
            return tvl;
        }

        public double getVolume24h() {
            return volume24h;
        }

        public double getYtdPriceChangePercentage() {
            return ytdPriceChangePercentage;
        }
    }


