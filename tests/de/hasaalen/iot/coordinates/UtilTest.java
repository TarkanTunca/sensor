package de.hasaalen.iot.coordinates;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class UtilTest {

    @Test
    void angularDistance() {
        GeoCoordinate a = new GeoCoordinate(48.79963, 9.78756);
        GeoCoordinate b = new GeoCoordinate(48.79881, 9.78554);
        double angularDistance = Util.angularDistance(a, b);
        assertThat(angularDistance, is(closeTo(2.72786954E-5, 0.00001E-5)));
    }
}