package org.example;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatchService {

    private final java.nio.file.WatchService watcher;
    private final Map<WatchKey, Path> watchKeys = new HashMap<>();

    public WatchService(Path startDir) throws IOException {
        watcher = FileSystems.getDefault().newWatchService();
        registerAll(startDir);
    }

    // Register one directory
    private void register(Path dir) throws IOException {

        WatchKey key = dir.register(
                (java.nio.file.WatchService) watcher,
                ENTRY_CREATE,
                ENTRY_DELETE,
                ENTRY_MODIFY
        );

        watchKeys.put(key, dir);
        System.out.println("Watching : " + dir);
    }

    // Register all directories and subdirectories
    private void registerAll(Path start) throws IOException {

        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir,
                                                     BasicFileAttributes attrs)
                    throws IOException {

                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    // Process events
    public void processEvents() {

        while (true) {

            WatchKey key;

            try {
                key = watcher.take();       // Wait for an event
            } catch (InterruptedException e) {
                return;
            }

            Path dir = watchKeys.get(key);

            if (dir == null)
                continue;

            for (WatchEvent<?> event : key.pollEvents()) {

                WatchEvent.Kind<?> kind = event.kind();

                Path fileName = (Path) event.context();

                Path child = dir.resolve(fileName);

                System.out.println(kind.name() + " : " + child);

                // If a new directory is created, register it also
                if (kind == ENTRY_CREATE) {

                    try {
                        if (Files.isDirectory(child)) {
                            registerAll(child);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            boolean valid = key.reset();

            if (!valid) {
                watchKeys.remove(key);

                if (watchKeys.isEmpty())
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Path dir = Path.of("PlayGround");

        // Create folder if it doesn't exist
        if (Files.notExists(dir)) {
            Files.createDirectory(dir);
        }

        // Display existing files
        System.out.println("Existing Files:");
        Files.walk(dir).forEach(System.out::println);

        System.out.println("\nWatching directory...");
        System.out.println("Create, Modify or Delete files inside PlayGround.\n");

        WatchService demo = new WatchService(dir);
        demo.processEvents();
    }
}