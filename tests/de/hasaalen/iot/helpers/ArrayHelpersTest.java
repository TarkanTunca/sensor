package de.hasaalen.iot.helpers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ArrayHelpersTest {

    @Test
    @Disabled
    void grow() {
        String[] a = {"a", "b", "c"};

        assertThat(ArrayHelpers.grow(a, 3), is(sameInstance(a)));
        assertThat(ArrayHelpers.grow(null, 2), is(nullValue()));
        String[] larger = ArrayHelpers.grow(a, 5);
        assertThat(larger, is(not(nullValue())));
        assertThat(larger.length, is(equalTo(5)));
        // Arrays.equals compares two array within a specific range
        assertTrue(Arrays.equals(a, 0, a.length, larger, 0, a.length));
    }
}