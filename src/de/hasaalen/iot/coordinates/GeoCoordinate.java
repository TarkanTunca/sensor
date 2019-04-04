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

    /**
     * Create a random point within distance d km of this point
     * <p>
     * Randomly generates a point within d kilometers of this. The algorithm simply
     * approximates the candidates using a rectangle of valid points and then checks
     * if the random point is within the specified distance. It repeats this process for
     * a number of times that depends on the distance d.
     *
     * @param d distance (in km) of the current point
     * @return a point within d km or this, if no such point was found
     */
    public GeoCoordinate randomizedPointWithinDistance(double d) {
        final double angularDistance = d / Util.RADIUS;
        final double maxLatDelta = Math.toRadians(latitude) + angularDistance;
        final double minLatDelta = Math.toRadians(latitude) - angularDistance;
        final double maxLonDelta = Math.toRadians(longitude) + angularDistance;
        final double minLonDelta = Math.toRadians(longitude) - angularDistance;


        for (int i = 0; i < NUM_RETIRES * d; i++) {
            double newLat = ThreadLocalRandom.current().nextDouble(minLatDelta, maxLatDelta);
            double newLong = ThreadLocalRandom.current().nextDouble(minLonDelta, maxLonDelta);
            GeoCoordinate candidate = new GeoCoordinate(Math.toDegrees(newLat), Math.toDegrees(newLong));
            if (Util.angularDistance(this, candidate) < angularDistance) {
                return candidate;
            }
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoCoordinate)) return false;
        GeoCoordinate that = (GeoCoordinate) o;
        return that.latitude == latitude &&
                that.longitude == longitude;
    }

}
