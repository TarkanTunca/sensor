package de.hasaalen.iot.tools.generatefiles;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GenerateFilesTest {

    @Test
    void generateHumidityLine() {
        String line = GenerateFiles.generateLine(Sensor.HUMIDITY);
        assertThat(line, is(not(nullValue())));
        assertThat(line, is(not(emptyString())));
        /*
        Bemerkung:
        Folgendes geht natürlich auch und ist genauso richtig:

            assertThat(line, is(not(equalTo(""))));
         */
    }

    @Test
    void generateTemperatureLine() {
        String line = GenerateFiles.generateLine(Sensor.TEMPERATURE);
        assertThat(line, is(not(nullValue())));
        assertThat(line, is(not(emptyString())));
    }

    @Test
    void generatePositionLine() {
        String line = GenerateFiles.generateLine(Sensor.POSITION);
        assertThat(line, is(not(nullValue())));
        assertThat(line, is(not(emptyString())));
    }

}