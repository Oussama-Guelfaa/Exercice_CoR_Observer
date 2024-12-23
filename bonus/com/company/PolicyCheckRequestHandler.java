package com.company;

/**
 * Handler to check if the user has access rights to the requested path.
 * Responds with 403 if the user is unauthorized.
 */
public class PolicyCheckRequestHandler extends RequestHandler {
    @Override
    public void handleRequest(WebRequest request) {
        if ("/dashboard".equals(request.getPath()) && !request.getLoggedUser().isAdmin()) {
            System.out.println("Status 403 : user is not authorized to access this content");
        } else if (successor != null) {
            successor.handleRequest(request); // Pass the request to the next handler
        }
    }
}
