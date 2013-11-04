package com.example.websocket;

import com.example.service.MemoryTickerService;
import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;

@ManagedService(path = "/websocket-example/memoryTicker")
public class MemoryTicker {

    private static final Logger log = LoggerFactory.getLogger(MemoryTicker.class);

    @Inject
    MemoryTickerService memoryTickerService;

    @Ready
    public final void onReady(final AtmosphereResource resource){
        log.info("Browser {} connected.", resource.uuid());
    }

    @Disconnect
    public final void onDisconnect(final AtmosphereResourceEvent event){
        if(event.isCancelled())
            log.info("Browser {} unexpectedly disconnected", event.getResource().uuid());
        else if(event.isClosedByClient())
            log.info("Browser {} closed the connection", event.getResource().uuid());
    }

    @Message(encoders = {MemoryTickerMessageEncoderDecoder.class}, decoders = {MemoryTickerMessageEncoderDecoder.class})
    public final MemoryTickerMessage onMessage(final MemoryTickerMessage message) throws IOException{
        log.info("{} just send {}", message.getUsedMemory(), message.getServerName());
        return message;
    }

}