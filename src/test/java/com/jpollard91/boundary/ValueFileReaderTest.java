package com.jpollard91.boundary;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ValueFileReaderTest {

    private ValueFileReader reader;

    @Before
    public void setUp() {
        reader = new ValueFileReader();
    }

    @Test
    public void parseLinesWithValidListReturnsExpected() {
        List<String> lines = Arrays.asList(
                "MYSQL_HOST=127.0.0.1",
                "MYSQL_PASSWORD=password123",
                "MYSQL_USER=user1",
                "MYSQL_DATABASE=testdb",
                "MYSQL_PORT=3306",
                "JDBC_RESOURCE_NAME=jdbc/mysql"
        );

        Map<String, String> keysValues = reader.parseLines(lines);

        assertTrue(keysValues.containsKey("MYSQL_HOST"));
        assertThat(keysValues.get("JDBC_RESOURCE_NAME"), is("jdbc/mysql"));
        assertThat(keysValues.size(), is(6));
    }
}