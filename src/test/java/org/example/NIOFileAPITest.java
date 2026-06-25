package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class NIOFileAPITest {

    private static final String HOME = System.getProperty("user.home");
    private static final String FOLDER_NAME = "TempPlayGround";

    @Test
    public void testFileOperations() throws IOException {


        // 1. Check Home Directory Exists
        Path homePath = Paths.get(HOME);

        Assertions.assertTrue(Files.exists(homePath));
        System.out.println("Home directory exists.");


        // 2. Delete Directory if Exists
        Path playPath = Paths.get(HOME, FOLDER_NAME);

        if (Files.exists(playPath)) {

            // Delete files first
            Files.list(playPath)
                    .forEach(file -> {
                        try {
                            Files.delete(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            Files.delete(playPath);
        }

        Assertions.assertFalse(Files.exists(playPath));
        System.out.println("Directory deleted successfully.");

        // 3. Create Directory
        Files.createDirectory(playPath);

        Assertions.assertTrue(Files.exists(playPath));
        System.out.println("Directory created.");

        // 4. Create Empty Files
        IntStream.rangeClosed(1, 5).forEach(i -> {

            Path file = Paths.get(playPath.toString(), "temp" + i + ".txt");

            try {
                Files.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Assertions.assertTrue(Files.exists(file));
        });

        System.out.println("Files created.");

        // 5. List Only Files
        System.out.println("\nRegular Files:");

        Files.list(playPath)
                .filter(Files::isRegularFile)
                .forEach(System.out::println);

        // 6. List Only Directories
        System.out.println("\nDirectories:");

        Files.list(playPath)
                .filter(Files::isDirectory)
                .forEach(System.out::println);

        // 7. List Only TXT Files
        System.out.println("\nTXT Files:");

        Files.list(playPath)
                .filter(path -> path.toString().endsWith(".txt"))
                .forEach(System.out::println);
    }
}