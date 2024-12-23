package com.company;

/**
 * Handler to render the content for valid requests.
 * Responds with 200 and the appropriate content.
 */
public class RenderContentHandler extends RequestHandler {
    @Override
    public void handleRequest(WebRequest request) {
        if ("/dashboard".equals(request.getPath())) {
            System.out.println("Status 200 : Dashboard content here");
        } else if ("/home".equals(request.getPath())) {
            System.out.println("Status 200 : Home content here");
        }
    }
}
