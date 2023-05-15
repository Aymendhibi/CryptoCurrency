package com.example.cryptocurrency.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CryptoCurrency implements Serializable {
    private List<AuditInfo> auditInfoList;
    private double circulatingSupply;
    private double cmcRank;
    private String dateAdded;
    private int id;
    private double isActive;
    private boolean isAudited;
    private String lastUpdated;
    private double marketPairCount;
    private double maxSupply;
    private String name;
    // private Platform platform;
    private List<Quote> quotes;
    private double selfReportedCirculatingSupply;
    private String slug;
    @Expose
    private String symbol;
    private List<String> tags;
    private double totalSupply;

    public CryptoCurrency(List<AuditInfo> auditInfoList, double circulatingSupply, double cmcRank, String dateAdded, int id, double isActive, boolean isAudited, String lastUpdated, double marketPairCount, double maxSupply, String name, List<Quote> quotes, double selfReportedCirculatingSupply, String slug, String symbol, List<String> tags, double totalSupply) {
        this.auditInfoList = auditInfoList;
        this.circulatingSupply = circulatingSupply;
        this.cmcRank = cmcRank;
        this.dateAdded = dateAdded;
        this.id = id;
        this.isActive = isActive;
        this.isAudited = isAudited;
        this.lastUpdated = lastUpdated;
        this.marketPairCount = marketPairCount;
        this.maxSupply = maxSupply;
        this.name = name;
        // this.platform = platform;
        this.quotes = quotes;
        this.selfReportedCirculatingSupply = selfReportedCirculatingSupply;
        this.slug = slug;
        this.symbol = symbol;
        this.tags = tags;
        this.totalSupply = totalSupply;
    }

    public List<AuditInfo> getAuditInfoList() {
        return auditInfoList;
    }

    public double getCirculatingSupply() {
        return circulatingSupply;
    }

    public double getCmcRank() {
        return cmcRank;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public int getId() {
        return id;
    }

    public double getIsActive() {
        return isActive;
    }

    public boolean isAudited() {
        return isAudited;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public double getMarketPairCount() {
        return marketPairCount;
    }

    public double getMaxSupply() {
        return maxSupply;
    }

    public String getName() {
        return name;
    }

    // public Platform getPlatform() {
    //     return platform;
    // }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public double getSelfReportedCirculatingSupply() {
        return selfReportedCirculatingSupply;
    }

    public String getSlug() {
        return slug;
    }

    public String getSymbol() {
        return symbol ;
    }

    public List<String> getTags() {
        return tags;
    }

    public double getTotalSupply() {
        return totalSupply;
    }

    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "auditInfoList=" + auditInfoList +
                ", circulatingSupply=" + circulatingSupply +
                ", cmcRank=" + cmcRank +
                ", dateAdded='" + dateAdded + '\'' +
                ", id=" + id +
                ", isActive=" + isActive +
                ", isAudited=" + isAudited +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", marketPairCount=" + marketPairCount +
                ", maxSupply=" + maxSupply +
                ", name='" + name + '\'' +
                ", quotes=" + quotes +
                ", selfReportedCirculatingSupply=" + selfReportedCirculatingSupply +
                ", slug='" + slug + '\'' +
                ", symbol='" + symbol + '\'' +
                ", tags=" + tags +
                ", totalSupply=" + totalSupply +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CryptoCurrency)) return false;
        CryptoCurrency that = (CryptoCurrency) o;
        return Double.compare(that.circulatingSupply, circulatingSupply) == 0 && Double.compare(that.cmcRank, cmcRank) == 0 && id == that.id && Double.compare(that.isActive, isActive) == 0 && isAudited == that.isAudited && Double.compare(that.marketPairCount, marketPairCount) == 0 && Double.compare(that.maxSupply, maxSupply) == 0 && Double.compare(that.selfReportedCirculatingSupply, selfReportedCirculatingSupply) == 0 && Double.compare(that.totalSupply, totalSupply) == 0 && Objects.equals(auditInfoList, that.auditInfoList) && Objects.equals(dateAdded, that.dateAdded) && Objects.equals(lastUpdated, that.lastUpdated) && Objects.equals(name, that.name) && Objects.equals(quotes, that.quotes) && Objects.equals(slug, that.slug) && Objects.equals(symbol, that.symbol) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auditInfoList, circulatingSupply, cmcRank, dateAdded, id, isActive, isAudited, lastUpdated, marketPairCount, maxSupply, name, quotes, selfReportedCirculatingSupply, slug, symbol, tags, totalSupply);
    }
}