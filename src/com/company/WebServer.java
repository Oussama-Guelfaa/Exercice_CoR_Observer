package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * WebServer processes requests and notifies observers about them.
 */
public class WebServer implements WebRequestObservable {
    private final List<WebRequestObserver> observers = new ArrayList<>();
    private RequestHandler handler;

    public void setHandler(RequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public void attach(WebRequestObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(WebRequestObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(WebRequest webRequest) {
        for (WebRequestObserver observer : observers) {
            observer.update(webRequest); // Notify all observers
        }
    }

    public void getRequest(WebRequest request) {
        notifyObservers(request); // Notify observers about the request
        if (handler != null) {
            handler.handleRequest(request); // Pass the request through the Chain of Responsibility
        }
    }
}
