package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FileLogger observes WebServer requests and logs them to a file.
 */
public class FileLogger implements WebRequestObserver {
    private final String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
        try {
            Files.deleteIfExists(Paths.get(this.filePath)); // Ensure the log file is clean at start
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void update(WebRequest request) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(this.filePath, true))) {
            out.println("Request made to " + request.getPath() + " by " +
                    (request.getLoggedUser().isAdmin() ? "admin user" : "non admin user"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

