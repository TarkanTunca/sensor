package de.hasaalen.iot.coordinates;

public class Util {

    /**
     * This is an averaged earth radius (equatorial radius and 'pole to center').
     * <p>
     * We 'pretend that the earth is a sphere' in all computations.
     */
    public static final int RADIUS = 6371; // in km

    public static double angularDistance(GeoCoordinate a, GeoCoordinate b) {
        final double aLongitudeRads = Math.toRadians(a.getLongitude());
        final double aLatitudeRads = Math.toRadians(a.getLatitude());
        final double bLongitudeRads = Math.toRadians(b.getLongitude());
        final double bLatitudeRads = Math.toRadians(b.getLatitude());

        final double deltaLongitude = Math.abs(aLongitudeRads - bLongitudeRads);
        final double deltaLatitude = Math.abs(aLatitudeRads - bLatitudeRads);

        // Haversine formula for the creat circle distance; unit is radians for input and output
        final double havSineLatitude = Math.sin(deltaLatitude / 2);
        final double havSineLongitude = Math.sin(deltaLongitude / 2);
        return 2 * Math.asin(Math.sqrt(havSineLatitude * havSineLatitude +
                                               Math.cos(aLatitudeRads) * Math.cos(bLatitudeRads) * havSineLongitude *
                                                       havSineLongitude));
    }
}
