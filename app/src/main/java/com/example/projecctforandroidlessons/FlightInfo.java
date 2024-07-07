package com.example.projecctforandroidlessons;

import java.util.Objects;

public class FlightInfo {
    private final String price;
    private final String timeStart;
    private final String timeEnd;
    private final String duration;
    private final String from;
    private final String to;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightInfo that = (FlightInfo) o;
        return Objects.equals(price, that.price) && Objects.equals(timeStart, that.timeStart) && Objects.equals(timeEnd, that.timeEnd) && Objects.equals(duration, that.duration) && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, timeStart, timeEnd, duration, from, to);
    }

    public FlightInfo(String price, String timeStart, String timeEnd, String duration, String from, String to) {
        this.price = price;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.duration = duration;
        this.from = from;
        this.to = to;

    }

}
