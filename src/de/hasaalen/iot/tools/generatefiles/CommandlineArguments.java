package de.hasaalen.iot.tools.generatefiles;

class CommandlineArguments {
    final int numFiles;
    final int numLines;
    final Sensor type;

    CommandlineArguments() {
        numFiles = 1;
        numLines = 100;

        // null here will cause a random sensor type to be selected
        type = null;
    }

    CommandlineArguments(int numFiles, int numLines, Sensor type) {
        this.type = type;
        this.numLines = numLines;
        this.numFiles = numFiles;
    }

    public static CommandlineArguments fromStringArray(String[] args) {
        if (args == null || args.length < 2) {
            return null;
        }
        //TODO: Exceptions
        final int numFiles = Integer.parseInt(args[0]);
        final int numLines = Integer.parseInt(args[1]);

        Sensor type = null;
        if (args.length >= 3) {
            switch (args[2]) {
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
        return new CommandlineArguments(numFiles, numLines, type);
    }
}