package com.company;

import com.company.FileLogger;
import com.company.WebRequest; 
import com.company.WebServerInterface;

public class WebServerProxy implements WebServerInterface {
    private final WebServerInterface realWebServer;
    private final FileLogger fileLogger;

    public WebServerProxy(WebServer realWebServer2, FileLogger fileLogger) {
        this.realWebServer = realWebServer2;
        this.fileLogger = fileLogger;
    }

    public WebServerProxy(WebServer realWebServer2, FileLogger fileLogger2) {
        //TODO Auto-generated constructor stub
    }

    @Override
    public void getRequest(WebRequest request) {
        // 1) Logguer la requête
        fileLogger.logRequest(request);
        // 2) Déléguer au serveur réel
        realWebServer.getRequest(request);
    }
}

