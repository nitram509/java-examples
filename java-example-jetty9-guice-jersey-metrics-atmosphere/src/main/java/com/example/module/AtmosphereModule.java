package com.example.module;

import com.example.service.MemoryTickerService;
import com.example.websocket.MemoryTicker;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import org.atmosphere.guice.AtmosphereGuiceServlet;
import org.atmosphere.guice.GuiceManagedAtmosphereServlet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AtmosphereModule extends ServletModule {


    @Override
    protected void configureServlets() {
        bind(MemoryTickerService.class).in(Singleton.class);
        bind(MemoryTicker.class).in(Singleton.class);

        prevents_ConfigurationException__failed_to_add_Jersey_init_parameters_to_Atmosphere_servlet();

        Map<String, String> params = new HashMap<>();
//        params.put("com.sun.jersey.config.property.packages", "com.example");
//        params.put("org.atmosphere.websocket.messageContentType", "application/json");
        params.put("org.atmosphere.useWebSocket", "true");
        params.put("org.atmosphere.useNative", "true");
        serve("/websocket-example/*").with(GuiceManagedAtmosphereServlet.class, params);
    }

    private void prevents_ConfigurationException__failed_to_add_Jersey_init_parameters_to_Atmosphere_servlet() {
        bind(new TypeLiteral<Map<String, String>>() {
        })
                .annotatedWith(Names.named(AtmosphereGuiceServlet.JERSEY_PROPERTIES))
                .toInstance(Collections.<String, String>emptyMap());
    }

}
