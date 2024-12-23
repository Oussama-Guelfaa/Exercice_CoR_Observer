package com.company;

import java.util.List;

/**
 * Handler to check if the requested content exists.
 * Responds with 404 if the path is invalid.
 */
public class ExistingContentCheckRequestHandler extends RequestHandler {
    private static final List<String> validPaths = List.of("/dashboard", "/home");

    @Override
    public void handleRequest(WebRequest request) {
        if (!validPaths.contains(request.getPath())) {
            System.out.println("Status 404 : Page missing");
        } else if (successor != null) {
            successor.handleRequest(request); // Pass the request to the next handler
        }
    }
}

