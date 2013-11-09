package com.rkaneko.embedded;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

import java.net.URL;
import java.security.ProtectionDomain;

public class JettyLauncher {

    public static void main(String[] args) throws Exception {
        ProtectionDomain domain = JettyLauncher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webapp = new WebAppContext();
        webapp.setDescriptor("WEB-INF/web.xml");
        webapp.setConfigurations(new Configuration[]{
            new AnnotationConfiguration(),
            new WebXmlConfiguration(),
            new WebInfConfiguration(),
            new MetaInfConfiguration()
        });
        webapp.setContextPath("/");
        webapp.setWar(location.toExternalForm());

        Server server = new Server(8090);
        server.setHandler(webapp);
        server.start();
        server.join();
    }
}
