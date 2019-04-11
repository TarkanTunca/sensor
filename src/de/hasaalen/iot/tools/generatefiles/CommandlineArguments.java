package de.hasaalen.iot.tools.generatefiles;

class CommandlineArguments {
    final int numFiles;
    final int numLines;
    final Sensor type;
    final String directory;

    CommandlineArguments() {
        numFiles = 1;
        numLines = 100;
        directory = ""; // this will cause the current directory to be used.

        // null here will cause a random sensor type to be selected
        type = null;
    }

    CommandlineArguments(String directory, int numFiles, int numLines, Sensor type) {
        this.directory = directory;
        this.type = type;
        this.numLines = numLines;
        this.numFiles = numFiles;
    }

    public static CommandlineArguments fromStringArray(String[] args) {
        if (args == null || args.length < 3) {
            return null;
        }
        final String directory = args[0];

        // We may declare a final variable without initializing it, if we GUARANTEE initialization before first use.
        final int numFiles;
        final int numLines;
        try {
            numFiles = Integer.parseInt(args[1]);
            numLines = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.err.println("Error: '" + args[1] + "' or '" + args[2] + "' is not a valid number");
            return null;
        }

        Sensor type = null;
        if (args.length >= 4) {
            switch (args[3]) {
                case "humidity":
                    type = Sensor.HUMIDITY;
                    break;
                case "temperature":
                    type = Sensor.TEMPERATURE;
                    break;
                case "position":
                    type = Sensor.POSITION;
                    break;
                case "random":
                default:
                    type = null;
                    break;
            }
        }
        return new CommandlineArguments(directory, numFiles, numLines, type);
    }
}