package org.example.trenuricaps.domain;

import java.util.Objects;

public class Searches {
    private String userId;
    private String departureId;
    private String destinationId;

    public Searches(String userId, String departureId, String destinationId) {
        this.userId = userId;
        this.departureId = departureId;
        this.destinationId = destinationId;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) { this.userId = userId; }
    public String getDepartureId() {
        return departureId;
    }
    public void setDepartureId(String departureId) {
        this.departureId = departureId;
    }
    public String getDestinationId() {
        return destinationId;
    }
    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Searches searches = (Searches) o;
        return Objects.equals(userId, searches.userId) &&
                Objects.equals(departureId, searches.departureId) &&
                Objects.equals(destinationId, searches.destinationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, departureId, destinationId);
    }
}
