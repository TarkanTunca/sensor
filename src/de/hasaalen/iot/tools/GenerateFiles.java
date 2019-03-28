package de.hasaalen.iot.tools;

import de.hasaalen.iot.coordinates.GeoCoordinate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateFiles {


    private static final GeoCoordinate gmuend = new GeoCoordinate(48.79963, 9.78756);

    private enum Sensor {
        HUMIDITY, TEMPERATURE, POSITION
    }

    private static class Arguments {
        final int numFiles;
        final int numLines;
        final Sensor type;

        Arguments() {
            numFiles = 1;
            numLines = 100;
            type = null;
        }

        Arguments(int numFiles, int numLines, Sensor type) {
            this.type = type;
            this.numLines = numLines;
            this.numFiles = numFiles;
        }
    }

    private static String generateLine(Sensor sensor) {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        StringBuilder builder = new StringBuilder();
        switch (sensor) {
            case HUMIDITY:
                builder.append(String.format("%2s%%,", r.nextInt(100)));
                break;
            case POSITION:
                GeoCoordinate random = gmuend.randomizedPointWithinDistance(2);
                builder.append(random.getLatitude());
                builder.append(",");
                builder.append(random.getLongitude());
                builder.append(",");
                break;
            case TEMPERATURE:
                final int temp = r.nextInt(-40, 55);
                builder.append(temp);
                builder.append(",");

        }
        builder.append(LocalDateTime.now().minusSeconds(r.nextInt(3600)).toString());
        builder.append("\n");
        return builder.toString();
    }


    public static void main(String[] args) {
        Arguments arguments = new Arguments(1, 20, Sensor.POSITION);
        for (int i = 0; i < arguments.numFiles; i++) {
            final Sensor sensor;
            if (arguments.type == null) {
                sensor = Sensor.values()[ThreadLocalRandom.current().nextInt(3)];
            } else {
                sensor = arguments.type;
            }
            final String baseName = sensor + "_" + LocalDateTime.now().toString();
            final Path p = Paths.get(baseName + ".csv");
            try (BufferedWriter writer = Files.newBufferedWriter(p)) {
                for (int j = 0; j < arguments.numLines; j++) {
                    writer.write(generateLine(sensor));
                }

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
    }
}
