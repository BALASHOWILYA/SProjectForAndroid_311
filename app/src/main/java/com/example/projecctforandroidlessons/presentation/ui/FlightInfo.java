package com.example.projecctforandroidlessons.presentation.ui;

import java.util.Objects;

public class FlightInfo {
    private final String price;
    private final String timeStart;
    private final String timeEnd;
    private final String duration;
    private final String from;
    private final String to;
    private final int customerId;
    private final int routeId;

    public FlightInfo(String price, String timeStart, String timeEnd, String duration, String from, String to, int customerId, int routeId) {
        this.price = price;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.duration = duration;
        this.from = from;
        this.to = to;
        this.customerId = customerId;
        this.routeId = routeId;
    }

    // Getters...

    public String getPrice() {
        return price;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getDuration() {
        return duration;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRouteId() {
        return routeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightInfo that = (FlightInfo) o;
        return customerId == that.customerId && routeId == that.routeId && Objects.equals(price, that.price) && Objects.equals(timeStart, that.timeStart) && Objects.equals(timeEnd, that.timeEnd) && Objects.equals(duration, that.duration) && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, timeStart, timeEnd, duration, from, to, customerId, routeId);
    }
}