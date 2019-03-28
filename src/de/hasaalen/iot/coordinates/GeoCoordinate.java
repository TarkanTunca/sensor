package de.hasaalen.iot.coordinates;

import java.util.concurrent.ThreadLocalRandom;

public class GeoCoordinate {
    private final double latitude;
    private final double longitude;
    private final int NUM_RETIRES = 10;

    public GeoCoordinate(double la, double lo) {
        this.latitude = la;
        this.longitude = lo;
    }

    @Override
    public String toString() {
        return String.format("(%f;%f)", latitude, longitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public GeoCoordinate randomizedPointWithinDistance(double d) {
        final double angularDistance = d / Util.RADIUS;
        final double maxLatDelta = latitude + angularDistance;
        final double minLatDelta = latitude - angularDistance;
        final double maxLonDelta = longitude + angularDistance;
        final double minLonDelta = longitude - angularDistance;


        for (int i = 0; i < NUM_RETIRES * d; i++) {
            double latDelta = ThreadLocalRandom.current().nextDouble(minLatDelta, maxLatDelta);
            double lonDelta = ThreadLocalRandom.current().nextDouble(minLonDelta, maxLonDelta);
            GeoCoordinate candidate = new GeoCoordinate(latitude + latDelta, longitude + lonDelta);
            if (Util.angularDistance(this, candidate) < angularDistance) {
                return candidate;
            }
        }
        return this;
    }

}
