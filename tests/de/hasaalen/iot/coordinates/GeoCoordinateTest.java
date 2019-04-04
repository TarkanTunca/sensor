package de.hasaalen.iot.coordinates;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
This is a test class. You can create a test for a given class (such as 'GeoCoordinate' in this case) by navigating to
that class and Pressing Ctrl+Shift+T.
 */


class GeoCoordinateTest {


    @Test
    void testEquals() {
        GeoCoordinate a = new GeoCoordinate(0.341, 0.87657);
        GeoCoordinate different = new GeoCoordinate(4.5231, 543);
        GeoCoordinate equalValues = new GeoCoordinate(0.341, 0.87657);
        assertThat(a, equalTo(a));
        assertThat(a, not(equalTo(different)));
        assertThat(a, equalTo(equalValues));
    }

    @Test
    void testGetLongitude() {
        double expected = 0.231;
        GeoCoordinate a = new GeoCoordinate(-0.4, expected);
        assertThat(a.getLongitude(), equalTo(expected));
    }

    @Test
    void testGetLatitude() {
        double expected = 0.231;
        GeoCoordinate a = new GeoCoordinate(expected, -0.4);
        assertThat(a.getLatitude(), equalTo(expected));
    }

    @Test
    void testToString() {
        final String expected = "(48,786222;9,786118)";
        GeoCoordinate coo = new GeoCoordinate(48.78622187567267, 9.786118320659753);
        assertThat(coo.toString(), is(equalTo(expected)));
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