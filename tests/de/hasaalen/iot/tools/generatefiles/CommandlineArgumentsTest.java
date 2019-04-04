package de.hasaalen.iot.tools.generatefiles;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class CommandlineArgumentsTest {

    @Test
    void testDefaultConstructor() {
        CommandlineArguments a = new CommandlineArguments();
        assertThat(a.type, is(nullValue()));
        assertThat(a.numFiles, is(equalTo(1)));
        assertThat(a.numLines, is(equalTo(100)));
    }

    @Test
    void testConstructor() {
        CommandlineArguments a = new CommandlineArguments(2, 20, Sensor.HUMIDITY);
        assertThat(a.type, is(equalTo(Sensor.HUMIDITY)));
        assertThat(a.numFiles, is(equalTo(2)));
        assertThat(a.numLines, is(equalTo(20)));
    }

    @Test
    void fromStringArray() {
        String[] oneArg = {"23"};
        String[] twoArgs = {"34", "134"};
        String[] threeArgs = {"33", "714", "humidity"};

        /* If a null array is passed, or if the array contains less than two entries,
        a null value should be returned to indicate an error.
         */
        CommandlineArguments defaultArgs = CommandlineArguments.fromStringArray(null);
        assertThat(defaultArgs, is(nullValue()));
        CommandlineArguments fromOneArg = CommandlineArguments.fromStringArray(oneArg);
        assertThat(fromOneArg, is(nullValue()));

        /* We can see here that this test is suboptimal: It is just too long and therefore
        unnecessarily difficult to read.

        For this reason, we usually split tests up into multiple test-methods.
        TODO: Split this test into three test methods
         */
        CommandlineArguments fromTwoArgs = CommandlineArguments.fromStringArray(twoArgs);
        assertThat(fromTwoArgs.numFiles, equalTo(34));
        assertThat(fromTwoArgs.numLines, equalTo(134));
        assertThat(fromTwoArgs.type, is(nullValue()));

        CommandlineArguments fromThreeArgs = CommandlineArguments.fromStringArray(threeArgs);
        assertThat(fromThreeArgs.numFiles, equalTo(33));
        assertThat(fromThreeArgs.numLines, equalTo(714));
        assertThat(fromThreeArgs.type, is(equalTo(Sensor.HUMIDITY)));
    }
}