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
        //TODO
        return null;
    }
}