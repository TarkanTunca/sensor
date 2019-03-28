package de.hasaalen.iot.coordinates;

public class Util {
    public static final int RADIUS = 6371; // in km

    public static double angularDistance(GeoCoordinate a, GeoCoordinate b) {
        final double deltaLongitude = Math.abs(a.getLongitude() - b.getLongitude());
        final double deltaLatitude = Math.abs(a.getLatitude() - b.getLatitude());
        final double termA = Math.sin(deltaLatitude / 2);
        final double termB = Math.sin(deltaLongitude / 2);
        return 2 * Math.asin(Math.sqrt(termA * termA + Math.cos(a.getLatitude()) * Math.cos(b.getLatitude()) * termB * termB));
    }
}
