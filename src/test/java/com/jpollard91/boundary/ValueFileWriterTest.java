package com.jpollard91.boundary;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ValueFileWriterTest {

    private ValueFileWriter writer;

    @Before
    public void setUp() {
        writer = new ValueFileWriter();
    }

    @Test
    public void iterateLinesProducesExpected() {
        Map<String, String> keysValues = new HashMap<>();
        keysValues.put("MYSQL_HOST", "127.0.0.1");
        keysValues.put("JDBC_RESOURCE_NAME", "jdbc/mysql");
        keysValues.put("MYSQL_PASSWORD", "abc123");
        keysValues.put("AN_EXTRA_VALUE", "extraval");
        keysValues.put("MYSQL_PORT", "3306");
        keysValues.put("MYSQL_DATABASE", "testdb");

        List<String> originalLines = Arrays.asList(
                "echo 'asadmin generate-something --properties ServerName={{MYSQL_HOST}}:DatabaseName={{MYSQL_DATABASE}}:Password={{MYSQL_PASSWORD}}",
                "echo 'asadmin do-something-else mysql --pool {{JDBC_RESOURCE_NAME}} --extra-val {{MYSQL_PORT}}"
        );

        List<String> newLines = writer.iterateLines(keysValues, originalLines);

        assertThat(newLines.size(), is(2));
        assertThat(newLines.get(0), is("echo 'asadmin generate-something --properties ServerName=127.0.0.1:DatabaseName=testdb:Password=abc123"));
        assertThat(newLines.get(1), is("echo 'asadmin do-something-else mysql --pool jdbc/mysql --extra-val 3306"));
    }
}