package com.jpollard91.boundary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValueFileReader {

    public Map<String, String> readKeyValueFile(String keyValueFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(keyValueFile));

        return parseLines(lines);
    }

    Map<String, String> parseLines(List<String> lines) {
        Map<String, String> keysValues = new HashMap<>();

        for (String l : lines) {
            String[] keyValue = l.split("=");
            keysValues.put(keyValue[0], keyValue[1]);
        }

        return keysValues;
    }


}
