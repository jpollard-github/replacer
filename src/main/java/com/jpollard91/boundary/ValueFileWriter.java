package com.jpollard91.boundary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValueFileWriter {

    public void iterateLines(Map<String, String> keysValues, String originalFile, String newFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(originalFile));

        List<String> newLines = iterateLines(keysValues, lines);

        Files.write(Paths.get(newFile), newLines);
    }

    List<String> iterateLines(Map<String, String> keysValues, List<String> lines) {
        List<String> newLines = new ArrayList<>();

        for (String line : lines) {
            String newLine = line;

            for (Map.Entry<String, String> entry : keysValues.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                String keyToSearch = "{{" + key + "}}";

                if (line.contains(keyToSearch)) {
                    newLine = newLine.replace(keyToSearch, value);
                }
            }

            newLines.add(newLine);
        }
        return newLines;
    }
}
