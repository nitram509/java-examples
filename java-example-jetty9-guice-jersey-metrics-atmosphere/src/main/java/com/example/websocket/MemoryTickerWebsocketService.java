package com.example.websocket;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@ManagedService(path = "/websocket-example")
public class MemoryTickerWebsocketService {
    private static final Logger log = LoggerFactory.getLogger(MemoryTickerWebsocketService.class);

    @Ready
    public final void onReady(final AtmosphereResource r){
        log.info("Browser {} connected.", r.uuid());
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