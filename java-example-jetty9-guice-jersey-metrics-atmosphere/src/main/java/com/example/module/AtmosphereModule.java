package com.example.module;

import com.example.service.MemoryTickerService;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import org.atmosphere.guice.AtmosphereGuiceServlet;

public class AtmosphereModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(MemoryTickerService.class).in(Singleton.class);
        bind(AtmosphereGuiceServlet.class).in(Singleton.class);

        serve("/websocket-example/*").with(AtmosphereGuiceServlet.class);
    }

}
