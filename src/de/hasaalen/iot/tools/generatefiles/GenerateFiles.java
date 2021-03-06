package de.hasaalen.iot.tools.generatefiles;

import de.hasaalen.iot.coordinates.GeoCoordinate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateFiles {


    private static final GeoCoordinate gmuend = new GeoCoordinate(48.79963, 9.78756);

    /**
     * Generate a random line of "data" from the sensor specified as argument
     *
     * @param sensor
     * @return a CSV line (including \n) of data for the given sensor
     */
    public static String generateLine(Sensor sensor) {
        ThreadLocalRandom rng = ThreadLocalRandom.current();
        StringBuilder builder = new StringBuilder();
        switch (sensor) {
            case HUMIDITY:
                builder.append(String.format("%2s%%,", rng.nextInt(100)));
                break;
            case POSITION:
                GeoCoordinate random = gmuend.randomizedPointWithinDistance(2);
                builder.append(random.getLatitude());
                builder.append(",");
                builder.append(random.getLongitude());
                builder.append(",");
                break;
            case TEMPERATURE:
                final int temp = rng.nextInt(-40, 55);
                builder.append(temp);
                builder.append("C,");

        }
        builder.append(LocalDateTime.now().minusSeconds(rng.nextInt(3600)).toString());
        builder.append("\n");
        return builder.toString();
    }


    public static void main(String[] args) {
        CommandlineArguments arguments = CommandlineArguments.fromStringArray(args);
        if (arguments == null) {
            System.err.println("Error. Need arguments: directory, numFiles [, numLines [,  sensorType]]");
            return;
        }

        for (int i = 0; i < arguments.numFiles; i++) {

            final Sensor sensor;
            if (arguments.type == null) {
                sensor = Sensor.values()[ThreadLocalRandom.current().nextInt(3)];
            } else {
                sensor = arguments.type;
            }

            Path p = constructFilename(arguments, sensor);
            if (p == null) {
                // Error. Could not generate a path
                return;
            }

            // This is a try-catch block/try-with-resources statement. You can safely ignore this for now, until we
            // cover Exceptions this is more detail
            try (BufferedWriter writer = Files.newBufferedWriter(p)) {
                for (int j = 0; j < arguments.numLines; j++) {
                    writer.write(generateLine(sensor));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
    }

    private static Path constructFilename(CommandlineArguments arguments, Sensor sensor) {
        try {
            final String baseName = sensor + "_" + LocalDate.now().toString();
            Path p = Paths.get(arguments.directory, baseName + ".csv");
            for (int k = 1; Files.exists(p); k++) {
                p = Paths.get(arguments.directory, baseName + "_" + k + ".csv");
            }
            return p;
        } catch (InvalidPathException e) {
            System.err.println("Invalid path: " + e.getMessage());
            return null;
        }
    }
}
