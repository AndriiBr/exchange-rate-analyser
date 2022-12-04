package com.exchange.rate.analyser.util;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class TestJsonReader {

    public static String readFileAsString(String fileName) throws Exception {
        File file = readFileByName(fileName);
        return new String(Files.readAllBytes(Paths.get(file.toURI())));
    }

    private static File readFileByName(String fileName) {
        URL url = findFileUrl(fileName);
        return new File(url.getFile());
    }

    private static URL findFileUrl(String fileName) {
        return Objects.requireNonNull(TestJsonReader.class.getClassLoader().getResource(fileName));
    }
}
