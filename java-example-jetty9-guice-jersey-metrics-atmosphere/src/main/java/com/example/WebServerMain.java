package com.example;

import com.example.module.GuiceConfig;
import com.google.inject.servlet.GuiceFilter;
import org.atmosphere.cpr.AtmosphereServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;

import static java.util.EnumSet.allOf;

public class WebServerMain {
    private static final Logger logger = LoggerFactory.getLogger(WebServerMain.class);

    public static void main(String[] args) throws Exception {

        Server server = new Server(getPort());

        ServletContextHandler handler = new ServletContextHandler(server, "/");

        handler.addEventListener(new GuiceConfig());
        handler.addFilter(GuiceFilter.class, "/*", allOf(DispatcherType.class));

        handler.addServlet(AtmosphereServlet.class, "/websocket-example/*");

        ServletHolder servletHolder = handler.addServlet(DefaultServlet.class, "/");
        servletHolder.setInitParameter("resourceBase","src/main/webapp");
        servletHolder.setInitParameter("dirAllowed","false");
        handler.setWelcomeFiles(new String[] {"index.html"});

        server.start();
        logger.info("Server started");
        server.join();
    }

    private static int getPort() {
        String port = System.getenv("PORT") == null ? "8081" : System.getenv("PORT");
        return Integer.parseInt(port);
    }
}

