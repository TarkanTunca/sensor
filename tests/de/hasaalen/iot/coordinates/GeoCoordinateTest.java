package de.hasaalen.iot.coordinates;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
This is a test clas. You can create a test for a given class (such as 'GeoCoordinate' in this case) by navigating to
that class and Pressing Ctrl+Shift+T.
 */


class GeoCoordinateTest {


    @Test
    void testGetLongitude() {
    }

    @Test
    void testGetLatitude() {
    }

    @Test
    void testToString() {

    }

    @Test
    void testRandomizedPointWithinDistance() {
        GeoCoordinate a = new GeoCoordinate(51.4989, -0.17901);
        GeoCoordinate random = a.randomizedPointWithinDistance(2);
        // These are assertions. They perform the actual tests.
        assertThat(a, is(not(sameInstance(random))));
        assertThat(Util.angularDistance(a, random), is(lessThan(2.0)));
    }
}