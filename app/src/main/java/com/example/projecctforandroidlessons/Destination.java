package com.example.projecctforandroidlessons;

import java.util.Objects;

public class Destination {

    private final String destination_name;
    private final String popularity_status;

    public Destination(String destination_nane, String popularity_status) {
        this.destination_name = destination_nane;
        this.popularity_status = popularity_status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return Objects.equals(destination_name, that.destination_name) && Objects.equals(popularity_status, that.popularity_status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination_name, popularity_status);
    }

    public String getDestination_name() {
        return destination_name;
    }

    public String getPopularity_status() {
        return popularity_status;
    }
}
