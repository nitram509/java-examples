package com.example.module;

import com.example.resource.FooResource;
import com.example.service.MemoryTickerService;
import com.google.inject.AbstractModule;

import javax.inject.Singleton;

public class ExampleModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FooResource.class).in(Singleton.class);
        bind(MemoryTickerService.class).in(Singleton.class);
    }
}
