package com.example.websocket;

import java.util.Date;

public class MemoryTickerMessage {
    private String serverName;
    private long usedMemory;
    private long timestamp;

    public MemoryTickerMessage() {
        this("", 0L);
    }

    public MemoryTickerMessage(String serverName, long usedMemory) {
        this.usedMemory = usedMemory;
        this.serverName = serverName;
        this.timestamp = new Date().getTime();
    }

    public String getServerName() {
        return serverName;
    }

    public long getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(long usedMemory) {
        this.usedMemory = usedMemory;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}