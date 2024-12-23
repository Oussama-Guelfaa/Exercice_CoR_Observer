package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileNotFoundException;

public class FileLogger {
    private final String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
        try {
            Files.deleteIfExists(Paths.get(this.filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void logRequest(WebRequest request) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(this.filePath, true))) {
            out.println("Request made to " + request.getPath() + " by " +
                    (request.getLoggedUser().isAdmin() ? "admin user" : "non admin user"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}