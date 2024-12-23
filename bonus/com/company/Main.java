package com.company;

import com.company.WebServerInterface;
import com.company.RequestHandler;
import com.company.ExistingContentCheckRequestHandler;
import com.company.PolicyCheckRequestHandler;
import com.company.RenderContentHandler;
import com.company.User;
import com.company.WebRequest;
import com.company.WebServerProxy;
import com.company.WebServer;
/**
 *  We have a small web server that receive requests with the method getRequest();
 *  The first parameter is the path of the request, and the second is an object that represents the
 *  user that is currently logged in.
 *
 *  The only valid paths for our web app are "/dashboard" and "/home".
 *  "/dashboard" is only accessible to admin users.
 *  "/home" is accessible to any user.
 *  The web server writes directly its response to the console using System.out.println.
 *
 *  When a non authorized user wants to access the content, the webserver must respond with the message :
 *  "Status 403 : user is not authorized to access this content"
 *
 *  When we try to access content that do not exist, the server must respons with the message :
 *  "Status 404 : Page missing".
 *
 *  Otherwise the server respond with the asked content which is :
 *  "/dashboard" => "Status 200 : Dashboard content here"
 *  "/home" => "Status 200 : Home content here"
 *
 *  We also want to have a file system log of all request that were made.
 *
 *
 *
 *  1 - Use the Chain of Responsibility Pattern
 *  1.a - Make an abstract class RequestHandler, which will serve as the base class
 *        for handlers according to the Chain of Responsibility pattern.
 *        It must have a handleRequest() method, and a successor member which is also a RequestHandler
 *  1.b - Implement a ExistingContentCheckRequestHandler which handles a WebRequest, and checks
 *        if the requested content (the path) is valid. If not, it produces the response for the webserver (404)
 *        otherwise it passes the request to the next handler
 *  1.c - Implement a PolicyCheckRequestHandler() that checks that the User has the right to access
 *        the requested content. If not it produces the response for the webserver (403), otherwise
 *        it passes the request to the next handler()
 *  1.d - Implement a RenderContentHandler() which renders the content for the WebRequest (200)
 *  1.e - Modify WebServer, it should reference the first handler of the chain of handlers,
 *        and call the chain in its method getRequest().
 *        You can create the instances of handlers and chain them in your Main, then give the first handler
 *        of the chain to the WebServer constructor
 *
 *
 *  2 - Use the Observable pattern
 *  In order to log all request made to the server in a log file, we are going to use the
 *  Observer Pattern
 *  2.a - Modify the WebServer so that it implements the WebRequestObservable interface.
 *        We should be able to register observers on the web server, and any web request made
 *        to the web server should notify all the registered observers with the WebRequest object.
 *  2.b - Modify the FileLogger to be an implementation of WebRequestObserver, in order for it
 *        to log each time it is notified of a request
 *  2.c - Tie all of this together in your main
 *
 *
 *  3 - Bonus. If you want, you can undo the Observer pattern, and try to solve the same
 *  problem (logging requests in a file) by using the Proxy pattern
 */


 public class Main {
    public static void main(String[] args) {
        // Serveur réel
        WebServer realWebServer = new WebServer();


        // Chaîne de responsabilité
        RequestHandler existingContentHandler = new ExistingContentCheckRequestHandler();
        RequestHandler policyHandler = new PolicyCheckRequestHandler();
        RequestHandler renderHandler = new RenderContentHandler();
        existingContentHandler.setSuccessor(policyHandler);
        policyHandler.setSuccessor(renderHandler);

        // On configure le WebServer "réel"
        ((WebServer) realWebServer).setHandler(existingContentHandler);

        // Création du FileLogger et du Proxy
        FileLogger fileLogger = new FileLogger("logs.txt");
        WebServerInterface proxy = new WebServerProxy(realWebServer, fileLogger);

        // Requêtes de test
        User regularUser = new User(false);
        User adminUser = new User(true);

        proxy.getRequest(new WebRequest("/dashboard", regularUser));
        proxy.getRequest(new WebRequest("/dashboard/nonExistingPage", adminUser));
        proxy.getRequest(new WebRequest("/dashboard", adminUser));
        proxy.getRequest(new WebRequest("/home", regularUser));
    }
}