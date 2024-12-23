package com.company;

/**
 * Abstract base class for the Chain of Responsibility.
 */
public abstract class RequestHandler {
    protected RequestHandler successor;

    public void setSuccessor(RequestHandler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(WebRequest request);
}
