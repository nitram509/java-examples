package com.example.websocket;

import org.atmosphere.config.managed.Decoder;
import org.atmosphere.config.managed.Encoder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class MemoryTickerMessageEncoderDecoder implements Encoder<MemoryTickerMessage, String>, Decoder<String, MemoryTickerMessage> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public MemoryTickerMessage decode(final String s){
        try{
            return mapper.readValue(s, MemoryTickerMessage.class);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String encode(final MemoryTickerMessage message){
        try{
            return mapper.writeValueAsString(message);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}