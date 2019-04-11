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
        assertThat(a.directory, is(emptyString()));
    }

    @Test
    void testConstructor() {
        CommandlineArguments a = new CommandlineArguments("foo", 2, 20, Sensor.HUMIDITY);
        assertThat(a.directory, is(equalTo("foo")));
        assertThat(a.type, is(equalTo(Sensor.HUMIDITY)));
        assertThat(a.numFiles, is(equalTo(2)));
        assertThat(a.numLines, is(equalTo(20)));
    }

    @Test
    void testTooFewArgs() {
        String[] oneArg = {"dir"};
        String[] twoArg =  {"dir", "23"};
        /* If a null array is passed, or if the array contains less than two entries,
        a null value should be returned to indicate an error.
         */
        CommandlineArguments defaultArgs = CommandlineArguments.fromStringArray(null);
        assertThat(defaultArgs, is(nullValue()));
        CommandlineArguments fromOneArg = CommandlineArguments.fromStringArray(oneArg);
        assertThat(fromOneArg, is(nullValue()));
        CommandlineArguments fromTwoArg = CommandlineArguments.fromStringArray(twoArg);
        assertThat(fromTwoArg, is(nullValue()));
    }

    @Test
    void testFourArgs() {
        String[] threeArgs = {"dir", "33", "714", "humidity"};
        CommandlineArguments fromFourArgs = CommandlineArguments.fromStringArray(threeArgs);
        assertThat(fromFourArgs.directory, is(equalTo("dir")));
        assertThat(fromFourArgs.numFiles, equalTo(33));
        assertThat(fromFourArgs.numLines, equalTo(714));
        assertThat(fromFourArgs.type, is(equalTo(Sensor.HUMIDITY)));
    }

    @Test
    void testThreeArgs() {
        String[] twoArgs = {"dir", "34", "134"};
        CommandlineArguments fromThreeArgs = CommandlineArguments.fromStringArray(twoArgs);
        assertThat(fromThreeArgs.directory, is(equalTo("dir")));
        assertThat(fromThreeArgs.numFiles, equalTo(34));
        assertThat(fromThreeArgs.numLines, equalTo(134));
        assertThat(fromThreeArgs.type, is(nullValue()));
    }
}