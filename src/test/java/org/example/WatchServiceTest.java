package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;

public class WatchServiceTest {

    @Test
    void watchDirectory() throws IOException {

        Path dir = Path.of("PlayGround");

        if (Files.notExists(dir)) {
            Files.createDirectory(dir);
        }

        // Display existing files
        Files.list(dir)
                .filter(Files::isRegularFile)
                .forEach(System.out::println);

        // Start watching
        new WatchService(dir).processEvents();
    }
}