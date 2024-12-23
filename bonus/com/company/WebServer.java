package com.company;

/**
 * WebServer processes requests and notifies observers about them.
 */
public class WebServer implements WebServerInterface {
    private RequestHandler handler;

    public void setHandler(RequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public void getRequest(WebRequest request) {
        if (handler != null) {
            handler.handleRequest(request);
        }
    }
}